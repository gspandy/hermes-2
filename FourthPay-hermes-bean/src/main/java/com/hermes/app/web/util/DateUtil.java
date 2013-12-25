package com.hermes.app.web.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {

    public static String getDateTimePattern() {
        return "yyyy-MM-dd HH:mm:ss";
    }

    public static String getDatePattern() {
        return "yyyy-MM-dd";
    }

    /**
     * 获取日期格式 yyyy-MM-dd HH:mm:ss
     * @param aDate
     * @return
     */
    public static final String getDateTime(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDateTimePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     *
     * @param date
     * @param formatPattern
     * @return
     */
    public static String getDateString(Date date, String formatPattern) {
        if (date == null) {
            return "";
        }
        if ((formatPattern == null) || formatPattern.equals("")) {
            formatPattern = getDatePattern();
        }
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(formatPattern);
        return sdf.format(date);
    }

    /**
     * @description 增加日期
     * @param date
     *            日期
     * @param i
     *            天数
     */
    public static String dateAdd(String date, int i) {
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(date.substring(0, 4));// 年
        cal.set(Calendar.YEAR, year);
        int month = Integer.parseInt(date.substring(5, 7));// 月，注意要减1，因为一月对应的是0
        cal.set(Calendar.MONTH, month - 1);
        int day = Integer.parseInt(date.substring(8));// 日
        cal.set(Calendar.DAY_OF_MONTH, day);
        // 如果想要得到第二天的日期就加1，如果超过了当月的最大天数，Calendar会自动处理
        cal.add(Calendar.DAY_OF_MONTH, i);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * @description 增加日期
     * @author duan_jping
     * @param date
     *            日期
     * @param i
     *            天数
     */
    public static String dateAdd(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, i);
        Date dateAdd = new Date(calendar.getTimeInMillis());
        SimpleDateFormat sdf = new SimpleDateFormat(getDatePattern());
        return sdf.format(dateAdd);
    }
}