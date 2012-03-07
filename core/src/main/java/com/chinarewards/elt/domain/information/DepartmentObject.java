package com.chinarewards.elt.domain.information;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import com.chinarewards.elt.domain.org.Department;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "department")
public class DepartmentObject  extends ReceivingObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	private Department dept;

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}
	
}
