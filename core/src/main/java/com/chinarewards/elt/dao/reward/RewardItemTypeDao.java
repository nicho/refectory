/**
 * 
 */
package com.chinarewards.elt.dao.reward;

import java.util.List;

import javax.persistence.NoResultException;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.reward.base.RewardItemType;

/**
 * DAO of {@link RewardItemType }
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardItemTypeDao extends BaseDao<RewardItemType> {

	@SuppressWarnings("unchecked")
	public List<RewardItemType> getAll() {
		return getEm().createQuery("FROM RewardItemType type").getResultList();
	}

	public RewardItemType findByExactName(String name) {
		try {
			return (RewardItemType) getEm()
					.createQuery("FROM RewardItemType WHERE name=:name")
					.setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
}
