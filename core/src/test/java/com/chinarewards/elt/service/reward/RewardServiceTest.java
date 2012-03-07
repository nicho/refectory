package com.chinarewards.elt.service.reward;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.reward.person.NomineeLot;
import com.chinarewards.elt.domain.reward.rule.CandidateRule;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.base.RewardStatus;
import com.chinarewards.elt.model.reward.exception.NominateRewardException;
import com.chinarewards.elt.service.common.JPATestCase;
import com.chinarewards.elt.service.helper.RewardItemHelper;
import com.chinarewards.elt.service.helper.StaffHelper;
import com.chinarewards.elt.service.helper.UserHelper;
import com.chinarewards.elt.util.DateUtil;

public class RewardServiceTest extends JPATestCase {
	public void testNominate() {

		List<String> staffList = StaffHelper.getDefaultStaffListById(injector);

		// need services
		RewardLogic rewardLogic = injector.getInstance(RewardLogic.class);
		RewardItem nominateItem = RewardItemHelper
				.getDefaultNominateRewardItem(injector);
		// prepare default caller
		SysUser caller = UserHelper.getDefaultUser(injector);

		Date now = DateUtil.getTime();
		Reward reward = rewardLogic.awardFromRewardItem(caller,
				nominateItem.getId(), now);
		// check reward
		assertNotNull(reward.getId());
		assertEquals(RewardStatus.PENDING_NOMINATE, reward.getStatus());
		// check candidate rule
		CandidateRule rule = reward.getCandidateRule();
		assertNotNull(rule.getId());

		// 添加judge
		// JudgeLogic judgeLogic = injector.getInstance(JudgeLogic.class);
		// judgeLogic.cloneJudgesFromRewardItemToReward(caller,
		// nominateItem.getId(), reward.getId());

		@SuppressWarnings("unchecked")
		List<Judge> list = em
				.createQuery("FROM Judge r WHERE r.reward.id =:itemId ")
				.setParameter("itemId", reward.getId()).getResultList();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getStaff().getName() + "-------"
					+ list.get(i).getStaff().getId());
		}

		// 设置用户的员工为提名人
		Staff sf = em.find(Staff.class, list.get(0).getStaff().getId());
		caller.setStaff(sf);

		System.out.println("caller.getStaff()========"
				+ caller.getStaff().getId());
		assertEquals(3, list.size());
		try {

			NomineeLot lot = rewardLogic.nominateReward(caller, reward.getId(),
					staffList);
			assertNotNull(lot.getId());
		} catch (NominateRewardException e) {
			e.printStackTrace();
		}
	}

	public void testAwardReward() {

		List<String> staffList = StaffHelper.getDefaultStaffListById(injector);

		// need services
		RewardLogic rewardLogic = injector.getInstance(RewardLogic.class);
		RewardItem awardItem = RewardItemHelper.getDefaultRewardItem(injector);
		// prepare default caller
		SysUser caller = UserHelper.getDefaultUser(injector);

		Date now = DateUtil.getTime();
		Reward reward = rewardLogic.awardFromRewardItem(caller,
				awardItem.getId(), now);
		// check reward
		assertNotNull(reward.getId());
		assertEquals(RewardStatus.NEW, reward.getStatus());
		// check candidate rule
		CandidateRule rule = reward.getCandidateRule();
		assertNotNull(rule.getId());

		// 添加judge
		// JudgeLogic judgeLogic = injector.getInstance(JudgeLogic.class);
		// judgeLogic.cloneJudgesFromRewardItemToReward(caller,
		// awardItem.getId(), reward.getId());

		// 设置颁奖人
		Staff sf = em.find(Staff.class, staffList.get(0));
		caller.setStaff(sf);

		String lot = rewardLogic.awardReward(caller, reward.getId(), staffList);
		assertNotNull(lot);

	}

}
