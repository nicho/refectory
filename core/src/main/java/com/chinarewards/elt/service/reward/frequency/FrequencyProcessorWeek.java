package com.chinarewards.elt.service.reward.frequency;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.reward.RewardItemDao;
import com.chinarewards.elt.dao.reward.RewardItemStoreDao;
import com.chinarewards.elt.dao.reward.WeekFrequencyDao;
import com.chinarewards.elt.dao.reward.WeekFrequencyDaysDao;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.base.RewardItemStore;
import com.chinarewards.elt.domain.reward.frequency.Frequency;
import com.chinarewards.elt.domain.reward.frequency.WeekFrequency;
import com.chinarewards.elt.domain.reward.frequency.WeekFrequencyDays;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.DateRangeModel;
import com.chinarewards.elt.model.reward.frequency.RewardsFrequency;
import com.chinarewards.elt.model.reward.frequency.WeekDays;
import com.chinarewards.elt.model.reward.frequency.Weekly;
import com.chinarewards.elt.util.DateUtil;
import com.chinarewards.elt.util.WeekUtil;
import com.google.inject.Inject;

/**
 * The implementation of weekly frequency.
 * 
 * @author yanin
 * @since 1.0
 */
public class FrequencyProcessorWeek implements FrequencyProcessor {

	private final WeekFrequencyDao weekFrequencyDao;
	private final WeekFrequencyDaysDao weekFrequencyDaysDao;
	private final RewardItemDao rewardItemDao;
	RewardItemStoreDao rewardItemStoreDao;
	Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	public FrequencyProcessorWeek(WeekFrequencyDao weekFrequencyDao,
			WeekFrequencyDaysDao weekFrequencyDaysDao,RewardItemStoreDao rewardItemStoreDao,
			RewardItemDao rewardItemDao) {
		this.weekFrequencyDao = weekFrequencyDao;
		this.weekFrequencyDaysDao = weekFrequencyDaysDao;
		this.rewardItemDao = rewardItemDao;
		this.rewardItemStoreDao = rewardItemStoreDao;
	}

	private Weekly cast(RewardsFrequency frequency) {
		return (Weekly) frequency;
	}

	@Override
	public Frequency bindFrequencyToRewardItem(SysUser caller,
			String rewardItemId, RewardsFrequency frequency) {
		WeekFrequency weekFrequency = new WeekFrequency();
		Date now = DateUtil.getTime();
		Weekly weekly = cast(frequency);
		weekFrequency.setInterval(weekly.getInterval());
		weekFrequency.setCreatedAt(now);
		weekFrequency.setLastModifiedAt(now);
		weekFrequency.setCreatedBy(caller);
		weekFrequency.setLastModifiedBy(caller);
		weekFrequencyDao.save(weekFrequency);

		// Add weekFrquencyDays
		for (WeekDays days : weekly.getWeekdays()) {
			WeekFrequencyDays weekDays = new WeekFrequencyDays();
			weekDays.setFrequency(weekFrequency);
			weekDays.setSort(days.getFlag());
			weekDays.setWeekDays(days);

			weekFrequencyDaysDao.save(weekDays);
		}

		RewardItem rewardItem = rewardItemDao.findById(RewardItem.class,
				rewardItemId);
		rewardItem.setFrequency(weekFrequency);

		return weekFrequency;
	}
	
	@Override
	public Frequency bindFrequencyToRewardItemStore(SysUser caller,
			String rewardItemStoreId, RewardsFrequency frequency) {
		WeekFrequency weekFrequency = new WeekFrequency();
		Date now = DateUtil.getTime();
		Weekly weekly = cast(frequency);
		weekFrequency.setInterval(weekly.getInterval());
		weekFrequency.setCreatedAt(now);
		weekFrequency.setLastModifiedAt(now);
		weekFrequency.setCreatedBy(caller);
		weekFrequency.setLastModifiedBy(caller);
		weekFrequencyDao.save(weekFrequency);

		// Add weekFrquencyDays
		for (WeekDays days : weekly.getWeekdays()) {
			WeekFrequencyDays weekDays = new WeekFrequencyDays();
			weekDays.setFrequency(weekFrequency);
			weekDays.setSort(days.getFlag());
			weekDays.setWeekDays(days);

			weekFrequencyDaysDao.save(weekDays);
		}

		RewardItemStore rewardItemStore = rewardItemStoreDao.findById(RewardItemStore.class,
				rewardItemStoreId);
		rewardItemStore.setFrequency(weekFrequency);

		return weekFrequency;
	}


	@Override
	public String generateRewardName(String name, Frequency frequency,
			Date runTime) {
		return name + " -" + DateUtil.formatData(" yyyy年MM月dd日 ", runTime);
	}

	@Override
	public Date calNextRunTime(Frequency frequency, Date lastRunTime) {
		WeekFrequency freq = (WeekFrequency) frequency;

		int intervalInt = freq.getInterval();
		logger.debug(
				" Process in calNextRunTime method,param [frequency={}, lastRunTime={}]:",
				new Object[] { frequency, lastRunTime });
		List<WeekFrequencyDays> daysList = weekFrequencyDaysDao
				.findWeekFrequencyDaysByFrequencyId(frequency.getId());
		List<WeekDays> weekList = new ArrayList<WeekDays>();
		for (WeekFrequencyDays d : daysList) {
			weekList.add(d.getWeekDays());
		}

		WeekDays currWeek = WeekUtil.getCurrTimeWeek(lastRunTime);

		// the int value is already sorted
		List<Integer> days = new ArrayList<Integer>();
		for (WeekDays weekConst : weekList) {
			days.add(weekConst.getFlag());
		}

		int currDay = currWeek.getFlag();
		int nextDay = getNextDayInList(currDay, days);

		Date nextRewardsTime = null;
		if (nextDay < currDay) {
			nextRewardsTime = DateUtil.getDateOfParameterOnDay(lastRunTime, 7
					* intervalInt + nextDay - currDay);
		} else if (nextDay > currDay) {
			nextRewardsTime = DateUtil.getDateOfParameterOnDay(lastRunTime,
					nextDay - currDay);
		} else {
			// 相等的情况
			nextRewardsTime = DateUtil.getDateOfParameterOnDay(lastRunTime,
					7 * intervalInt);
		}
		return nextRewardsTime;
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
			return getMinNumber(weekDays);
		}
	}

	/**
	 * 得到最小的数
	 * 
	 * @param weekDays
	 * @return
	 */
	private int getMinNumber(List<Integer> weekDays) {
		int min = weekDays.get(0);
		for (int i : weekDays) {
			if (i < min) {
				min = i;
			}
		}
		return min;
	}

	@Override
	public DateRangeModel generateDateRange(RewardsFrequency frequency,
			Date currTime) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DateRangeModel generateDateRange(Frequency frequency, Date currTime) {
		throw new UnsupportedOperationException();
	}
}
