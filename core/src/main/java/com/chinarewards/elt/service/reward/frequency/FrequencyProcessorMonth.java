package com.chinarewards.elt.service.reward.frequency;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.reward.MonthFrequencyDao;
import com.chinarewards.elt.dao.reward.RewardItemDao;
import com.chinarewards.elt.dao.reward.RewardItemStoreDao;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.base.RewardItemStore;
import com.chinarewards.elt.domain.reward.frequency.Frequency;
import com.chinarewards.elt.domain.reward.frequency.MonthFrequency;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.DateRangeModel;
import com.chinarewards.elt.model.reward.frequency.Monthly;
import com.chinarewards.elt.model.reward.frequency.RewardsFrequency;
import com.chinarewards.elt.util.DateUtil;
import com.google.inject.Inject;

/**
 * The implementation of monthly frequency.
 * 
 * @author yanxin
 * @since 1.0
 */
public class FrequencyProcessorMonth implements FrequencyProcessor {

	RewardItemDao rewardItemDao;
	RewardItemStoreDao rewardItemStoreDao;
	MonthFrequencyDao monthFrequencyDao;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	public FrequencyProcessorMonth(RewardItemDao rewardItemDao,RewardItemStoreDao rewardItemStoreDao,
			MonthFrequencyDao monthFrequencyDao) {
		this.rewardItemDao = rewardItemDao;
		this.monthFrequencyDao = monthFrequencyDao;
        this.rewardItemStoreDao = rewardItemStoreDao;
	}

	private Monthly cast(RewardsFrequency frequency) {
		return (Monthly) frequency;
	}

	@Override
	public Frequency bindFrequencyToRewardItem(SysUser caller,
			String rewardItemId, RewardsFrequency frequency) {
		MonthFrequency monthFrequency = new MonthFrequency();
		Date now = DateUtil.getTime();
		Monthly monthly = cast(frequency);
		monthFrequency.setInterval(monthly.getInterval());
		monthFrequency.setMonthDay(monthly.getDay());
		monthFrequency.setCreatedAt(now);
		monthFrequency.setLastModifiedAt(now);
		monthFrequency.setCreatedBy(caller);
		monthFrequency.setLastModifiedBy(caller);
		monthFrequencyDao.save(monthFrequency);

		RewardItem rewardItem = rewardItemDao.findById(RewardItem.class,
				rewardItemId);

		rewardItem.setFrequency(monthFrequency);

		return monthFrequency;
	}
	@Override
	public Frequency bindFrequencyToRewardItemStore(SysUser caller,
			String rewardItemStoreId, RewardsFrequency frequency) {
		MonthFrequency monthFrequency = new MonthFrequency();
		Date now = DateUtil.getTime();
		Monthly monthly = cast(frequency);
		monthFrequency.setInterval(monthly.getInterval());
		monthFrequency.setMonthDay(monthly.getDay());
		monthFrequency.setCreatedAt(now);
		monthFrequency.setLastModifiedAt(now);
		monthFrequency.setCreatedBy(caller);
		monthFrequency.setLastModifiedBy(caller);
		monthFrequencyDao.save(monthFrequency);

		RewardItemStore rewardItemStore = rewardItemStoreDao.findById(RewardItemStore.class,
				rewardItemStoreId);

		rewardItemStore.setFrequency(monthFrequency);

		return monthFrequency;
	}

	@Override
	public String generateRewardName(String name, Frequency frequency,
			Date runTime) {
		return name + " -" + DateUtil.formatData(" yyyy年MM月", runTime);
	}

	@Override
	public Date calNextRunTime(Frequency frequency, Date lastRunTime) {
		MonthFrequency freq = (MonthFrequency) frequency;
		logger.debug(
				" process in calNextRunTime method,param[frequency={}, lastRunTime={}]:",
				new Object[] { frequency,
						DateUtil.formatData(null, lastRunTime) });

		return getNextDate(lastRunTime, freq.getInterval(), freq.getMonthDay());
	}

