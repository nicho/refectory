package com.chinarewards.elt.dao.reward;

import java.util.List;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.reward.person.PreWinnerLot;
import com.chinarewards.elt.model.reward.base.PreWinnerLotStatus;

/**
 * DAO of {@link PreWinnerLot}
 * 
 * @author yanxin
 * @since 1.0
 */
public class PreWinnerLotDao extends BaseDao<PreWinnerLot> {

	@SuppressWarnings("unchecked")
	public List<PreWinnerLot> getPreWinnerLotsByRewardId(String rewardId) {
		return getEm()
				.createQuery(
						" FROM PreWinnerLot lot WHERE lot.reward.id=:rewardId ")
				.setParameter("rewardId", rewardId).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PreWinnerLot> getPreWinnerLotsByRewardIdAndStatusList(
			String rewardId, List<PreWinnerLotStatus> statusList) {
		return getEm()
				.createQuery(
						" FROM PreWinnerLot lot WHERE lot.reward.id=:rewardId AND lot.status in (:statusList) ORDER BY lot.createdAt desc")
				.setParameter("rewardId", rewardId)
				.setParameter("statusList", statusList).getResultList();
	}
}
