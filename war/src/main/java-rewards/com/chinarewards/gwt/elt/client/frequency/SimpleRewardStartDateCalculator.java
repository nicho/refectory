/**
 * 
 */
package com.chinarewards.gwt.elt.client.frequency;

import java.util.Date;

/**
 * @author yanxin
 * 
 */
public class SimpleRewardStartDateCalculator implements
		RewardStartDateCalculator {

	@Override
	public Date calculateStartDate(RewardDateInfo info) {
		// 初始化为开始时间
		Date startDate = info.getStartDate();
		// 无开始时间，置当前时间
		if (info.getStartDate() == null) {
			startDate = info.getCurrentDate();
		}
//		else {
//			if (info.getCurrentDate().after(info.getStartDate())) {
//				startDate = info.getCurrentDate();
//			}
//		}
		// 有上次颁奖时间
//		if (info.getLastRewardDate() != null) {
//			startDate = info.getLastRewardDate();
//		}

		
		return startDate;
	}
}
