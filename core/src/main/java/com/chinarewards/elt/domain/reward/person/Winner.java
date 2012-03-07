package com.chinarewards.elt.domain.reward.person;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.base.WinnerProcessFlag;
import com.chinarewards.elt.model.transaction.TransactionUnit;

/**
 * Someone who win the reward finally. It is so hard!
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
public class Winner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8649193748405987774L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	/**
	 * Where it come from
	 */
	@OneToOne
	private PreWinner preWinner;

	/**
	 * Who win this Reward
	 */
	@ManyToOne
	private Staff staff;

	/**
	 * Which Reward result it.
	 */
	@ManyToOne
	private Reward reward;

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

	@Enumerated(EnumType.STRING)
	private WinnerProcessFlag processFlag;

	/**
	 * Win time, usually it is the same as createdAt, but sometimes it is
	 * different. We use this field as the win time.
	 */
	private Date winTime;

	/**
	 * Outer transaction id
	 */
	private String refTransactionId;

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

	public PreWinner getPreWinner() {
		return preWinner;
	}

	public void setPreWinner(PreWinner preWinner) {
		this.preWinner = preWinner;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
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

	public WinnerProcessFlag getProcessFlag() {
		return processFlag;
	}

	public void setProcessFlag(WinnerProcessFlag processFlag) {
		this.processFlag = processFlag;
	}

	public Date getWinTime() {
		return winTime;
	}

	public void setWinTime(Date winTime) {
		this.winTime = winTime;
	}

	public String getRefTransactionId() {
		return refTransactionId;
	}

	public void setRefTransactionId(String refTransactionId) {
		this.refTransactionId = refTransactionId;
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
