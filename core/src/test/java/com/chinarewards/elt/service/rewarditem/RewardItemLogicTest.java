package com.chinarewards.elt.service.rewarditem;

import java.util.List;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.rule.CandidateRule;
import com.chinarewards.elt.domain.reward.rule.DirectCandidateData;
import com.chinarewards.elt.domain.reward.rule.DirectCandidateRule;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.base.RewardItemParam;
import com.chinarewards.elt.service.common.JPATestCase;
import com.chinarewards.elt.service.helper.CorporationHelper;
import com.chinarewards.elt.service.helper.DepartmentHelper;
import com.chinarewards.elt.service.helper.RewardItemHelper;
import com.chinarewards.elt.service.helper.StaffHelper;
import com.chinarewards.elt.service.helper.UserHelper;
import com.chinarewards.elt.service.reward.RewardItemLogic;
import com.chinarewards.elt.service.reward.rule.CandidateRuleLogic;

/**
 * The test case of {@link RewardItemLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardItemLogicTest extends JPATestCase {

	/**
	 * Test about creating a new {@link RewardItem} successful.
	 */
	public void testCreateNewRewardItem_ok() {

		// Need some services
		RewardItemLogic rewardItemLogic = injector
				.getInstance(RewardItemLogic.class);
		CandidateRuleLogic candidateRuleLogic = injector
				.getInstance(CandidateRuleLogic.class);

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

		// Prepare user
		SysUser caller = UserHelper.getDefaultUser(injector);
		// create rewarditem
		RewardItem item = rewardItemLogic.saveRewardItem(caller, param);
		// check item
		assertNotNull(item.getId());
		assertNotNull(item.getName());
		logger.debug("The saved RewardItem:{}", item);
		// Check candidate
		CandidateRule rule = candidateRuleLogic
				.findCandidateRuleFromRewardItem(item.getId());
		DirectCandidateRule directRule = (DirectCandidateRule) rule;
		assertNotNull(directRule.getId());
		List<DirectCandidateData> dataList = directRule.getCandidateDataList();
		assertEquals(1, dataList.size());

	}
}
