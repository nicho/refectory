package com.chinarewards.elt.domain.reward.frequency;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.elt.model.reward.frequency.WeekDays;

/**
 * The definitions to contain which days in a week frequency.
 * 
 * @author yanxin
 * @since 1.0
 * 
 */
@Entity
public class WeekFrequencyDays implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6146631873385610792L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	/**
	 * which day of week
	 */
	@Enumerated(EnumType.STRING)
	private WeekDays weekDays;

	@ManyToOne
	private WeekFrequency frequency;

	/**
	 * sort
	 */
	private int sort;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WeekDays getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(WeekDays weekDays) {
		this.weekDays = weekDays;
	}

	public WeekFrequency getFrequency() {
		return frequency;
	}

	public void setFrequency(WeekFrequency frequency) {
		this.frequency = frequency;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
