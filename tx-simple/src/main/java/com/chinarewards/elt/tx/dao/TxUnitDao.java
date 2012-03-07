package com.chinarewards.elt.tx.dao;

import com.chinarewards.elt.tx.domain.TxUnit;

public class TxUnitDao extends TxBaseDao<TxUnit> {

	public TxUnit findUnitByCode(String code) {

		TxUnit u = null;
		try {
			u = (TxUnit) getEm()
					.createQuery("FROM TxUnit t WHERE t.code = :code")
					.setParameter("code", code).getSingleResult();
		} catch (Exception e) {
			// nothing
		}

		return u;
	}
}
