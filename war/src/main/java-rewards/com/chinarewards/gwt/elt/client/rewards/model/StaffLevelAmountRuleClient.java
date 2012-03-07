package com.chinarewards.gwt.elt.client.rewards.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yanxin
 * @since 0.2.0 2010-01-22
 */
public class StaffLevelAmountRuleClient extends RewardsAmountRuleClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4008522767532607555L;

	private List<StaffLevelAmountDataClient> dataList = new ArrayList<StaffLevelAmountDataClient>();

	public StaffLevelAmountRuleClient() {

	}

	public StaffLevelAmountRuleClient(List<StaffLevelAmountDataClient> dataList) {
		this.dataList = dataList;
	}

	public List<StaffLevelAmountDataClient> getDataList() {
		return dataList;
	}

	public void setDataList(List<StaffLevelAmountDataClient> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "StaffLevelAmountRuleClient [dataList=" + dataList + "]";
	}

}
