package com.chinarewards.elt.dao.budget;

import java.util.List;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.budget.CorpBudget;

public class CorpBudgetDao extends BaseDao<CorpBudget> {

	/**
	 * @param class1
	 * @param corpid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public CorpBudget findByCorpId(String corporationId) {
		String sql = "FROM CorpBudget c WHERE c.corporationId = :corporationId and sysdate between c.beginDate and c.endDate ";
		logger.debug(" findByCorpId==" + corporationId + "--" + sql);
		List<CorpBudget> resultList = getEm().createQuery(sql)
				.setParameter("corporationId", corporationId)
				.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}

	}
	
	/**增加部门预算时，初始化得到当前财年的数据用于下拉表选择
	 * @param class1
	 * @param corpid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CorpBudget> findCorpBudget(String corporationId) {
		String sql = "FROM CorpBudget c WHERE  c.corporationId = :corporationId order by endDate desc";
		
		List<CorpBudget> resultList = getEm().createQuery(sql)
				.setParameter("corporationId", corporationId)
				//.setParameter("nowdate", currTime)
				.getResultList();
		
		return resultList;

	}

}
