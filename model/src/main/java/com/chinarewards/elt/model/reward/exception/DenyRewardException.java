package com.chinarewards.elt.model.reward.exception;

/**
 * Deny a Reward failure.
 * 
 * @author yanxin
 * @since 1.0
 */
public class DenyRewardException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4722241103101608239L;

	public DenyRewardException() {

	}

	public DenyRewardException(String msg) {
		super(msg);
	}

	public DenyRewardException(Throwable e) {
		super(e);
	}

	public DenyRewardException(String msg, Throwable e) {
		super(msg, e);
	}
}
