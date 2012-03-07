package com.chinarewards.elt.model.org;

import java.util.Date;

/**
 * The model to save or update Staff.
 * 
 * @author yanxin
 * @since 1.0
 */
public class StaffVo {

	private String id;

	private String name;

	private String description;

	/**
	 * It associate to a Department.
	 */
	private String deptId;

	/**
	 * It associate a corporation.
	 */
	private String corpId;

	/**
	 * mobile phone number
	 */
	private String phone;

	/**
	 * email
	 */
	private String email;

	/**
	 * notification mode
	 */
	private NoticeMode noticeMode;

	/**
	 * Flag whether is deleted
	 */
	private boolean deleted;

	/**
	 * account id of outer tx.
	 */
	private String txAccountId;

	/**
	 * surname, e.g. 严 or Michael(迈克尔)
	 */
	private String surname;

	/**
	 * personal name, e.g. 宝宝 or Jackson(杰克逊)
	 */
	private String personalName;

	/**
	 * job number(工号)
	 */
	private String jobNo;

	/**
	 * date of birthday(生日)
	 */
	private Date dob;

	/**
	 * entry date means which day you enter the corporation.(入职日期)
	 */
	private Date entryDate;

	/**
	 * position in the corporation(职位)
	 */
	private String position;

	/**
	 * gender
	 */
	private Gender gender;

	/**
	 * native place(籍贯)
	 */
	private String nativePlace;

	/**
	 * nation(民族)
	 */
	private String nation;

	/**
	 * identification card code(身份证号)
	 */
	private String IDCardNo;

	/**
	 * address (居住地)
	 */
	private String location;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public NoticeMode getNoticeMode() {
		return noticeMode;
	}

	public void setNoticeMode(NoticeMode noticeMode) {
		this.noticeMode = noticeMode;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getTxAccountId() {
		return txAccountId;
	}

	public void setTxAccountId(String txAccountId) {
		this.txAccountId = txAccountId;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIDCardNo() {
		return IDCardNo;
	}

	public void setIDCardNo(String iDCardNo) {
		IDCardNo = iDCardNo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
