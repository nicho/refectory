package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;

/**
 * 奖励 --选择员工部分的model
 * 
 * @author yanxin
 * 
 */
public class NomineeModelClient implements Serializable,
		Comparable<NomineeModelClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4815988602631452935L;

	/**
	 * 员工姓名(多人)
	 */
	private String name;

	/**
	 * 提名ID
	 */
	private String judgeStaffId;

	public NomineeModelClient() {

	}

	public NomineeModelClient(String name, String judgeStaffId) {

		this.name = name;

		this.judgeStaffId = judgeStaffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJudgeStaffId() {
		return judgeStaffId;
	}

	public void setJudgeStaffId(String judgeStaffId) {
		this.judgeStaffId = judgeStaffId;
	}

	@Override
	public int compareTo(NomineeModelClient o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
