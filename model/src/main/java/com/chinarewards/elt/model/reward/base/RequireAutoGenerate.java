package com.chinarewards.elt.model.reward.base;

/**
 * It defines whether require running Reward out by "batch system" at least one
 * time. This model define not for entity.
 * 
 * @author yanxin
 * @since 1.0
 * 
 */
public enum RequireAutoGenerate {

	/**
	 * Require cyclic generate, but not award directly.
	 */
	requireCyclic,

	/**
	 * Require generate only one time, but not award directly.
	 */
	requireOneOff,

	/**
	 * require nothing.
	 */
	requireNone;
}
