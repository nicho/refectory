package com.chinarewards.elt.domain.org;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 * It defines the departments of a corporation. e.g. IT department, Sales
 * Department.
 * 
 * @author lw
 * @since 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "team")
public class Team extends Organization {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Corporation corporation;
	@ManyToOne
	private Department department;
	private String code;//编号
	 
	private int deleted;//删除状态0存在,1删除
	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "";
	}

	


	

	public Corporation getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getCode() {
		return code;
	}





	public void setCode(String code) {
		this.code = code;
	}
}
