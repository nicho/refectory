package com.chinarewards.elt.dao.reward;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.reward.frequency.Frequency;

/**
 * DAO of {@link Frequency}
 * 
 * @author yanxin
 * @since 1.0
 */
public class FrequencyDao extends BaseDao<Frequency> {

	public Frequency findFrequencyByRewardItemId(String rewardItemId) {
		return (Frequency) getEm()
				.createQuery(
						"SELECT ri.frequency FROM RewardItem ri WHERE ri.id=:rewardItemId")
				.setParameter("rewardItemId", rewardItemId).getSingleResult();
	}
	
	public Frequency findFrequencyByRewardStoreItemId(String rewardItemStoreId) {
		return (Frequency) getEm()
				.createQuery(
						"SELECT ri.frequency FROM RewardItemStore ri WHERE ri.id=:rewardItemStoreId")
				.setParameter("rewardItemStoreId", rewardItemStoreId).getSingleResult();
	}
}
