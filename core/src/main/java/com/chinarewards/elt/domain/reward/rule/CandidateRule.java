package com.chinarewards.elt.domain.reward.rule;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.elt.domain.user.SysUser;

/**
 * Candidate: Someone who has the ability to win a reward. It was associated to
 * RewardsItem, when generate a rewards from RewardsItem, it should copy.It is
 * the first step to win a reward.It may contain Staff, Department, Team or
 * other groups who extends Organization.
 * 
 * The rule to find who were in the candidate list. In other words, i can use it
 * choose some qualified peoples.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "RULE_TYPE")
public class CandidateRule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1910804479135826938L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

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
