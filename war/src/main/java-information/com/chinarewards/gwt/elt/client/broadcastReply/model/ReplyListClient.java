package com.chinarewards.gwt.elt.client.broadcastReply.model;

import java.io.Serializable;
import java.util.Date;

public class ReplyListClient implements Serializable,
		Comparable<ReplyListClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;
	/**
	 * ID
	 */
	private String id;
	private String replyUserId;
	private String replyUserName;
	private String replyUserPhoto;
	private String replyContent;
	private Date replyTime;
	private String parent;
	
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public String getReplyUserPhoto() {
		return replyUserPhoto;
	}

	public void setReplyUserPhoto(String replyUserPhoto) {
		this.replyUserPhoto = replyUserPhoto;
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

	@Override
	public int compareTo(ReplyListClient o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
