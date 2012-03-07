package com.chinarewards.elt.service.reward;

import java.util.Calendar;
import java.util.Date;

import com.chinarewards.elt.domain.reward.frequency.DayFrequency;
import com.chinarewards.elt.domain.reward.frequency.MonthFrequency;
import com.chinarewards.elt.domain.reward.frequency.YearFrequency;
import com.chinarewards.elt.model.common.DateRangeModel;
import com.chinarewards.elt.model.reward.frequency.DailyVo;
import com.chinarewards.elt.model.reward.frequency.MonthlyVo;
import com.chinarewards.elt.service.common.JPATestCase;
import com.chinarewards.elt.service.reward.frequency.FrequencyFactory;
import com.chinarewards.elt.util.DateUtil;

public class FrequencyFactoryTest extends JPATestCase {

	public void testDaySelectorUnit() {

		FrequencyFactory factory = injector.getInstance(FrequencyFactory.class);
		Date currTime = buildDate(2010, 10, 10, 0, 0, 0, 0);

		DayFrequency freq = new DayFrequency();
		freq.setInterval(10);
		Date nextRunTime = factory.getProcessor(freq).calNextRunTime(freq,
				currTime);
		assertEquals("2010-11-20 00:00:00",
				DateUtil.formatData(null, nextRunTime));
	}

	public void testMonthSelectorUnit() {
		FrequencyFactory factory = injector.getInstance(FrequencyFactory.class);
		Date currTime = buildDate(2010, 10, 10, 0, 0, 0, 0);
		//
		MonthFrequency freq = new MonthFrequency();
		freq.setInterval(12);
		freq.setMonthDay(10);
		Date nextRunTime = factory.getProcessor(freq).calNextRunTime(freq,
				currTime);
		assertEquals("2011-11-10 00:00:00",
				DateUtil.formatData(null, nextRunTime));
	}

	public void testYearSelectorUnit() {
		FrequencyFactory factory = injector.getInstance(FrequencyFactory.class);
		Date currTime = buildDate(2010, 9, 21, 0, 0, 0, 0);
		YearFrequency freq = new YearFrequency();
		freq.setInterval(1);
		freq.setYearMonth(10);
		freq.setMonthDay(21);
		Date nextRunTime = factory.getProcessor(freq).calNextRunTime(freq,
				currTime);
		assertEquals("2011-10-21 00:00:00",
				DateUtil.formatData(null, nextRunTime));
	}

	// 生日奖，每多少天，计算范围
	public void testGenerateDateRangeModel_DailyVo() {
		FrequencyFactory factory = injector.getInstance(FrequencyFactory.class);
		Date currTime = buildDate(2010, 9, 21, 0, 0, 0, 0);
		DailyVo freq = new DailyVo(10);
		DateRangeModel range = factory.getProcessor(freq).generateDateRange(
				freq, currTime);
		Date from = range.getFrom();
		Date to = range.getTo();
		assertEquals("2010-10-11 00:00:00", DateUtil.formatData(null, from));
		assertEquals("2010-10-21 00:00:00", DateUtil.formatData(null, to));

		currTime = buildDate(2010, 2, 5, 0, 0, 0, 0);
		freq = new DailyVo(10);
		range = factory.getProcessor(freq).generateDateRange(freq, currTime);
		from = range.getFrom();
		to = range.getTo();
		assertEquals("2010-02-23 00:00:00", DateUtil.formatData(null, from));
		assertEquals("2010-03-05 00:00:00", DateUtil.formatData(null, to));
	}

	public void testGenerateDateRangeModel_MonthlyVo() {
		FrequencyFactory factory = injector.getInstance(FrequencyFactory.class);
		Date currTime = buildDate(2010, 1, 21, 0, 0, 0, 0);
		// 每月31号
		MonthlyVo freq = new MonthlyVo(1, 31);
		DateRangeModel range = factory.getProcessor(freq).generateDateRange(
				freq, currTime);
		Date from = range.getFrom();
		Date to = range.getTo();
		assertEquals("2010-01-31 00:00:00", DateUtil.formatData(null, from));
		assertEquals("2010-02-28 00:00:00", DateUtil.formatData(null, to));
	}

	// 2010-11-10 00:00:00
	private Date buildDate(int year, int month, int day, int hour, int minute,
			int second, int millisecond) {
		Calendar calendar = Calendar.getInstance();
		// calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, millisecond);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONDAY, month);// 11月
		calendar.set(Calendar.YEAR, year);

		return calendar.getTime();
	}

}
