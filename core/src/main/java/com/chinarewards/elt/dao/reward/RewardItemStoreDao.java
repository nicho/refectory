/**
 * 
 */
package com.chinarewards.elt.dao.reward;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.base.RewardItemStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.reward.base.RequireAutoGenerate;
import com.chinarewards.elt.model.reward.search.RewardItemSearchVo;
import com.chinarewards.elt.util.DateUtil;
import com.chinarewards.elt.util.StringUtil;

/**
 * DAO of {@link RewardItem}
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardItemStoreDao extends BaseDao<RewardItemStore> {

	@SuppressWarnings("unchecked")
	public List<RewardItemStore> fetchRewardsItemsStore(
			RewardItemSearchVo criteria) {

		logger.debug("Process in fetchRewardsItems method, parameter : {}",
				criteria.toString());
		List<RewardItemStore> result = new ArrayList<RewardItemStore>();
		PaginationDetail pageDetail = criteria.getPaginationDetail();
		if (null != pageDetail) {
			logger.debug(
					" pagination detail - start:{}, limit:{}",
					new Object[] { pageDetail.getStart(), pageDetail.getLimit() });
		}

		Query query = getFetchRewardsItemsStoreQuery(SEARCH, criteria);

		result = query.getResultList();
		logger.debug("finished by fetchRewardsItems method. result.size:{}",
				result.size());
		return result;
	}

	public int countRewardsItemsStore(RewardItemSearchVo criteria) {
		logger.debug(
				" Process in countRewardsItemsStore method, parameter : {}",
				criteria.toString());
		int count = 0;
		Query query = getFetchRewardsItemsStoreQuery(COUNT, criteria);
		count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(
				" finshed by countRewardsItemsStore method, result count : {}",
				count);
		return count;
	}

	private Query getFetchRewardsItemsStoreQuery(String type,
			RewardItemSearchVo criteria) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();
		System.out.println("RewardItemSearchVo : " + criteria);
		if (SEARCH.equals(type)) {
			eql.append(" SELECT item FROM RewardItemStore item WHERE 1 = 1 and  item.deleted=:deleted");
		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(item) FROM RewardItemStore item WHERE 1 = 1 and  item.deleted=:deleted");
		}
		param.put("deleted", false);
		if (!StringUtil.isEmptyString(criteria.getAccountDeptName())) {
			eql.append(" AND item.accountDept IN (FROM Department dept WHERE UPPER(dept.name) LIKE :accountDeptName) ");
			param.put("accountDeptName", "%"
					+ criteria.getAccountDeptName().trim().toUpperCase() + "%");
		}
		if (!StringUtil.isEmptyString(criteria.getBuildDeptName())) {
			eql.append(" AND item.builderDept IN (FROM Department dept WHERE UPPER(dept.name) LIKE :buildDeptName) ");
			param.put("buildDeptName", "%"
					+ criteria.getBuildDeptName().trim().toUpperCase() + "%");
		}
		// 根据部门id来查询
		if (null != criteria.getDeptIds() && !criteria.getDeptIds().isEmpty()) {
			eql.append(" AND item.builderDept.id IN (:deptIds)");
			param.put("deptIds", criteria.getDeptIds());
		}
		// 根据企业id来查询
		if (!StringUtil.isEmptyString(criteria.getCorporationId())) {
			eql.append(" AND item.corporation.id = :corporationId");
			param.put("corporationId", criteria.getCorporationId());
		}
		if (!StringUtil.isEmptyString(criteria.getDefinition())) {
			eql.append(" AND UPPER(item.definition) LIKE :definition ");
			param.put("definition", "%"
					+ criteria.getDefinition().trim().toUpperCase() + "%");

		}
		// 根据创建时间来查询
		if (null != criteria.getCreateTime()
				&& !criteria.getCreateTime().equals("")
				&& null != criteria.getCreateTimeEnd()
				&& !criteria.getCreateTimeEnd().equals("")) {
			eql.append(" and ( item.createdAt  between :createTime and :createdAtEnd)");
			param.put("createTime", criteria.getCreateTime());
			param.put("createdAtEnd", criteria.getCreateTimeEnd());

		}

		if (!StringUtil.isEmptyString(criteria.getName())) {
			eql.append(" AND UPPER(item.name) LIKE :name ");
			param.put("name", "%" + criteria.getName().trim().toUpperCase()
					+ "%");
		}
		if (!StringUtil.isEmptyString(criteria.getStandard())) {
			eql.append(" AND UPPER(item.standard) LIKE :standard ");
			param.put("standard", "%"
					+ criteria.getStandard().trim().toUpperCase() + "%");
		}
		if (!StringUtil.isEmptyString(criteria.getTypeName())) {
			eql.append(" AND item.type IN (FROM RewardType type WHERE UPPER(type.name) LIKE :typeName) ");
			param.put("typeName", "%"
					+ criteria.getTypeName().trim().toUpperCase() + "%");
		}

		if (SEARCH.equals(type)) {
			if (null != criteria && null != criteria.getSortingDetail()) {
				eql.append(" ORDER BY item."
						+ criteria.getSortingDetail().getSort() + " "
						+ criteria.getSortingDetail().getDirection());
			}
		}

		System.out.println("EQL : " + eql);
		Query query = getEm().createQuery(eql.toString());
		if (SEARCH.equals(type)) {
			if (criteria.getPaginationDetail() != null) {
				int start = criteria.getPaginationDetail().getStart();
				int limit = criteria.getPaginationDetail().getLimit();
				logger.debug(" paginationDetail - start:{}, limit:{}",
						new Object[] { start, limit });

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

	/**
	 * <pre>
	 * 查找所有的奖项有下次运行时间,并且下次奖励时间是在今天范围内
	 * (2010-01-09 00:00:00 --- 2010-01-09-23:59:59)
	 * 并且这个奖项有周期性设置
	 * 
	 * <pre>
	 * 
	 * @param currTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RewardItem> findAllNeedToOperatorAutoRewardsItem(Date currTime) {
		Date lastDay = DateUtil.getLastTimeOfThisDay(currTime);
		logger.debug(" process findAllNeedToOperatorAutoRewardsItem method,parameter currTime:"
				+ DateUtil.formatData(null, currTime)
				+ ",lastDay:"
				+ DateUtil.formatData(null, lastDay));
		List<RequireAutoGenerate> autoList = new ArrayList<RequireAutoGenerate>();
		autoList.add(RequireAutoGenerate.requireCyclic);
		autoList.add(RequireAutoGenerate.requireOneOff);

		return getEm()
				.createQuery(
						" FROM RewardItem item WHERE 1=1"
								// +
								// " (item.startTime<=:currTime OR item.startTime IS NULL) AND (item.endTime>=:currTime OR item.endTime IS NULL) "
								// +
								// " AND (item.endTime>=:currTime OR item.endTime IS NULL)"
								+ " AND item.nexRunBatchTime<=:lastDay "
								+ " AND item.autoGenerate in (:autoList)"
								+ " AND item.enabled = :flag")
				.setParameter("lastDay", lastDay)
				.setParameter("autoList", autoList).setParameter("flag", true)
				.getResultList();
	}

	/**
	 * 得到奖项最后一次颁奖的 时间
	 * 
	 * @param rewardItemId
	 * @return
	 */
	public Date getLastRewardDate(String rewardItemId) {
		return getEm().find(RewardItem.class, rewardItemId).getLastAwardDate();
	}
}
