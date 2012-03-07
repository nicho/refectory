/**
 * 
 */
package com.chinarewards.gwt.elt.client.frequency;

import java.util.Date;

import com.chinarewards.gwt.elt.client.frequency.RewardStartDateCalculator.RewardDateInfo;

import com.chinarewards.gwt.elt.util.DateTool;

/**
 * @author yanxin
 * @since 0.2.0
 */
public class SimpleRewardDateInfo implements RewardDateInfo {

	private Date startDate;

	private Date endDate;

	private Date currentDate;

	private Date lastRewardDate;

	public SimpleRewardDateInfo(Date startDate, Date currentDate,
			Date lastRewardDate) {
		this.startDate = startDate;
		
		this.currentDate = currentDate;
		this.lastRewardDate = lastRewardDate;
	}

	public Date getStartDate() {
		return DateTool.stripTimeComponents(startDate);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return DateTool.stripTimeComponents(endDate);
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCurrentDate() {
		return DateTool.stripTimeComponents(currentDate);
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Date getLastRewardDate() {
		return DateTool.stripTimeComponents(lastRewardDate);
	}

	public void setLastRewardDate(Date lastRewardDate) {
		this.lastRewardDate = lastRewardDate;
	}

}
