package com.chinarewards.elt.dao.reward;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.reward.person.Candidate;
import com.chinarewards.elt.model.reward.base.RewardStatus;
import com.chinarewards.elt.model.vo.WinnersRecordQueryVo;
import com.chinarewards.elt.util.StringUtil;

/**
 * Dao of {@link Candidate}
 * 
 * @author yanxin
 * @since 1.0
 */
public class CandidateDao extends BaseDao<Candidate> {

	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidatesByRewardId(String rewardId) {
		return getEm()
				.createQuery("FROM Candidate c WHERE c.reward.id = :rewardId")
				.setParameter("rewardId", rewardId).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidateByStaffId(String staffId) {
		return getEm().createQuery(
				"FROM Candidate c WHERE  c.staff.id = :staffId").setParameter(
				"staffId", staffId).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidateByStaffIdAndStatus(String staffId,List<RewardStatus> status) {
		return getEm().createQuery(
				"FROM Candidate c WHERE  c.staff.id = :staffId  AND c.reward.status IN (:status)").setParameter(
				"staffId", staffId).setParameter("status", status).getResultList();
	}

	public Candidate findCandidateByStaffRewardId(String rewardId,
			String staffId) {
		Query query = getEm()
				.createQuery(
						"FROM Candidate c WHERE c.reward.id = :rewardId AND c.staff.id = :staffId")
				.setParameter("rewardId", rewardId)
				.setParameter("staffId", staffId);
		if (query.getSingleResult() != null) {
			return (Candidate) query.getSingleResult();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidatesByRewardIdAndQueryVo(String rewardId,
			WinnersRecordQueryVo queryVo) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> param = new HashMap<String, Object>();
		sql.append("FROM Candidate  WHERE reward.id = (:rewardId)");
		param.put("rewardId", rewardId);
		if (!StringUtil.isEmptyString(queryVo.getKey())) {
			sql.append(" AND ( Upper(staff.name) LIKE Upper(:key))");
			param.put("key", "%" + queryVo.getKey() + "%");
		}
		if (queryVo.getSortingDetail() != null) {
			sql.append(" ORDER BY " + queryVo.getSortingDetail().getSort()
					+ " " + queryVo.getSortingDetail().getDirection());
		} else {
			sql.append(" ORDER BY reward.createdAt DESC ");
		}
		Query q = getEm().createQuery(sql.toString());

		for (String key : param.keySet()) {
			q.setParameter(key, param.get(key));
		}
		if (queryVo.getPaginationDetail() != null) {
			q.setMaxResults(queryVo.getPaginationDetail().getLimit());
			q.setFirstResult(queryVo.getPaginationDetail().getStart());
		}
		return q.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidatesByRewardIdandStaffIds(String rewardId,
			List<String> staffIds) {
		return getEm()
				.createQuery(
						"FROM Candidate c  WHERE c.reward.id = :rewardId and c.staff.id IN (:staffIds)")
				.setParameter("rewardId", rewardId)
				.setParameter("staffIds", staffIds).getResultList();
	}

	public int updateCandidatesNominateCount(String rewardId,
			List<String> staffIds) {
		return getEm()
				.createQuery(
						"UPDATE  Candidate c  SET c.nominatecount=c.nominatecount+1  WHERE c.reward.id = :rewardId and c.staff.id IN (:staffIds)")
				.setParameter("rewardId", rewardId)
				.setParameter("staffIds", staffIds).executeUpdate();
	}
}
