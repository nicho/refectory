package com.chinarewards.gwt.elt.client.frequency;

import java.util.Date;
import java.util.List;


import com.chinarewards.gwt.elt.client.rewards.model.*;
import com.chinarewards.gwt.elt.util.DateTool;


/**
 * Calculate the next reward time. The default policy of the return date is
 * started from the given date.
 * 
 * @author yanxin
 * @since 0.2.0
 */
public class NextRewardsDateCalculatorNoRewardedBefore implements
		NextRewardsDateCalculator {

	public Date calNextRewardsDate(Date startTime, FrequencyClient frequency) {

		// Initial day will be today.
		Date rewardsDate = new Date(startTime.getTime());

		if (frequency instanceof DayFrequencyClient) {
			// do nothing!
		} else if (frequency instanceof WeekFrequencyClient) {
			WeekFrequencyClient weekFrequency = (WeekFrequencyClient) frequency;
			List<Integer> weekDays = weekFrequency.getWeekDays();

			int weekDayInt = DateTool.getWeekDayOfDate(startTime);

			if (weekDays.contains(weekDayInt)) {
				return startTime;
			}

			int min = getNextDayInList(weekDayInt, weekDays);
			if (min < weekDayInt) {
				rewardsDate = DateTool.addSomeDay(startTime,
						(7 + min - weekDayInt));
			} else if (min > weekDayInt) {
				rewardsDate = DateTool
						.addSomeDay(startTime, (min - weekDayInt));
			} else {
				// 相等的情况
				// rewardsDate = DateTool.addSomeDay(startTime, 7);
				rewardsDate = startTime;
			}

		} else if (frequency instanceof MonthFrequencyClient) {
			MonthFrequencyClient monthFrequency = (MonthFrequencyClient) frequency;
			int monthDay = monthFrequency.getMonthDay();

			int startYear = startTime.getYear();
			int startMonth = startTime.getMonth();
			int startDay = startTime.getDate();

			int rewardsYear = startYear;
			int rewardsMonth = startMonth;
			int rewardsDay = monthDay;
			// --
			if (startDay >= monthDay) {
				// ----加上1个月
				rewardsMonth = rewardsMonth + 1;
				// 月份0-11
				if (rewardsMonth > 11) {
					rewardsYear = rewardsYear + 1;
					rewardsMonth = rewardsMonth - 12;
				}
				// --
			}
			// getDaysOfMonth接收的月份是1-12,而这里rewardsMonth:0-11.故加一
			int monthDays = DateTool.getDaysOfMonth(rewardsYear,
					rewardsMonth + 1);
			if (rewardsDay > monthDays) {
				rewardsDay = monthDays;
			}
			rewardsDate = new Date(rewardsYear, rewardsMonth, rewardsDay);
		} else if (frequency instanceof YearFrequencyClient) {
			YearFrequencyClient yearFrequency = (YearFrequencyClient) frequency;
			int yearInt = yearFrequency.getInterval();
			int yearMonth = yearFrequency.getYearMonth();
			int yearMonthDay = yearFrequency.getYearMonthDay();

			int startYear = startTime.getYear();
			int startMonth = startTime.getMonth();	// 0 based
			int startDay = startTime.getDate();

			int rewardsYear = startYear;
			int rewardsMonth = yearMonth - 1;	// make it 0-based as well.

			if (startMonth > rewardsMonth
					|| (startMonth == rewardsMonth && yearMonthDay < startDay)) {
				rewardsYear = rewardsYear + 1;
			}
			int monthDays = DateTool.getDaysOfMonth(rewardsYear,
					rewardsMonth + 1);
			int rewardsDay = yearMonthDay;
			if (rewardsDay > monthDays) {
				rewardsDay = monthDays;
			}
			rewardsDate = new Date(rewardsYear, rewardsMonth, rewardsDay);
		}

		return DateTool.stripTimeComponents(rewardsDate);
	}

	/**
	 * 今天是星期day（如四）,那如果List中有比4大的则返回比四大的数中最小的那个,否则返回最小的数。
	 * 
	 * @param day
	 * @param weekDays
	 * @return
	 */
	private int getNextDayInList(int day, List<Integer> weekDays) {
		int near = 7;
		boolean flag = false;
		for (int i : weekDays) {
			if (i > day && i <= near) {
				near = i;
				flag = true;
			}
		}
		if (flag) {
			return near;
		} else {
			return DateTool.getMinNumber(weekDays);
		}
	}
}
