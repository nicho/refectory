package com.chinarewards.elt.domain.reward.person;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.base.PreWinnerLotStatus;

/**
 * It exists because of the auditing mechanism. The reward owner provides a
 * pre-winner list to a senior member who can approve or deny it.So as you know,
 * a rewards maybe has several PreWinnerLot. But only exist one whose status is
 * {@link PreWinnerLotStatus.PASS}.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
public class PreWinnerLot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -740103008017302800L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ManyToOne
	private Reward reward;

	@Enumerated(EnumType.STRING)
	private PreWinnerLotStatus status;

	private String reason;

	@Transient
	private List<PreWinner> preWinners;

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

	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

	public PreWinnerLotStatus getStatus() {
		return status;
	}

	public void setStatus(PreWinnerLotStatus status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<PreWinner> getPreWinners() {
		return preWinners;
	}

	public void setPreWinners(List<PreWinner> preWinners) {
		this.preWinners = preWinners;
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
