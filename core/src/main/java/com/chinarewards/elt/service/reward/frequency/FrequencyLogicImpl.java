package com.chinarewards.elt.service.reward.frequency;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.dao.reward.FrequencyDao;
import com.chinarewards.elt.dao.reward.RewardItemDao;
import com.chinarewards.elt.dao.reward.WeekFrequencyDaysDao;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.frequency.DayFrequency;
import com.chinarewards.elt.domain.reward.frequency.Frequency;
import com.chinarewards.elt.domain.reward.frequency.MonthFrequency;
import com.chinarewards.elt.domain.reward.frequency.WeekFrequency;
import com.chinarewards.elt.domain.reward.frequency.WeekFrequencyDays;
import com.chinarewards.elt.domain.reward.frequency.YearFrequency;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.DateRangeModel;
import com.chinarewards.elt.model.reward.frequency.RewardsFrequency;
import com.chinarewards.elt.util.DateUtil;
import com.google.inject.Inject;

/**
 * The implementation of {@link FrequencyLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class FrequencyLogicImpl implements FrequencyLogic {

	FrequencyFactory frequencyFactory;
	FrequencyDao frequencyDao;
	WeekFrequencyDaysDao weekFrequencyDaysDao;
	private final RewardItemDao rewardItemDao;

	@Inject
	public FrequencyLogicImpl(FrequencyFactory frequencyFactory,
			FrequencyDao frequencyDao,
			WeekFrequencyDaysDao weekFrequencyDaysDao,
			RewardItemDao rewardItemDao) {
		this.frequencyFactory = frequencyFactory;
		this.frequencyDao = frequencyDao;
		this.weekFrequencyDaysDao = weekFrequencyDaysDao;
		this.rewardItemDao = rewardItemDao;
	}

	@Override
	public void removeFrequencyFromRewardItem(String rewardItemId) {
		Frequency f = frequencyDao.findFrequencyByRewardItemId(rewardItemId);
		if (f instanceof WeekFrequency) {
			List<WeekFrequencyDays> weekDays = weekFrequencyDaysDao
					.findWeekFrequencyDaysByFrequencyId(f.getId());
			for (WeekFrequencyDays days : weekDays) {
				weekFrequencyDaysDao.delete(days);
			}
		}
		frequencyDao.delete(f);
	}

	@Override
	public void removeFrequencyFromRewardItemStore(String rewardItemStoreId) {
		Frequency f = frequencyDao
				.findFrequencyByRewardStoreItemId(rewardItemStoreId);
		if (f instanceof WeekFrequency) {
			List<WeekFrequencyDays> weekDays = weekFrequencyDaysDao
					.findWeekFrequencyDaysByFrequencyId(f.getId());
			for (WeekFrequencyDays days : weekDays) {
				weekFrequencyDaysDao.delete(days);
			}
		}
		frequencyDao.delete(f);
	}

	@Override
	public void bindFrequencyToRewardItem(SysUser caller, String rewardItemId,
			RewardsFrequency frequency) {
		frequencyFactory.getProcessor(frequency).bindFrequencyToRewardItem(
				caller, rewardItemId, frequency);
	}

	@Override
	public void bindFrequencyToRewardItemStore(SysUser caller,
			String rewardItemStoreId, RewardsFrequency frequency) {
		frequencyFactory.getProcessor(frequency)
				.bindFrequencyToRewardItemStore(caller, rewardItemStoreId,
						frequency);
	}

	@Override
	public Frequency getFrequencyOfRewardItem(String rewardItemId) {
		Frequency f = frequencyDao.findFrequencyByRewardItemId(rewardItemId);
		if (f instanceof WeekFrequency) {
			List<WeekFrequencyDays> weekDays = weekFrequencyDaysDao
					.findWeekFrequencyDaysByFrequencyId(f.getId());
			WeekFrequency weekFrequency = (WeekFrequency) f;
			weekFrequency.setWeekFrequencyDays(weekDays);
		}

		return f;
	}

	@Override
	public Frequency getFrequencyOfRewardItemStore(String rewardItemStoreId) {
		Frequency f = frequencyDao
				.findFrequencyByRewardStoreItemId(rewardItemStoreId);
		if (f instanceof WeekFrequency) {
			List<WeekFrequencyDays> weekDays = weekFrequencyDaysDao
					.findWeekFrequencyDaysByFrequencyId(f.getId());
			WeekFrequency weekFrequency = (WeekFrequency) f;
			weekFrequency.setWeekFrequencyDays(weekDays);
		}

		return f;
	}

	@Override
	public DateRangeModel calDateRangeFromFrequency(Frequency frequency,
			Date currTime) {
		return frequencyFactory.getProcessor(frequency).generateDateRange(
				frequency, currTime);
	}

	@Override
	public String calRewardNameFromFrequency(String name, Frequency frequency,
			Date runTime) {
		return frequencyFactory.getProcessor(frequency).generateRewardName(
				name, frequency, runTime);
	}

	@Override
	public Date calNextAwardTime(Frequency frequency, Date lastRunTime) {
		return frequencyFactory.getProcessor(frequency).calNextRunTime(
				frequency, lastRunTime);
	}

	@Override
	public Frequency copyFrequencyToRewardItem(SysUser caller,
			String rewardItemStoreId, String rewardItemId) {

		Frequency f = getFrequencyOfRewardItemStore(rewardItemStoreId);
		RewardItem rewardItem = rewardItemDao.findById(RewardItem.class,
				rewardItemId);
		if (f instanceof DayFrequency) {
			
			DayFrequency df = new DayFrequency();
			df.setInterval(f.getInterval());
			df.setCreatedAt(new Date());
			df.setCreatedBy(caller);
			df.setLastModifiedAt(new Date());
			df.setLastModifiedBy(caller);
			frequencyDao.save(df);
			rewardItem.setFrequency(df);
		} else if (f instanceof MonthFrequency) {
			MonthFrequency mf=(MonthFrequency)f;
			MonthFrequency monthFrequency = new MonthFrequency();
			Date now = DateUtil.getTime();
			monthFrequency.setInterval(mf.getInterval());
			monthFrequency.setMonthDay(mf.getMonthDay());
			monthFrequency.setCreatedAt(now);
			monthFrequency.setLastModifiedAt(now);
			monthFrequency.setCreatedBy(caller);
			monthFrequency.setLastModifiedBy(caller);
			frequencyDao.save(monthFrequency);
			rewardItem.setFrequency(monthFrequency);
		} else if (f instanceof WeekFrequency) {
			WeekFrequency mf=(WeekFrequency)f;
			WeekFrequency weekFrequency = new WeekFrequency();
			Date now = DateUtil.getTime();

			weekFrequency.setInterval(mf.getInterval());
			weekFrequency.setCreatedAt(now);
			weekFrequency.setLastModifiedAt(now);
			weekFrequency.setCreatedBy(caller);
			weekFrequency.setLastModifiedBy(caller);
			frequencyDao.save(weekFrequency);

			// Add weekFrquencyDays
			for (WeekFrequencyDays days : mf.getWeekFrequencyDays()) {
				WeekFrequencyDays weekDays = new WeekFrequencyDays();
				weekDays.setFrequency(weekFrequency);
				weekDays.setSort(days.getSort());
				weekDays.setWeekDays(days.getWeekDays());

				weekFrequencyDaysDao.save(weekDays);
			}
			rewardItem.setFrequency(weekFrequency);
		} else if (f instanceof YearFrequency) {
			YearFrequency mf=(YearFrequency)f;
			YearFrequency yearFrequency = new YearFrequency();
			Date now = DateUtil.getTime();
			yearFrequency.setInterval(mf.getInterval());
			yearFrequency.setYearMonth(mf.getYearMonth());
			yearFrequency.setMonthDay(mf.getMonthDay());
			yearFrequency.setCreatedAt(now);
			yearFrequency.setLastModifiedAt(now);
			yearFrequency.setCreatedBy(caller);
			yearFrequency.setLastModifiedBy(caller);
			frequencyDao.save(yearFrequency);
			rewardItem.setFrequency(yearFrequency);
		}

		rewardItemDao.update(rewardItem);

		return null;

	}
	
}
