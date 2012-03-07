package com.chinarewards.elt.model.reward.base;

/**
 * It defines whether the reward generated from RewardItem would award directly
 * or others.
 * 
 * @author yanxin
 * @since 1.0
 * 
 */
public enum RequireAutoAward {

	/**
	 * Require award directly after generate Reward from RewardItem.
	 */
	requireAutoAward,

	/**
	 * Require run nominate mechanism.
	 */
	requireNominate,

	/**
	 * Require nothing. It means just generate Reward and then do nothing.l
	 */
	requireNone;
}
