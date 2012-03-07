package com.chinarewards.elt.model.broadcast;

import java.util.List;

import com.chinarewards.elt.domain.information.BroadcastReply;
import com.chinarewards.elt.domain.information.Broadcasting;

public class BroadcastAndReplyQueryListVo {
	private int total;
	private Broadcasting broadcasting;

	private List<BroadcastReply> resultList;

	
	public Broadcasting getBroadcasting() {
		return broadcasting;
	}

	public void setBroadcasting(Broadcasting broadcasting) {
		this.broadcasting = broadcasting;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<BroadcastReply> getResultList() {
		return resultList;
	}

	public void setResultList(List<BroadcastReply> resultList) {
		this.resultList = resultList;
	}

}
