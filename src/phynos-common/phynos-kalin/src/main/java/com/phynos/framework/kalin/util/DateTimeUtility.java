package com.phynos.framework.kalin.util;

import java.util.Calendar;

public class DateTimeUtility {
	
	public static java.util.Date minRoundDate(java.util.Date date){
		if(date == null)
			return null;
		
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String text = format.format(date);
		
		try{
			return format.parse(text);
		}catch(Exception ex){
			return date;
		}
	}
	
	public static java.util.Date maxRoundDate(java.util.Date date){
		if(date == null)
			return null;
		
		return minRoundDate(new java.util.Date(date.getTime() + 24 * 60 * 60 * 1000));
	}
	
	public static java.util.Date addDays(java.util.Date date, int value){
		
		return new java.util.Date(date.getTime() + value * 24 * 60 * 60 * 1000);
	}
	
	public static java.util.Date addMonths(java.util.Date date, int value){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		month += value;
		year += month / 12;
		month %= 12;
		c.set(year, month, day);
		
		return c.getTime();
	}
	
	public static void main(String[] args) throws Exception {
		String strDate = "2014-01-21";
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = format.parse(strDate);
		System.out.println(format.format(date));
		date = addDays(date, 11);
		System.out.println(format.format(date));
	}
}

