package com.chinarewards.elt.model.reward.base;

public enum RewardStatus {

	/* 待颁奖 */
	NEW,

	/* 待提名 */
	PENDING_NOMINATE,

	/* 待审批 */
	PENDING_APPLICATION,

	/* 已完成 */
	REWARDED,

	/* 已否决 */
	DENIED
}
