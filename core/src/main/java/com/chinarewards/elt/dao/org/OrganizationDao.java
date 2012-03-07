package com.chinarewards.elt.dao.org;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.org.Organization;

public class OrganizationDao extends BaseDao<Organization> {

	@SuppressWarnings("unchecked")
	public Map<String, Organization> findOrganizationByIds(
			List<String> organziationIds) {
		Map<String, Organization> res = new HashMap<String, Organization>();
		List<Object[]> temp = getEm()
				.createQuery(
						" SELECT org.id, org FROM Organization org WHERE org.id IN(:organziationIds) ")
				.setParameter("organziationIds", organziationIds)
				.getResultList();
		for (Object[] objs : temp) {
			String orgId = (String) objs[0];
			if (!res.containsKey(orgId)) {
				res.put(orgId, (Organization) objs[1]);
			}
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public List<String> findOrganizationBySomePropertyPageAction(
			String corporationId, String falg, int limit) {
		System.out.println("findOrganizationBySomePropertyPageAction");
		return getEm()
				.createNativeQuery(//ROOT_DEPT
						" SELECT staff.id FROM organization staff LEFT OUTER JOIN organization dept ON staff.department_id = dept.id WHERE staff.ORG_TYPE='staff' AND (UPPER(staff.name) LIKE :falg "
								+ " OR UPPER(staff.email) LIKE :falg ) "
								+ " AND staff.deleted=:deleted AND staff.corporation_id=:corporationId "
								+ " UNION ALL  SELECT dp.id FROM organization dp WHERE dp.ORG_TYPE='department' AND UPPER(dp.name) LIKE :falg AND UPPER(dp.name) not LIKE '%ROOT_DEPT%' AND dp.corporation_id=:corporationId "
								+ " UNION ALL  SELECT team.id FROM organization team WHERE team.ORG_TYPE='team' AND UPPER(team.name) LIKE :falg  AND team.corporation_id=:corporationId ")
				.setParameter("falg", "%" + falg.toUpperCase() + "%")
				.setParameter("deleted",0)//没有删除
				.setParameter("corporationId", corporationId)
				.setMaxResults(limit).getResultList();
	}
}
