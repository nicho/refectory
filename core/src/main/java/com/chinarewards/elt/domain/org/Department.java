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
 * @author yanxin
 * @since 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "department")
public class Department extends Organization {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8744769908375326416L;

	/**
	 * It flag which corporation it belongs to.
	 */
	@ManyToOne
	private Corporation corporation;

	/**
	 * Who is the parent department.
	 */
	@ManyToOne
	//(fetch = FetchType.LAZY)
	private Department parent;

	/**
	 * You should maintain {@link #lft} and {@link #rgt}
	 */
	private int lft;

	private int rgt;
	

	public Corporation getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public int getLft() {
		return lft;
	}

	public void setLft(int lft) {
		this.lft = lft;
	}

	public int getRgt() {
		return rgt;
	}

	public void setRgt(int rgt) {
		this.rgt = rgt;
	}

	@Override
	public String toString() {
		return "Department [corporation=" + corporation + ", parent=" + parent
				+ ", lft=" + lft + ", rgt=" + rgt + "]";
	}
}
