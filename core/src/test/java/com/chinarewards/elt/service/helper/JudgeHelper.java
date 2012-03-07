package com.chinarewards.elt.service.helper;

import javax.persistence.EntityManager;

import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.service.reward.rule.JudgeLogic;
import com.google.inject.Injector;

public class JudgeHelper {
	private static Judge defaultJudge = null;

	/**
	 * Get the default user.
	 * 
	 * @return
	 */
	public static Judge getDefaultJudge(Injector injector) {
		EntityManager em = injector.getInstance(EntityManager.class);
		if (defaultJudge != null
				&& em.find(Judge.class, defaultJudge.getId()) != null)
			return defaultJudge;
		// require some services
		JudgeLogic judgeLogic = injector.getInstance(JudgeLogic.class);
		// defaultJudge = judgeLogic.bindJudgesToRewardItem(caller,
		// rewardItemId, staffIds)
		// 占时不需要..还没实现
		return defaultJudge;
	}
}
