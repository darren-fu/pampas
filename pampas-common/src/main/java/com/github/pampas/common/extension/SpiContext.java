/*
 *
 *  *  Copyright 2009-2018.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package com.github.pampas.common.extension;

import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.config.ConfigLoader;
import com.github.pampas.common.config.Configurable;
import com.github.pampas.common.config.VersionConfig;
import com.github.pampas.common.exception.PampasException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * <pre>
 * 	扩展增加的方式：
 * 		支持 JDK ServiceProvider
 *
 * 		支持 spi 配置
 * </pre>
 * <p>
 * ->from motan
 *
 * @param <T> the type parameter
 */
@SuppressWarnings({"unused", "Duplicates", "unchecked", "WeakerAccess"})
public class SpiContext<T> {

    private static final Logger log = LoggerFactory.getLogger(SpiContext.class);

    private static InnerMethodHolder inner = new InnerMethodHolder();

    //SpiContext 缓存
    private static ConcurrentMap<Class<?>, SpiContext<?>> contextMap = new ConcurrentHashMap();

    //单例缓存
    private ConcurrentMap<String, T> singletonInstances = null;
    private static ConcurrentMap<Class, Object> singletonClzInstances = new ConcurrentHashMap();

    //SpiClass缓存
    private ConcurrentMap<String, SpiClass<T>> spiClassMap = null;

    //SPI interface
    private Class<T> type;

    private boolean singleton = false;

    //是否完成初始化：加载Spi实现类
    private volatile AtomicBoolean init = new AtomicBoolean(false);

    // spi requestPath prefix
    private static final String PREFIX = "META-INF/services/";

    private ClassLoader classLoader;

    private SpiContext(Class<T> type) {
        this(type, Thread.currentThread().getContextClassLoader());
    }

    private SpiContext(Class<T> type, ClassLoader classLoader) {
        this.type = type;
        this.classLoader = classLoader;
    }


    /**
     * Gets context.
     *
     * @param <T>  the type parameter
     * @param type the type
     * @return the context
     */
    @SuppressWarnings("unchecked")
    public static <T> SpiContext<T> getContext(Class<T> type) {
        inner.checkInterfaceType(type);

        SpiContext<T> loader = (SpiContext<T>) contextMap.get(type);

        if (loader == null) {
            loader = initContext(type);
        }
        return loader;
    }

    @SuppressWarnings("unchecked")
    private static synchronized <T> SpiContext<T> initContext(Class<T> type) {
        SpiContext<T> loader = (SpiContext<T>) contextMap.get(type);

        if (loader == null) {
            loader = new SpiContext(type);
            Spi spi = type.getAnnotation(Spi.class);
            if (spi.scope() == Scope.SINGLETON) {
                loader.singleton = true;
            }
            contextMap.putIfAbsent(type, loader);

            loader = (SpiContext<T>) contextMap.get(type);
            checkInit(loader);
        }
        return loader;
    }

    private static void checkInit(SpiContext context) {
        if (!context.init.get() && context.init.compareAndSet(false, true)) {
            context.spiClassMap = context.loadSpiClasses(PREFIX);
            context.singletonInstances = new ConcurrentHashMap();
        }
    }

    /**
     * Gets extension class.
     *
     * @param name the name
     * @return the extension class
     */
    public Class<T> getSpiClass(String name) {

        SpiClass spiClass = spiClassMap.get(name);

        return spiClass == null ? null : spiClass.getClz();
    }

    /**
     * Gets extension.
     *
     * @param name the name
     * @return the extension
     */
    public T getSpiInstance(String name) {
        if (name == null) {
            return null;
        }
        T t = null;
        try {
            if (this.singleton) {
                t = getSingletonInstance(name);
            } else {
                SpiClass<T> spiClass = spiClassMap.getOrDefault(name, SpiClass.EMPTY_SPI);
                Class<T> clz = spiClass.getClz();
                if (clz == null) {
                    return null;
                }

                t = clz.newInstance();
                if (t instanceof Configurable) {
                    // todo: init with Config
                }
            }
        } catch (Exception e) {
            inner.failThrows(type, "Error when getSpiInstance " + name, e);
        }
        return t;
    }


