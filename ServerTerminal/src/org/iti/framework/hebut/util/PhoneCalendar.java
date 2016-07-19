package org.iti.framework.hebut.util;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.iti.framework.hebut.util.DateUtil;

public class PhoneCalendar implements Serializable {

	private static final long serialVersionUID = 8905521264553879692L;
	public static final long MILLISECOND_PER_DAY = DateUtil.TIME_SECOND_PER_DAY
			* DateUtil.TIME_MILLISECOND_PER_SECOND;
	public static final String[] COURSE_TIME = new String[] { "",
			"08:00-09:50", "10:10-12:00", "14:00-15:50", "16:10-18:00",
			"19:00-20:50" };
	private String year;
	private String term;
	private String weeks;
	private String startTime;

	public void setYear(String year) {
		this.year = year;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "PhoneCalendar [year=" + year + ", term=" + term + ", weeks="
				+ weeks + ", startTime=" + startTime + "]";
	}

	// 根据指定日期得到当天处在学期的第几周
	public int getCurrentWeekCount(int currentMonth, int currentDay) {
		String[][] calendar = this.getCalendar();
		String currentDateString = Integer.valueOf(currentMonth).toString()
				+ "_" + Integer.valueOf(currentDay).toString();
		int i = 0;
		_out: for (; i < calendar.length; i++) {
			String[] week = calendar[i];
			for (String day : week) {
				if (day != null && day.trim().equals(currentDateString.trim())) {
					break _out;
				}
			}
		}
		i++;
		if (i > calendar.length)
			i = -1;
		return i;
	}