	/**
	 * 计算开始的时间
	 * 
	 * @param currTime
	 *            ---当前时间
	 * @param interval
	 *            ---间隔月份
	 * @param flagDay
	 *            ---频率规定颁奖时间(月份的哪一天)
	 * @return
	 */
	private Date getStartDate(Date currTime, int interval, int flagDay) {
		Calendar c = Calendar.getInstance();
		c.setTime(currTime);
		int yearNum = c.get(Calendar.YEAR);
		int monthNum = c.get(Calendar.MONTH);
		int dayNum = flagDay;
		int targetMonthNum = monthNum - interval;
		while (targetMonthNum < 0) {
			yearNum--;
			targetMonthNum += 12;
		}
		int max = getDaysOfMonth(yearNum, targetMonthNum + 1);
		if (flagDay > max) {
			dayNum = max;
		}

		c.set(yearNum, targetMonthNum, dayNum);
		return DateUtil.getEarlierTimeOfThisDay(c.getTime());
	}

	/**
	 * 计算结束的时间
	 * 
	 * @param currTime
	 *            ---当前时间
	 * @param interval
	 *            ---间隔月份
	 * @param flagDay
	 *            ---频率规定颁奖时间(月份的哪一天)
	 * @return
	 */
	private Date getEndDate(Date currTime, int interval, int flagDay) {
		Calendar c = Calendar.getInstance();
		c.setTime(currTime);
		int yearNum = c.get(Calendar.YEAR);
		int monthNum = c.get(Calendar.MONTH);
		int dayNum = flagDay;

		int max = getDaysOfMonth(yearNum, monthNum + 1);
		logger.debug("yearNum:{}, monthNum:{}, max:{}", new Object[] { yearNum,
				monthNum, max });
		if (flagDay > max) {
			dayNum = max;
		}

		c.set(yearNum, monthNum, dayNum);
		return DateUtil.getEarlierTimeOfThisDay(c.getTime());
	}

	private Date getNextDate(Date currTime, int interval, int flagDay) {
		Calendar c = Calendar.getInstance();
		c.setTime(currTime);
		int yearNum = c.get(Calendar.YEAR);
		int monthNum = c.get(Calendar.MONTH);
		int dayNum = flagDay;
		int targetMonthNum = monthNum + interval;
		while (targetMonthNum > 11) {
			yearNum++;
			targetMonthNum -= 12;
		}
		int max = getDaysOfMonth(yearNum, targetMonthNum + 1);
		if (flagDay > max) {
			dayNum = max;
		}

		c.set(yearNum, targetMonthNum, dayNum);
		return DateUtil.getEarlierTimeOfThisDay(c.getTime());
	}

	/**
	 * 1-12 代表月份，返回当月的总天数
	 * 
	 * @param month
	 * @return
	 */
	private int getDaysOfMonth(int year, int month) {
		int day = 31;
		switch (month) {
		// 大月
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		// 小月
		case 4:
		case 6:
		case 9:
		case 11:
			day = 30;
			break;
		// 2月
		case 2:
			if ((0 == year % 400) || (0 == year % 4 && 0 != year % 100)) {
				day = 29;
			} else {
				day = 28;
			}
			break;
		}

		return day;
	}

	@Override
	public DateRangeModel generateDateRange(RewardsFrequency frequency,
			Date currTime) {
		Monthly freq = cast(frequency);
		logger.debug(" Process in generatorDateRangeModel method, parameterDaySelectorUnit.toString : "
				+ freq
				+ ", Date currTime:"
				+ DateUtil.formatData(null, currTime));
		DateRangeModel res = new DateRangeModel();
		res.setFrom(getStartDate(currTime, freq.getInterval(), freq.getDay()));
		res.setTo(getEndDate(currTime, freq.getInterval(), freq.getDay()));
		return res;
	}

	@Override
	public DateRangeModel generateDateRange(Frequency frequency, Date currTime) {
		MonthFrequency freq = (MonthFrequency) frequency;
		logger.debug(" Process in generatorDateRangeModel method, parameterDaySelectorUnit.toString : "
				+ freq
				+ ", Date currTime:"
				+ DateUtil.formatData(null, currTime));
		DateRangeModel res = new DateRangeModel();
		res.setFrom(getStartDate(currTime, freq.getInterval(),
				freq.getMonthDay()));
		res.setTo(getEndDate(currTime, freq.getInterval(), freq.getMonthDay()));
		return res;
	}
}
