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

package com.github.pampas.common.tools;


import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 运用stream流处理集合的工具方法
 * author: fuliang
 * date: 2017/2/21
 */
@SuppressWarnings("unused")
public class StreamTools {

    /**
     * 收集对象中的Key，过滤掉重复值
     *
     * @param <K>       返回的Key类型
     * @param <V>       要收集对象的对象类型
     * @param coll      要收集的对象
     * @param keyGetter the key getter
     * @return Key集合 list
     */
    public static <K, V> List<K> collectKey(Collection<V> coll, Function<V, K> keyGetter) {
        if (CommonTools.isEmpty(coll)) {
            return Collections.emptyList();
        }

        return coll.stream().map(keyGetter).distinct().collect(Collectors.toList());
    }


    /**
     * 比对 两个集合， 取出source 中有而 compare中没有的数据，
     * 示例，对比两个Response，获取source集合中不一样的Response:
     * source [responses]: [Response(code=test_Code0, msg=test_message0), Response(code=test_Code1, msg=test_message1), Response(code=test_Code2, msg=test_message2)]
     * compare [strLst]: ["test_Code0", "test_Code2"]
     * <p>
     * List<Response> diff = diff(responses, strLst, (v, r) -> ObjectTools.equalsBetween(v.getCode(), r));
     * return : [Response(code=test_Code1, msg=test_message1, info=null)]
     *
     * @param <K>     the type parameter
     * @param <V>     the type parameter
     * @param <R>     the type parameter
     * @param source  the source
     * @param compare the compare
     * @param matcher the matches
     * @return the list
     */
    public static <K, V, R> List<V> diff(Collection<V> source, Collection<R> compare, BiPredicate<V, R> matcher) {
        if (CommonTools.isEmpty(source)) {
            return Collections.emptyList();
        }
        if (CommonTools.isEmpty(compare)) {
            return new ArrayList<>(source);
        }

        return source.stream().filter(sourceVal ->
                compare.stream().noneMatch(
                        compareVal -> matcher.test(sourceVal, compareVal)
                )

        ).collect(Collectors.toList());
    }

    /**
     * 比对 两个集合，根据matches 取出共同的数据，
     * 示例，对比两个Response，获取source集合中符合matcher的Response:
     * source [responses]: [Response(code=test_Code0, msg=test_message0), Response(code=test_Code1, msg=test_message1), Response(code=test_Code2, msg=test_message2)]
     * compare [strLst]: ["test_Code0", "test_Code2"]
     * <p>
     * List<Response> union = union(responses, strLst, (v, r) -> ObjectTools.equalsBetween(v.getCode(), r));
     * return : [Response(code=test_Code0, msg=test_message0, info=null), Response(code=test_Code2, msg=test_message2, info=null)]
     *
     * @param <K>     the type parameter
     * @param <V>     the type parameter
     * @param <R>     the type parameter
     * @param source  the source
     * @param compare the compare
     * @param matcher the matcher
     * @return the list
     */
    public static <K, V, R> List<V> union(Collection<V> source, Collection<R> compare, BiPredicate<V, R> matcher) {
        if (CommonTools.isEmpty(source)) {
            return Collections.emptyList();
        }
        if (CommonTools.isEmpty(compare)) {
            return new ArrayList<>(source);
        }

        return source.stream().filter(sourceVal ->
                compare.stream().anyMatch(compareVal -> matcher.test(sourceVal, compareVal))
        ).collect(Collectors.toList());
    }


    /**
     * 批量收集对象中的Key，过滤掉重复值
     *
     * @param <K>       返回的Key类型
     * @param <V>       要收集对象的对象类型
     * @param coll      要收集的对象
     * @param keyGetter the key getter
     * @return Key集合 list
     */
    public static <K, V> List<K> collectFlatKey(Collection<V> coll, Function<V, List<K>> keyGetter) {
        if (CommonTools.isEmpty(coll)) {
            return Collections.emptyList();
        }

        return coll.stream().flatMap(item -> keyGetter.apply(item).stream()).distinct().collect(Collectors.toList());
    }

    /**
     * 根据指定Key分组
     * 如果存在一个描述人的类Person(id, name, area)，给定3个人(1, "Tom", "南京")，(2, "Cate", "南京")，(3, "Lily", "北京")
     * 那么希望给这三个人根据地域（area）进行分组，只需调用方法
     * groupBy(Arrays.asList(person1, person2, person3), Person::getArea)
     * 返回值为Map:{"南京" : [person1, person2], "北京" : [person3]}
     *
     * @param <K>       分组的Key类型
     * @param <V>       集合元素
     * @param coll      要进行分组的集合
     * @param keyGetter 根据集合中的元素获取分组的Key
     * @return 分好组的Map map
     */
    public static <K, V> Map<K, List<V>> groupBy(Collection<V> coll, Function<V, K> keyGetter) {
        if (CommonTools.isEmpty(coll)) {
            return Collections.emptyMap();
        }
        return coll.stream().collect(Collectors.groupingBy(keyGetter));
    }
    /**
     * 转化集合为Map，map的key从集合对象中抽取，不同雨groupBy，重复的key保留第一个对象
     *
     * @param coll      集合
     * @param keyGetter Key getter
     * @param <K>       返回的Key类型
     * @param <V>       要收集对象的对象类型
     * @return MAP
     */
    public static <K, V> Map<K, V> toMap(Collection<V> coll, Function<V, K> keyGetter) {
        if (CollectionUtils.isEmpty(coll)) {
            return Collections.emptyMap();
        }
        Map<K, V> map = coll.stream().collect(Collectors.toMap(keyGetter, v -> v, (first, duplicate) -> first));
        return map;
    }

