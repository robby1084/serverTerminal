package org.iti.framework.hebut.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	public static long ONE_DAY = 1000L * 60L * 60L * 24L;
	public static long TWO_DAY = 1000L * 60L * 60L * 24L * 2L;
	public static long THREE_DAY = 1000L * 60L * 60L * 24L * 3L;
	public static long FOUR_DAY = 1000L * 60L * 60L * 24L * 4L;
	public static long FIVE_DAY = 1000L * 60L * 60L * 24L * 5L;

	public static String getTimeStr(long time) {

		long timeT = System.currentTimeMillis() - time;
		if (timeT > FIVE_DAY) {
			return DateUtil.format_10.format(new Date(time));
		} else if (timeT > FOUR_DAY) {
			return "4天前";
		} else if (timeT > THREE_DAY) {
			return "3天前";
		} else if (timeT > TWO_DAY) {
			return "前天";
		} else if (timeT > ONE_DAY) {
			return "昨天";
		} else if (timeT > 1000L * 60L * 60L * 23L) {
			return "23小时前";
		} else if (timeT > 1000L * 60L * 60L * 22L) {
			return "22小时前";
		} else if (timeT > 1000L * 60L * 60L * 21L) {
			return "21小时前";
		} else if (timeT > 1000L * 60L * 60L * 20L) {
			return "20小时前";
		} else if (timeT > 1000L * 60L * 60L * 19L) {
			return "19小时前";
		} else if (timeT > 1000L * 60L * 60L * 18L) {
			return "18小时前";
		} else if (timeT > 1000L * 60L * 60L * 17L) {
			return "17小时前";
		} else if (timeT > 1000L * 60L * 60L * 16L) {
			return "16小时前";
		} else if (timeT > 1000L * 60L * 60L * 15L) {
			return "15小时前";
		} else if (timeT > 1000L * 60L * 60L * 14L) {
			return "14小时前";
		} else if (timeT > 1000L * 60L * 60L * 13L) {
			return "13小时前";
		} else if (timeT > 1000L * 60L * 60L * 12L) {
			return "12小时前";
		} else if (timeT > 1000L * 60L * 60L * 11L) {
			return "11小时前";
		} else if (timeT > 1000L * 60L * 60L * 10L) {
			return "10小时前";
		} else if (timeT > 1000L * 60L * 60L * 9L) {
			return "9小时前";
		} else if (timeT > 1000L * 60L * 60L * 8L) {
			return "8小时前";
		} else if (timeT > 1000L * 60L * 60L * 7L) {
			return "7小时前";
		} else if (timeT > 1000L * 60L * 60L * 6L) {
			return "6小时前";
		} else if (timeT > 1000L * 60L * 60L * 5L) {
			return "5小时前";
		} else if (timeT > 1000L * 60L * 60L * 4L) {
			return "4小时前";
		} else if (timeT > 1000L * 60L * 60L * 3L) {
			return "3小时前";
		} else if (timeT > 1000L * 60L * 60L * 2L) {
			return "2小时前";
		} else if (timeT > 1000L * 60L * 60L * 1L) {
			return "1小时前";
		} else if (timeT > 1000L * 60L * 59L) {
			return "59分钟前";
		} else if (timeT > 1000L * 60L * 58L) {
			return "58分钟前";
		} else if (timeT > 1000L * 60L * 57L) {
			return "57分钟前";
		} else if (timeT > 1000L * 60L * 56L) {
			return "56分钟前";
		} else if (timeT > 1000L * 60L * 55L) {
			return "55分钟前";
		} else if (timeT > 1000L * 60L * 54L) {
			return "54分钟前";
		} else if (timeT > 1000L * 60L * 53L) {
			return "53分钟前";
		} else if (timeT > 1000L * 60L * 52L) {
			return "52分钟前";
		} else if (timeT > 1000L * 60L * 51L) {
			return "51分钟前";
		} else if (timeT > 1000L * 60L * 50L) {
			return "50分钟前";
		} else if (timeT > 1000L * 60L * 49L) {
			return "49分钟前";
		} else if (timeT > 1000L * 60L * 48L) {
			return "48分钟前";
		} else if (timeT > 1000L * 60L * 47L) {
			return "47分钟前";
		} else if (timeT > 1000L * 60L * 46L) {
			return "46分钟前";
		} else if (timeT > 1000L * 60L * 45L) {
			return "45分钟前";
		} else if (timeT > 1000L * 60L * 44L) {
			return "44分钟前";
		} else if (timeT > 1000L * 60L * 43L) {
			return "43分钟前";
		} else if (timeT > 1000L * 60L * 42L) {
			return "42分钟前";
		} else if (timeT > 1000L * 60L * 41L) {
			return "41分钟前";
		} else if (timeT > 1000L * 60L * 40L) {
			return "40分钟前";
		} else if (timeT > 1000L * 60L * 39L) {
			return "39分钟前";
		} else if (timeT > 1000L * 60L * 38L) {
			return "38分钟前";
		} else if (timeT > 1000L * 60L * 37L) {
			return "37分钟前";
		} else if (timeT > 1000L * 60L * 36L) {
			return "36分钟前";
		} else if (timeT > 1000L * 60L * 35L) {
			return "35分钟前";
		} else if (timeT > 1000L * 60L * 34L) {
			return "34分钟前";
		} else if (timeT > 1000L * 60L * 33L) {
			return "33分钟前";
		} else if (timeT > 1000L * 60L * 32L) {
			return "32分钟前";
		} else if (timeT > 1000L * 60L * 31L) {
			return "31分钟前";
		} else if (timeT > 1000L * 60L * 30L) {
			return "30分钟前";
		} else if (timeT > 1000L * 60L * 29L) {
			return "29分钟前";
		} else if (timeT > 1000L * 60L * 28L) {
			return "28分钟前";
		} else if (timeT > 1000L * 60L * 27L) {
			return "27分钟前";
		} else if (timeT > 1000L * 60L * 26L) {
			return "26分钟前";
		} else if (timeT > 1000L * 60L * 25L) {
			return "25分钟前";
		} else if (timeT > 1000L * 60L * 24L) {
			return "24分钟前";
		} else if (timeT > 1000L * 60L * 23L) {
			return "23分钟前";
		} else if (timeT > 1000L * 60L * 22L) {
			return "22分钟前";
		} else if (timeT > 1000L * 60L * 21L) {
			return "21分钟前";
		} else if (timeT > 1000L * 60L * 20L) {
			return "20分钟前";
		} else if (timeT > 1000L * 60L * 19L) {
			return "19分钟前";
		} else if (timeT > 1000L * 60L * 18L) {
			return "18分钟前";
		} else if (timeT > 1000L * 60L * 17L) {
			return "17分钟前";
		} else if (timeT > 1000L * 60L * 16L) {
			return "16分钟前";
		} else if (timeT > 1000L * 60L * 15L) {
			return "15分钟前";
		} else if (timeT > 1000L * 60L * 14L) {
			return "14分钟前";
		} else if (timeT > 1000L * 60L * 13L) {
			return "13分钟前";
		} else if (timeT > 1000L * 60L * 12L) {
			return "12分钟前";
		} else if (timeT > 1000L * 60L * 11L) {
			return "11分钟前";
		} else if (timeT > 1000L * 60L * 10L) {
			return "10分钟前";
		} else if (timeT > 1000L * 60L * 9L) {
			return "9分钟前";
		} else if (timeT > 1000L * 60L * 8L) {
			return "8分钟前";
		} else if (timeT > 1000L * 60L * 7L) {
			return "7分钟前";
		} else if (timeT > 1000L * 60L * 6L) {
			return "6分钟前";
		} else if (timeT > 1000L * 60L * 5L) {
			return "5分钟前";
		} else if (timeT > 1000L * 60L * 4L) {
			return "4分钟前";
		} else if (timeT > 1000L * 60L * 3L) {
			return "3分钟前";
		} else if (timeT > 1000L * 60L * 2L) {
			return "2分钟前";
		} else if (timeT > 1000L * 60L * 1L) {
			return "1分钟前";
		} else {
			return "刚刚";
		}
	}

	public static String getTimeStr(long time, SimpleDateFormat format) {
		long timeLag = System.currentTimeMillis() - time;
		long time_ = System.currentTimeMillis() % ONE_DAY;
		if (timeLag < time_) {
			return "今天";
		} else if (timeLag < time_ + ONE_DAY) {
			return "昨天";
		} else if (timeLag < time_ + TWO_DAY) {
			return "前天";
		} else {
			return format.format(new Date(time));
		}
	}
}
