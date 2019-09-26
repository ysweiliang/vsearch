package com.macro.vsearch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author yuans
 * @create 2019-09-24-14:16
 */
public class DateUtils {

    public static String getNowTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(System.currentTimeMillis());
    }

    /**
     * 当前日期加上天数后的日期
     * @param num 为增加的天数
     * @return
     */
    public static String currentCountDay(int num) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        Date date = ca.getTime();
        String endDate = format.format(date);
        return endDate;
    }

    /**
     * 指定日期加上天数后的日期 yyyy-MM-dd HH:mm:ss
     * @param num 为增加的天数
     * @param newDate 创建时间
     * @return
     * @throws ParseException
     */
    public static String designCountDay(int num, String newDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currDate = format.parse(newDate);
        Calendar ca = Calendar.getInstance();
        ca.setTime(currDate);
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        currDate = ca.getTime();
        String endDate = format.format(currDate);
        return endDate;
    }

    /**
     * 指定日期加上天数后的日期 yyyy-MM-dd
     * @param num 为增加的天数
     * @param newDate 创建时间
     * @return
     * @throws ParseException
     */
    public static String designCountDate(int num, String newDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date currDate = format.parse(newDate);
        Calendar ca = Calendar.getInstance();
        ca.setTime(currDate);
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        currDate = ca.getTime();
        String endDate = format.format(currDate);
        return endDate;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔,返回秒数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentSecond(Date date1,Date date2) {
        int differentSecond = (int) ((date2.getTime() - date1.getTime()) /1000);
        return Math.abs(differentSecond);
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔,返回天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return Math.abs(days);
    }

    /**
     * yyyy-MM-dd hh:mm:ss 转化成 yyyy年MM月dd日 hh:mm:ss
     * @param date
     * @return
     */
    public static String changeDate(String date) {
        String[] dateTime = date.trim().split("-");
        String newTime = dateTime[0] + "年" + dateTime[1] + "月" + dateTime[2];
        String[] newDate = newTime.split(" ");
        return newDate[0] + "日 " + newDate[1];
    }

    /**
     * yyyy-MM-dd 转化成 yyyy年MM月dd日
     * @param date
     * @return
     */
    public static String changeDateDay(String date) {
        String[] dateTime = date.trim().split("-");
        String newTime = dateTime[0] + "年" + dateTime[1] + "月" + dateTime[2];
        String[] newDate = newTime.split(" ");
        return newDate[0] + "日 ";
    }

}
