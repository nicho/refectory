package com.chinarewards.gwt.elt.client.frequency;

import java.util.Collections;
import java.util.List;

import com.chinarewards.gwt.elt.client.rewards.model.DayFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.MonthFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.WeekFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.YearFrequencyClient;

public class FrequencyCalculator {

	private static String[] weeks = { "一", "二", "三", "四", "五", "六", "日" };

	public FrequencyCalculator() {
	}

	private static String getWeekCharFromInt(int i) {
		if (i > 7 && i < 1) {
			return "";
		}
		return weeks[i - 1];
	}

	public static String getTextFromFrequency(FrequencyClient frequency) {
		StringBuilder sb = new StringBuilder();
		if (frequency instanceof DayFrequencyClient) {
			DayFrequencyClient dayFrequency = (DayFrequencyClient) frequency;
			sb.append("每").append(dayFrequency.getInterval()).append("天");
			sb.append("一次");
		} else if (frequency instanceof WeekFrequencyClient) {
			WeekFrequencyClient weekFrequency = (WeekFrequencyClient) frequency;
			sb.append("每").append(weekFrequency.getInterval()).append("个星期");
			if (weekFrequency.getWeekDays() != null
					&& !weekFrequency.getWeekDays().isEmpty()) {
				List<Integer> weekDays = weekFrequency.getWeekDays();
				Collections.sort(weekDays);
				sb.append("的  ");
				for (int i : weekDays) {
					sb.append("星期").append(getWeekCharFromInt(i)).append(" ");
				}
			}
		} else if (frequency instanceof MonthFrequencyClient) {
			MonthFrequencyClient monthFrequency = (MonthFrequencyClient) frequency;
			sb.append("每").append(monthFrequency.getInterval()).append("个月");
			if (monthFrequency.getMonthDay() != null) {
				sb.append("的第").append(monthFrequency.getMonthDay())
						.append("日");
			}
		} else if (frequency instanceof YearFrequencyClient) {
			YearFrequencyClient yearFrequency = (YearFrequencyClient) frequency;
			sb.append("每").append(yearFrequency.getInterval()).append("年");
			if (yearFrequency.getYearMonth() != 0) {
				sb.append("的第" + yearFrequency.getYearMonth()).append("月");
			}
			if (yearFrequency.getYearMonthDay() != 0) {
				sb.append("的第").append(yearFrequency.getYearMonthDay() + "日");
			}
		} else {
			sb.append("未设定");
		}

		return sb.toString();
	}
}
