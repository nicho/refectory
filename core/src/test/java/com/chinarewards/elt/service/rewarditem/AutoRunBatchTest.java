package com.chinarewards.elt.service.rewarditem;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.model.RequireApproval;
import com.chinarewards.elt.service.common.JPATestCase;
import com.chinarewards.elt.service.helper.DateHelper;
import com.chinarewards.elt.service.helper.RewardItemHelper;
import com.chinarewards.elt.service.reward.RewardItemLogic;

/**
 * Test the auto run batch which is in charge of generating reward.
 * 
 * Test method is mainly
 * {@link RewardItemLogic #runAutoRewardGeneratorBatch(java.util.Date)}
 * 
 * @author yanxin
 * @since 1.0
 */
public class AutoRunBatchTest extends JPATestCase {

	/**
	 * The normal case
	 */
	@SuppressWarnings("unchecked")
	public void testRunAutoRewardBatch_ok() {
		// need some services
		RewardItemLogic rewardItemLogic = injector
				.getInstance(RewardItemLogic.class);

		// Prepare some auto-generate RewardItems.
		RewardItem item = RewardItemHelper.getDefaultAutoAwardRewardItem(
				injector, RequireApproval.None);

		Date runTime = DateHelper.getDate(2011, 12, 23);
		rewardItemLogic.runAutoRewardGeneratorBatch(runTime);

		// XXX Here not provide RewardItem search method. So use EntityManager.
		EntityManager em = injector.getInstance(EntityManager.class);
		List<Reward> list = em
				.createQuery("FROM Reward r WHERE r.rewardItem.id =:itemId")
				.setParameter("itemId", item.getId()).getResultList();
		assertEquals(3, list.size());
	}
}
