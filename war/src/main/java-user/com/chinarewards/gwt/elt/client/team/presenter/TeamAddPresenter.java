package com.chinarewards.gwt.elt.client.team.presenter;

import java.util.List;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.team.model.TeamSearchVo;
import com.chinarewards.gwt.elt.client.team.model.TeamVo;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public interface TeamAddPresenter extends Presenter<TeamAddPresenter.TeamAddDisplay> {
	public void initEditor(String instanceId,TeamSearchVo vo);
	public static interface TeamAddDisplay extends Display {

		public HasValue<String> getName();

		public HasValue<String> getExplains();
		public HasClickHandlers getChooseStaffBtnClick() ;
		public HasClickHandlers getChooseStaffBtnsClick() ;
		SpecialTextArea<OrganicationClient> getSpecialTextArea();
		SpecialTextArea<OrganicationClient> getSpecialTextAreas();
		// 得到负责人的id
		List<String> getManagerIds();
		// 得到成员的id
		List<String> getMemberIds();
		public HasClickHandlers getSaveClick();

		public void clear();
		
		public void initEditTeam(TeamVo teamVo,String instanceId);
		public HasClickHandlers getBackClick();

		void setBreadCrumbs(Widget breadCrumbs);
		// 修改时显示管理人
		void showManagerInfo(TeamVo info);
		// 修改时显示成员人
		void showMemberInfo(TeamVo info);
	}

	
}
