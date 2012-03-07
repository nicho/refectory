package com.chinarewards.elt.tx.dao;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.tx.domain.AccountBalance;

public class AccountBalanceDao extends TxBaseDao<AccountBalance> {

	@SuppressWarnings("unchecked")
	public List<AccountBalance> findBalanceByUnitCodeAndExpireDate(
			String accountId, String unitCode, Date expireDate) {
		return getEm()
				.createQuery(
						"FROM AccountBalance ab WHERE ab.account.id =:accountId and ab.unit.code=:code and ab.expireDate =:expireDate")
				.setParameter("accountId", accountId)
				.setParameter("code", unitCode)
				.setParameter("expireDate", expireDate).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<AccountBalance> findBalanceByUnitCode(String accountId,
			String unitCode) {
		return getEm()
				.createQuery(
						"FROM AccountBalance ab WHERE ab.account.id =:accountId and ab.unit.code=:code and ab.expireDate >:now order by ab.expireDate asc")
				.setParameter("accountId", accountId)
				.setParameter("code", unitCode).setParameter("now", new Date())
				.getResultList();
	}
}
