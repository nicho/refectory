package com.chinarewards.gwt.elt.client.department.util;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.gwt.elt.client.department.model.DepartmentVo;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentPresenter.DepartmentDisplay;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;

/**
 * @author yanrui
 * */
public class DepartmentAdapterClient {
	/**
	 * 封装表单属性
	 * */
	public static DepartmentVo adapterDisplay(DepartmentDisplay display) {
		DepartmentVo departmentVo = new DepartmentVo();

		departmentVo.setId(display.getDepartmentId().getValue());
		departmentVo.setName(display.getDepartmentName().getValue());

		departmentVo.setParentId(display.getParentId().getValue());

		SpecialTextArea<OrganicationClient> leaderArea = display
				.getLeaderArea();
		if (leaderArea != null) {
			List<OrganicationClient> leaderList = leaderArea.getItemList();
			List<String> leaderIds = new ArrayList<String>();
			for (int i = 0; i < leaderList.size(); i++) {
				leaderIds.add(leaderList.get(i).getId());
			}
			departmentVo.setLeaderIds(leaderIds);
		}

		// 之前的leader
		SpecialTextArea<OrganicationClient> preLeaderArea = display
				.getPreLeaderArea();
		if (preLeaderArea != null) {
			List<OrganicationClient> preLeaderList = preLeaderArea
					.getItemList();
			List<String> preLeaderIds = new ArrayList<String>();
			for (int i = 0; i < preLeaderList.size(); i++) {
				preLeaderIds.add(preLeaderList.get(i).getId());
			}
			departmentVo.setPreLeaderIds(preLeaderIds);
		}

		return departmentVo;
	}
}
