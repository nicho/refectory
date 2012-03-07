package com.chinarewards.elt.model.broadcast;

import java.util.List;

import com.chinarewards.elt.domain.information.Broadcasting;

public class BroadcastQueryListVo {
	private int total;

	private List<Broadcasting> resultList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Broadcasting> getResultList() {
		return resultList;
	}

	public void setResultList(List<Broadcasting> resultList) {
		this.resultList = resultList;
	}
	
}
