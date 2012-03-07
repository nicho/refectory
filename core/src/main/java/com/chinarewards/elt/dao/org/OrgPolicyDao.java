package com.chinarewards.elt.dao.org;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.org.OrgPolicy;

public class OrgPolicyDao extends BaseDao<OrgPolicy> {

	/**
	 * Find single record with matching owner organization ID and policy key.
	 * This method will throw an exception if more than one records is found.
	 * 
	 * @param orgId
	 * @param policyKey
	 * @return
	 */
	public OrgPolicy findByOrganizationIdPolicyKey(String orgId,
			String policyKey) {

		Query q = getEm().createQuery(
				"FROM OrgPolicy WHERE owner.id = :orgId"
						+ " AND key2 = :policyKey");
		q.setParameter("orgId", orgId);
		q.setParameter("policyKey", policyKey);

		try {
			return (OrgPolicy) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
}
