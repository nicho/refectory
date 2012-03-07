package com.chinarewards.elt.model.reward.base;

/**
 * The status of a PreWinnerLot. The status change dues to someone who approve
 * the PreWinnerLot request.
 * 
 * @author yanxin
 * @since 1.0
 */
public enum PreWinnerLotStatus {

	/**
	 * untreated
	 */
	NEW,

	/**
	 * pass
	 */
	PASS,

	/**
	 * deny
	 */
	DENY,

}
