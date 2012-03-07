package com.chinarewards.elt.model.staff;

import java.util.List;

import com.chinarewards.elt.domain.reward.person.Winner;

public class StaffWinVo {
	private int total;

	private List<Winner> resultList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Winner> getResultList() {
		return resultList;
	}

	public void setResultList(List<Winner> resultList) {
		this.resultList = resultList;
	}
	
}
