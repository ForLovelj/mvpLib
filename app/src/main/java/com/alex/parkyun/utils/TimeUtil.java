package com.alex.parkyun.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dth
 * Des:
 * Date: 2018-01-24.
 */

public class TimeUtil {

    /**
     * 获取当前系统时间
     *
     * @return 格式化的时间戳
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date());
    }

    /**
     * 获取当前系统时间的毫秒数
     *
     * @return 当前时间的毫秒数
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前系统时间
     *
     * @param pattern
     *            时间格式
     * @return 格式化的时间戳
     */
    public static String getCurrentTime(String pattern) {
        return new SimpleDateFormat(pattern, Locale.CHINA).format(new Date());
    }

    /**
     * 小时数转换成秒数
     *
     * @param hour
     *            小时数
     * @return 秒数
     */
    public static long hour2Second(int hour) {
        return hour * 60 * 60;
    }

    /**
     * 小时数转换成毫秒数
     *
     * @param hour
     *            小时数
     * @return 毫秒数
     */
    public static long hour2Millis(int hour) {
        return hour * 60 * 60 * 1000;
    }

    public static String formatTime(long timeMillis) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timeMillis));
    }
}
