package com.chinarewards.elt.dao.reward;

import java.util.List;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.reward.person.Nominee;

/**
 * Dao of {@link Nominee}
 * 
 * @author yanxin
 * @since 1.0
 */
public class NomineeDao extends BaseDao<Nominee> {
	/**
	 * Find NomineeList by staffId and rewardId.
	 * 
	 * @param staffId
	 * @param rewardId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Nominee> findNomineeList(String staffId, String rewardId) {
		return getEm()
				.createQuery(
						"FROM Nominee j WHERE j.nomineeLot.reward.id =:rewardId AND j.staff.id =:staffId")
				.setParameter("rewardId", rewardId)
				.setParameter("staffId", staffId).getResultList();
	}

	/**
	 * Find nominees by id of nomineelot.
	 * 
	 * @param nomineeLotId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Nominee> findNomineesByNomineeLotId(String nomineeLotId) {
		return getEm()
				.createQuery(
						"FROM Nominee n WHERE n.nomineeLot.id =:nomineeLotId")
				.setParameter("nomineeLotId", nomineeLotId).getResultList();
	}
}
