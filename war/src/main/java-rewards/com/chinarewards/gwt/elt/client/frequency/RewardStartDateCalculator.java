/**
 * 
 */
package com.chinarewards.gwt.elt.client.frequency;

import java.util.Date;

/**
 * 
 * @author yanxin
 * 
 */
public interface RewardStartDateCalculator {

	public interface RewardDateInfo {

		public Date getStartDate();

	
		public Date getCurrentDate();

		public Date getLastRewardDate();

	}

	/**
	 * Calculate which date value to use as the start date for calculating the
	 * next reward date. The return value is NOT a reward date.
	 * 
	 * 
	 * @param info
	 * @return the start date, or <code>null</code> if no valid date value can
	 *         be determined.
	 */
	public Date calculateStartDate(RewardDateInfo info);

}
