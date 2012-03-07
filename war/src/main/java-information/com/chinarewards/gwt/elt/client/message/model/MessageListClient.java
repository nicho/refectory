package com.chinarewards.gwt.elt.client.message.model;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListCriteria.BroadcastingCategory;
import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListCriteria.BroadcastingStatus;

public class MessageListClient implements Serializable, Comparable<MessageListClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;
	/**
	 * ID
	 */
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

	
	private String createdByUserName;	
	/**
	 * 类别
	 */
	private BroadcastingCategory category;
	/**
	 * 广播时间
	 */
	private Date broadcastingTime;

	/**
	 * 回复数
	 */
	private int replyNumber;
	/**
	 * 员工照片
	 */
	private String staffPhoto;
	
	public String getStaffPhoto() {
		return staffPhoto;
	}

	public void setStaffPhoto(String staffPhoto) {
		this.staffPhoto = staffPhoto;
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

	public String getCreatedByUserName() {
		return createdByUserName;
	}

	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}

	public BroadcastingCategory getCategory() {
		return category;
	}

	public void setCategory(BroadcastingCategory category) {
		this.category = category;
	}

	public Date getBroadcastingTime() {
		return broadcastingTime;
	}

	public void setBroadcastingTime(Date broadcastingTime) {
		this.broadcastingTime = broadcastingTime;
	}

	public int getReplyNumber() {
		return replyNumber;
	}

	public void setReplyNumber(int replyNumber) {
		this.replyNumber = replyNumber;
	}

	@Override
	public int compareTo(MessageListClient o) {
		// TODO Auto-generated method stub
		return 0;
	}



}
