package com.chinarewards.elt.domain.information;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import com.chinarewards.elt.domain.org.Staff;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "staff")
public class StaffObject extends ReceivingObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	private Staff staff;

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
}