	public String[] getTermPeriod() {
		String[][] calendar = this.getCalendar();
		String[] start = calendar[1][0].split("_");
		String[] end = calendar[calendar.length - 1][6].split("_");
		String calendarStartTime = this.startTime.split("\\.")[0];
		Calendar cal = Calendar.getInstance();
		Date startDate;
		try {
			startDate = DateUtil.format_1.parse(calendarStartTime);
			cal.setTime(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int startYear = cal.get(Calendar.YEAR);
		int endYear = Integer.parseInt(end[0].trim()) < Integer
				.parseInt(start[0].trim()) ? (startYear + 1) : startYear;
		String startStr = dateTrans(startYear, start);
		String endStr = dateTrans(endYear, end);
		return new String[] { startStr, endStr };
	}

	/**
	 * 返回第week周周day第seq节的开始结束时间的毫秒表示
	 * 
	 * @param week
	 *            第几周
	 * @param day
	 *            周几(1-7)
	 * @param seq
	 *            第几节(1-5)
	 */
	public Date getCourseTime(int week, int day, int seq, String[][] calendar) {
		if (day < 1 || day > 7) {
			throw new IllegalArgumentException("Param day must between 1 and 7");
		}
		if (seq < 1 || seq > 5) {
			throw new IllegalArgumentException("Param day must between 1 and 5");
		}
		String[] weekPeriod = getWeekPeriod(week, calendar);
		String seqTime = COURSE_TIME[seq];
		String startSeqTime = seqTime.split("-")[0] + ":00";
		String start = weekPeriod[0] + "_" + startSeqTime;
		Date date;
		long time = 0;
		try {
			date = DateUtil.format_8.parse(start);
			time = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (int i = 1; i < day; i++) {
			time += MILLISECOND_PER_DAY;
		}
		date = new Date(time);
		return date;
	}

	// 返回某一周的开始日期和结束日期，格式为yy年mm月dd日
	public String[] getWeekPeriod(int week) {
		String[][] calendar = this.getCalendar();
		return getDateInWeek(week, calendar);
	}

	public String[] getDateInWeek(int week, String[][] calendar) {
		if (week > calendar.length)
			return new String[] { "", "" };
		// 取出指定周的开始日期和结束日期（月和日）
		week--;
		if (week == -2) {
			week = 0;
		}
		String[] start = calendar[week][0].split("_");
		String[] end = calendar[week][6].split("_");
		return new String[] { start[0], end[0], start[1], end[1] };
	}

	public String[] getWeekPeriod(int week, String[][] calendar) {
		if (week > calendar.length)
			return new String[] { "", "" };
		// 取出指定周的开始日期和结束日期（月和日）
		week--;
		if (week == -2) {
			week = 0;
		}
		String[] start = calendar[week][0].split("_");
		String[] end = calendar[week][6].split("_");

		// 取出学期的开始日期，转化成毫秒时间并设给一个Calendar对象
		String calendarStartTime = this.startTime.split("\\.")[0];
		Calendar cal = Calendar.getInstance();
		Date startDate;
		try {
			startDate = DateUtil.format_1.parse(calendarStartTime);
			cal.setTime(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 确定年
		int startYear = cal.get(Calendar.YEAR);
		int endYear = Integer.parseInt(end[0].trim()) < Integer
				.parseInt(start[0].trim()) ? (startYear + 1) : startYear;
		String startStr = dateTrans(startYear, start);
		String endStr = dateTrans(endYear, end);
		return new String[] { startStr, endStr };
	}

	private String dateTrans(int year, String[] date) {

		StringBuilder sb = new StringBuilder().append(year).append("年")
				.append(date[0]).append("月").append(date[1]).append("日");
		return sb.toString();
	}

	// 将这一学期的每一天按MM—DD的格式存到数组中
	public String[][] getCalendar() {
		// 取出学期的开始时间
		String calendarStartTime = this.startTime.split("\\.")[0];
		// 取出学期的周数
		int weeks = 20;
		try {
			weeks = Integer.parseInt(this.weeks.trim());
		} catch (Throwable e) {
			weeks = 20;
		}
		// 计算出学期的天数
		int daySum = weeks * 7;
		// 得到一个Calendar对象
		Calendar calendar = Calendar.getInstance();
		// 将这一学期的开始时间转化成毫秒时间
		Date startDate;
		try {
			startDate = DateUtil.format_1.parse(calendarStartTime);
			calendar.setTime(startDate);
			String[][] array = new String[weeks][7];
			int weekCount = 0;
			for (int i = 0; i < daySum; i++) {
				// 设置calendar的时间
				calendar.setTime(startDate);
				int startMonth = calendar.get(Calendar.MONTH) + 1;
				int startDay = calendar.get(Calendar.DATE);
				long startMillions = startDate.getTime() + 24L * 60L * 60L
						* 1000L;
				startDate = new Date(startMillions);
				array[weekCount / 7][i % 7] = new StringBuilder()
						.append(startMonth).append("_").append(startDay)
						.toString();
				weekCount++;
			}
			return array;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * MM月_dd日
	 * 
	 * @return
	 */
	public String[][] _getCalendar() {
		// 取出学期的开始时间
		String calendarStartTime = this.startTime.split("\\.")[0];
		// 取出学期的周数
		int weeks = 20;
		try {
			weeks = Integer.parseInt(this.weeks.trim());
		} catch (Throwable e) {
			weeks = 20;
		}
		// 计算出学期的天数
		int daySum = weeks * 7;
		// 得到一个Calendar对象
		Calendar calendar = Calendar.getInstance();
		// 将这一学期的开始时间转化成毫秒时间
		Date startDate;
		try {
			startDate = DateUtil.format_1.parse(calendarStartTime);
			calendar.setTime(startDate);
			String[][] array = new String[weeks][7];
			int weekCount = 0;
			for (int i = 0; i < daySum; i++) {
				// 设置calendar的时间
				calendar.setTime(startDate);
				int startMonth = calendar.get(Calendar.MONTH) + 1;
				int startDay = calendar.get(Calendar.DATE);
				long startMillions = startDate.getTime() + 24L * 60L * 60L
						* 1000L;
				startDate = new Date(startMillions);
				array[weekCount / 7][i % 7] = new StringBuilder()
						.append(startMonth < 10 ? "0" : "").append(startMonth)
						.append("_").append(startDay < 10 ? "0" : "")
						.append(startDay).toString();
				weekCount++;
			}
			return array;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}
}
