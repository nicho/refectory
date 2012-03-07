package com.chinarewards.elt.service.reward.frequency;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.reward.RewardItemDao;
import com.chinarewards.elt.dao.reward.RewardItemStoreDao;
import com.chinarewards.elt.dao.reward.YearFrequencyDao;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.base.RewardItemStore;
import com.chinarewards.elt.domain.reward.frequency.Frequency;
import com.chinarewards.elt.domain.reward.frequency.YearFrequency;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.DateRangeModel;
import com.chinarewards.elt.model.reward.frequency.RewardsFrequency;
import com.chinarewards.elt.model.reward.frequency.Yearly;
import com.chinarewards.elt.util.DateUtil;
import com.google.inject.Inject;

/**
 * The implementation of yearly frequency.
 * 
 * @author yanxin
 * @since 1.0
 */
public class FrequencyProcessorYear implements FrequencyProcessor {

	RewardItemDao rewardItemDao;

	YearFrequencyDao yearFrequencyDao;
	RewardItemStoreDao rewardItemStoreDao;
	Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	public FrequencyProcessorYear(RewardItemDao rewardItemDao,RewardItemStoreDao rewardItemStoreDao,
			YearFrequencyDao yearFrequencyDao) {
		this.rewardItemDao = rewardItemDao;
		this.yearFrequencyDao = yearFrequencyDao;
		this.rewardItemStoreDao = rewardItemStoreDao;
	}

	private Yearly cast(RewardsFrequency frequency) {
		return (Yearly) frequency;
	}

	@Override
	public Frequency bindFrequencyToRewardItem(SysUser caller,
			String rewardItemId, RewardsFrequency frequency) {
		YearFrequency yearFrequency = new YearFrequency();
		Date now = DateUtil.getTime();
		Yearly yearly = cast(frequency);
		yearFrequency.setInterval(yearly.getInterval());
		yearFrequency.setYearMonth(yearly.getMonth());
		yearFrequency.setMonthDay(yearly.getDay());
		yearFrequency.setCreatedAt(now);
		yearFrequency.setLastModifiedAt(now);
		yearFrequency.setCreatedBy(caller);
		yearFrequency.setLastModifiedBy(caller);
		yearFrequencyDao.save(yearFrequency);

		RewardItem rewardItem = rewardItemDao.findById(RewardItem.class,
				rewardItemId);
		rewardItem.setFrequency(yearFrequency);

		return yearFrequency;
	}
	
	@Override
	public Frequency bindFrequencyToRewardItemStore(SysUser caller,
			String rewardItemStoreId, RewardsFrequency frequency) {
		YearFrequency yearFrequency = new YearFrequency();
		Date now = DateUtil.getTime();
		Yearly yearly = cast(frequency);
		yearFrequency.setInterval(yearly.getInterval());
		yearFrequency.setYearMonth(yearly.getMonth());
		yearFrequency.setMonthDay(yearly.getDay());
		yearFrequency.setCreatedAt(now);
		yearFrequency.setLastModifiedAt(now);
		yearFrequency.setCreatedBy(caller);
		yearFrequency.setLastModifiedBy(caller);
		yearFrequencyDao.save(yearFrequency);

		RewardItemStore rewardItemStore = rewardItemStoreDao.findById(RewardItemStore.class,
				rewardItemStoreId);
		rewardItemStore.setFrequency(yearFrequency);

		return yearFrequency;
	}

	@Override
	public String generateRewardName(String name, Frequency frequency,
			Date runTime) {
		return name + " -" + DateUtil.formatData(" yyyy年", runTime);
	}

	@Override
	public Date calNextRunTime(Frequency frequency, Date lastRunTime) {
		YearFrequency freq = (YearFrequency) frequency;
		logger.debug(
				" process in calNextRunTime method,param:[frequency={}, lastRunTime={}]",
				new Object[] { frequency,
						DateUtil.formatData(null, lastRunTime) });
		return DateUtil.getDateOfParameterOnYear(lastRunTime,
				freq.getInterval());
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
