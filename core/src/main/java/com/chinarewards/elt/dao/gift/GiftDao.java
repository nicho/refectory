package com.chinarewards.elt.dao.gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.gift.Gift;
import com.chinarewards.elt.model.gift.search.GiftListVo;
import com.chinarewards.elt.util.StringUtil;

public class GiftDao extends BaseDao<Gift> {
	@SuppressWarnings("unchecked")
	public List<Gift> giftList(GiftListVo giftVo) {
		List<Gift> result = new ArrayList<Gift>();

		Query query = getFetchGiftQuery(SEARCH, giftVo);

		result = query.getResultList();
		logger.debug("finished by fetchRewardsItems method. result.size:{}",
				result.size());
		return result;
	}

	public int countGift(GiftListVo giftVo) {
		logger.debug(" Process in countRewardsItems method, parameter : {}",
				giftVo.toString());
		int count = 0;
		Query query = getFetchGiftQuery(COUNT, giftVo);
		if (query.getSingleResult() != null)
			count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(" finshed by gift method, result count : {}", count);
		return count;
	}

	private Query getFetchGiftQuery(String type, GiftListVo criteria) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();

		if (SEARCH.equals(type)) {
			eql.append(" SELECT g FROM Gift g WHERE 1 = 1 and  g.deleted= :deleted");
			param.put("deleted", false);
		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(g) FROM Gift g WHERE 1 = 1 and  g.deleted= :deleted");
			param.put("deleted", false);
		}
		if (criteria.getStatus()!=null) {
			eql.append(" AND UPPER(g.status) = :status ");
			param.put("status", criteria.getStatus());
		}
		if (!StringUtil.isEmptyString(criteria.getType())) {
			eql.append(" AND UPPER(g.type) LIKE :type ");
			param.put("type", "%" + criteria.getType().trim().toUpperCase()
					+ "%");
		}

		if (!StringUtil.isEmptyString(criteria.getName())) {
			eql.append(" AND g.name LIKE :name ");
			param.put("name", "%" + criteria.getName().trim().toUpperCase()
					+ "%");
		}
		if (!StringUtil.isEmptyString(criteria.getExplains())) {
			eql.append(" AND g.explains LIKE :explains ");
			param.put("explains", "%"
					+ criteria.getExplains().trim().toUpperCase() + "%");
		}
		if (SEARCH.equals(type)) {
		if (criteria.getSortingDetail() != null) {
			eql.append(" ORDER BY g."
					+ criteria.getSortingDetail().getSort() + " "
					+ criteria.getSortingDetail().getDirection());
		}
		}
		System.out.println("EQL : " + eql);
		Query query = getEm().createQuery(eql.toString());
		if (SEARCH.equals(type)) {
			if (criteria.getPaginationDetail() != null) {
				int start = criteria.getPaginationDetail().getStart();
				int limit = criteria.getPaginationDetail().getLimit();
				logger.debug(" paginationDetail - start:{}, limit:{}",
						new Object[] { start, limit });

				query.setFirstResult(start);
				query.setMaxResults(limit);
			}
		}
		if (param.size() > 0) {
			Set<String> key = param.keySet();
			for (String s : key) {
				query.setParameter(s, param.get(s));
			}
		}

		return query;
	}
}
