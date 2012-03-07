package com.chinarewards.elt.model.broadcastReply;

import java.io.Serializable;

import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;

/**
 * This class is designed to wrap the parameter to search main-accounts using
 * paging.
 * 
 * @author nicho
 * @since 2012年2月14日 14:18:46
 */
public class BroadcastReplyListCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8038695352233973821L;
	private PaginationDetail paginationDetail = new PaginationDetail();

	private SortingDetail sortingDetail = new SortingDetail();

	private String broadcastId;
	private String replyParentId;
	
	public String getReplyParentId() {
		return replyParentId;
	}

	public void setReplyParentId(String replyParentId) {
		this.replyParentId = replyParentId;
	}

	public String getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(String broadcastId) {
		this.broadcastId = broadcastId;
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
