package com.chinarewards.elt.service.reward;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.person.Candidate;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.reward.person.PreWinner;
import com.chinarewards.elt.domain.reward.person.PreWinnerLot;
import com.chinarewards.elt.domain.reward.person.Winner;
import com.chinarewards.elt.domain.reward.rule.CandidateRule;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.RequireApproval;
import com.chinarewards.elt.model.reward.base.RewardStatus;
import com.chinarewards.elt.model.reward.base.WinnerProcessFlag;
import com.chinarewards.elt.model.reward.exception.NoEffectivePreWinnerException;
import com.chinarewards.elt.service.common.JPATestCase;
import com.chinarewards.elt.service.helper.CorporationHelper;
import com.chinarewards.elt.service.helper.RewardItemHelper;
import com.chinarewards.elt.service.helper.UserHelper;
import com.chinarewards.elt.service.org.CorporationLogic;
import com.chinarewards.elt.service.reward.rule.CandidateLogic;
import com.chinarewards.elt.service.reward.rule.JudgeLogic;
import com.chinarewards.elt.service.reward.rule.PreWinnerLogic;
import com.chinarewards.elt.service.reward.rule.WinnerLogic;
import com.chinarewards.elt.service.staff.StaffLogic;
import com.chinarewards.elt.util.DateUtil;

/**
 * The test case of {@link RewardLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardLogicTest extends JPATestCase {

	/**
	 * Award a reward from the rewarditem which only want to generate a reward.
	 */
	public void testAward_none() {
		// need services
		RewardLogic rewardLogic = injector.getInstance(RewardLogic.class);

		RewardItem noneItem = RewardItemHelper.getDefaultRewardItem(injector);
		// prepare default caller
		SysUser caller = UserHelper.getDefaultUser(injector);
		Date now = DateUtil.getTime();
		Reward reward = rewardLogic.awardFromRewardItem(caller,
				noneItem.getId(), now);
		// check reward
		assertNotNull(reward.getId());
		assertEquals(RewardStatus.NEW, reward.getStatus());
		// check candidate rule
		CandidateRule rule = reward.getCandidateRule();
		assertNotNull(rule.getId());

	}

	/**
	 * Award a reward from the rewarditem which need to nominate.
	 */
	public void testAward_needNominate() {
		RewardLogic rewardLogic = injector.getInstance(RewardLogic.class);
		JudgeLogic judgeLogic = injector.getInstance(JudgeLogic.class);
		Date now = DateUtil.getTime();
		RewardItem nominateItem = RewardItemHelper
				.getDefaultNominateRewardItem(injector);
		assertNotNull(nominateItem);
		// prepare default caller
		SysUser caller = UserHelper.getDefaultUser(injector);
		Reward reward = rewardLogic.awardFromRewardItem(caller,
				nominateItem.getId(), now);
		// check reward
		assertNotNull(reward.getId());
		assertEquals(RewardStatus.PENDING_NOMINATE, reward.getStatus());
		// check candidate rule
		CandidateRule rule = reward.getCandidateRule();
		assertNotNull(rule.getId());
		// check judges
		List<Judge> judges = judgeLogic.findJudgesFromReward(reward.getId());
		assertEquals(3, judges.size());
	}

	/**
	 * Award a reward from the rewarditem which need to award automatic.
	 */
	public void testAward_autoAward_noApproval() {
		// need services
		RewardLogic rewardLogic = injector.getInstance(RewardLogic.class);
		PreWinnerLogic preWinnerLogic = injector
				.getInstance(PreWinnerLogic.class);
		CandidateLogic candidateLogic = injector
				.getInstance(CandidateLogic.class);
		WinnerLogic winnerLogic = injector.getInstance(WinnerLogic.class);
		StaffLogic staffLogic = injector.getInstance(StaffLogic.class);
		CorporationLogic corporationLogic = injector
				.getInstance(CorporationLogic.class);
		Date now = DateUtil.getTime();
		RewardItem autoAwardItem = RewardItemHelper
				.getDefaultAutoAwardRewardItem(injector, RequireApproval.None);
		logger.debug("------------corpId- txAccount={}", autoAwardItem
				.getCorporation().getTxAccountId());
		assertNotNull(autoAwardItem);
		// prepare default caller
		SysUser caller = UserHelper.getDefaultUser(injector);
		Reward reward = rewardLogic.awardFromRewardItem(caller,
				autoAwardItem.getId(), now);
		// check reward
		assertNotNull(reward.getId());
		assertEquals(RewardStatus.REWARDED, reward.getStatus());
		// check candidate rule
		CandidateRule rule = reward.getCandidateRule();
		assertNotNull(rule.getId());
		// Check candidate list
		List<Candidate> canList = candidateLogic.getCandidatesFromReward(reward
				.getId());
		assertEquals(3, canList.size());

		// check pre-winner lot
		try {
			PreWinnerLot lot = preWinnerLogic.getPassedPreWinnerOfReward(reward
					.getId());
			assertNotNull(lot.getId());
			List<PreWinner> preWinners = preWinnerLogic
					.getPreWinnerListFromLot(lot.getId());
			assertEquals(3, preWinners.size());
		} catch (NoEffectivePreWinnerException e) {
			fail("Should not throw exception");
		}
		// check winner
		List<Winner> winners = winnerLogic.getWinnersOfReward(reward.getId());
		assertEquals(3, winners.size());
		for (Winner winner : winners) {
			assertEquals(autoAwardItem.getAwardAmt(), winner.getAmt());
			assertEquals(autoAwardItem.getAwardUnit(), winner.getUnit());
			assertEquals(WinnerProcessFlag.PROCESS_SUCCESS,
					winner.getProcessFlag());
			// check account balance
			double balance = staffLogic.getBalance(winner.getStaff().getId());
			assertEquals(autoAwardItem.getAwardAmt(), balance);
		}

		// check corporation balance
		Corporation corp = autoAwardItem.getCorporation();
		double corpBalance = corporationLogic.callBalance(corp.getId());
		double expectBalance = CorporationHelper.getInitBalance() - 3
				* autoAwardItem.getAwardAmt();
		assertEquals(expectBalance, corpBalance);
	}
}
