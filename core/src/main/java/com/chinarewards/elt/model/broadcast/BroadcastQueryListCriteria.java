package com.chinarewards.elt.model.broadcast;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.information.BroadcastMessage;
import com.chinarewards.elt.model.information.BroadcastingCategory;
import com.chinarewards.elt.model.information.BroadcastingStatus;

/**
 * This class is designed to wrap the parameter to search main-accounts using
 * paging.
 * 
 * @author nicho
 * @since 2012年2月14日 14:18:46
 */
public class BroadcastQueryListCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8038695352233973821L;
	private PaginationDetail paginationDetail = new PaginationDetail();

	private SortingDetail sortingDetail = new SortingDetail();

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
	
	/**
	 * 广播类型
	 */
	private BroadcastingCategory category;
	
	/**
	 * 广播 or 消息
	 */
	private BroadcastMessage broadcastMessagetype;
	
	/**
	 * 接收用户ID
	 */
	private String receivingUserId;
	
	/**
	 * 广播ID list
	 */
	private List<String> broadcastList;
	/**
	 * 是否根据当前时间取广播
	 */
	private boolean nowDate;
	
	/**
	 * 创建人用户ID
	 */
	private String createUserId;
	/**
	 * 查询关键字
	 */
	private String queryKey;
	
	/**
	 * 员工ID
	 */
	private String staffId;
	
	/**
	 * 接收对象员工ID
	 */
	private String recevingStaffId;
	

	public String getRecevingStaffId() {
		return recevingStaffId;
	}

	public void setRecevingStaffId(String recevingStaffId) {
		this.recevingStaffId = recevingStaffId;
	}
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}
	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public boolean isNowDate() {
		return nowDate;
	}

	public void setNowDate(boolean nowDate) {
		this.nowDate = nowDate;
	}
	public List<String> getBroadcastList() {
		return broadcastList;
	}

	public void setBroadcastList(List<String> broadcastList) {
		this.broadcastList = broadcastList;
	}

	public String getReceivingUserId() {
		return receivingUserId;
	}

	public void setReceivingUserId(String receivingUserId) {
		this.receivingUserId = receivingUserId;
	}

	public BroadcastMessage getBroadcastMessagetype() {
		return broadcastMessagetype;
	}

	public void setBroadcastMessagetype(BroadcastMessage broadcastMessagetype) {
		this.broadcastMessagetype = broadcastMessagetype;
	}

	public BroadcastingCategory getCategory() {
		return category;
	}

	public void setCategory(BroadcastingCategory category) {
		this.category = category;
	}

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

	public PaginationDetail getPaginationDetail() {
		return paginationDetail;
	}

	public void setPaginationDetail(PaginationDetail paginationDetail) {
		this.paginationDetail = paginationDetail;
	}

	public SortingDetail getSortingDetail() {
		return sortingDetail;
	}

	public void setSortingDetail(SortingDetail sortingDetail) {
		this.sortingDetail = sortingDetail;
	}

}
