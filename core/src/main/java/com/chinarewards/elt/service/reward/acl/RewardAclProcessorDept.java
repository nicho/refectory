package com.chinarewards.elt.service.reward.acl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.chinarewards.elt.dao.org.DepartmentManagerDao;
import com.chinarewards.elt.dao.reward.RewardDao;
import com.chinarewards.elt.dao.reward.RewardItemDao;
import com.chinarewards.elt.dao.reward.RewardItemStoreDao;
import com.chinarewards.elt.dao.user.UserDao;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.base.RewardItemStore;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.reward.search.RewardItemSearchVo;
import com.chinarewards.elt.model.reward.search.RewardSearchVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.service.org.DepartmentLogic;
import com.chinarewards.elt.util.StringUtil;
import com.google.inject.Inject;

public class RewardAclProcessorDept extends AbstractRewardAclProcessor {
	private final RewardDao rewardsDao;
	private final UserDao userDao;
	private final RewardItemDao rewardsItemDao;
	private final DepartmentManagerDao deptMgrDao;
	private final DepartmentLogic departmentLogic;
	private final RewardItemStoreDao rewardsItemStoreDao;

	@Inject
	public RewardAclProcessorDept(RewardDao rewardsDao, UserDao userDao,
			RewardItemDao rewardsItemDao, DepartmentManagerDao deptMgrDao,
			DepartmentLogic departmentLogic,
			RewardItemStoreDao rewardsItemStoreDao) {
		this.rewardsDao = rewardsDao;
		this.userDao = userDao;
		this.rewardsItemDao = rewardsItemDao;
		this.deptMgrDao = deptMgrDao;
		this.departmentLogic = departmentLogic;
		this.rewardsItemStoreDao = rewardsItemStoreDao;
	}

	private List<String> getSupportedDeptIds(String staffId) {
		List<String> departmentIds = deptMgrDao
				.findDepartmentIdsManagedByStaffId(staffId);

		Set<String> allDepartmentIds = new HashSet<String>();
		for (String id : departmentIds) {
			allDepartmentIds.addAll(departmentLogic.getWholeChildrenIds(id,
					true));
		}
		departmentIds.clear();
		departmentIds.addAll(allDepartmentIds);
		logger.debug(
				" Department IDs (include siblings) managed by this user: {}",
				departmentIds);

		return departmentIds;
	}

	@Override
	public PageStore<RewardItem> fetchRewardItems(UserContext context,
			RewardItemSearchVo criteria) {
		logger.debug(
				" Process in fetchRewardsItems method, UserId:{}, criteria:{}",
				new Object[] { context.getUserId(), criteria });
		SysUser sysUser = userDao.findById(SysUser.class, context.getUserId());

		List<UserRole> roles = new ArrayList<UserRole>(Arrays.asList(context
				.getUserRoles()));

		logger.debug("Is department manager:{}",
				roles.contains(UserRole.DEPT_MGR));

		logger.debug("criteria.getDeptIds() = {}", criteria.getDeptIds());

		// serialize to a list of department IDs.
		if (null != criteria.getDeptIds() && !criteria.getDeptIds().isEmpty()) {
			// The depIds have priority. If it exist, do not need to observe
			// departmentId again.
		} else if (!StringUtil.isEmptyString(criteria.getDepartmentId())) {
			List<String> deptIds = null;
			logger.debug("criteria.isSubDepartmentChoose: {}",
					criteria.isSubDepartmentChosen());
			if (criteria.isSubDepartmentChosen()) {
				deptIds = departmentLogic.getWholeChildrenIds(
						criteria.getDepartmentId(), true);
				logger.debug("Siblings dept IDs of {}: {}",
						criteria.getDepartmentId(), deptIds);
			} else {
				deptIds = new ArrayList<String>();
				deptIds.add(criteria.getDepartmentId());
			}
			criteria.setDeptIds(new ArrayList<String>(deptIds));
			criteria.setSubDepartmentChosen(false);
		}

		logger.debug(
				"criteria.getDeptIds() after resolving child departments = {}",
				criteria.getDeptIds());

		// Strip out any invisible departments

		List<String> expectedDeptIds = getSupportedDeptIds(sysUser.getStaff()
				.getId());

		if (criteria.getDeptIds() != null && !criteria.getDeptIds().isEmpty()) {
			List<String> ids = new ArrayList<String>();
			ids.addAll(criteria.getDeptIds());
			ids.retainAll(expectedDeptIds);
			criteria.setDeptIds(ids);
			logger.debug("After stripping, criteria.getDeptIds()={}",
					criteria.getDeptIds());
			// if we made it empty, no hope
			if (ids.isEmpty()) {
				PageStore<RewardItem> pageStore = new PageStore<RewardItem>();
				pageStore.setResultCount(0);
				pageStore.setResultList(new ArrayList<RewardItem>());
				return pageStore;
			}
		} else {
			criteria.setDeptIds(expectedDeptIds);
		}

		PageStore<RewardItem> pageStore = new PageStore<RewardItem>();
		pageStore.setResultCount(rewardsItemDao.countRewardsItems(criteria));
		List<RewardItem> itemList = rewardsItemDao.fetchRewardsItems(criteria);
		pageStore.setResultList(itemList);
		return pageStore;
	}
	


