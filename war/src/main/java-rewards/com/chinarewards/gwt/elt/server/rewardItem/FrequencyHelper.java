package com.chinarewards.gwt.elt.server.rewardItem;

import java.util.List;

import com.chinarewards.elt.domain.reward.frequency.DayFrequency;
import com.chinarewards.elt.domain.reward.frequency.Frequency;
import com.chinarewards.elt.domain.reward.frequency.MonthFrequency;
import com.chinarewards.elt.domain.reward.frequency.WeekFrequency;
import com.chinarewards.elt.domain.reward.frequency.WeekFrequencyDays;
import com.chinarewards.elt.domain.reward.frequency.YearFrequency;
import com.chinarewards.elt.model.reward.frequency.WeekDays;
import com.chinarewards.elt.service.reward.RewardItemService;
import com.chinarewards.gwt.elt.client.rewards.model.DayFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.MonthFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.WeekFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.YearFrequencyClient;

public class FrequencyHelper {
	public FrequencyClient getFrequencyFromUnitList(RewardItemService rewardsItemService,Frequency units) {
		FrequencyClient frequency = null;
		if (units != null) {
		//	for (FrequencySelectorUnit unit : units) {
				if (units instanceof DayFrequency) {
					frequency = new DayFrequencyClient();
					frequency.setInterval(units.getInterval());
					
				} else if (units instanceof WeekFrequency) {
					frequency = new WeekFrequencyClient();
					frequency.setInterval(units.getInterval());
					List<WeekFrequencyDays> datas = rewardsItemService.findWeekFrequencyDaysByFrequencyId(units.getId());
					for (WeekFrequencyDays weekSelectorUnitData : datas) {
						int i = getNumberFromWeekConst(weekSelectorUnitData.getWeekDays());
						if (i != -1) {
							((WeekFrequencyClient) frequency).getWeekDays()
									.add(i);
						}
					}
					

				} else if (units instanceof MonthFrequency) {
					frequency = new MonthFrequencyClient();
					frequency.setInterval(units.getInterval());
					((MonthFrequencyClient) frequency).setMonthDay(((MonthFrequency) units).getMonthDay());
					
				} else if (units instanceof YearFrequency) {
					frequency = new YearFrequencyClient();
					frequency.setInterval(units.getInterval());
					((YearFrequencyClient) frequency).setYearMonth(((YearFrequency) units).getYearMonth());
					((YearFrequencyClient) frequency).setYearMonthDay(((YearFrequency) units).getMonthDay());
					
				}
			}
		//}

		return frequency;
	}

	/**
	 * 返回1到7,-1代表错误
	 * 
	 * @param weekConst
	 * @return
	 */
	public int getNumberFromWeekConst(WeekDays weekConst) {
		if (WeekDays.MON == weekConst) {
			return 1;
		} else if (WeekDays.TUS == weekConst) {
			return 2;
		} else if (WeekDays.WEN == weekConst) {
			return 3;
		} else if (WeekDays.THUR == weekConst) {
			return 4;
		} else if (WeekDays.FRI == weekConst) {
			return 5;
		} else if (WeekDays.SAT == weekConst) {
			return 6;
		} else if (WeekDays.SUN == weekConst) {
			return 7;
		} else {
			// error
			return -1;
		}
	}
}
