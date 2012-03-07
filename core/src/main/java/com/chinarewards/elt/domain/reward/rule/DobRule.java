package com.chinarewards.elt.domain.reward.rule;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * The rule according to birthday to choose qualified peoples.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "dob")
public class DobRule extends CandidateRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2509673318616347142L;

	/**
	 * {@link #rangeFrom} and {@link #rangeTo} only exist when this DobRule
	 * associate to a Reward. It is calculated at that time.
	 */
	private Date rangeFrom;

	private Date rangeTo;

	public Date getRangeFrom() {
		return rangeFrom;
	}

	public void setRangeFrom(Date rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	public Date getRangeTo() {
		return rangeTo;
	}

	public void setRangeTo(Date rangeTo) {
		this.rangeTo = rangeTo;
	}
}