	@Override
	// 奖项库查询
	public PageStore<RewardItemStore> fetchRewardItemsStore(
			UserContext context, RewardItemSearchVo criteria) {// 与HR的一样不过滤

		logger.debug(
				" Process in fetchRewardsItems method, UserId:{}, criteria:{}",
				new Object[] { context.getUserId(), criteria });
		SysUser sysUser = userDao.findById(SysUser.class, context.getUserId());

		List<UserRole> roles = new ArrayList<UserRole>(Arrays.asList(context
				.getUserRoles()));

		logger.debug("Is department manager:{}",
				roles.contains(UserRole.DEPT_MGR));

		logger.debug("criteria.getDeptIds() = {}", criteria.getDeptIds());

		// Should not pollute the input object! Clone it!
		if (null != criteria.getDeptIds() && !criteria.getDeptIds().isEmpty()) {
			// The depIds have priority. If it exist, do not need to observe
			// departmentId again.
		} else if (!StringUtil.isEmptyString(criteria.getDepartmentId())) {
			List<String> deptIds = null;
			if (criteria.isSubDepartmentChosen()) {
				deptIds = departmentLogic.getWholeChildrenIds(
						criteria.getDepartmentId(), true);
			} else {
				deptIds = new ArrayList<String>();
				deptIds.add(criteria.getDepartmentId());
			}
			criteria.setDeptIds(new ArrayList<String>(deptIds));
			criteria.setSubDepartmentChosen(false); // since we have converted
													// it.
		}

		logger.debug(
				"criteria.getDeptIds() after resolving child departments = {}",
				criteria.getDeptIds());

		List<String> expectedDeptIds = getSupportedDeptIds(sysUser.getStaff()
				.getId());

		if (criteria.getDeptIds() != null && !criteria.getDeptIds().isEmpty()) {
			List<String> ids = new ArrayList<String>();
			ids.addAll(criteria.getDeptIds());
			ids.retainAll(expectedDeptIds);
			criteria.setDeptIds(ids);
			logger.debug("After stripping, criteria.getDeptIds()={}",
					criteria.getDeptIds());
			if (ids.isEmpty()) {
				PageStore<RewardItemStore> pageStore = new PageStore<RewardItemStore>();
				pageStore.setResultCount(rewardsItemStoreDao
						.countRewardsItemsStore(criteria));
				List<RewardItemStore> itemList = rewardsItemStoreDao
						.fetchRewardsItemsStore(criteria);
				pageStore.setResultList(itemList);
				return pageStore;
			}
		} else {
			criteria.setDeptIds(expectedDeptIds);
		}

		PageStore<RewardItemStore> pageStore = new PageStore<RewardItemStore>();
		pageStore.setResultCount(rewardsItemStoreDao
				.countRewardsItemsStore(criteria));
		List<RewardItemStore> itemList = rewardsItemStoreDao
				.fetchRewardsItemsStore(criteria);
		pageStore.setResultList(itemList);
		return pageStore;
	}

	@Override
	public PageStore<Reward> fetchRewards(UserContext context,
			RewardSearchVo criteria) {
		logger.debug(" Process in StaffRoleProcessor fetchRewards method,UserId:"
				+ context.getUserId());
		PageStore<Reward> res = new PageStore<Reward>();

		SysUser hrUser = userDao.findById(SysUser.class, context.getUserId());

		List<UserRole> roles = new ArrayList<UserRole>(Arrays.asList(context
				.getUserRoles()));

		logger.debug("Is department manager:{}",
				roles.contains(UserRole.DEPT_MGR));

		logger.debug(" This HrUser is department manager Staff ");
		List<String> departmentIds = deptMgrDao
				.findDepartmentIdsManagedByStaffId(hrUser.getStaff().getId());
		if (departmentIds.size() > 0) {
			Set<String> allDepartmentIds = new HashSet<String>();
			for (String id : departmentIds) {
				allDepartmentIds.addAll(departmentLogic.getWholeChildrenIds(id,
						true));
			}
			departmentIds.clear();
			departmentIds.addAll(allDepartmentIds);

			logger.debug(" This Hruser manager department.id:" + departmentIds);
			res = rewardsDao
					.searchRewards_departmentId(departmentIds, criteria);
		}

		return res;
	}

}
