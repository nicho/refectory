package com.chinarewards.elt.model.reward.exception;

/**
 * Nominate a Reward failure.
 * 
 * @author yanxin
 * @since 1.0
 */
public class NominateRewardException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9054497209256795948L;

	public NominateRewardException() {

	}

	public NominateRewardException(String msg) {
		super(msg);
	}

	public NominateRewardException(Throwable e) {
		super(e);
	}

	public NominateRewardException(String msg, Throwable e) {
		super(msg, e);
	}
}
