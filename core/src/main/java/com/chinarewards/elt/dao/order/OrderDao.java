package com.chinarewards.elt.dao.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.order.Orders;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.order.OrderListCriteria;

public class OrderDao extends BaseDao<Orders> {
	public Orders findByOrdersId(String orderId) {
		try {
			return (Orders) getEm()
					.createQuery("FROM Orders WHERE id = :orderId")
					.setParameter("orderId", orderId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	public PageStore<Orders> queryOrderPageAction(OrderListCriteria criteria) {

		PageStore<Orders> result = new PageStore<Orders>();

		result.setResultList(this.orderList(criteria));
		result.setResultCount(this.countOrder(criteria));

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Orders> orderList(OrderListCriteria criteria) {
		List<Orders> result = new ArrayList<Orders>();

		Query query = getFetchOrderQuery(SEARCH, criteria);

		result = query.getResultList();

		return result;
	}

	public int countOrder(OrderListCriteria criteria) {

		int count = 0;
		Query query = getFetchOrderQuery(COUNT, criteria);
		if (query.getSingleResult() != null)
			count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(" finshed by Order method, result count : {}", count);
		return count;
	}

	private Query getFetchOrderQuery(String type, OrderListCriteria vo) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();

		if (SEARCH.equals(type)) {
			eql.append(" SELECT o FROM Orders o  where  o.deleted= :deleted");
			param.put("deleted", vo.isDeleted());
		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(o) FROM Orders o where  o.deleted= :deleted");
			param.put("deleted", vo.isDeleted());
		}
		if (vo.getOrderStatus() != null) {
			eql.append(" AND o.orderStatus = :orderStatus ");
			param.put("orderStatus", vo.getOrderStatus());
		}

		if (SEARCH.equals(type)) {
			if (vo.getSortingDetail() != null) {
				eql.append(" ORDER BY o." + vo.getSortingDetail().getSort()
						+ " " + vo.getSortingDetail().getDirection());
			}
		}
		System.out.println("EQL : " + eql);
		Query query = getEm().createQuery(eql.toString());
		if (SEARCH.equals(type)) {
			if (vo.getPaginationDetail() != null
					&& vo.getPaginationDetail().getLimit() != 0) {
				int start = vo.getPaginationDetail().getStart();
				int limit = vo.getPaginationDetail().getLimit();

				query.setFirstResult(start);
				query.setMaxResults(limit);
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

}
