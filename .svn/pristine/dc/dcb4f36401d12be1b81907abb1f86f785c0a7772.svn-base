package com.nuite.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {


	/**
	 * 获取x天之后（负数表示之前）的开始日期（0点）
	 */
	public static String getDateDiffBeginDate(int x) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, x);// 今天+x天
        Date newDate = cal.getTime();
		String newDateStr = sdf.format(newDate);
		return newDateStr;
	}
	
	
	
	/**
	 * 获取从某天开始，x天之后（负数表示之前）的开始日期（0点）
	 */
	public static String getDateDiffBeginDateFromDate(int x, Date beginDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
        cal.add(Calendar.DAY_OF_MONTH, x);// 开始日期+x天
        Date newDate = cal.getTime();
		String newDateStr = sdf.format(newDate);
		return newDateStr;
	}
	
	
	
	/**
	* 获取 x周后（负数表示前）周一时间
	* @return
	*/
	public static String getWeekDiffMonDate(int x){
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周开始的第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.WEEK_OF_MONTH, cal.get(Calendar.WEEK_OF_MONTH) + x);
		Date mondayDate = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(mondayDate);
	}
	
	
	
	/**
	* 获取从某天开始， x周后（负数表示前）周一时间
	* @return
	*/
	public static String getWeekDiffMonDateFromDate(int x, Date beginDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		cal.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周开始的第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.WEEK_OF_MONTH, cal.get(Calendar.WEEK_OF_MONTH) + x);
		Date mondayDate = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(mondayDate);
	}
	
	
	
	/**
	 * 获取日期所在年份+周数
	 * @return
	 */
	public static String getDateYearAndWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置星期一为一周开始的第一天
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		if(month == 11 && week == 1) {
			year++;
		}
		return year + "." + week;
	}
	
	
	
	
	/**
	* 得到本周周一日期(时分秒为0)
	* @return
	*/
	public static Date getWeekMonDate(){
		try {
			Calendar cal = Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周开始的第一天
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			Date mondayDate = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(sdf.format(mondayDate));
		} catch (ParseException e) {
			return null;
		}
	}
	
	
	
	/**
	* 获取本月1日日期(时分秒为0)
	* @return
	*/
	public static Date getMonthFirstDate() {
		try {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, 1);  // 设置为1号
			Date monthDate = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(sdf.format(monthDate));
		} catch (ParseException e) {
			return null;
		}

	}

	
	
	/**
	* 获取本月1日日期String(时分秒为0)
	* @return
	*/
	public static String getMonthFirstDateStr() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);  // 设置为1号
		Date monthDate = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(monthDate);
	}
	
	
	
	/**
	* 获取今年1月1日日期String(时分秒为0)
	* @return
	*/
	public static String getYearFirstDateStr() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 0);  // 设置为1月
		cal.set(Calendar.DAY_OF_MONTH, 1);  // 设置为1号
		Date monthDate = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(monthDate);
	}
	
}
