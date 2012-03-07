package com.chinarewards.elt.domain.reward.frequency;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * The frequency level is "month" which means every (interval) months would
 * trigger something. At the meantime, you would set value to which day of
 * month.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "month")
public class MonthFrequency extends Frequency {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1621451115644190167L;

	/**
	 * day of month
	 */
	private int monthDay;

	public int getMonthDay() {
		return monthDay;
	}

	public void setMonthDay(int monthDay) {
		this.monthDay = monthDay;
	}

}
