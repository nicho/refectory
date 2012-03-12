package com.chinarewards.elt.dao.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.order.Orders;
import com.chinarewards.elt.model.order.search.OrderListVo;
import com.chinarewards.elt.util.StringUtil;

public class OrderDao extends BaseDao<Orders> {
	@SuppressWarnings("unchecked")
	public List<Orders> OrderList(OrderListVo OrderVo) {
		List<Orders> result = new ArrayList<Orders>();

		Query query = getFetchOrderQuery(SEARCH, OrderVo);

		result = query.getResultList();
		
		return result;
	}

	public int countOrder(OrderListVo OrderVo) {
		
		int count = 0;
		Query query = getFetchOrderQuery(COUNT, OrderVo);
		if (query.getSingleResult() != null)
			count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(" finshed by Order method, result count : {}", count);
		return count;
	}

	private Query getFetchOrderQuery(String type, OrderListVo vo) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();

		if (SEARCH.equals(type)) {
			eql.append(" SELECT o FROM Orders o ,Gift g where o.giftId=g.id and  o.deleted= :deleted");
			param.put("deleted", vo.getDeleted());
		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(o) FROM Orders o,Gift g where o.giftId=g.id  and  o.deleted= :deleted");
			param.put("deleted", vo.getDeleted());
		}
		if (vo.getStatus()!=null) {
			eql.append(" AND o.status = :status ");
			param.put("status", vo.getStatus());
		}
		if (!StringUtil.isEmptyString(vo.getUserId())) {
			eql.append(" AND o.userId =:userId ");
			param.put("userId", vo.getUserId());
		}
		
		if (!StringUtil.isEmptyString(vo.getGiftvo().getSource())) {
			eql.append(" AND g.source =:source ");
			param.put("source", vo.getGiftvo().getSource());
		}
		if (!StringUtil.isEmptyString(vo.getName())) {//查询姓名或编码时
			eql.append(" AND o.name LIKE :name ");
			param.put("name", "%" + vo.getName().trim().toUpperCase()+ "%");
			eql.append(" or o.orderCode like:orderCode ");
			param.put("orderCode","%" +  vo.getName().trim().toUpperCase()+ "%");
		}
		// 根据创建时间来查询
			if (null != vo.getExchangeDate()&& !vo.getExchangeDate().equals("")
					&& null != vo.getExchangeDateEnd()&& !vo.getExchangeDateEnd().equals("")) {
				eql.append(" and ( o.exchangeDate  between :createTime and :createdAtEnd)");
				param.put("createTime", vo.getExchangeDate());
				param.put("createdAtEnd", vo.getExchangeDateEnd());

			}
		if (SEARCH.equals(type)) {
			if (vo.getSortingDetail() != null) {
				eql.append(" ORDER BY o."
						+ vo.getSortingDetail().getSort() + " "
						+ vo.getSortingDetail().getDirection());
			}
		}
		System.out.println("EQL : " + eql);
		Query query = getEm().createQuery(eql.toString());
		if (SEARCH.equals(type)) {
			if (vo.getPaginationDetail() != null) {
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
