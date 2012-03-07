package com.chinarewards.elt.domain.reward.frequency;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * The frequency level is "year" which means every (interval) years would
 * trigger something. At the meantime, you would set value to which month and
 * which day.
 * 
 * @author yanxin
 * @since 1.0
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "year")
public class YearFrequency extends Frequency {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8328076847428297014L;

	/**
	 * month of year
	 */
	private int yearMonth;

	/**
	 * day of month
	 */
	private int monthDay;

	public int getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(int yearMonth) {
		this.yearMonth = yearMonth;
	}

	public int getMonthDay() {
		return monthDay;
	}

	public void setMonthDay(int monthDay) {
		this.monthDay = monthDay;
	}
}
