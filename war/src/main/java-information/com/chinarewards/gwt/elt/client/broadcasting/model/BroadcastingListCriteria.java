/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcasting.model;

import java.util.Date;

import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.model.SortingDetailClient;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author nicho
 * @since 2012年2月14日 11:29:59
 */
public class BroadcastingListCriteria implements IsSerializable {

	public enum BroadcastingStatus {
		/* 已广播 */
		HASBROADCAST("已广播"),

		/* 未广播 */
		NOTBROADCAST("未广播");

		private final String displayName;

		BroadcastingStatus(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

	public enum BroadcastingCategory {
		/* 公司广播 */
		COMPANYBROADCAST("公司广播"),

		STAFFBROADCAST("员工广播"),

		SYSBROADCAST("系统广播"),

		REWARDBROADCAST("奖励广播"),

		THEMEBROADCAST("主题广播"),
		QUIETLYINFORMATION("悄悄话"),
		DALLIANCEINFORMATION("调戏信息"),
		/* 其他广播 */
		OTHERBROADCAST("其他广播");

		private final String displayName;

		BroadcastingCategory(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;

	/**
	 * 公司ID
	 */
	private String corporationId;
	/**
	 * 状态
	 */
	private BroadcastingStatus status;
	/**
	 * 创建人名称(员工名称)
	 */
	private String createdByUserName;
	/**
	 * 开始时间
	 */
	private Date broadcastingTimeStart;
	/**
	 * 结束时间
	 */
	private Date broadcastingTimeEnd;

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
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

	public PaginationDetailClient getPagination() {
		return pagination;
	}

	public void setPagination(PaginationDetailClient pagination) {
		this.pagination = pagination;
	}

	public SortingDetailClient getSorting() {
		return sorting;
	}

	public void setSorting(SortingDetailClient sorting) {
		this.sorting = sorting;
	}

}
