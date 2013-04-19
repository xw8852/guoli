package com.guoli.hotel.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.util.Log;

public class DateUtils {
    
    public static final String FORMAT_TIME_HHMM = "HH:mm";
    public static final long MILLIS_FOR_ONE_DAY = 86400000;
    public static final String FORMAT_DATE_YYMMDD = "yyyy-MM-dd";
    public static final String FORMAT_TIME_YYMMDD = "yyyy/MM/dd";
	
	public static int getDayOfWeek(long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		return c.get(Calendar.DAY_OF_WEEK);
	}
	
	public static int getCurrentDayOfMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	public static String getCurrentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA); 
		return sdf.format(new Date());		
	}
	
	/**
	 * 当前日期(时间为00:00:00)距UTC时间为1970-1-1的毫秒值
	 * @return
	 */
	public static long getCurrentDate() {
		Calendar c = Calendar.getInstance(); 
		clearTimeInCalendar(c);
		return c.getTimeInMillis();
	}
	
	/**
	 * 当前时间距当前时区为1970-1-1的毫秒值
	 * @return
	 */
	public static long getCurrentTime() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 时区偏移值
	 * @return
	 */
	public static long getDefaultOffset() {
		return TimeZone.getDefault().getRawOffset();
	}
	
	/**
	 * 当前日期(时间为00:00:00)距UTC时间为1970-1-1的毫秒值,在减去偏移值
	 * @return
	 */
	public static long getCurrentUTCDate() {		
		return (getCurrentDate() - getDefaultOffset());
	}
	
	/**
	 * 当前时间距UTC时间为1970-1-1的毫秒值
	 * @return
	 */
	public static long getCurrentUTCTime() {		
		return (getCurrentTime() - getDefaultOffset());
	}
	
	/**
	 * long型转化为String型
	 * @param millis 当前时区时间距当前时区时间为1970-1-1的毫秒值
	 * @param format
	 * @return
	 */
	public static String long2Date(long millis, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format, Locale.CHINA);
		return df.format(millis);
	}
	
	/**
	 * long型转化为String型
	 * @param millis 当前时区时间距当前时区时间为1970-1-1的毫秒值
	 * @param format
	 * @return
	 */
	public static String long2JSTDate(long millis, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format, Locale.CHINA);
		return df.format(millis - getDefaultOffset() +getJstOffset());
	}
	
	/**
	 * long型转化为String型UTC日期
	 * @param millis 当前时区时间距当前时区时间为1970-1-1的毫秒值
	 * @param utcFormat
	 * @return
	 */
	public static String long2UTCDate(long millis, String utcFormat) {
		SimpleDateFormat df = new SimpleDateFormat(utcFormat, Locale.CHINA);
		return df.format(millis-getDefaultOffset());
	}
	
	/**
	 * 当前时区日期距UTC时间为1970-1-1的毫秒值
	 * @param format
	 * @param date
	 * @return
	 */
	public static long defTizeZonedate2UTCLong(String date, String format) {
		long retValue = 0;
		SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
		try{
			retValue = formatter.parse(date).getTime();
		} catch(Exception e) {
			retValue = 0;
		}
		return retValue;
	}
	
	/**
	 * JST时间转换为long型UTC值
	 * @param jstDate
	 * @param format
	 * @return
	 */
	public static long jstDate2UTCLong(String jstDate, String format) {
		
		return (defTizeZonedate2UTCLong(jstDate, format) + getDefaultOffset() - TimeZone.getTimeZone("GMT+09:00").getRawOffset());		
	}
	
	/**
	 * 得到Jst OffSet
	 * @return
	 */
	public static long getJstOffset() {
		return TimeZone.getTimeZone("GMT+09:00").getRawOffset();
	}
	
	/**
	 * 当前时间距当前时区时间为1970-1-1的毫秒值
	 * @param date
	 * @param format
	 * @return
	 */
	public static long date2Long(String date, String format) {
		return defTizeZonedate2UTCLong(date, format) + getDefaultOffset();
	}
	
	/**
	 * 在当前日期基础上加几天,并返回String类型日期
	 * @param days 加上的天数
	 * @param format 返回类型格式
	 * @return
	 */
	public static String addDateWithCurrentDate(int days, String format) {
		Calendar c = Calendar.getInstance(); 
		c.add(Calendar.DATE, days); 
		SimpleDateFormat df = new SimpleDateFormat(format, Locale.CHINA);
		return df.format(c.getTime());
	}
	
	/**
	 * 在当前日期基础上加几天,并返回距当前时区时间为为1970-1-1的毫秒值
	 * @param days 加上的天数
	 * @return
	 */
	public static long addDateWithCurrentDate(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days);
		clearTimeInCalendar(c);
		return c.getTimeInMillis();
	}
	
	/**
	 * 在指定基础上加几天,并返回距当前时区时间为为1970-1-1的毫秒值
	 * @param days 加上的天数
	 * @param date 指定日期
	 * @return
	 */
	public static long addDate(int days, Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		clearTimeInCalendar(c);
		c.add(Calendar.DATE, days);
		return c.getTimeInMillis();
	}
	
	/**
	 * 在指定基础上加几天,并返回距当前时区时间为为1970-1-1的毫秒值
	 * @param days 加上的天数
	 * @param millis 指定日期
	 * @return
	 */
	public static long addDate(int days, long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		clearTimeInCalendar(c);
		c.add(Calendar.DATE, days);
		return c.getTimeInMillis();
	}
	
	private static void clearTimeInCalendar(Calendar c) {		 
		c.set(Calendar.HOUR_OF_DAY, 0); 
		c.set(Calendar.MINUTE, 0); 
		c.set(Calendar.SECOND, 0);
	}
	
	/**
	 * 把指定格式("yyyy/MM/dd HH:mm:ss")的时间字符串(date)转换为另一种指定的格式("yyyy-MM-dd HH:mm:ss")
	 * @param date
	 * @param oldFormat
	 * @param newFormat
	 * @return
	 */
	public static String string2String(String date,String oldFormat, String newFormat){
	      SimpleDateFormat df = new SimpleDateFormat(oldFormat, Locale.CHINA);
	      try {
                  Date cdate = df.parse(date);
                  df = new SimpleDateFormat(newFormat, Locale.CHINA);
                  return df.format(cdate);
	      } catch (ParseException e) {
                  e.printStackTrace();
            }
	      return null;
	}
	
	public static long addHoursTime(int hours){
	      return getCurrentTime() + hours*60*60*1000;
	}
	
	/**
	 * 获取年份
	 * @param str
	 * @param format
	 * @return
	 */
	public static int getYearFromString(String str, String format) {
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(format, Locale.CHINA);
		try {
			c.setTime(df.parse(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取月份
	 * @param str
	 * @param format
	 * @return
	 */
	public static int getMonthFromString(String str, String format) {
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(format, Locale.CHINA);
		try {
			c.setTime(df.parse(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c.get(Calendar.MONTH);
	}

	/**
	 * 获取日
	 * @param str
	 * @param format
	 * @return
	 */
	public static int getDayFromString(String str, String format) {
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(format, Locale.CHINA);
		try {
			c.setTime(df.parse(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c.get(Calendar.DATE);
	}
	
	/**
	 * 计算间隔日
	 * @param start
	 * @param end
	 * @param format
	 * @return
	 */
	public static long reduceDate(String start, String end, String format) {
		return (DateUtils.date2Long(end, format) - DateUtils.date2Long(start, format)) / MILLIS_FOR_ONE_DAY;
	}
	
	/**
	 * 按格式生成时间
	 * @param year
	 * @param month
	 * @param day
	 * @param format
	 * @return
	 */
	public static String getTimeWithFormat(int hour, int minute, String format) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		return new SimpleDateFormat(format, Locale.CHINA).format(c.getTime());
	}
	
	/**
	 * 按格式生成日期
	 * @param year
	 * @param month
	 * @param day
	 * @param format
	 * @return
	 */
	public static String getDateWithFormat(int year, int month, int day,
			String format) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DATE, day);
		return new SimpleDateFormat(format, Locale.CHINA).format(c.getTime());
	}
	
	/***
	 * 格式化时间,例如:"7月"转换为"07月"
	 * @param num
	 * @return
	 */
	public static String int2String(int num){
	      if(num < 10)
                return "0" + num;
          return "" + num;
	}
	
	/**
	 * 获取下一半小时的时间HH:MM
	 * @param times
	 * @return
	 */
	public static String getNextHalfTime(long times){
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeInMillis(times);
	    int minutes = cal.get(Calendar.MINUTE);
	    if(minutes > 30){
	        cal.add(Calendar.HOUR_OF_DAY, 1);
	        cal.set(Calendar.MINUTE, 0);
	    }else{
	        cal.set(Calendar.MINUTE, 30);
	    }
	    cal.set(Calendar.SECOND, 0);
	    String ret = long2Date(cal.getTimeInMillis(), FORMAT_TIME_HHMM);
	    return ret;
	}
	
	/**
	 * 获取下一小时的时间HH:MM
	 * @param times
	 * @return
	 */
	public static String getNextHourTime(long times){
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeInMillis(times);
	    cal.add(Calendar.HOUR_OF_DAY, 1);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    String ret = long2Date(cal.getTimeInMillis(), FORMAT_TIME_HHMM);
	    return ret;
	}
	
	public static String bars2SlashDateFormat(String barsDate){
	    return string2String(barsDate, FORMAT_DATE_YYMMDD, FORMAT_TIME_YYMMDD);
	}
	
	public static String slash2barsDateFormat(String slashDate){
	    return string2String(slashDate, FORMAT_TIME_YYMMDD, FORMAT_DATE_YYMMDD);
	}
	
	/**
	 * 获取下一年的当前时间
	 * @return
	 */
	public static long getCurrDayOfNextYear(){
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.YEAR, 1);
	    return cal.getTimeInMillis();
	}
	
	/**
	 * 获取下一月的当前时间
	 * @return
	 */
	public static long getDayOfNextMonth(){
	    Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        return cal.getTimeInMillis();
	}
	
	/**
	 * 系统默认是否为JST时区
	 * @return
	 */
	public static boolean isDefJSTTimeZone(){
	    final String JST = "Asia/Tokyo";
	    TimeZone timeZone = TimeZone.getDefault();
	    String ID = timeZone.getID();
	    return ID.equals(JST);
	}
	
	/***
	 * 
	 * getTime:把时间字符串转换为毫秒时间. <br/>
	 * @author maple
	 * @param dateString
	 * @param pattern
	 * @return
	 * @since JDK 1.6
	 */
	public static long getTime(String dateString, String pattern){
	    if (dateString == null || ("").equals(dateString) || pattern == null || ("").equals(pattern)) {
	        return 0;
	    }
	    SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
	    Date date = null;
	    try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
	    return date.getTime();
	}
	
	/***
	 * 
	 * compareDate:比较两个时间的先后. <br/>
	 * @author maple
	 * @param date 指定时间
	 * @param targetDate   比较目标时间
	 * @param style    时间指定格式
	 * @return 1 指定时间大于目标时间,0相等,-1指定时间小于目标时间
	 * @since JDK 1.6
	 */
    public static int compareDate(String date, String targetDate, String style) {
        long time = DateUtils.getTime((String) date, style);
        long targetTime = DateUtils.getTime((String) targetDate, style);
        Log.i("DEBUG", "checkLeaveDate()---> date=" + date + ", targetDate=" + targetDate
                + ", time=" + time + ", targetTime=" + targetTime);
        if (time > targetTime) { return 1; }
        if (time == targetTime) { return 0; }
        return -1;
    }
}
