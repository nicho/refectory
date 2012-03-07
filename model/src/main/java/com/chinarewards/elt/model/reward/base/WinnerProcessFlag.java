package com.chinarewards.elt.model.reward.base;

/**
 * 得奖者处理状态
 * 
 * @author roger
 * 
 */
public enum WinnerProcessFlag {

	/**
	 * 未处理
	 */
	UNPROCESS,

	/**
	 * 处理失败
	 */
	PROCESS_FAIL,

	/**
	 * 成功处理
	 */
	PROCESS_SUCCESS;
}
