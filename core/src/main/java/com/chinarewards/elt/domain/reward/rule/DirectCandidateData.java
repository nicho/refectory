package com.chinarewards.elt.domain.reward.rule;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.elt.domain.org.Organization;
import com.chinarewards.elt.domain.user.SysUser;

/**
 * The actual chosen Organization of DirectRule.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
public class DirectCandidateData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3204376233695507983L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ManyToOne
	private DirectCandidateRule directCandidateRule;

	/**
	 * Here use the base class to adapt different subclasses.
	 */
	@ManyToOne
	private Organization org;

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

	public DirectCandidateRule getDirectCandidateRule() {
		return directCandidateRule;
	}

	public void setDirectCandidateRule(DirectCandidateRule directCandidateRule) {
		this.directCandidateRule = directCandidateRule;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
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
