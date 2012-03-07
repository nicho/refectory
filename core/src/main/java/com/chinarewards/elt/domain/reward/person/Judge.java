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
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.base.RewardItemStore;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.base.JudgeStatus;

/**
 * 
 * Someone who can put others in the nominee list. Usually they are the senior
 * members.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
public class Judge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -893051164079006922L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ManyToOne
	private RewardItem rewardItem;
	
	@ManyToOne
	private RewardItemStore rewardItemStore;

	public RewardItemStore getRewardItemStore() {
		return rewardItemStore;
	}

	public void setRewardItemStore(RewardItemStore rewardItemStore) {
		this.rewardItemStore = rewardItemStore;
	}

	/**
	 * The Reward would copy it from RewardItem. So Judge would associate to
	 * both RewardItem and Reward.
	 */
	@ManyToOne
	private Reward reward;

	@ManyToOne
	private Staff staff;

	/**
	 * This status only exist when this Judge associated to a Reward.
	 */
	@Enumerated(EnumType.STRING)
	private JudgeStatus status;

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

	public RewardItem getRewardItem() {
		return rewardItem;
	}

	public void setRewardItem(RewardItem rewardItem) {
		this.rewardItem = rewardItem;
	}

	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public JudgeStatus getStatus() {
		return status;
	}

	public void setStatus(JudgeStatus status) {
		this.status = status;
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
