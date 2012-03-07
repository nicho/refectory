package com.chinarewards.elt.dao.broadcast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.information.BroadcastingReceiving;
import com.chinarewards.elt.model.information.BroadcastMessage;
import com.chinarewards.elt.util.StringUtil;

public class BroadcastingReceivingDao  extends BaseDao<BroadcastingReceiving>{
	@SuppressWarnings("unchecked")
	public List<BroadcastingReceiving> findBroadcastingReceivingList(String broadcastId) {
		return getEm()
				.createQuery("FROM BroadcastingReceiving d WHERE d.broadcast.id =:broadcastId")
				.setParameter("broadcastId", broadcastId).getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<String> findBroadcastingReceivingIdList(String corpId,String deptId,String staffId,List<String> teamIds,BroadcastMessage broadcastMessage) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();

		hql.append(" SELECT d.broadcast.id FROM BroadcastingReceiving d WHERE ");
		
		hql.append(" d.broadcast.broadcastMessagetype = :broadcastMessagetype AND (  ");
		param.put("broadcastMessagetype", broadcastMessage);
			
		String orString="";
		if (!StringUtil.isEmptyString(corpId)) {
			orString+=" OR d.receiving.corporation.id =:corpId ";
			param.put("corpId",corpId);
		}	
		if (!StringUtil.isEmptyString(deptId)) {
			orString+=" OR d.receiving.dept.id =:deptId ";
			param.put("deptId",deptId);
		}	
		if (!StringUtil.isEmptyString(staffId)) {
			orString+=" OR d.receiving.staff.id =:staffId ";
			param.put("staffId",staffId);
		}	
		if (teamIds!=null && teamIds.size()>0) {
			orString+=" OR d.receiving.team.id IN (:teamIds) ";
			param.put("teamIds",teamIds);
		}	
		hql.append(orString.substring(3));
		hql.append(" ) ");
		Query query = getEm().createQuery(hql.toString());
		if (param.size() > 0) {
			Set<String> key = param.keySet();
			for (String s : key) {
				query.setParameter(s, param.get(s));
			}
		}
		return query.getResultList();
	}
}
