package com.bjpowernode.springboot.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class TimeUtils {

    /**
     * 计算时间差(单位分)
     *
     * @param begin 开始时间(时间戳)
     * @param end   结束时间(时间戳)
     */
    public static Long diff(Long begin, Long end) {
        long df = begin - end;

        return df / 1000 / 60;
    }

    /**
     * 获得Date日期
     *
     * @param time (格式:yyyy-MM-dd)
     */
    public static Date stringConvertDate(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * 获得获得时间戳
     *
     * @param time (格式:yyyy-MM-dd HH:mm:ss)
     */
    public static Long stringConvertLong(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return getTime(time, simpleDateFormat);
    }

    /**
     * 获得获得时间戳
     *
     * @param time (格式:yyyy/MM/dd)
     */
    public static Long stringConvertLong2(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return getTime(time, simpleDateFormat);
    }

    private static Long getTime(String time, SimpleDateFormat simpleDateFormat) {
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTime.getTime();
    }

    /**
     * 获得获得时间戳
     *
     * @param time (格式:yyyy-MM-dd)
     */
    public static Long stringConvertLong3(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return getTime(time, simpleDateFormat);
    }


    /**
     * 得到系统当前日期
     * "yyyyMMdd"
     */
    public static String getCurentDate() {
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMdd");
        String datetime = tempDate.format(new Date());
        return datetime;
    }

    /**
     * 得到系统当前日期
     * "yyyy-MM-dd"
     */
    public static String getCurent2Date() {
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = tempDate.format(new Date());
        return datetime;
    }

    /**
     * 得到系统当前日期的前一天
     * "yyyy-MM-dd"
     */
    public static String beforeDayByNowDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); //得到前一天
        Date date = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * 获取一段时间的每一天日期
     *
     * @param start yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @throws Exception
     */
    public static List<String> getBetweenDate(String start, String end) {
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> d.plusDays(1)).limit(distance + 1).forEach(f -> list.add(f.toString()));
        return list;
    }


    /**
     * @Description 时间戳获取某刻时间的季度
     */
    public static Long getSeason(Long date) {
        Long season = 0L;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1L;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2L;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3L;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4L;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * @Description 时间戳转化成年月日(yyyy / MM / dd)
     */
    public static String stampToTime(Long time) {
        String res = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date(time);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @Description 时间戳转化成年月日(yyyy - MM - dd)
     */
    public static String stampTo2Time(Long time) {
        String res = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(time);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @Description: 时间戳转化成年月(yyyy - MM)
     */
    public static String stampTo3Time(Long time) {
        String res = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            Date date = new Date(time);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @Description: 时间戳转化成年月时分秒(yyyy - MM - dd HH : mm : ss)
     * @Date: 2020/10/20 16:15
     */
    public static String longToTime(Long time) {
        String res = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(time);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(beforeDayByNowDay());
    }

}
