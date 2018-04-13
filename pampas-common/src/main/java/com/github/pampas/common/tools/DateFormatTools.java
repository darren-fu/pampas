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

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

/**
 * Date的parse()与format(), 采用Apache Common Lang中线程安全, 性能更佳的FastDateFormat
 * <p>
 * 注意Common Lang版本，3.5版才使用StringBuilder，3.4及以前使用StringBuffer.
 * <p>
 * 1. 常用格式的FastDateFormat定义
 * <p>
 * 2. 日期格式不固定时的String<->Date 转换函数.
 * <p>
 * 3. 打印时间间隔，如"01:10:10"，以及用户友好的版本，比如"刚刚"，"10分钟前"
 *
 * @author calvin (#-_-~~~代码的搬运工)
 * @see FastDateFormat#parse(String) FastDateFormat#parse(String)
 * @see FastDateFormat#format(Date) FastDateFormat#format(Date)
 * @see FastDateFormat#format(long) FastDateFormat#format(long)
 */
@SuppressWarnings("unused")
public class DateFormatTools {
    private static final Logger log = LoggerFactory.getLogger(DateFormatTools.class);
    /**
     * The constant PATTERN_ISO.
     */
    // 以T分隔日期和时间，并带时区信息，符合ISO8601规范
    public static final String PATTERN_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
    /**
     * The constant PATTERN_ISO_ON_SECOND.
     */
    public static final String PATTERN_ISO_ON_SECOND = "yyyy-MM-dd'T'HH:mm:ssZZ";
    /**
     * The constant PATTERN_ISO_ON_DATE.
     */
    public static final String PATTERN_ISO_ON_DATE = "yyyy-MM-dd";

    /**
     * The constant PATTERN_DEFAULT.
     */
// 以空格分隔日期和时间，不带时区信息
    public static final String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * The constant PATTERN_DEFAULT_ON_SECOND.
     */
    public static final String PATTERN_DEFAULT_ON_SECOND = "yyyy-MM-dd HH:mm:ss";

    // 使用工厂方法FastDateFormat.getInstance(), 从缓存中获取实例

    /**
     * The constant ISO_FORMAT.
     */
// 以T分隔日期和时间，并带时区信息，符合ISO8601规范
    public static final FastDateFormat ISO_FORMAT = FastDateFormat.getInstance(PATTERN_ISO);
    /**
     * The constant ISO_ON_SECOND_FORMAT.
     */
    public static final FastDateFormat ISO_ON_SECOND_FORMAT = FastDateFormat.getInstance(PATTERN_ISO_ON_SECOND);
    /**
     * The constant ISO_ON_DATE_FORMAT.
     */
    public static final FastDateFormat ISO_ON_DATE_FORMAT = FastDateFormat.getInstance(PATTERN_ISO_ON_DATE);

    /**
     * The constant DEFAULT_FORMAT.
     */
// 以空格分隔日期和时间，不带时区信息
    public static final FastDateFormat DEFAULT_FORMAT = FastDateFormat.getInstance(PATTERN_DEFAULT);
    /**
     * The constant DEFAULT_ON_SECOND_FORMAT.
     */
    public static final FastDateFormat DEFAULT_ON_SECOND_FORMAT = FastDateFormat.getInstance(PATTERN_DEFAULT_ON_SECOND);


    public static Date parseDate(String date) {
        try {
            long dateLong = Long.parseLong(date);
            return new Date(dateLong);
        } catch (Exception ex) {
            try {
                return DEFAULT_ON_SECOND_FORMAT.parse(date);
            } catch (ParseException e) {
                log.error("failed to parse date:{}", date, e);
            }
        }
        return null;
    }


    /**
     * 分析日期字符串, 仅用于pattern不固定的情况.
     * <p>
     * 否则直接使用DateFormats中封装好的FastDateFormat.
     * <p>
     * FastDateFormat.getInstance()已经做了缓存，不会每次创建对象，但直接使用对象仍然能减少在缓存中的查找.
     *
     * @param pattern    the pattern
     * @param dateString the date string
     * @return the date
     * @throws ParseException the parse exception
     */
    public static Date pareDate(String pattern, String dateString) throws ParseException {
        return FastDateFormat.getInstance(pattern).parse(dateString);
    }

    /**
     * 格式化日期, 仅用于pattern不固定的情况.
     * <p>
     * 否则直接使用本类中封装好的FastDateFormat.
     * <p>
     * FastDateFormat.getInstance()已经做了缓存，不会每次创建对象，但直接使用对象仍然能减少在缓存中的查找.
     *
     * @param pattern the pattern
     * @param date    the date
     * @return the string
     */
    public static String formatDate(String pattern, Date date) {
        return FastDateFormat.getInstance(pattern).format(date);
    }

    /**
     * 格式化日期, 仅用于不固定pattern不固定的情况.
     * <p>
     * 否否则直接使用本类中封装好的FastDateFormat.
     * <p>
     * FastDateFormat.getInstance()已经做了缓存，不会每次创建对象，但直接使用对象仍然能减少在缓存中的查找.
     *
     * @param pattern the pattern
     * @param date    the date
     * @return the string
     */
    public static String formatDate(String pattern, long date) {
        return FastDateFormat.getInstance(pattern).format(date);
    }

    /////// 格式化间隔时间/////////

    /**
     * 按HH:mm:ss.SSS格式，格式化时间间隔.
     * <p>
     * endDate必须大于startDate，间隔可大于1天，
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the string
     */
    public static String formatDuration(Date startDate, Date endDate) {
        return DurationFormatUtils.formatDurationHMS(endDate.getTime() - startDate.getTime());
    }

    /**
     * 按HH:mm:ss.SSS格式，格式化时间间隔
     * <p>
     * 单位为毫秒，必须大于0，可大于1天
     *
     * @param durationMillis the duration millis
     * @return the string
     */
    public static String formatDuration(long durationMillis) {
        return DurationFormatUtils.formatDurationHMS(durationMillis);
    }

    /**
     * 按HH:mm:ss格式，格式化时间间隔
     * <p>
     * endDate必须大于startDate，间隔可大于1天
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the string
     */
    public static String formatDurationOnSecond(Date startDate, Date endDate) {
        return DurationFormatUtils.formatDuration(endDate.getTime() - startDate.getTime(), "HH:mm:ss");
    }

    /**
     * 按HH:mm:ss格式，格式化时间间隔
     * <p>
     * 单位为毫秒，必须大于0，可大于1天
     *
     * @param durationMillis the duration millis
     * @return the string
     */
    public static String formatDurationOnSecond(long durationMillis) {
        return DurationFormatUtils.formatDuration(durationMillis, "HH:mm:ss");
    }


}
