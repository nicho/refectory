package com.chinarewards.elt.model.reward.frequency;

/**
 * 
 * @author roger
 * 
 */
public enum WeekDays {

	/**
	 * 星期一
	 */
	MON("星期一", 1),

	/**
	 * 星期二
	 */
	TUS("星期二", 2),

	/**
	 * 星期三
	 */
	WEN("星期三", 3),

	/**
	 * 星期四
	 */
	THUR("星期四", 4),

	/**
	 * 星期五
	 */
	FRI("星期五", 5),

	/**
	 * 星期六
	 */
	SAT("星期六", 6),

	/**
	 * 星期天
	 */
	SUN("星期天", 7);

	private String message;

	private int flag;

	private WeekDays(String message, int flag) {
		this.message = message;
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public int getFlag() {
		return flag;
	}
}