    private T getSingletonInstance(String name) throws InstantiationException, IllegalAccessException {
        T obj = singletonInstances.get(name);

        if (obj != null) {
            return obj;
        }

        SpiClass<T> spiClass = spiClassMap.getOrDefault(name, SpiClass.EMPTY_SPI);
        Class<T> clz = spiClass.getClz();

        if (clz == null) {
            return null;
        }

        obj = clz.newInstance();

        singletonInstances.putIfAbsent(name, obj);
        obj = singletonInstances.get(name);
        singletonClzInstances.put(clz, obj);
        if (obj instanceof Configurable) {
            // todo: init with Config
            SpiContext<ConfigLoader> configContext = SpiContext.getContext(ConfigLoader.class);
            List<ConfigLoader> configLoaders = configContext.getSpiInstances(null);
            Configurable configurable = (Configurable) obj;
            Class<? extends VersionConfig> configClass = configurable.configClass();

            for (ConfigLoader configLoader : configLoaders) {
                configurable.setupWithConfig(configLoader.loadConfig(configClass));
                configLoader.markConfigurable(configClass, configurable);
            }
        }
        return obj;
    }

    /**
     * Add extension class.
     *
     * @param clz the clz
     */
    public void addSpiClass(Class<T> clz) {
        if (clz == null) {
            return;
        }


        inner.checkExtensionType(type, clz);

        String spiName = getSpiName(clz);

        synchronized (spiClassMap) {
            if (spiClassMap.containsKey(spiName)) {
                inner.failThrows(clz, ":Error spiName already exist " + spiName);
            } else {
                SpiClass<T> spiClass = createSpiClass(clz);
                spiClassMap.put(spiName, spiClass);
            }
        }
    }


    /**
     * 有些地方需要spi的所有激活的instances，所以需要能返回一个列表的方法 注意：1 SpiMeta 中的active 为true； 2
     * 按照spiMeta中的sequence进行排序 FIXME： 是否需要对singleton来区分对待，后面再考虑 fishermen
     *
     * @param key the key
     * @return extensions extensions
     */
    @SuppressWarnings("unchecked")
    public List<T> getSpiInstances(String key) {
        if (spiClassMap.size() == 0) {
            return Collections.emptyList();
        }

        List<Class<T>> spiClassList = getSpiClasses(key);
        if (CollectionUtils.isEmpty(spiClassList)) {
            return Collections.EMPTY_LIST;
        }

        List<T> list = spiClassList.stream().map(clz -> getSpiInstance(getSpiName(clz))).collect(Collectors.toList());

        return list;
    }


    public List<Class<T>> getSpiClasses(String key) {

        if (spiClassMap.size() == 0) {
            return Collections.emptyList();
        }

        // 如果只有一个实现，直接返回
        List<Class<T>> exts = new ArrayList(spiClassMap.size());

        // 多个实现，按优先级排序返回
        for (Map.Entry<String, SpiClass<T>> entry : spiClassMap.entrySet()) {
            Class<T> clz = entry.getValue().getClz();
            SpiMeta spiCondition = clz.getAnnotation(SpiMeta.class);
            if (StringUtils.isBlank(key)) {
                exts.add(clz);
            } else if (spiCondition != null && spiCondition.key() != null) {
                for (String k : spiCondition.key()) {
                    if (key.equals(k)) {
                        exts.add(clz);
                        break;
                    }
                }
            }
        }
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        Collections.sort(exts, new SpiComparator());
        return exts;
    }


    private ConcurrentMap<String, SpiClass<T>> loadSpiClasses(String prefix) {
        String fullName = prefix + type.getName();
        List<String> classNames = new ArrayList();

        try {
            Enumeration<URL> urls;
            if (classLoader == null) {
                urls = ClassLoader.getSystemResources(fullName);
            } else {
                urls = classLoader.getResources(fullName);
            }

            if (urls == null || !urls.hasMoreElements()) {
                return new ConcurrentHashMap();
            }

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();

                inner.parseUrl(type, url, classNames);
            }
        } catch (Exception e) {
            throw new PampasException(
                    "ExtensionLoader loadSpiClasses error, prefix: " + prefix + " type: " + type.getClass(), e);
        }

