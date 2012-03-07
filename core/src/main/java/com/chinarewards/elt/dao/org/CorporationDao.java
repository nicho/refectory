package com.chinarewards.elt.dao.org;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.model.user.UserStatus;

public class CorporationDao extends BaseDao<Corporation> {
	
	/**
	 * Search corporation with case-insensitive matching of corporation
	 * sub-domain name and with any matching HrUser records with matching user
	 * status.
	 * 
	 * @param subdomain
	 * @param userStatus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Corporation> findBySubdomainHrUserStatus(String subdomain,
			UserStatus userStatus) {

		Query q = getEm()
				.createQuery(
						"SELECT DISTINCT corp FROM HrUser u INNER JOIN u.corporation corp WHERE UPPER(corp.subdomain) = :subdomain"
								+ (userStatus == null ? ""
										: " AND u.status = :userStatus"));
		q.setParameter("subdomain", subdomain.toUpperCase());
		if (userStatus != null)
			q.setParameter("userStatus", userStatus);
		return q.getResultList();

	}

	/**
	 * Find corporation which is matching subdomain name in a case-insensitive
	 * manner whenever its account is active or inactive.
	 * 
	 * @param subdomain
	 * @return
	 */
	public Corporation findBySubdomainCI(String subdomain) {
		String s = subdomain.toUpperCase();
		Query q = getEm()
				.createQuery(
						"SELECT corp FROM HrUser u INNER JOIN u.corporation corp WHERE UPPER(corp.subdomain) = :subdomain "
				// + " AND u.status = :userStatus"
				).setParameter("subdomain", s)
		// .setParameter("userStatus", UserStatus.Active)
		;
		try {
			return (Corporation) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Corporation> findCorporationByCRMEnterpirseId(
			String crmEnterPriserId) {
		return getEm()
				.createQuery(
						" FROM Corporation c WHERE c.corporationId=:crmEnterPriserId ")
				.setParameter("crmEnterPriserId", crmEnterPriserId)
				.getResultList();
	}
	@SuppressWarnings("unchecked")
	public int getCorp() {
		 int sum = 0;
		 List<Corporation> list = getEm().createQuery(" FROM Corporation c  ").getResultList();
		 if(list.size()>0)
			 sum=list.size();
		 return sum;
	}
}
