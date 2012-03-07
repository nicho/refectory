package com.chinarewards.elt.service.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.RequireApproval;
import com.chinarewards.elt.model.reward.base.RequireAutoAward;
import com.chinarewards.elt.model.reward.base.RequireAutoGenerate;
import com.chinarewards.elt.model.reward.base.RewardItemParam;
import com.chinarewards.elt.model.reward.frequency.WeekDays;
import com.chinarewards.elt.model.reward.frequency.WeeklyVo;
import com.chinarewards.elt.service.reward.RewardItemLogic;
import com.google.inject.Injector;

/**
 * Provides useful {@link RewardItem}
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardItemHelper {

	private static RewardItem defaultItem;
	private static RewardItem nominateItem;
	private static RewardItem autoAwardItem;

	/**
	 * Get a default instance of {@link RewardItem}which would do nothing.
	 * 
	 * @return
	 */
	public static RewardItem getDefaultRewardItem(Injector injector) {
		// need some services
		RewardItemLogic rewardItemLogic = injector
				.getInstance(RewardItemLogic.class);
		EntityManager em = injector.getInstance(EntityManager.class);

		if (defaultItem != null
				&& em.find(RewardItem.class, defaultItem.getId()) != null)
			return defaultItem;

		// Prepare corporation&& em.find(RewardItem.class, defaultItem.getId())
		// != null
		Corporation corp = CorporationHelper.getDefaultCorporation(injector);
		// Prepare department
		Department dept = DepartmentHelper.getDefaultDept(injector);
		// Allow no approval
		DepartmentHelper.permitDepartmentNoApproval(dept.getId(), injector);
		// Prepare Staff list
		List<Staff> staffList = StaffHelper.getDefaultStaffList(injector);

		RewardItemParam param = RewardItemHelper.prepareRewardItem(corp, dept,
				staffList);
		param.setAutoAward(RequireAutoAward.requireNone);

		// Prepare user
		SysUser caller = UserHelper.getDefaultUser(injector);
		// create rewarditem
		defaultItem = rewardItemLogic.saveRewardItem(caller, param);
		return defaultItem;

	}

	/**
	 * Get a default instance of {@link RewardItem}which need to nominate.
	 * 
	 * @return
	 */
	public static RewardItem getDefaultNominateRewardItem(Injector injector) {
		// need some services
		RewardItemLogic rewardItemLogic = injector
				.getInstance(RewardItemLogic.class);
		EntityManager em = injector.getInstance(EntityManager.class);

		if (nominateItem != null
				&& em.find(RewardItem.class, defaultItem.getId()) != null)
			return nominateItem;

		// Prepare corporation
		Corporation corp = CorporationHelper.getDefaultCorporation(injector);
		// Prepare department
		Department dept = DepartmentHelper.getDefaultDept(injector);
		// Allow no approval
		DepartmentHelper.permitDepartmentNoApproval(dept.getId(), injector);
		// Prepare Staff list
		List<Staff> staffList = StaffHelper.getDefaultStaffList(injector);

		RewardItemParam param = RewardItemHelper.prepareRewardItem(corp, dept,
				staffList);
		// Prepare judges
		List<String> judgeList = new ArrayList<String>();
		for (Staff s : staffList) {
			judgeList.add(s.getId());
		}
		param.setJudgeIds(judgeList);
		param.setAutoAward(RequireAutoAward.requireNominate);

		// Prepare user
		SysUser caller = UserHelper.getDefaultUser(injector);
		// create rewarditem
		nominateItem = rewardItemLogic.saveRewardItem(caller, param);
		return nominateItem;
	}

	/**
	 * Get a default instance of {@link RewardItem}which need to nominate.
	 * 
	 * @return
	 */
	public static RewardItem getDefaultAutoAwardRewardItem(Injector injector,
			RequireApproval require) {
		// need some services
		RewardItemLogic rewardItemLogic = injector
				.getInstance(RewardItemLogic.class);
		EntityManager em = injector.getInstance(EntityManager.class);

		if (autoAwardItem != null
				&& em.find(RewardItem.class, defaultItem.getId()) != null)
			return autoAwardItem;

		// Prepare corporation
		Corporation corp = CorporationHelper.getDefaultCorporation(injector);
		// Prepare department
		Department dept = DepartmentHelper.getDefaultDept(injector);
		if (RequireApproval.None == require) {
			// Allow no approval
			DepartmentHelper.permitDepartmentNoApproval(dept.getId(), injector);
		}
		// Prepare Staff list
		List<Staff> staffList = StaffHelper.getDefaultStaffList(injector);

		RewardItemParam param = RewardItemHelper.prepareRewardItem(corp, dept,
				staffList);
		param.setAutoAward(RequireAutoAward.requireAutoAward);

		// Prepare user
		SysUser caller = UserHelper.getDefaultUser(injector);
		// create rewarditem
		autoAwardItem = rewardItemLogic.saveRewardItem(caller, param);
		// enable it to make it can auto run
		rewardItemLogic.enableRewardItem(caller, autoAwardItem.getId());

		return autoAwardItem;
	}

	public static RewardItemParam prepareRewardItem(Corporation corp,
			Department dept, List<Staff> staffList) {

		// prepare RewardItemParam
		RewardItemParam param = new RewardItemParam();
		param.setName("每月之星");
		param.setAwardAmt(100);
		param.setAwardUnit(CorporationHelper.getDefaultTxUnit());

		param.setBuilderDeptId(dept.getId());
		param.setAccountDeptId(dept.getId());
		param.setAutoGenerate(RequireAutoGenerate.requireCyclic);
		param.setAutoAward(RequireAutoAward.requireAutoAward);
		Date publishDate = DateHelper.getDate(2011, 12, 1);
		Date expectAwardDate = DateHelper.getDate(2011, 12, 10);
		param.setPublishDate(publishDate);
		param.setExpectAwardDate(expectAwardDate);
		// Prepare candidate list
		List<String> canList = new ArrayList<String>();
		canList.add(dept.getId());
		param.setCandidateList(canList);

		// Prepare frequency every two week's Monday and Thursday
		WeeklyVo weekly = new WeeklyVo();
		WeekDays[] days = new WeekDays[2];
		days[0] = WeekDays.MON;
		days[1] = WeekDays.THUR;
		weekly.setInterval(2);
		weekly.setWeekdays(days);
		param.setFrequency(weekly);

		return param;
	}
}
