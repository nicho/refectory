package com.chinarewards.gwt.elt.client.mail.model;

import java.io.Serializable;
import java.util.Date;

public class MailVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String staffId;
	private String content;
	public MailVo(){
		
	}
	
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}