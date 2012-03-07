package com.chinarewards.elt.service.reward.frequency;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.reward.DayFrequencyDao;
import com.chinarewards.elt.dao.reward.RewardItemDao;
import com.chinarewards.elt.dao.reward.RewardItemStoreDao;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.base.RewardItemStore;
import com.chinarewards.elt.domain.reward.frequency.DayFrequency;
import com.chinarewards.elt.domain.reward.frequency.Frequency;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.DateRangeModel;
import com.chinarewards.elt.model.reward.frequency.Daily;
import com.chinarewards.elt.model.reward.frequency.RewardsFrequency;
import com.chinarewards.elt.util.DateUtil;
import com.google.inject.Inject;

/**
 * The implementation of daily frequency.
 * 
 * @author yanxin
 * @since 1.0
 */
public class FrequencyProcessorDay implements FrequencyProcessor {

	private final DayFrequencyDao dayFrequencyDao;
	private final RewardItemDao rewardItemDao;
	private final RewardItemStoreDao rewardItemStoreDao;
	Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	public FrequencyProcessorDay(DayFrequencyDao dayFrequencyDao,
			RewardItemDao rewardItemDao,RewardItemStoreDao rewardItemStoreDao) {
		this.dayFrequencyDao = dayFrequencyDao;
		this.rewardItemDao = rewardItemDao;
		this.rewardItemStoreDao = rewardItemStoreDao;
	}

	private Daily cast(RewardsFrequency frequency) {
		return (Daily) frequency;
	}

	@Override
	public Frequency bindFrequencyToRewardItem(SysUser caller,
			String rewardItemId, RewardsFrequency frequency) {
		DayFrequency dayFrequency = new DayFrequency();
		Date now = DateUtil.getTime();
		dayFrequency.setInterval(cast(frequency).getInterval());
		dayFrequency.setCreatedAt(now);
		dayFrequency.setLastModifiedAt(now);
		dayFrequency.setCreatedBy(caller);
		dayFrequency.setLastModifiedBy(caller);
		dayFrequencyDao.save(dayFrequency);

		RewardItem rewardItem = rewardItemDao.findById(RewardItem.class,
				rewardItemId);
		rewardItem.setFrequency(dayFrequency);

		return dayFrequency;
	}
	
	@Override
	public Frequency bindFrequencyToRewardItemStore(SysUser caller,
			String rewardItemStoreId, RewardsFrequency frequency) {
		DayFrequency dayFrequency = new DayFrequency();
		Date now = DateUtil.getTime();
		dayFrequency.setInterval(cast(frequency).getInterval());
		dayFrequency.setCreatedAt(now);
		dayFrequency.setLastModifiedAt(now);
		dayFrequency.setCreatedBy(caller);
		dayFrequency.setLastModifiedBy(caller);
		dayFrequencyDao.save(dayFrequency);

		RewardItemStore rewardItemStore = rewardItemStoreDao.findById(RewardItemStore.class,
				rewardItemStoreId);
		rewardItemStore.setFrequency(dayFrequency);

		return dayFrequency;
	}

	@Override
	public String generateRewardName(String name, Frequency frequency,
			Date runTime) {
		return name + " -" + DateUtil.formatData(" yyyy年MM月dd日", runTime);
	}

	@Override
	public Date calNextRunTime(Frequency frequency, Date lastRunTime) {
		DayFrequency freq = (DayFrequency) frequency;
		logger.debug(
				"Process in calNextRunTime method, param[frequency={}, lastRunTime={}]",
				new Object[] { freq, DateUtil.formatData(null, lastRunTime) });
		return DateUtil
				.getDateOfParameterOnDay(lastRunTime, freq.getInterval());
	}

	@Override
	public DateRangeModel generateDateRange(RewardsFrequency frequency,
			Date currTime) {
		Daily freq = cast(frequency);
		logger.debug(" Process in generatorDateRangeModel method, parameterDaySelectorUnit.toString : "
				+ freq
				+ ",Date currTime:"
				+ DateUtil.formatData(null, currTime));
		DateRangeModel res = new DateRangeModel();
		res.setFrom(DateUtil.getEarlierTimeOfThisDay(DateUtil
				.getDateOfParameterOnDay(currTime, -freq.getInterval())));
		res.setTo(DateUtil.getEarlierTimeOfThisDay(currTime));
		return res;
	}

	@Override
	public DateRangeModel generateDateRange(Frequency frequency, Date currTime) {
		DayFrequency freq = (DayFrequency) frequency;
		logger.debug(" Process in generatorDateRangeModel method, parameterDaySelectorUnit.toString : "
				+ freq
				+ ",Date currTime:"
				+ DateUtil.formatData(null, currTime));
		DateRangeModel res = new DateRangeModel();
		res.setFrom(DateUtil.getEarlierTimeOfThisDay(DateUtil
				.getDateOfParameterOnDay(currTime, -freq.getInterval())));
		res.setTo(DateUtil.getEarlierTimeOfThisDay(currTime));
		return res;
	}
}
