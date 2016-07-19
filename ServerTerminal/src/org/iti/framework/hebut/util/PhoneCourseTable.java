package org.iti.framework.hebut.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iti.framework.hebut.util.DateUtil;

public class PhoneCourseTable implements Serializable {

	/**
	 * 一个PhoneCourseTable对象中有一个List<PhoneCalendar>,该List中只有一个PhoneCalendar对象
	 * 有一个变量role表明身份是学生还是教师，一个变量year存放年份，一个变量term存放学期
	 * 还有一个List<Course>，这个List中存放Course对象
	 */
	private static final long serialVersionUID = -4382525121959869941L;
	public static final String ALARM_SETUP_REGEX = "\\d*\\d_\\d*\\d";
	public static final String ROLE_STUDENT = "student";
	public static final String ROLE_TEACHER = "teacher";
	private List<PhoneCalendar> calendarList;
	private String role;
	private String year;
	private String term;
	private List<Course> courseList;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public static Course newCourseinstance(String name, String code) {
		Course course = new Course();
		return course;
	}

	public static class Course implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4417820035159571399L;

		public static class Alarm implements Serializable {

			/**
			 * 
			 */
			private static final long serialVersionUID = 91730167846483515L;
			private List<Long> alarms;
			private boolean isEffective = false;
			private String setupAlarm;

			public List<Long> getAlarms() {
				return alarms;
			}

			public void setAlarms(List<Long> alarms) {
				this.alarms = alarms;
			}

			public boolean isEffective() {
				return isEffective;
			}

			public void setEffective(boolean isEffective) {
				this.isEffective = isEffective;
			}

			public String getSetupAlarm() {
				return setupAlarm;
			}

			public void setSetupAlarm(String setupAlarm) {
				this.setupAlarm = setupAlarm;
			}

			private void _setupAlarm(int hour, int minute) {
				this.setupAlarm = new StringBuilder().append(hour).append("_")
						.append(minute).toString();
			}

			public boolean isNeedAlarm(long time) {
				if (alarms != null && alarms.size() > 0) {
					long first = alarms.get(0);
					long last = alarms.get(alarms.size() - 1);
					boolean value1 = first <= (time + 5 * DateUtil.TIME_MILLISECOND_PER_SECOND);
					boolean value2 = last >= (time - 5 * DateUtil.TIME_MILLISECOND_PER_SECOND);
					if (value1 && value2) {
						boolean isNeed = false;
						for (long alarm : alarms) {
							long x = Math.abs(alarm - time);
							if (x <= 5 * DateUtil.TIME_MILLISECOND_PER_SECOND) {
								isNeed = true;
								break;
							}
						}
						return isNeed;
					} else
						return false;
				}
				return false;
			}

			public boolean isNeedAlarm() {
				long current = System.currentTimeMillis();
				if (alarms != null && alarms.size() > 0) {
					long lastAlarm = alarms.get(alarms.size() - 1);
					return Math.abs(lastAlarm - current) <= 5 * DateUtil.TIME_MILLISECOND_PER_SECOND;
				}
				return false;
			}

			@Override
			public String toString() {
				return "Alarm ["
						+ (alarms != null ? "alarms=" + alarms + ", " : "")
						+ "isEffective="
						+ isEffective
						+ ", "
						+ (setupAlarm != null ? "setupAlarm=" + setupAlarm : "")
						+ "]";
			}

		}

		public enum Type {
			HEBUT {
				@Override
				public String getName() {
					return "本一";
				}
			},
			HEBUT_COLLAGE {
				@Override
				public String getName() {
					return "本三";
				}
			},
			FRANCE {
				@Override
				public String getName() {
					return "中法班";
				}
			};
			public abstract String getName();
		}

