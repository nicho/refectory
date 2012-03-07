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
import com.chinarewards.elt.model.information.BroadcastMessage;
import com.chinarewards.elt.model.information.BroadcastingCategory;
import com.chinarewards.elt.model.information.BroadcastingStatus;
@Entity
public class Broadcasting implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, updatable = false, length = 50)
	private String id;
	/**
	 * 编号
	 */
	private String number;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 状态
	 */
	private BroadcastingStatus status;

	@ManyToOne
	private SysUser createdBy;	
	/**
	 * 类别
	 */
	private BroadcastingCategory category;

	/**
	 * 广播时间-开始时间
	 */
	private Date broadcastingTimeStart;
	/**
	 * 广播时间-结束时间
	 */
	private Date broadcastingTimeEnd;
	
	@ManyToOne
	private SysUser lastModifiedBy;
	
	private Date createdAt;

	private Date lastModifiedAt;
	/**
	 * 是否允许回复
	 */
	private boolean isAllowreplies;
	

	/**
	 * 公司
	 */
	@OneToOne(fetch = FetchType.EAGER)
	private Corporation corporation;
	
	/**
	 * 是否删除
	 */
	private boolean deleted;
	/**
	 * 回复数
	 */
	private int replyNumber;
	
	/**
	 * 广播 or 消息
	 */
	private BroadcastMessage broadcastMessagetype;

	public BroadcastMessage getBroadcastMessagetype() {
		return broadcastMessagetype;
	}
	public void setBroadcastMessagetype(BroadcastMessage broadcastMessagetype) {
		this.broadcastMessagetype = broadcastMessagetype;
	}
	public int getReplyNumber() {
		return replyNumber;
	}
	public void setReplyNumber(int replyNumber) {
		this.replyNumber = replyNumber;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BroadcastingStatus getStatus() {
		return status;
	}
	public void setStatus(BroadcastingStatus status) {
		this.status = status;
	}
	public SysUser getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(SysUser createdBy) {
		this.createdBy = createdBy;
	}
	public BroadcastingCategory getCategory() {
		return category;
	}
	public void setCategory(BroadcastingCategory category) {
		this.category = category;
	}


	public Date getBroadcastingTimeStart() {
		return broadcastingTimeStart;
	}
	public void setBroadcastingTimeStart(Date broadcastingTimeStart) {
		this.broadcastingTimeStart = broadcastingTimeStart;
	}
	public Date getBroadcastingTimeEnd() {
		return broadcastingTimeEnd;
	}
	public void setBroadcastingTimeEnd(Date broadcastingTimeEnd) {
		this.broadcastingTimeEnd = broadcastingTimeEnd;
	}
	public SysUser getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(SysUser lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
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
	public boolean isAllowreplies() {
		return isAllowreplies;
	}
	public void setAllowreplies(boolean isAllowreplies) {
		this.isAllowreplies = isAllowreplies;
	}
	
	
	
}
