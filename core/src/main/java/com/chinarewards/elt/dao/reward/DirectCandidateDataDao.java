package com.chinarewards.elt.dao.reward;

import java.util.List;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.org.Organization;
import com.chinarewards.elt.domain.reward.rule.DirectCandidateData;

/**
 * DAO of {@link DirectCandidateData}
 * 
 * @author yanxin
 * @since 1.0
 */
public class DirectCandidateDataDao extends BaseDao<DirectCandidateData> {

	@SuppressWarnings("unchecked")
	public List<Organization> findOrganizationsByDirectRuleId(
			String directRuleId) {
		return getEm()
				.createQuery(
						" SELECT drs.org FROM DirectCandidateData drs WHERE drs.directCandidateRule.id=:directRuleId ")
				.setParameter("directRuleId", directRuleId).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DirectCandidateData> findDirectCandidateDataListByDirectRuleId(
			String directRuleId) {
		return getEm()
				.createQuery(
						"  FROM DirectCandidateData drs WHERE drs.directCandidateRule.id=:directRuleId ")
				.setParameter("directRuleId", directRuleId).getResultList();
	}

}
