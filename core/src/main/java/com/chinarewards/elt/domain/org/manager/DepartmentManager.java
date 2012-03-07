package com.chinarewards.elt.domain.org.manager;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.Staff;

/**
 * The manager of Department.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
public class DepartmentManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3746331305215075718L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	/**
	 * It associated to a staff means every manager must be a staff at first.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	private Staff staff;

	/**
	 * Which department is to be managed.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	private Department department;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
