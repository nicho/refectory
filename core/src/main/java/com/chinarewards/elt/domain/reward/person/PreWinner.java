package com.chinarewards.elt.domain.reward.person;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.transaction.TransactionUnit;

/**
 * You can see definition in {@link PreWinnerLot}. A PreWinnerLot contains some
 * PreWinners.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
public class PreWinner implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3329658650846149791L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	/**
	 * Owns to which lot
	 */
	@ManyToOne
	private PreWinnerLot preWinnerLot;

	/**
	 * The staff.
	 */
	@ManyToOne
	private Staff staff;

	/**
	 * Award currency unit
	 */
	@Enumerated(EnumType.STRING)
	private TransactionUnit unit;

	/**
	 * Award amount
	 */
	private double amt;

	/**
	 * Award reason
	 */
	private String reason;

	private Date createdAt;

	private Date lastModifiedAt;

	@ManyToOne
	private SysUser createdBy;

	@ManyToOne
	private SysUser lastModifiedBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PreWinnerLot getPreWinnerLot() {
		return preWinnerLot;
	}

	public void setPreWinnerLot(PreWinnerLot preWinnerLot) {
		this.preWinnerLot = preWinnerLot;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public TransactionUnit getUnit() {
		return unit;
	}

	public void setUnit(TransactionUnit unit) {
		this.unit = unit;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	public SysUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(SysUser createdBy) {
		this.createdBy = createdBy;
	}

	public SysUser getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(SysUser lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

}
