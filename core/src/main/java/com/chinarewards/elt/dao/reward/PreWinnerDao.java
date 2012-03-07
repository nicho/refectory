package com.chinarewards.elt.dao.reward;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.reward.person.PreWinner;

/**
 * DAO of {@link PreWinner}
 * 
 * @author yanxin
 * @since 1.0
 */
public class PreWinnerDao extends BaseDao<PreWinner> {

	@SuppressWarnings("unchecked")
	public Map<String, PreWinner> findPreWinnersByIds(List<String> ids) {
		Map<String, PreWinner> res = new HashMap<String, PreWinner>();
		List<Object[]> temp = getEm()
				.createQuery(
						" SELECT winner.id, winner FROM PreWinner winner WHERE winner.id IN(:ids) ")
				.setParameter("ids", ids).getResultList();
		for (Object[] objs : temp) {
			String orgId = (String) objs[0];
			if (!res.containsKey(orgId)) {
				res.put(orgId, (PreWinner) objs[1]);
			}
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public List<PreWinner> findPreWinnerByPreWinnerLotId(String preWinnerLotId) {
		return getEm()
				.createQuery(
						" FROM PreWinner winner WHERE winner.preWinnerLot.id=:preWinnerLotId ")
				.setParameter("preWinnerLotId", preWinnerLotId).getResultList();
	}
}
