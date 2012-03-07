package com.chinarewards.elt.domain.reward.frequency;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * The frequency level is "day" which means every (interval) days would trigger
 * something.
 * 
 * @author yanxin
 * @since 1.0
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "day")
public class DayFrequency extends Frequency {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6293847660098760971L;
}
