package com.chinarewards.elt.dao.reward;

import java.util.List;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.reward.person.Judge;

public class JudgeDao extends BaseDao<Judge> {

	/**
	 * Find list of judge by id of a RewardItem.
	 * 
	 * @param rewardItemId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Judge> findJudgesFromRewardItem(String rewardItemId) {
		return getEm()
				.createQuery(
						"FROM Judge j WHERE j.rewardItem.id =:rewardItemId")
				.setParameter("rewardItemId", rewardItemId).getResultList();
	}
   
	/**
	 * Find list of judge by id of a RewardItemStore.
	 * 
	 * @param rewardItemStoreId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Judge> findJudgesFromRewardItemStore(String rewardItemStoreId) {
		return getEm()
				.createQuery(
						"FROM Judge j WHERE j.rewardItemStore.id =:rewardItemStoreId")
				.setParameter("rewardItemStoreId", rewardItemStoreId).getResultList();
	}
	/**
	 * Find list of judge by id of a Reward.
	 * 
	 * @param rewardId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Judge> findJudgesFromReward(String rewardId) {
		return getEm().createQuery("FROM Judge j WHERE j.reward.id =:rewardId")
				.setParameter("rewardId", rewardId).getResultList();
	}

	/**
	 * Find Judge by staffId and rewardId.
	 * 
	 * @param staffId
	 * @param rewardId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Judge findJudgeByStaffIdAndRewardId(String staffId, String rewardId) {
		List<Judge> judgeList = getEm()
				.createQuery(
						"FROM Judge j WHERE j.reward.id =:rewardId AND j.staff.id =:staffId")
				.setParameter("rewardId", rewardId)
				.setParameter("staffId", staffId).getResultList();
		if (judgeList.size() > 0)
			return judgeList.get(0);
		else
			return null;
	}
}
