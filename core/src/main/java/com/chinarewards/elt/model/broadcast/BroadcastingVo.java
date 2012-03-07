package com.chinarewards.elt.model.broadcast;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BroadcastingVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 内容
	 */
	private String broadcastingId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 广播时间-开始时间
	 */
	private Date broadcastingTimeStart;
	/**
	 * 广播时间-结束时间
	 */
	private Date broadcastingTimeEnd;
	/**
	 * 是否允许回复
	 */
	private boolean isAllowreplies;
	
	private List<String[]> organList;

	public String getBroadcastingId() {
		return broadcastingId;
	}

	public void setBroadcastingId(String broadcastingId) {
		this.broadcastingId = broadcastingId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public boolean isAllowreplies() {
		return isAllowreplies;
	}

	public void setAllowreplies(boolean isAllowreplies) {
		this.isAllowreplies = isAllowreplies;
	}

	public List<String[]> getOrganList() {
		return organList;
	}

	public void setOrganList(List<String[]> organList) {
		this.organList = organList;
	}
	
}
