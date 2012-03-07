package com.chinarewards.elt.domain.reward.frequency;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

/**
 * The frequency level is "week" which means every (interval) weeks would
 * trigger something. And at the meantime, you would set value to which
 * weekdays.
 * 
 * @author yanxin
 * @since 1.0
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "week")
public class WeekFrequency extends Frequency {

	/**
	 * 
	 */
	private static final long serialVersionUID = -939461699840059581L;

	@Transient
	private List<WeekFrequencyDays> weekFrequencyDays;

	public List<WeekFrequencyDays> getWeekFrequencyDays() {
		return weekFrequencyDays;
	}

	public void setWeekFrequencyDays(List<WeekFrequencyDays> weekFrequencyDays) {
		this.weekFrequencyDays = weekFrequencyDays;
	}
}
