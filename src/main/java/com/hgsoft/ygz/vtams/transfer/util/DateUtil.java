package com.hgsoft.ygz.vtams.transfer.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 *
 * @author 赖少涯
 * @date 2017-10-12
 */
public class DateUtil {

    /**
     * 定义默认的日期格式化器：SimpleDateFormat是线程不安全的，但是此处并不涉及对defaultFormater的修改，所以无影响
     */
    private static final SimpleDateFormat defaultFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * 使用指定格式器格式化日期
     *
     * @param date    日期
     * @param pattern 格式
     * @return 格式化后的日期字符串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 使用默认格式器格式化日期:yyyy-MM-dd'T'HH:mm:ss
     *
     * @param date
     * @return string类型日期
     */
    public static String format(Date date) {
        return defaultFormater.format(date);
    }

    /**
     * 将时间戳转为默认格式的日期:yyyy-MM-dd'T'HH:mm:ss
     *
     * @param timestamp 时间戳
     * @return string类型日期
     */
    public static String format(Long timestamp) {
        return format(new Date(timestamp));
    }

    /**
     * 将时间戳转为指定格式的日期
     *
     * @param timestamp 时间戳
     * @return string类型日期
     */
    public static String format(Long timestamp, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(timestamp));
    }


    /**
     * 返回当前sql类型的时间戳
     *
     * @return sql类型的Timestamp
     */
    public static Timestamp getCurrentSqlTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }


    /**
     * 将时间戳转为指定格式的日期
     *
     * @param source  日期源字符串
     * @param pattern yyyy-MM-dd
     * @return Date类型日期
     */
    public static Date parse(final String source, final String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
