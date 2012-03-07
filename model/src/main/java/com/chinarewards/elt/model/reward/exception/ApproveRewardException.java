package com.chinarewards.elt.model.reward.exception;

/**
 * Approve a Reward failure.
 * 
 * @author yanxin
 * @since 1.0
 */
public class ApproveRewardException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 600644145305719264L;

	public ApproveRewardException() {

	}

	public ApproveRewardException(String msg) {
		super(msg);
	}

	public ApproveRewardException(Throwable e) {
		super(e);
	}

	public ApproveRewardException(String msg, Throwable e) {
		super(msg, e);
	}
}
