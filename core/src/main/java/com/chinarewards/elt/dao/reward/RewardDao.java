package com.chinarewards.elt.dao.reward;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.dao.user.UserDao;
import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.Organization;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.reward.base.PreWinnerLotStatus;
import com.chinarewards.elt.model.reward.base.RewardStatus;
import com.chinarewards.elt.model.reward.search.RewardSearchVo;
import com.chinarewards.elt.service.org.DepartmentLogic;
import com.chinarewards.elt.util.StringUtil;
import com.google.inject.Inject;

/**
 * DAO of {@link Reward}
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardDao extends BaseDao<Reward> {

	/**
	 * XXX here appear logic, it is strange!
	 */
	DepartmentLogic departmentLogic;
	UserDao userDao;

	@Inject
	public RewardDao(DepartmentLogic departmentLogic, UserDao userDao) {
		this.departmentLogic = departmentLogic;
		this.userDao = userDao;
	}

	/**
	 * Find tx account id of the specified Reward.
	 * 
	 * @param rewardId
	 * @return
	 */
	public String findCorporationTxIdByRewardId(String rewardId) {
		return (String) getEm()
				.createQuery(
						"SELECT r.corporation.txAccountId FROM Reward r WHERE r.id = :rewardId")
				.setParameter("rewardId", rewardId).getSingleResult();
	}

	public PageStore<Reward> searchRewards_hrManager(String corporationId,
			RewardSearchVo criteria) {
		// 是否查某部门所有子部门下的数据
		if (!StringUtil.isEmptyString(criteria.getBuilderDeptId())
				&& criteria.isSubDepartmentChosen()) {
			List<String> childrenIds = departmentLogic.getWholeChildrenIds(
					criteria.getBuilderDeptId(), true);
			criteria.setDeptIds(new ArrayList<String>(childrenIds));
		}
		PageStore<Reward> res = new PageStore<Reward>();
		List<Reward> list = searchRewardsData_hrManager(corporationId, criteria);
		int count = searchRewardsCount_hrManager(corporationId, criteria);
		res.setResultList(list);
		res.setResultCount(count);
		return res;
	}

	public PageStore<Reward> searchRewards_departmentId(
			List<String> departmentIds, RewardSearchVo criteria) {
		// 是否查某部门所有子部门下的数据
		// if (!StringUtil.isEmptyString(criteria.getBuilderDeptId())
		// && criteria.isSubDepartmentChosen()) {
		// List<String> childrenIds = departmentLogic.getWholeChildrenIds(
		// criteria.getBuilderDeptId(), true);
		// criteria.setDeptIds(new ArrayList<String>(childrenIds));
		// }
		PageStore<Reward> res = new PageStore<Reward>();
		List<Reward> list = searchRewardsData_departmentId(departmentIds,
				criteria);
		int count = searchRewardsCount_departmentId(departmentIds, criteria);
		res.setResultList(list);
		res.setResultCount(count);
		return res;
	}

	public PageStore<Reward> searchRewards_staff(String staffId,
			RewardSearchVo criteria) {
		PageStore<Reward> res = new PageStore<Reward>();
		List<Reward> list = searchRewardsData_staff(staffId, criteria);
		int count = searchRewardsCount_staff(staffId, criteria);
		res.setResultList(list);
		res.setResultCount(count);
		return res;
	}

	@SuppressWarnings("unchecked")
	public List<Reward> searchRewardsData_hrManager(String corporationId,
			RewardSearchVo criteria) {
		return generatorSearchRewardsQuery(true, corporationId, null, SEARCH,
				criteria).getResultList();
	}

	private int searchRewardsCount_hrManager(String corporationId,
			RewardSearchVo criteria) {
		return Integer.parseInt(generatorSearchRewardsQuery(true,
				corporationId, null, COUNT, criteria).getSingleResult()
				.toString());
	}

	@SuppressWarnings("unchecked")
	private List<Reward> searchRewardsData_departmentId(
			List<String> departmentIds, RewardSearchVo criteria) {
		return generatorSearchRewardsQuery(false, null, departmentIds, SEARCH,
				criteria).getResultList();
	}

	private int searchRewardsCount_departmentId(List<String> departmentIds,
			RewardSearchVo criteria) {
		return Integer.parseInt(generatorSearchRewardsQuery(false, null,
				departmentIds, COUNT, criteria).getSingleResult().toString());
	}

	@SuppressWarnings("unchecked")
	public List<Reward> searchRewardsData_staff(String staffId,
			RewardSearchVo criteria) {
		return generatorSearchRewardsQuery(staffId, SEARCH, criteria)
				.getResultList();
	}

	public int searchRewardsCount_staff(String staffId, RewardSearchVo criteria) {
		return Integer.parseInt(generatorSearchRewardsQuery(staffId, COUNT,
				criteria).getSingleResult().toString());
	}

	private Query generatorSearchRewardsQuery(boolean isHrManager,
			String corporationId, List<String> departmentIds, String type,
			RewardSearchVo criteria) {
		StringBuffer hql = new StringBuffer();
		if (SEARCH.equals(type)) {
			hql.append(" FROM Reward rew WHERE 1=1 ");
		} else if (COUNT.equals(type)) {
			hql.append("SELECT COUNT(rew) FROM Reward rew WHERE 1=1 ");
		}

		Map<String, Object> param = new HashMap<String, Object>();
		if (isHrManager) {
			hql.append(" AND rew.corporation.id=:corporationId ");
			param.put("corporationId", corporationId);
		} else {
			hql.append(" AND rew.builderDept.id IN (:departmentIds) ");
			param.put("departmentIds", departmentIds);
		}

		if (criteria.getStatus() != null) {
			List<RewardStatus> rstatus = new ArrayList<RewardStatus>();
			if (criteria.getStatus() == RewardStatus.PENDING_NOMINATE
					|| criteria.getStatus() == RewardStatus.NEW) {
				rstatus.add(RewardStatus.PENDING_NOMINATE);
				rstatus.add(RewardStatus.NEW);
				hql.append(" AND rew.status IN (:status)");
				param.put("status", rstatus);
			} else {
				hql.append(" AND rew.status = :status");
				param.put("status", criteria.getStatus());
			}

		} else {
			hql.append(" AND rew.status != :status");
			param.put("status", RewardStatus.REWARDED);
		}

		if (!StringUtil.isEmptyString(criteria.getName())) {
			hql.append(" AND ( Upper(rew.name) LIKE Upper(:key))");
			param.put("key", "%" + criteria.getName() + "%");
		}

		if (!StringUtil.isEmptyString(criteria.getDefinition())) {
			hql.append(" AND ( Upper(rew.definition) LIKE Upper(:definition))");
			param.put("definition", "%" + criteria.getDefinition() + "%");
		}

		// hql.append(" AND 0 <> (SELECT COUNT(*) FROM Winners win WHERE win.rewards=rew) ");
		// hql.append(" AND rew.status = :status");
		// param.put("status", RewardsStatus.REWARDED);

		// 增加新的查询条件
		addQueryCondition(hql, param, criteria);

		// 过滤已删除的数据
		hql.append(" AND rew.deleted != :deleted");
		param.put("deleted", true);

		if (SEARCH.equals(type)) {
			if (null != criteria && null != criteria.getSortingDetail()) {
				hql.append(" ORDER BY rew.");
				hql.append(criteria.getSortingDetail().getSort());
				hql.append(" ");
				hql.append(criteria.getSortingDetail().getDirection());
			} else {
				hql.append(" ORDER BY rew.createdAt DESC ");
			}
		}

		logger.debug(" EQL :" + hql);
		Query query = getEm().createQuery(hql.toString());
		if (param.size() > 0) {
			Set<String> key = param.keySet();
			for (String s : key) {
				query.setParameter(s, param.get(s));
			}
		}

		if (SEARCH.equals(type) && null != criteria
				&& null != criteria.getPaginationDetail()) {
			int start = criteria.getPaginationDetail().getStart();
			int limit = criteria.getPaginationDetail().getLimit();
			logger.debug(" pagination detail - start:{}, limit:{}",
					new Object[] { start, limit });
			query.setFirstResult(start);
			query.setMaxResults(limit);
		}

		return query;
	}

	private Query generatorSearchRewardsQuery(String staffId, String type,
			RewardSearchVo criteria) {
		StringBuffer hql = new StringBuffer();
		if (SEARCH.equals(type)) {
			hql.append(" FROM Reward rew WHERE 1=1 ");
		} else if (COUNT.equals(type)) {
			hql.append("SELECT COUNT(rew) FROM Reward rew WHERE 1=1 ");
		}

		Map<String, Object> param = new HashMap<String, Object>();

		if (criteria.getStatus() != null) {
			List<RewardStatus> rstatus = new ArrayList<RewardStatus>();
			if (criteria.getStatus() == RewardStatus.PENDING_NOMINATE
					|| criteria.getStatus() == RewardStatus.NEW) {
				rstatus.add(RewardStatus.PENDING_NOMINATE);
				rstatus.add(RewardStatus.NEW);
				hql.append(" AND rew.status IN (:status)");
				param.put("status", rstatus);
			} else {
				hql.append(" AND rew.status = :status");
				param.put("status", criteria.getStatus());
			}

		} else {
			hql.append(" AND rew.status != :status");
			param.put("status", RewardStatus.REWARDED);
		}

		if (!StringUtil.isEmptyString(criteria.getName())) {
			hql.append(" AND ( Upper(rew.name) LIKE Upper(:key))");
			param.put("key", "%" + criteria.getName() + "%");
		}

		if (!StringUtil.isEmptyString(criteria.getDefinition())) {
			hql.append(" AND ( Upper(rew.definition) LIKE Upper(:definition))");
			param.put("definition", "%" + criteria.getDefinition() + "%");
		}
		// 增加新的查询条件
//		addQueryCondition(hql, param, criteria);

		// 过滤已删除的数据
		hql.append(" AND rew.deleted != :deleted");
		param.put("deleted", true);

		if (SEARCH.equals(type)) {
			if (null != criteria && null != criteria.getSortingDetail()) {
				hql.append(" ORDER BY rew.");
				hql.append(criteria.getSortingDetail().getSort());
				hql.append(" ");
				hql.append(criteria.getSortingDetail().getDirection());
			} else {
				hql.append(" ORDER BY rew.createdAt DESC ");
			}
		}

		logger.debug(" EQL :" + hql);
		Query query = getEm().createQuery(hql.toString());
		if (param.size() > 0) {
			Set<String> key = param.keySet();
			for (String s : key) {
				query.setParameter(s, param.get(s));
			}
		}

		if (SEARCH.equals(type) && null != criteria
				&& null != criteria.getPaginationDetail()) {
			int start = criteria.getPaginationDetail().getStart();
			int limit = criteria.getPaginationDetail().getLimit();
			logger.debug(" pagination detail - start:{}, limit:{}",
					new Object[] { start, limit });
			query.setFirstResult(start);
			query.setMaxResults(limit);
		}

		return query;
	}

	private void addQueryCondition(StringBuffer hql, Map<String, Object> param,
			RewardSearchVo criteria) {
		if (null != criteria) {

			if (!StringUtil.isEmptyString(criteria.getAccountDeptId())) {
				hql.append(" AND rew.accountDept.id = :accountDeptId ");
				param.put("accountDeptId", criteria.getAccountDeptId());

			}
			// XXX what is this!?!?
			if (!StringUtil.isEmptyString(criteria.getBuilderDeptId())) {
				if (!criteria.isSubDepartmentChosen()) {
					hql.append(" AND rew.builderDept.id = :builderDeptId ");
					param.put("builderDeptId", criteria.getBuilderDeptId());
				} else {
					// XXX should not overload getDeptIds() with
					// SubDepartmentChose! Add getBuilderDeptsIds()!
					hql.append(" AND rew.builderDept.id IN (:deptIds) ");
					param.put("deptIds", criteria.getDeptIds());
				}
			}
			if (!StringUtil.isEmptyString(criteria.getDefinition())) {
				hql.append(" AND UPPER(rew.definition) LIKE :definition ");
				param.put("definition", "%"
						+ criteria.getDefinition().trim().toUpperCase() + "%");
			}

			if (!StringUtil.isEmptyString(criteria.getName())) {
				hql.append(" AND UPPER(rew.name) LIKE :name ");
				param.put("name", "%" + criteria.getName().trim().toUpperCase()
						+ "%");
			}
			if (!StringUtil.isEmptyString(criteria.getCorporationId())) {
				hql.append(" AND rew.corporation.id = :corporationId ");
				param.put("corporationId", criteria.getCorporationId());
			}
			if (null != criteria.getAwardFrom()) {
				hql.append(" AND rew.awardAmt >= :awardFrom ");
				param.put("awardFrom", criteria.getAwardFrom());
			}
			if (null != criteria.getAwardTo()) {
				hql.append(" AND rew.awardAmt <= :awardTo ");
				param.put("awardTo", criteria.getAwardTo());
			}
			if (!StringUtil.isEmptyString(criteria.getRewardItemId())) {
				logger.debug("rewardItemId:{}", criteria.getRewardItemId());
				hql.append(" AND rew.rewardItem.id = :rewardItemId ");
				param.put("rewardItemId", criteria.getRewardItemId());
			}
			if (null != criteria.getAwardUnit()) {
				hql.append(" AND rew.rewardUnit = :rewardUnit ");
				param.put("rewardUnit", criteria.getAwardUnit());
			}
			if (!StringUtil.isEmptyString(criteria.getStandard())) {
				hql.append(" AND rew.standard LIKE :standard ");
				param.put("standard", "%"
						+ criteria.getStandard().trim().toUpperCase() + "%");
			}
			logger.debug("staffId:{}", criteria.getWinnerStaffId());
			if (!StringUtil.isEmptyString(criteria.getWinnerStaffId())) {
				hql.append(" AND rew.id IN(SELECT w.reward.id FROM Winner w WHERE w.staff.id = :staffId)");
				param.put("staffId", criteria.getWinnerStaffId());
			}

			// 添加当前用户需要提名的数据
			if (!StringUtil.isEmptyString(criteria.getJudgeUserId())) {
				String staffid = userDao
						.findById(SysUser.class, criteria.getJudgeUserId())
						.getStaff().getId();
				hql.append(" AND rew.id IN(SELECT w.reward.id FROM Judge w WHERE w.staff.id = :judgeUserId)");
				param.put("judgeUserId", staffid);
			}
		}
	}

	/**
	 * 得到某公司/部门 一段时间内的实际奖励总金额(不包括未审核的奖励)
	 * 
	 * @param org
	 * @param since
	 * @param until
	 */
	public double getActualRewardsMoney(Organization org, Date since, Date until) {
		StringBuffer hql = new StringBuffer(
				"SELECT SUM(w.awardAmount) FROM Winner w WHERE 1=1");
		Map<String, Object> params = new HashMap<String, Object>();
		if (since != null) {
			hql.append(" AND w.reward.awardDate >= :since ");
			params.put("since", since);
		}
		if (until != null) {
			hql.append(" AND w.reward.awardDate < :until ");
			params.put("until", until);
		}
		if (org instanceof Corporation) {
			hql.append(" AND w.reward.organization.id = :corporationId");
			params.put("corporationId", org.getId());
		} else if (org instanceof Department) {
			List<String> childrenIds = departmentLogic.getWholeChildrenIds(
					org.getId(), true);
			hql.append(" AND w.reward.accountDept.id IN (:departmentIds)");
			params.put("departmentIds", childrenIds);
		} else if (org instanceof Staff) {
			throw new UnsupportedOperationException(" not support Staff case ");
		}

		Query q = getEm().createQuery(hql.toString());
		for (String key : params.keySet()) {
			q.setParameter(key, params.get(key));
		}
		double d = 0L;
		if (q.getSingleResult() != null) {
			d = Double.parseDouble(q.getSingleResult().toString());
		}
		return d;
	}

	/**
	 * 得到某公司/部门 一段时间内的所有奖励总金额(包括未审核的奖励)
	 * 
	 * FIXME this interface does not respect the currency.
	 * 
	 * @param org
	 * @param since
	 * @param until
	 * @param exceptRewardId
	 *            --不能包含该奖励的钱
	 */
	public double getTotalRewardsMoney(Organization org, Date since,
			Date until, String exceptRewardId) {
		StringBuffer hql = new StringBuffer(
				"SELECT SUM(w.awardAmount) FROM PreWinner w WHERE 1=1");
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" AND w.preWinnerLot.status in (:statusList)");
		//
		List<PreWinnerLotStatus> statusList = new ArrayList<PreWinnerLotStatus>();
		{
			statusList.add(PreWinnerLotStatus.NEW);
			statusList.add(PreWinnerLotStatus.PASS);
			params.put("statusList", statusList);
		}
		//
		hql.append(" AND w.reward.status in (:rewardStatusList) ");
		{
			List<RewardStatus> l = new ArrayList<RewardStatus>();
			l.add(RewardStatus.PENDING_APPLICATION);
			l.add(RewardStatus.REWARDED);
			params.put("rewardStatusList", l);
		}

		if (!StringUtil.isEmptyString(exceptRewardId)) {
			hql.append(" AND w.reward.id <> :exceptRewardId");
			params.put("exceptRewardId", exceptRewardId);
		}

		//
		if (since != null) {
			hql.append(" AND w.reward.awardDate >= :since ");
			params.put("since", since);
		}
		if (until != null) {
			hql.append(" AND w.reward.awardDate < :until ");
			params.put("until", until);
		}
		if (org instanceof Corporation) {
			hql.append(" AND w.reward.organization.id = :corporationId");
			params.put("corporationId", org.getId());
		} else if (org instanceof Department) {
			List<String> childrenIds = departmentLogic.getWholeChildrenIds(
					org.getId(), true);
			logger.debug("childrenIds = {}", childrenIds);
			hql.append(" AND w.reward.accountDept.id IN (:departmentIds)");
			params.put("departmentIds", childrenIds);
		} else if (org instanceof Staff) {
			throw new UnsupportedOperationException(" not support Staff case ");
		}

		Query q = getEm().createQuery(hql.toString());
		for (String key : params.keySet()) {
			q.setParameter(key, params.get(key));
		}
		double d = 0L;
		if (q.getSingleResult() != null) {
			d = Double.parseDouble(q.getSingleResult().toString());
		}
		return d;
	}

	@SuppressWarnings("unchecked")
	public List<Reward> getRewardsByRewardsItem(String rewardItemId) {
		return getEm()
				.createQuery(
						" FROM Reward r WHERE r.rewardItem.id = :rewardItemId")
				.setParameter("rewardItemId", rewardItemId).getResultList();
	}
	@SuppressWarnings("unchecked")
	public int getNominatorByStaffId(String staffId) {
		List<Judge> judgeList = getEm()
				.createQuery(
						"select j FROM Judge j,Reward r WHERE j.reward.id =r.id and j.status='NONE' and r.status='PENDING_NOMINATE'  AND j.staff.id =:staffId")
				.setParameter("staffId", staffId).getResultList();
		
		return judgeList.size();
		
	}
	@SuppressWarnings("unchecked")
	public int getRewardsByStaffId(String staffId) {
		List<Judge> judgeList = getEm()
				.createQuery(
						"select j FROM Judge j,Reward r WHERE j.reward.id =r.id and j.status='NOMINATED' and r.status='NEW'  AND j.staff.id =:staffId")
				.setParameter("staffId", staffId).getResultList();
		
		return judgeList.size();
		
	}
		
	@SuppressWarnings("unchecked")
	public  List<Reward> hrBoxRewards(String corporationId,RewardSearchVo criteria) {
		String sql = "FROM Reward rew  WHERE rew.corporation.id = :corporationId AND rew.status = :status  and rew.deleted = :deleted and rew.lastModifiedAt between :lastmonth and sysdate ";
		
		List<Reward> resultList = getEm().createQuery(sql)
				.setParameter("corporationId", corporationId)
				.setParameter("status", criteria.getStatus())
				.setParameter("deleted", false)
				.setParameter("lastmonth", criteria.getLastMonth())
				.getResultList();
		
		return resultList;
		
	}
}
