package com.chinarewards.gwt.elt.client.gift.model;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.gwt.elt.client.gift.model.GiftCriteria.GiftStatus;
import com.chinarewards.gwt.elt.util.StringUtil;

public class GiftClient implements Serializable, Comparable<GiftClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;

	public GiftClient() {
	}

	// 状态
	private GiftStatus status;
	private String id;
	private String name;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 库存
	 */
	private String inventory;

	protected String thisAction;

	/**
	 * 积分
	 */
	private int integral;

	/**
	 * 有效期
	 */
	private Date indate;
	/**
	 * 图片
	 */
	private String photo;

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public GiftStatus getStatus() {
		return status;
	}

	public void setStatus(GiftStatus status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public String getSourceText() {
		if (source != null) {
			if (StringUtil.trim(source).equals("inner")) {
				return "内部直接提供";
			}
			if (StringUtil.trim(source).equals("outter")) {
				return "外部货品公司提供";
			}
		}
		return "";
	}
	public void setSource(String source) {
		this.source = source;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	@Override
	public int compareTo(GiftClient o) {
		return o == null ? -1 : o.getId().compareTo(id);
	}

	public String getThisAction() {
		return thisAction;
	}

	public void setThisAction(String thisAction) {
		this.thisAction = thisAction;
	}

}
