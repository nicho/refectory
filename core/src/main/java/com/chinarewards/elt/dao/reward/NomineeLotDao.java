package com.chinarewards.elt.dao.reward;

import java.util.List;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.reward.person.NomineeLot;

/**
 * Dao of {@link NomineeLot}
 * 
 * @author yanxin
 * @since 1.0
 */
public class NomineeLotDao extends BaseDao<NomineeLot> {
	/**
	 * Find NomineeLotList by judgeId and rewardId.
	 * 
	 * @param judgeId
	 * @param rewardId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<NomineeLot> findNomineeLotList(String judgeId, String rewardId) {
		return getEm()
				.createQuery(
						"FROM NomineeLot j WHERE j.reward.id =:rewardId AND j.judge.id =:judgeId")
				.setParameter("rewardId", rewardId)
				.setParameter("judgeId", judgeId).getResultList();
	}

	/**
	 * Find NomineeLotList by rewardId.
	 * 
	 * @param judgeId
	 * @param rewardId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<NomineeLot> findNomineeLotsByRewardId(String rewardId) {
		return getEm()
				.createQuery("FROM NomineeLot nl WHERE nl.reward.id =:rewardId")
				.setParameter("rewardId", rewardId).getResultList();
	}
}
