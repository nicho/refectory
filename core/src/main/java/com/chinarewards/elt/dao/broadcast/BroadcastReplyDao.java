package com.chinarewards.elt.dao.broadcast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.information.BroadcastReply;
import com.chinarewards.elt.model.broadcastReply.BroadcastReplyListCriteria;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.util.StringUtil;

public class BroadcastReplyDao extends BaseDao<BroadcastReply> {

	public PageStore<BroadcastReply> findBroadcastReplyList(
			BroadcastReplyListCriteria criteria) {
		PageStore<BroadcastReply> result = new PageStore<BroadcastReply>();
		result.setResultList(queryBroadcastReplyPageActionData(criteria));
		result.setResultCount(queryBroadcastReplyPageActionCount(criteria));
		return result;
	}

	@SuppressWarnings("unchecked")
	private List<BroadcastReply> queryBroadcastReplyPageActionData(
			BroadcastReplyListCriteria searchVo) {
		return getBroadcastReplyQuery(searchVo, SEARCH).getResultList();
	}

	private int queryBroadcastReplyPageActionCount(
			BroadcastReplyListCriteria searchVo) {
		return Integer.parseInt(getBroadcastReplyQuery(searchVo, COUNT)
				.getSingleResult().toString());
	}

	private Query getBroadcastReplyQuery(BroadcastReplyListCriteria searchVo,
			String type) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		if (SEARCH.equals(type)) {
			hql.append(" SELECT reply FROM BroadcastReply reply WHERE 1=1 ");
		} else if (COUNT.equals(type)) {
			hql.append(" SELECT COUNT(reply) FROM BroadcastReply reply WHERE 1=1 ");
		}

		if (!StringUtil.isEmptyString(searchVo.getBroadcastId())) {
			hql.append(" AND reply.broadcast.id = :broadcastId ");
			param.put("broadcastId", searchVo.getBroadcastId());
		}

		// ORDER BY
		if (SEARCH.equals(type)) {
			if (searchVo.getSortingDetail() != null
					&& searchVo.getSortingDetail().getSort() != null
					&& searchVo.getSortingDetail().getDirection() != null) {
				hql.append(" ORDER BY reply."
						+ searchVo.getSortingDetail().getSort() + " "
						+ searchVo.getSortingDetail().getDirection());
			} else {
				hql.append(" ORDER BY reply.replyTime DESC ");
			}
		}
		logger.debug(" HQL:{} ", hql);
		Query query = getEm().createQuery(hql.toString());
		if (SEARCH.equals(type)) {
			if (searchVo.getPaginationDetail() != null && searchVo.getPaginationDetail().getLimit()!=0 && searchVo.getPaginationDetail().getStart()!=0) {
				int limit = searchVo.getPaginationDetail().getLimit();
				int start = searchVo.getPaginationDetail().getStart();

				logger.debug("pagination - start{}, limit:{}", new Object[] {
						start, limit });

				query.setMaxResults(limit);
				query.setFirstResult(start);
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
