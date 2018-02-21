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

package com.github.df.pampas.common.discover;

import com.github.df.pampas.common.base.PampasConsts;
import com.github.df.pampas.common.tools.CommonTools;
import com.github.df.pampas.common.tools.StringBuilderFactory;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.github.df.pampas.common.base.PampasConsts.Instance.*;


/**
 * 服务实例
 * Created by darrenfu on 17-6-25.
 */
@Data
@EqualsAndHashCode(of = {"instanceId", "serviceName", "protocol", "host", "port", "room"})
public class ServerInstance {

    private static final Logger log = LoggerFactory.getLogger(ServerInstance.class);

    /**
     * 服务实例ID unique
     */
    private String instanceId;

    /**
     * 服务名称
     */
    private String serviceName;


    private String protocol;

    /**
     * 主机
     */
    private String host;

    /**
     * 端口
     */
    private int port;


    /**
     * 机房
     */
    private String room;

    /**
     * 是否存活
     */
    private Boolean isAlive;

    /**
     * 实例开始服务时间
     */
    private Long startTimestamp;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 预热时间  ms
     */
    private Integer warmupSeconds;

    /**
     * 版本信息
     */
    private VersionInfo versionInfo;

    /**
     * 原始版本信息
     */
    private String version;

    /**
     * 其它属性
     */
    private Map<String, String> props;

    /**
     * 是否准备好
     */
    @Setter(value = AccessLevel.PRIVATE)
    private boolean ready = false;

    @Setter(value = AccessLevel.PRIVATE)
    private String uri;

    public ServerInstance(String serviceName) {
        this.serviceName = serviceName;
    }


    public static ServerInstance build(String serviceName, String protocol, String host, int port) {
        ServerInstance instance = new ServerInstance(serviceName);
        instance.setHost(host);
        instance.setPort(port);
        instance.setProtocol(protocol);
        instance.ready();
        return instance;
    }

    public static ServerInstance buildWithUri(String serviceName, String uri) {
        URL url = null;
        try {
            url = new URL(uri);
        } catch (MalformedURLException e) {
            log.warn("URL is not valid:{}", uri, e);
            throw new IllegalArgumentException(e);
        }

        ServerInstance instance = new ServerInstance(serviceName);
        instance.setHost(url.getHost());
        instance.setPort(url.getPort() < 0 ? url.getDefaultPort() : url.getPort());
        instance.setProtocol(url.getProtocol());
        instance.ready();
        return instance;
    }


    public String toUri() {
        if (StringUtils.isNotEmpty(uri)) {
            return this.uri;
        }
        StringBuilder sb = StringBuilderFactory.DEFAULT.stringBuilder();
        this.uri = sb.append(this.getProtocol()).append("://")
                .append(this.getHost()).append(":").append(this.getPort()).toString();
        return this.uri;
    }

    /**
     * 服务实例准备完成
     * 必须调用此方法完成服务实例的初始化工作
     * 否则服务实例将不能使用
     *
     * @return server instance
     */
    public ServerInstance ready() {
        if (this.port <= 0 || StringUtils.isEmpty(this.host)) {
            throw new IllegalArgumentException("host/port of server instance can not be null");
        }
        //是否存活
        this.setIsAlive(ObjectUtils.defaultIfNull(this.isAlive, true));

        //机房
        this.setRoom(ObjectUtils.defaultIfNull(this.room, PampasConsts.DEFAULT_ROOM));

        //服务名称
        this.setServiceName(ObjectUtils.defaultIfNull(this.serviceName, PampasConsts.DEFAULT_SERVICE));

        //ID
        if (StringUtils.isEmpty(this.instanceId)) {
            StringBuilder sb = StringBuilderFactory.DEFAULT.stringBuilder();
            sb.append(serviceName);
            sb.append("@");
            sb.append(host);
            sb.append(":");
            sb.append(port);
            this.setInstanceId(sb.toString());
        }


        //启动时间
        this.setStartTimestamp(ObjectUtils.defaultIfNull(this.startTimestamp,
                Long.valueOf(getPropValue(PROP_TIMESTAMP_KEY, PROP_TIMESTAMP_DEFAULT))));
        //权重
        this.setWeight(ObjectUtils.defaultIfNull(this.weight,
                Integer.valueOf(getPropValue(PROP_WEIGHT_KEY, PROP_WEIGHT_DEFAULT))));

        //设置预热时间
        this.setWarmupSeconds(ObjectUtils.defaultIfNull(this.warmupSeconds,
                Integer.valueOf(getPropValue(PROP_WARMUP_KEY, PROP_WARMUP_DEFAULT))));

        //版本
        String versionAttr = StringUtils.isNotEmpty(version) ? version : getPropValue(PROP_VERSION_KEY, PROP_VERSION_DEFAULT);
        this.version = versionAttr;
        this.setVersionInfo(ObjectUtils.defaultIfNull(this.versionInfo,
                StringUtils.isEmpty(versionAttr) ? VersionInfo.EMPTY_VERSION : VersionInfo.create(this.instanceId, versionAttr)));


        this.getPropValue(PROP_TIMESTAMP_KEY, PROP_TIMESTAMP_DEFAULT);
        this.ready = true;

        return this;
    }

    /**
     * 获取Map中属性值
     *
     * @param <T>          the type parameter
     * @param propKey      the prop key
     * @param defaultValue the default value
     * @return the prop value
     */
    public <T> String getPropValue(String propKey, T defaultValue) {
        if (props == null || props.size() == 0) {
            return String.valueOf(defaultValue);
        }
        String propVal = props.get(propKey);

        if (propVal == null) {
            return String.valueOf(defaultValue);
        }
        return propVal;
    }

    /**
     * 添加额外的属性
     *
     * @param prop  the prop
     * @param value the value
     * @return the server instance
     */
    public ServerInstance addPropValue(String prop, String value) {
        if (props == null) {
            props = new HashMap();
        }
        props.put(prop, value);
        return this;
    }

    /**
     * 添加额外的属性
     *
     * @param propMap the prop map
     * @return the server instance
     */
    public ServerInstance addPropValue(Map<String, String> propMap) {
        if (CommonTools.isEmpty(propMap)) {
            return this;
        }
        if (props == null) {
            props = new HashMap();
        }
        props.putAll(propMap);
        return this;
    }
}
