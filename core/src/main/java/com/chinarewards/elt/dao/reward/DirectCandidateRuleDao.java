package com.chinarewards.elt.dao.reward;

import java.util.List;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.reward.rule.DirectCandidateRule;

/**
 * DAO of {@link DirectCandidateRule}
 * 
 * @author yanxin
 * @since 1.0
 */
public class DirectCandidateRuleDao extends BaseDao<DirectCandidateRule> {

	@SuppressWarnings("unchecked")
	public List<DirectCandidateRule> findDirectRuleByRewardsId(String rewardId) {
		return getEm()
				.createQuery(
						" FROM DirectCandidateRule rir WHERE rir.reward.id=:rewardId ")
				.setParameter("rewardId", rewardId).getResultList();
	}
}
