package com.chinarewards.elt.model.reward.exception;

/**
 * Judge error.
 * 
 * @author yanxin
 * @since 1.0
 */
public class JudgeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4656501403046227685L;

	public JudgeException() {

	}

	public JudgeException(String msg) {
		super(msg);
	}

	public JudgeException(Throwable e) {
		super(e);
	}

	public JudgeException(String msg, Throwable e) {
		super(msg, e);
	}
}
