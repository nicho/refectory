package com.chinarewards.gwt.elt.client.frequency;

import java.util.Date;
import java.util.List;

import com.chinarewards.gwt.elt.client.rewards.model.*;
import com.chinarewards.gwt.elt.util.DateTool;

public class SimplePublishDateCalculator implements PublishDateCalculator {

	@Override
	public Date calPublishDateFromRewardsDate(Date rewardsDate,
			FrequencyClient frequency) {
		// 初始化
		Date publishDate = null;

		if (frequency instanceof DayFrequencyClient) {
			DayFrequencyClient dayFrequency = (DayFrequencyClient) frequency;
			int dayInt = dayFrequency.getInterval();
			publishDate = DateTool.addSomeDay(rewardsDate, -dayInt);

		} else if (frequency instanceof WeekFrequencyClient) {
			WeekFrequencyClient weekFrequency = (WeekFrequencyClient) frequency;
			int weekInt = weekFrequency.getInterval();
			List<Integer> weekDays = weekFrequency.getWeekDays();

			int weekDayInt = DateTool.getWeekDayOfDate(rewardsDate);
			int min = getLastDayInList(weekDayInt, weekDays);
			if (min < weekDayInt) {
				publishDate = DateTool.addSomeDay(rewardsDate,
						(min - weekDayInt));
			} else if (min > weekDayInt) {
				publishDate = DateTool.addSomeDay(rewardsDate,
						-(7 - (min - weekDayInt)));
			} else {
				// 相等的情况
				publishDate = DateTool.addSomeDay(rewardsDate, -weekInt * 7);
			}

		} else if (frequency instanceof MonthFrequencyClient) {
			MonthFrequencyClient monthFrequency = (MonthFrequencyClient) frequency;
			int monthInt = monthFrequency.getInterval();
			int monthDay = monthFrequency.getMonthDay();

			int startYear = rewardsDate.getYear();
			int startMonth = rewardsDate.getMonth();
			int startDay = rewardsDate.getDate();

			int publishYear = startYear;
			// ----减去几个月
			int publishMonth = startMonth - monthInt;
			int publishDay = monthDay;
			// 月份0-11
			if (publishMonth < 1) {
				publishYear = publishYear - 1;
				publishMonth = 12 + publishMonth;
			}
			// --
			// getDaysOfMonth接收的月份是1-12,而这里rewardsMonth:0-11.故加一
			int monthDays = DateTool.getDaysOfMonth(publishYear,
					publishMonth + 1);
			if (publishDay > monthDays) {
				publishDay = monthDays;
			}
			publishDate = new Date(publishYear, publishMonth, publishDay);

		} else if (frequency instanceof YearFrequencyClient) {
			YearFrequencyClient yearFrequency = (YearFrequencyClient) frequency;
			int yearInt = yearFrequency.getInterval();
			int yearMonth = yearFrequency.getYearMonth();
			int yearMonthDay = yearFrequency.getYearMonthDay();

			int startYear = rewardsDate.getYear();

			if (yearMonth > 12) {
				yearMonth = 12;
			}
			int publishYear = startYear - yearInt;
			int publishMonth = yearMonth - 1;
			int publishDay = yearMonthDay;

			// --
			// getDaysOfMonth接收的月份是1-12,而这里publishMonth:0-11.
			int monthDays = DateTool.getDaysOfMonth(publishYear,
					publishMonth + 1);
			if (publishDay > monthDays) {
				publishDay = monthDays;
			}
			publishDate = new Date(publishYear, publishMonth, publishDay);
		}

		// 加一天
		publishDate = DateTool.addSomeDay(publishDate, 1);
		return DateTool.stripTimeComponents(publishDate);
	}

	/**
	 * 今天是星期day（如四）,那如果List中有比4小的则返回比四小的数中最大的那个,否则返回最大的数。
	 * 
	 * @param day
	 * @param weekDays
	 * @return
	 */
	private static int getLastDayInList(int day, List<Integer> weekDays) {
		int near = 1;
		boolean flag = false;
		for (int i : weekDays) {
			if (i < day && i >= near) {
				near = i;
				flag = true;
			}
		}
		if (flag) {
			return near;
		} else {
			return DateTool.getMaxNumber(weekDays);
		}
	}
}
