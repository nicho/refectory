package com.chinarewards.elt.dao.reward;

import java.util.List;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.reward.frequency.WeekFrequencyDays;

/**
 * DAO of {@link WeekFrequencyDays}
 * 
 * @author yanxin
 * @since 1.0
 */
public class WeekFrequencyDaysDao extends BaseDao<WeekFrequencyDays> {

	@SuppressWarnings("unchecked")
	public List<WeekFrequencyDays> findWeekFrequencyDaysByFrequencyId(
			String frequencyId) {
		return getEm()
				.createQuery(
						" FROM WeekFrequencyDays wfd WHERE "
								+ " wfd.frequency.id=:frequencyId "
								+ " ORDER BY wfd.sort ASC ")
				.setParameter("frequencyId", frequencyId).getResultList();
	}
}
