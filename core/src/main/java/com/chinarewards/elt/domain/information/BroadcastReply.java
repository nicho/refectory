package com.chinarewards.elt.domain.information;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.user.SysUser;

@Entity
public class BroadcastReply implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, updatable = false, length = 50)
	private String id;
	
	@ManyToOne
	private Broadcasting broadcast;
	
	/**
	 * 回复用户
	 */
	@ManyToOne
	private SysUser replyUser;
	
	
	/**
	 * 回复内容
	 */
	private String replyContent;
	
	/**
	 * 回复时间
	 */
	private Date replyTime;
	
	@ManyToOne
	private SysUser lastModifiedBy;
	
	private Date lastModifiedAt;
	
	private BroadcastReply parent;
	/**
	 * 公司
	 */
	@OneToOne(fetch = FetchType.EAGER)
	private Corporation corporation;
	
	public BroadcastReply getParent() {
		return parent;
	}

	public void setParent(BroadcastReply parent) {
		this.parent = parent;
	}

	public Corporation getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Broadcasting getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(Broadcasting broadcast) {
		this.broadcast = broadcast;
	}

	public SysUser getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(SysUser replyUser) {
		this.replyUser = replyUser;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public SysUser getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(SysUser lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}
	
	
	
}
