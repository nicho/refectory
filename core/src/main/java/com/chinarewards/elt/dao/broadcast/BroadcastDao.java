package com.chinarewards.elt.dao.broadcast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.information.Broadcasting;
import com.chinarewards.elt.model.broadcast.BroadcastQueryListCriteria;
import com.chinarewards.elt.model.broadcast.BroadcastQueryListVo;
import com.chinarewards.elt.model.information.BroadcastMessage;
import com.chinarewards.elt.model.information.BroadcastingCategory;
import com.chinarewards.elt.util.StringUtil;

public class BroadcastDao  extends BaseDao<Broadcasting>{
	public BroadcastQueryListVo queryBroadcastPageAction(BroadcastQueryListCriteria searchVo) {


		BroadcastQueryListVo result = new BroadcastQueryListVo();

		result.setResultList(queryBroadcastPageActionData(searchVo));
		result.setTotal(queryBroadcastPageActionCount(searchVo));

		return result;
	}
   
	@SuppressWarnings("unchecked")
	private List<Broadcasting> queryBroadcastPageActionData(BroadcastQueryListCriteria searchVo) {
		return getBroadcastQuery(searchVo, SEARCH).getResultList();
	}

	private int queryBroadcastPageActionCount(BroadcastQueryListCriteria searchVo) {
		return Integer.parseInt(getBroadcastQuery(searchVo, COUNT)
				.getSingleResult().toString());
	}

	private Query getBroadcastQuery(BroadcastQueryListCriteria searchVo, String type) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		if (SEARCH.equals(type)) {
			hql.append(" SELECT broadcast FROM Broadcasting broadcast WHERE 1=1 ");
		} else if (COUNT.equals(type)) {
			hql.append(" SELECT COUNT(broadcast) FROM Broadcasting broadcast WHERE 1=1 ");
		}
		
		if (!StringUtil.isEmptyString(searchVo.getCorporationId())) {
			hql.append(" AND broadcast.corporation.id = :corporationId ");
			param.put("corporationId", searchVo.getCorporationId());
		}	
		if(searchVo.getStatus()!=null)
		{
			hql.append(" AND broadcast.status = :status ");
			param.put("status", searchVo.getStatus());
		}
		if(searchVo.getCategory()!=null)
		{
			if(searchVo.getCategory()==BroadcastingCategory.SYSBROADCAST)
			{
				hql.append(" AND (broadcast.category = :category1 OR broadcast.category = :category2)");
				param.put("category1", searchVo.getCategory());
				param.put("category2", BroadcastingCategory.REWARDBROADCAST);
			}
			else
			{
				hql.append(" AND broadcast.category = :category ");
				param.put("category", searchVo.getCategory());
			}
		}
		if (!StringUtil.isEmptyString(searchVo.getQueryKey())) 
		{
			hql.append(" AND broadcast.content LIKE :queryKey ");
			param.put("queryKey", "%"+searchVo.getQueryKey()+"%");
		}
		
		if (!StringUtil.isEmptyString(searchVo.getCreatedByUserName())) {
			hql.append(" AND broadcast.createdBy.staff.name LIKE :createdByUserName ");
			param.put("createdByUserName", "%"+searchVo.getCreatedByUserName()+"%");
		}
		if (searchVo.getBroadcastingTimeStart()!=null && searchVo.getBroadcastingTimeEnd()!=null) {
			hql.append(" and ( broadcast.broadcastingTimeStart  between :broadcastingTimeStart and :broadcastingTimeEnd)");
			param.put("broadcastingTimeStart", searchVo.getBroadcastingTimeStart());
			param.put("broadcastingTimeEnd", searchVo.getBroadcastingTimeEnd());

		}
		if(searchVo.isNowDate())
		{
			hql.append(" and ( SYSDATE  between broadcast.broadcastingTimeStart and broadcast.broadcastingTimeEnd)");
		}
		if (!StringUtil.isEmptyString(searchVo.getCreateUserId())) {
			hql.append(" AND broadcast.createdBy.id = :createUserId ");
			param.put("createUserId", searchVo.getCreateUserId());
		}
		hql.append(" AND broadcast.broadcastMessagetype = :broadcastMessagetype ");
		param.put("broadcastMessagetype", searchVo.getBroadcastMessagetype());
		
		hql.append(" AND broadcast.deleted = :deleted ");
		param.put("deleted", false);
	
		if (searchVo.getBroadcastList()!=null && searchVo.getBroadcastList().size()>0) {
			hql.append(" AND  broadcast.id  IN (:broadcastList) ");
			param.put("broadcastList", searchVo.getBroadcastList());

		}
		// ORDER BY
		if (SEARCH.equals(type)) {
			if (searchVo.getSortingDetail() != null && searchVo.getSortingDetail().getSort() != null && searchVo.getSortingDetail().getDirection() != null) {
				hql.append(" ORDER BY broadcast."
						+ searchVo.getSortingDetail().getSort() + " "
						+ searchVo.getSortingDetail().getDirection());
			} else {
				hql.append(" ORDER BY broadcast.broadcastingTimeStart DESC ");
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
	

	public String getMaxNumber(BroadcastMessage broadcastMessage) {
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT COUNT(broadcast) FROM Broadcasting broadcast WHERE 1=1  AND broadcast.broadcastMessagetype = :broadcastMessagetype ");
		Query query = getEm().createQuery(hql.toString()).setParameter("broadcastMessagetype", broadcastMessage);
		return query.getSingleResult().toString();
	}
}
