package org.iti.framework.hebut.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final long TIME_MILLISECOND_PER_SECOND = 1000L;
	public static final long TIME_SECOND_PER_SECOND = 1L;
	public static final long TIME_SECOND_PER_MINUTE = 60L * TIME_SECOND_PER_SECOND;
	public static final long TIME_SECOND_PER_HOUR = 60L * TIME_SECOND_PER_MINUTE;
	public static final long TIME_SECOND_PER_DAY = 24L * TIME_SECOND_PER_HOUR;
	public static final SimpleDateFormat format_1 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat format_2 = new SimpleDateFormat(
			"yyyy/MM/dd");
	public static final SimpleDateFormat format_3 = new SimpleDateFormat(
			"HH:mm:ss");
	public static final SimpleDateFormat format_4 = new SimpleDateFormat(
			"yyyy年MM月dd日　HH时mm分ss秒");
	public static final SimpleDateFormat format_5 = new SimpleDateFormat(
			"yyyy年MM月dd日");
	public static final SimpleDateFormat format_6 = new SimpleDateFormat(
			"HH时mm分ss秒");
	public static final SimpleDateFormat format_7 = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	public static final SimpleDateFormat format_8 = new SimpleDateFormat(
			"yyyy年MM月dd日_HH:mm:ss");
	public static final SimpleDateFormat format_9 = new SimpleDateFormat(
			"MM月dd日");
	public static final SimpleDateFormat format_10 = new SimpleDateFormat(
			"MM-dd HH:mm");

	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static Date str2Date(String str) {
		return str2Date(str, null);
	}

	public static Date str2Date(String str, String format) {
		if (str == null || str.length() == 0) {
			return null;
		}
		if (format == null || format.length() == 0) {
			format = FORMAT;
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Calendar str2Calendar(String str) {
		return str2Calendar(str, null);

	}

	public static int[] getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		int[] date = new int[3];
		date[0] = calendar.get(Calendar.YEAR);
		date[1] = calendar.get(Calendar.MONTH) + 1;
		date[2] = calendar.get(Calendar.DATE);
		return date;
	}

	public static final String CHINESE_WEEK = "星期";

	public static final String[] CHINESE_NUMBER = { "零", "一", "二", "三", "四",
			"五", "六", "七", "八", "九" };
	public static final String[] CHINESE_WEEK_NAME = new String[] {
			CHINESE_WEEK + "日", CHINESE_WEEK + CHINESE_NUMBER[1],
			CHINESE_WEEK + CHINESE_NUMBER[2], CHINESE_WEEK + CHINESE_NUMBER[3],
			CHINESE_WEEK + CHINESE_NUMBER[4], CHINESE_WEEK + CHINESE_NUMBER[5],
			CHINESE_WEEK + CHINESE_NUMBER[6], };

	public static int getCurrentWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return w < 0 ? 0 : w;
	}

	public static String getChineseCurrentWeek() {
		return CHINESE_WEEK_NAME[getCurrentWeek()];
	}

	public static String getChineseWeek(int week) {
		return CHINESE_WEEK_NAME[week];
	}

	public static Calendar str2Calendar(String str, String format) {

		Date date = str2Date(str, format);
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	public static String date2Str(Calendar c) {
		return date2Str(c, null);
	}

	public static String date2Str(Calendar c, String format) {
		if (c == null) {
			return null;
		}
		return date2Str(c.getTime(), format);
	}

	public static String date2Str(Date d) {
		return date2Str(d, null);
	}

	public static String date2Str(Date d, String format) {
		if (d == null) {
			return null;
		}
		if (format == null || format.length() == 0) {
			format = FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String s = sdf.format(d);
		return s;
	}

	/**
	 * 获取当前日期的字符串格式
	 * 
	 * @return
	 */
	public static String getCurDateStr() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DAY_OF_MONTH) + "-"
				+ c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE)
				+ ":" + c.get(Calendar.SECOND);
	}

	/**
	 * 获得当前日期的字符串格式，指定format
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurDateStr(String format) {
		Calendar c = Calendar.getInstance();
		return date2Str(c, format);
	}

	/**
	 * 输入长整型时间，输出日期字符串
	 * 
	 * @param format
	 * @param time
	 * @return
	 */
	public static String timeToDateStr(SimpleDateFormat format, long time) {
		return format.format(time);
	}

}
