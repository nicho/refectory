package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.gwt.elt.util.DateTool;

/**
 * 符合标准的员工
 * 
 * @author yanxin
 * @since 1.0.0 2010-12-13
 */
public class StaffClient extends OrganicationClient implements Serializable,
		Comparable<StaffClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2666336337400092567L;

	public StaffClient() {

	}

	public StaffClient(String name, String cardNo, String deptName, String email) {
		this(cardNo, name, cardNo, deptName, email);
	}

	public StaffClient(String id, String name, String cardNo, String deptName,
			String email) {
		this.id = id;
		this.name = name;
		this.cardNo = cardNo;
		this.deptName = deptName;
		this.email = email;
	}

	private String cardNo;

	private String email;

	/**
	 * 入职日期
	 */
	private Date employDate;

	/**
	 * 部门名称
	 */
	private String deptName;

	private String deptTreeName;

	// 部门ID
	private String deptId;

	/**
	 * 获奖次数
	 */
	private Integer reweardsTimes;

	/**
	 * 上次获奖时间
	 */
	private Date lastRewardsDate;

	private String phone;

	/**
	 * 等级
	 */
	private StaffLevelClient staffLevel;

	/**
	 * 被提名次数
	 */
	private int nominateCount;
	
	
	public int getNominateCount() {
		return nominateCount;
	}

	public void setNominateCount(int nominateCount) {
		this.nominateCount = nominateCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getEmployDate() {
		return employDate;
	}

	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}

	public Integer getReweardsTimes() {
		return reweardsTimes;
	}

	public void setReweardsTimes(Integer reweardsTimes) {
		this.reweardsTimes = reweardsTimes;
	}

	public Date getLastRewardsDate() {
		return lastRewardsDate;
	}

	public void setLastRewardsDate(Date lastRewardsDate) {
		this.lastRewardsDate = lastRewardsDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId
	 *            the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public StaffLevelClient getStaffLevel() {
		return staffLevel;
	}

	public void setStaffLevel(StaffLevelClient staffLevel) {
		this.staffLevel = staffLevel;
	}

	/**
	 * 由入职日期计算年资,1,1.5,2,2.5...等等。小数点后只有0.5
	 */
	public Double getEmployYears() {
		if (employDate == null) {
			return null;
		}
		return DateTool.getIntervalYears(employDate, new Date());
	}

	/**
	 * @return the deptTreeName
	 */
	public String getDeptTreeName() {
		return deptTreeName;
	}

	/**
	 * @param deptTreeName
	 *            the deptTreeName to set
	 */
	public void setDeptTreeName(String deptTreeName) {
		this.deptTreeName = deptTreeName;
	}

	@Override
	public int compareTo(StaffClient o) {
		return o == null ? -1 : o.getCardNo().compareTo(cardNo);
	}

}
