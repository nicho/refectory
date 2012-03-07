package com.chinarewards.elt.dao.org;

import java.util.List;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.org.OrgInit;

public class OrgInitDao extends BaseDao<OrgInit> {
	@SuppressWarnings("unchecked")
	public OrgInit getOrgInit() {
		List<OrgInit> list =getEm().createQuery("FROM OrgInit m ").getResultList();
		OrgInit orgInit=null;
		if(list.size()>0){
			orgInit = list.get(0);
		}
		return orgInit;
	}
	
}