    public static <K, V, Y> Map<K, Y> toMap(Collection<V> coll, Function<V, K> keyGetter, Function<V, Y> ValGetter) {
        if (CollectionUtils.isEmpty(coll)) {
            return Collections.emptyMap();
        }
        Map<K, Y> map = coll.stream().collect(Collectors.toMap(keyGetter, ValGetter, (first, duplicate) -> first));
        return map;
    }


    /**
     * To map map.
     *
     * @param <T>         the type parameter
     * @param <K>         the type parameter
     * @param <V>         the type parameter
     * @param coll        the coll
     * @param keyGetter   the key getter
     * @param valueGetter the value getter
     * @param merger      the merger
     * @return the map
     */
    public static <T, K, V> Map<K, V> toMap(Collection<T> coll,
                                            Function<T, K> keyGetter,
                                            Function<T, V> valueGetter,
                                            BinaryOperator merge) {
        if (CommonTools.isEmpty(coll)) {
            return Collections.emptyMap();
        }
        Map<K, V> collect = coll.stream().collect(Collectors.toMap(keyGetter, valueGetter, (BinaryOperator<V>) merge));
        return collect;
    }



    /**
     * 生成一个序列，类似于python的range函数
     *
     * @param size 范围
     * @return [1, 2, 3, 4, 5, 6, ... , size-1]
     */
    public static List<Long> range(long size) {
        if (size <= 1) {
            return Collections.emptyList();
        }

        List<Long> valueList = new ArrayList<>();
        for (long i = 0; i < size; i++) {
            valueList.add(i);
        }

        return valueList;
    }


    /**
     * 查找集合firsts中是否包含seconds集合,并且按照merger组装想要返回的结果
     * e.g.
     * firsts   = ["a", "b", "b", "c", "d", "e"]
     * seconds  = ["c", "d", "e", "f"]
     * martcher = String::equals
     * merger   = (a, b) -> new {@link Map.Entry}<>(a, b.isPresent())
     * return   = [(a, false),(b, false), (b, false), (c, true), (d, true), (e, true)]
     * <p>
     *
     * @param <F>     第一个集合firsts的类型
     * @param <S>     第二个集合seconds的类型
     * @param <R>     返回值类型
     * @param firsts  第一个集合
     * @param seconds 第二个集合
     * @param matcher 匹配条件
     * @param merger  合并需要的结果
     * @return Stream<R>   stream
     */
    public static <F, S, R> Stream<R> contains(Collection<F> firsts, Collection<S> seconds, BiFunction<F, S, Boolean> matcher, BiFunction<F, Optional<S>, R> merger) {
        return firsts.stream().map(
                f -> {
                    Optional<S> s = seconds.stream().filter(second -> matcher.apply(f, second)).findFirst();
                    return merger.apply(f, s);
                }
        );
    }

    /**
     * 由于JAVA8 stream.distinct的局限, 提供处理集合的distinct
     *
     * @param <T>          泛型
     * @param keyExtractor e.g. distinctByKey(Person::getName)
     * @return Predicate<T>   predicate
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, String> seen = new ConcurrentHashMap<>();
        return t -> seen.put(keyExtractor.apply(t), "") == null;
    }

    /**
     * 根据给定的pageSize分页
     *
     * @param <T>      集合类型
     * @param list     需要分页的集合
     * @param pageSize 你懂的
     * @return 一个分页的二维数组 list
     */
    public static <T> List<List<T>> pageByNum(List<T> list, int pageSize) {
        return IntStream.range(0, list.size()).boxed().filter(t -> t % pageSize == 0).map(t -> list.stream().skip(t).limit(pageSize).collect(Collectors.toList())).collect(Collectors.toList());
    }


    /**
     * 对content进行排序
     *
     * @param <F>        参考顺序的类型
     * @param <V>        需要排序的类型
     * @param <R>        返回的类型
     * @param orderRefer 用作参考的顺序列表
     * @param content    需要排序的对象集合
     * @param matcher    orderRefer 与 content的匹配函数
     * @param merger     合并函数,用来重组结果result
     * @return the list
     */
    public static <F, V, R> List<R> order(Collection<F> orderRefer, Collection<V> content,
                                          BiFunction<F, V, Boolean> matcher, BiFunction<F, V, R> merger) {
        return orderRefer.stream().map(
                order -> CollectionUtils.select(content, object -> matcher.apply(order, object))
                        .stream().map(object -> merger.apply(order, object)).collect(Collectors.toList()))
                .reduce(new ArrayList<>(), (a, b) -> {
                    a.addAll(b);
                    return a;
                });
    }


}
