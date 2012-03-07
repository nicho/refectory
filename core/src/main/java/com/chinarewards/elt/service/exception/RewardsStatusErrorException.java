package com.chinarewards.elt.service.exception;

/**
 * 奖励的状态 不适合做一些操作的时候会抛出该异常
 * 
 * @author yanxin
 * @since 0.2.0 2011-01-07
 */
public class RewardsStatusErrorException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4065727936678788480L;

	public RewardsStatusErrorException() {

	}

	public RewardsStatusErrorException(String message) {
		super(message);
	}
}