        classNames = classNames.stream().distinct().collect(Collectors.toList());
        return loadClass(classNames);
    }

    @SuppressWarnings("unchecked")
    private ConcurrentMap<String, SpiClass<T>> loadClass(List<String> classNames) {
        ConcurrentMap<String, SpiClass<T>> map = new ConcurrentHashMap();

        for (String className : classNames) {
            try {
                Class<T> clz;
                if (classLoader == null) {
                    clz = (Class<T>) Class.forName(className);
                } else {
                    clz = (Class<T>) Class.forName(className, true, classLoader);
                }
                String spiName = getSpiName(clz);

                inner.checkExtensionType(type, clz);
                SpiClass spiClass = createSpiClass(clz);

                if (map.containsKey(spiName)) {
                    inner.failThrows(clz, ":Error spiName already exist " + spiName);
                } else {
                    map.put(spiName, spiClass);
                }
            } catch (Exception e) {
                inner.failLog(type, "Error load spi class", e);
            }
        }

        return map;

    }

    private SpiClass<T> createSpiClass(Class<T> clz) {
        String spiName = getSpiName(clz);
        Scope scope = getScope(type);
        SpiMeta spiMeta = getSpiMeta(clz);

        SpiClass<T> spiClass = new SpiClass();
        spiClass.setClz(clz);
        spiClass.setScope(scope);
        spiClass.setSpiMeta(spiMeta);
        return spiClass;
    }

    /**
     * 获取扩展点的名字
     * 如果扩展类有SpiMeta的注解，那么获取对应的name，如果没有的话获取classname
     *
     * @param clz the clz
     * @return spi name
     */
    public static String getSpiName(Class<?> clz) {
        SpiMeta spiMeta = clz.getAnnotation(SpiMeta.class);
        return (spiMeta != null && !"".equals(spiMeta.name())) ? spiMeta.name() : clz.getSimpleName();
    }

    /**
     * Gets spi meta.
     *
     * @param clz the clz
     * @return the spi meta
     */
    public static SpiMeta getSpiMeta(Class<?> clz) {
        return clz.getAnnotation(SpiMeta.class);
    }

    /**
     * Gets scope.
     *
     * @param clz the clz
     * @return the scope
     */
    public static Scope getScope(Class<?> clz) {
        Spi spi = clz.getAnnotation(Spi.class);
        return spi.scope();
    }


    /**
     * 获取单例所有对象MAP
     * class->Instance
     *
     * @return 单例对象map
     */
    public static Map<Class, Object> getSingletonInstanceMap() {
        return Collections.unmodifiableMap(singletonClzInstances);
    }

    /**
     * 内部方法持有类
     */
    private static class InnerMethodHolder {

        private <T> boolean isSpiType(Class<T> clz) {
            return clz.isAnnotationPresent(Spi.class);
        }

        /**
         * check clz
         *
         * <pre>
         * 		1.  is interface
         * 		2.  is contains @Spi annotation
         * </pre>
         *
         * @param <T> the type parameter
         * @param clz the clz
         */
        protected <T> void checkInterfaceType(Class<T> clz) {
            if (clz == null) {
                inner.failThrows(null, "Error extension type is null");
            }

            if (!clz.isInterface()) {
                inner.failThrows(clz, "Error extension type is not interface");
            }

            if (!isSpiType(clz)) {
                inner.failThrows(clz, "Error extension type without @Spi annotation");
            }
        }


        /**
         * check extension clz
         *
         * <pre>
         * 		1) is public class
         * 		2) contain public constructor and has not-args constructor
         * 		3) check extension clz instanceof Type.class
         * </pre>
         *
         * @param spiType the spi type
         * @param clz     the clz
         */
        protected void checkExtensionType(Class spiType, Class clz) {
            checkClassPublic(clz);

            checkConstructorPublic(clz);

            checkClassInherit(spiType, clz);
        }

        /**
         * Check class inherit.
         *
         * @param spiType the spi type
         * @param clz     the clz
         */
        protected void checkClassInherit(Class spiType, Class clz) {
            if (!spiType.isAssignableFrom(clz)) {
                failThrows(clz, "Error is not instanceof " + spiType.getName());
            }
        }

        /**
         * Check class public.
         *
         * @param clz the clz
         */
        protected void checkClassPublic(Class clz) {
            if (!Modifier.isPublic(clz.getModifiers())) {
                failThrows(clz, "Error is not a public class");
            }
        }

        /**
         * Check constructor public.
         *
         * @param clz the clz
         */
        protected void checkConstructorPublic(Class clz) {
            Constructor<?>[] constructors = clz.getConstructors();

            if (constructors == null || constructors.length == 0) {
                failThrows(clz, "Error has no public no-args constructor");
            }

            for (Constructor<?> constructor : constructors) {
                if (Modifier.isPublic(constructor.getModifiers()) && constructor.getParameterTypes().length == 0) {
                    return;
                }
            }

            failThrows(clz, "Error has no public no-args constructor");
        }

        /**
         * Parse url.
         *
         * @param type       the type
         * @param url        the url
         * @param classNames the class names
         * @throws ServiceConfigurationError the service configuration error
         */
        protected void parseUrl(Class type, URL url, List<String> classNames) throws ServiceConfigurationError {
            InputStream inputStream = null;
            BufferedReader reader = null;
            try {
                inputStream = url.openStream();
                reader = new BufferedReader(new InputStreamReader(inputStream, PampasConsts.CHARSET_UTF8));

                String line;
                int indexNumber = 0;

                while ((line = reader.readLine()) != null) {
                    indexNumber++;
                    parseLine(type, url, line, indexNumber, classNames);
                }
            } catch (Exception x) {
                failLog(type, "Error reading spi configuration file", x);
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException y) {
                    failLog(type, "Error closing spi configuration file", y);
                }
            }
        }

        /**
         * Parse line.
         *
         * @param type       the type
         * @param url        the url
         * @param line       the line
         * @param lineNumber the line number
         * @param names      the names
         * @throws IOException               the io exception
         * @throws ServiceConfigurationError the service configuration error
         */
        protected void parseLine(Class type, URL url, String line, int lineNumber, List<String> names) throws IOException,
                ServiceConfigurationError {
            int ci = line.indexOf('#');

            if (ci >= 0) {
                line = line.substring(0, ci);
            }

            line = line.trim();

            if (line.length() <= 0) {
                return;
            }

            if ((line.indexOf(' ') >= 0) || (line.indexOf('\t') >= 0)) {
                failThrows(type, url, lineNumber, "Illegal spi configuration-file syntax");
            }

            int cp = line.codePointAt(0);
            if (!Character.isJavaIdentifierStart(cp)) {
                failThrows(type, url, lineNumber, "Illegal spi provider-class name: " + line);
            }

            for (int i = Character.charCount(cp); i < line.length(); i += Character.charCount(cp)) {
                cp = line.codePointAt(i);
                if (!Character.isJavaIdentifierPart(cp) && (cp != '.')) {
                    failThrows(type, url, lineNumber, "Illegal spi provider-class name: " + line);
                }
            }

            if (!names.contains(line)) {
                names.add(line);
            }
        }

        private <T> void failLog(Class<T> type, String msg, Throwable cause) {
            log.error(typeName(type) + ": " + msg, cause);
        }

        private <T> void failThrows(Class<T> type, String msg, Throwable cause) {
            throw new PampasException(typeName(type) + ": " + msg, cause);
        }

        private <T> void failThrows(Class<T> type, String msg) {
            throw new PampasException(typeName(type) + ": " + msg);
        }

        private <T> void failThrows(Class<T> type, URL url, int line, String msg) throws ServiceConfigurationError {
            failThrows(type, url + ":" + line + ": " + msg);
        }

        private String typeName(Class type) {
            return type == null ? "" : type.getName();
        }
    }


}
