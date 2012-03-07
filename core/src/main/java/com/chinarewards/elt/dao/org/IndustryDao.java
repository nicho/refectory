package com.chinarewards.elt.dao.org;

import java.util.List;

import javax.persistence.NoResultException;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.org.Industry;

public class IndustryDao extends BaseDao<Industry> {

	@SuppressWarnings("unchecked")
	public List<Industry> findAllIndustryData() {
		return getEm().createQuery(" FROM Industry ").getResultList();
	}

	public Industry findByExactName(String name) {
		try {
			return (Industry) getEm()
					.createQuery("FROM Industry WHERE name = :name")
					.setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