		@Override
		// 实现course类的hashCode()方法；
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((allClass == null) ? 0 : allClass.hashCode());
			result = prime * result
					+ ((courseAddress == null) ? 0 : courseAddress.hashCode());
			result = prime * result
					+ ((courseName == null) ? 0 : courseName.hashCode());
			result = prime * result
					+ ((teacherName == null) ? 0 : teacherName.hashCode());
			result = prime * result + ((time == null) ? 0 : time.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			result = prime * result + ((week == null) ? 0 : week.hashCode());
			return result;
		}

		@Override
		// 实现Course类的equals()方法
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Course other = (Course) obj;
			if (allClass == null) {
				if (other.allClass != null)
					return false;
			} else if (!allClass.equals(other.allClass))
				return false;
			if (courseAddress == null) {
				if (other.courseAddress != null)
					return false;
			} else if (!courseAddress.equals(other.courseAddress))
				return false;
			if (courseName == null) {
				if (other.courseName != null)
					return false;
			} else if (!courseName.equals(other.courseName))
				return false;
			if (teacherName == null) {
				if (other.teacherName != null)
					return false;
			} else if (!teacherName.equals(other.teacherName))
				return false;
			if (time == null) {
				if (other.time != null)
					return false;
			} else if (!time.equals(other.time))
				return false;
			if (type != other.type)
				return false;
			if (week == null) {
				if (other.week != null)
					return false;
			} else if (!week.equals(other.week))
				return false;
			return true;
		}

		private String time; // 上课时间,可以用来生成课表的2维数组
		private String courseName;
		private String teacherName; // 教师此项无值，为空
		private String week; // 上课周数
		private String courseAddress;
		private String allClass; // 只有教师的此字段才有值，学生为空
		private Type type;// true:本一,false:本三
		private Alarm alarm;

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getCourseName() {
			return courseName;
		}

		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}

		public String getTeacherName() {
			return teacherName;
		}

		public void setTeacherName(String teacherName) {
			this.teacherName = teacherName;
		}

		public String getWeek() {
			return week;
		}

		public void setWeek(String week) {
			this.week = week;
		}

		public String getCourseAddress() {
			return courseAddress;
		}

		public void setCourseAddress(String courseAddress) {
			this.courseAddress = courseAddress;
		}

		public String getAllClass() {
			return allClass;
		}

		public void setAllClass(String allClass) {
			this.allClass = allClass;
		}

		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}

		public Alarm getAlarm() {
			return alarm;
		}

		public void setAlarm(Alarm alarm) {
			this.alarm = alarm;
		}

		public String getKey() {
			String key = new StringBuilder().append(this.courseName)
					.append(this.week).append(this.time).toString();
			return key;
		}

		/**
		 * 根据time的值确定上课时间，周几第几节
		 * 
		 * @return
		 */
		public int[] getCourseTime() {
			if (this.time == null || this.time.trim().equals(""))
				return new int[] { -1, -1 };
			try {
				char[] time = this.time.toCharArray();
				// 字符跟整型数运算得到的结果也是整型
				int[] result = new int[] { time[0] == '日' ? 7 : (time[0] - 48),
						time[1] - 48 };
				return result;
			} catch (Throwable e) {
				e.printStackTrace();
				return new int[] { -1, -1 };
			}
		}

		public void setupAlarm(long advance, PhoneCalendar calendar) {
			List<Long> courseTimes = getCourseTimes(calendar);
			List<Long> alarms = new ArrayList<Long>();
			for (long courseTime : courseTimes)
				alarms.add(courseTime - advance);
			if (this.alarm == null) {
				this.alarm = new Alarm();
			}
			this.alarm.setAlarms(alarms);
		}

		public void setupAlarmShow(int hour, int minute) {
			this.alarm._setupAlarm(hour, minute);
		}

		public List<Long> getCourseTimes(PhoneCalendar calendar) {
			List<Long> times = new ArrayList<Long>();
			String[] weekPeriods = this.getWeek().split(",");
			for (int i = 0; i < weekPeriods.length - 1; i++) {
				String[] weekPeriod = weekPeriods[i].split("-");
				Integer start = 0;
				Integer end = 20;
				try {
					start = Integer.parseInt(weekPeriod[0].trim());
					end = weekPeriod.length == 1 ? start : Integer
							.parseInt(weekPeriod[1].trim());
				} catch (Throwable e) {
					e.printStackTrace();
					start = 0;
					end = 20;
				}
				int[] time = getCourseTime();
				for (Integer j = start; j <= end; j++) {
					Date date = calendar.getCourseTime(j, time[0], time[1],
							calendar._getCalendar());
					times.add(date.getTime());
				}
			}
			return times;
		}

		@Override
		public String toString() {
			return "Course ["
					+ (time != null ? "time=" + time + ", " : "")
					+ (courseName != null ? "courseName=" + courseName + ", "
							: "")
					+ (teacherName != null ? "teacherName=" + teacherName
							+ ", " : "")
					+ (week != null ? "week=" + week + ", " : "")
					+ (courseAddress != null ? "courseAddress=" + courseAddress
							+ ", " : "")
					+ (allClass != null ? "allClass=" + allClass + ", " : "")
					+ (type != null ? "type=" + type + ", " : "")
					+ (alarm != null ? "alarm=" + alarm : "") + "]";
		}

	}

	public List<PhoneCalendar> getCalendarList() {
		return calendarList;
	}

	public void setCalendarList(List<PhoneCalendar> calendarList) {
		this.calendarList = calendarList;
	}

	public Map<Integer, Map<Integer, Course[]>> mapWeekMap() {
		Map<Integer, Map<Integer, Course[]>> weekMap = new HashMap<Integer, Map<Integer, Course[]>>();
		for (Course course : this.getCourseList()) {

			String[] weekPeriods = course.getWeek().split(",");
			for (int i = 0; i < weekPeriods.length - 1; i++) {
				String[] weekPeriod = weekPeriods[i].split("-");
				Integer start = 0;
				Integer end = 20;
				try {
					start = Integer.parseInt(weekPeriod[0].trim());
					end = weekPeriod.length == 1 ? start : Integer
							.parseInt(weekPeriod[1].trim());
				} catch (Throwable e) {
					String noNum = weekPeriod[1].trim().replaceFirst("\\d\\d*",
							"");
					String num = weekPeriod[1].trim().replaceFirst(noNum, "");
					try {
						end = weekPeriod.length == 1 ? start : Integer
								.parseInt(num.trim());
					} catch (Throwable e1) {
						e.printStackTrace();
						start = 0;
						end = 20;
					}

				}
				for (Integer j = start; j <= end; j++) {
					if (!weekMap.containsKey(j)) {
						Map<Integer, Course[]> courses = new HashMap<Integer, Course[]>();
						weekMap.put(j, courses);
					}
					Map<Integer, Course[]> courses = weekMap.get(j);
					int w = 1;
					int seq = 1;
					try {
						char[] time = course.getTime().toCharArray();
						w = time[0] - 48;
						seq = time[1] - 48;
					} catch (Throwable e) {
						w = 1;
						seq = 1;
					}

					if (courses.get(w) == null) {
						Course[] coursePerD = new Course[8];
						courses.put(w, coursePerD);
					}
					Course[] coursePerD = courses.get(w);
					coursePerD[seq] = course;
					courses.put(w, coursePerD);
					weekMap.put(j, courses);
				}
			}
		}
		return weekMap;
	}

	@Override
	public String toString() {
		return "PhoneCourseTable ["
				+ (calendarList != null ? "calendarList=" + calendarList + ", "
						: "") + (role != null ? "role=" + role + ", " : "")
				+ (year != null ? "year=" + year + ", " : "")
				+ (term != null ? "term=" + term + ", " : "")
				+ (courseList != null ? "courseList=" + courseList : "") + "]";
	}

}
