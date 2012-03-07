package com.chinarewards.elt.service.reward.frequency;

import com.chinarewards.elt.domain.reward.frequency.DayFrequency;
import com.chinarewards.elt.domain.reward.frequency.Frequency;
import com.chinarewards.elt.domain.reward.frequency.MonthFrequency;
import com.chinarewards.elt.domain.reward.frequency.WeekFrequency;
import com.chinarewards.elt.domain.reward.frequency.YearFrequency;
import com.chinarewards.elt.model.reward.frequency.Daily;
import com.chinarewards.elt.model.reward.frequency.Monthly;
import com.chinarewards.elt.model.reward.frequency.RewardsFrequency;
import com.chinarewards.elt.model.reward.frequency.Weekly;
import com.chinarewards.elt.model.reward.frequency.Yearly;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * The implementation of {@link FrequencyFactory}
 * 
 * @author yanxin
 * @since 1.0
 */
public class FrequencyFactoryImpl implements FrequencyFactory {

	private final FrequencyProcessor yearFrequencyProcessor;
	private final FrequencyProcessor weekFrequencyProcessor;
	private final FrequencyProcessor monthFrequencyProcessor;
	private final FrequencyProcessor dayFrequencyProcessor;

	@Inject
	public FrequencyFactoryImpl(
			@Named("yearFrequencyProcessor") FrequencyProcessor yearFrequencyProcessor,
			@Named("weekFrequencyProcessor") FrequencyProcessor weekFrequencyProcessor,
			@Named("monthFrequencyProcessor") FrequencyProcessor monthFrequencyProcessor,
			@Named("dayFrequencyProcessor") FrequencyProcessor dayFrequencyProcessor) {
		this.yearFrequencyProcessor = yearFrequencyProcessor;
		this.weekFrequencyProcessor = weekFrequencyProcessor;
		this.monthFrequencyProcessor = monthFrequencyProcessor;
		this.dayFrequencyProcessor = dayFrequencyProcessor;

	}

	public FrequencyProcessor getProcessor(RewardsFrequency frequency) {
		FrequencyProcessor res = null;
		if (frequency instanceof Daily) {
			res = dayFrequencyProcessor;
		} else if (frequency instanceof Yearly) {
			res = yearFrequencyProcessor;
		} else if (frequency instanceof Weekly) {
			res = weekFrequencyProcessor;
		} else if (frequency instanceof Monthly) {
			res = monthFrequencyProcessor;
		} else {
			throw new IllegalArgumentException(
					" UNKNOW FrequencySelectorUnit TYPE :"
							+ frequency.getClass().getSimpleName());
		}
		return res;
	}

	@Override
	public FrequencyProcessor getProcessor(Frequency frequency) {
		FrequencyProcessor res = null;
		if (frequency instanceof DayFrequency) {
			res = dayFrequencyProcessor;
		} else if (frequency instanceof YearFrequency) {
			res = yearFrequencyProcessor;
		} else if (frequency instanceof WeekFrequency) {
			res = weekFrequencyProcessor;
		} else if (frequency instanceof MonthFrequency) {
			res = monthFrequencyProcessor;
		} else {
			throw new IllegalArgumentException(
					" UNKNOW FrequencySelectorUnit TYPE :"
							+ frequency.getClass().getSimpleName());
		}
		return res;
	}
}
