package com.chinarewards.elt.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.chinarewards.elt.model.reward.frequency.WeekConstComparator;
import com.chinarewards.elt.model.reward.frequency.WeekDays;

public class WeekUtil {

	/**
	 * 获取今天是星期几~~{@link WeekDays}
	 * 
	 * @param date
	 * @return
	 * @author roger
	 */
	public static WeekDays getCurrTimeWeek(Date date) {
		WeekDays res = null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		switch (c.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			res = WeekDays.SUN;
			break;
		case Calendar.MONDAY:
			res = WeekDays.MON;
			break;
		case Calendar.TUESDAY:
			res = WeekDays.TUS;
			break;
		case Calendar.WEDNESDAY:
			res = WeekDays.WEN;
			break;
		case Calendar.THURSDAY:
			res = WeekDays.THUR;
			break;
		case Calendar.FRIDAY:
			res = WeekDays.FRI;
			break;
		case Calendar.SATURDAY:
			res = WeekDays.SAT;
			break;
		}
		return res;
	}

	/**
	 * Sort the weekdays such that Monday is the first element, Sunday is the
	 * last.
	 * 
	 * @param weekdays
	 * @return
	 */
	public static final WeekDays[] sort(WeekDays[] weekdays) {

		List<WeekDays> l = Arrays.asList(weekdays);
		WeekDays[] ret = l.toArray(new WeekDays[0]);
		Arrays.sort(ret, new WeekConstComparator());
		return ret;

	}

}
