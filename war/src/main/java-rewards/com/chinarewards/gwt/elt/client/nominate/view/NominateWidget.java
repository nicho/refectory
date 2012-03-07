package com.chinarewards.gwt.elt.client.nominate.view;

import java.util.List;

import com.chinarewards.gwt.elt.client.nominate.presenter.NominatePresenter.NominateDisplay;
import com.chinarewards.gwt.elt.model.nominate.JudgeParamVo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class NominateWidget extends Composite implements NominateDisplay {

	@UiField
	Button nominatebutton;
	@UiField
	Label name;
	@UiField
	Label explain;
	@UiField
	Label condition;
	@UiField
	Label integral;
	@UiField
	Label recordName;
	@UiField
	InlineLabel number;
	@UiField
	VerticalPanel judge;
	// @UiField
	// VerticalPanel candidate;
	@UiField
	Label awardNature;
	@UiField
	Label begindate;
	@UiField
	Label awarddate;
	@UiField
	Label awardName;
	@UiField
	InlineLabel nominateMessage;

	@UiField
	InlineLabel expectNominateDate;
	@UiField
	InlineLabel nominateStaff;
	@UiField
	InlineLabel awardAmt;

	// 选人模块
	@UiField
	Panel staffPanel;
	@UiField
	Panel breadCrumbs;
	private static NominateWidgetUiBinder uiBinder = GWT
			.create(NominateWidgetUiBinder.class);

	interface NominateWidgetUiBinder extends UiBinder<Widget, NominateWidget> {
	}

	public NominateWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getNominateClickHandlers() {
		return nominatebutton;
	}

	@Override
	public void setName(String name) {
		this.name.setText(name);

	}

	@Override
	public void setExplain(String explain) {
		this.explain.setText(explain);

	}

	@Override
	public void setCondition(String condition) {
		this.condition.setText(condition);

	}

	@Override
	public void setIntegral(String integral) {
		this.integral.setText(integral);

	}

	@Override
	public void setRecordName(String recordName) {
		this.recordName.setText(recordName);

	}

	@Override
	public void setNumber(String number) {
		this.number.setText(number);

	}

	@Override
	public void setAwardAmt(String awardAmt) {
		this.awardAmt.setText(awardAmt);

	}

	@Override
	public void setJudge(List<JudgeParamVo> judge) {
		String judgeStr = "";
		for (int i = 0; i < judge.size(); i++) {
			if ("NOMINATED".equals(judge.get(i).getJudgeStatus()))
				judgeStr += judge.get(i).getName() + "(已完成提名),";
			else
				judgeStr += judge.get(i).getName() + ",";

		}
		InlineLabel nominatelab = new InlineLabel(judgeStr);
		this.judge.add(nominatelab);

	}

	// @Override
	// public void setCandidate(List<CandidateParamVo> candidate) {
	// boolean fal = true;//是否创建人界面
	// if (fal) {
	// String str = "";
	// for (int i = 0; i < 20; i++) {
	// str += "&nbsp";
	// }
	// HTML label = new HTML(str + "被提名次数");
	//
	// this.candidate.add(label);
	// }
	//
	// for (int i = 0; i < candidate.size(); i++) {
	// CandidateParamVo candidateVo =candidate.get(i);
	// String checkBoxName=candidateVo.getName();
	// String name=candidateVo.getName();
	//
	// if (fal) {
	// String str2 = "";
	// for (int j = name.length(); j < 20; j++) {
	// str2 += "-";
	// }
	// checkBoxName =name+ str2 + candidateVo.getNominateCount();// 是否显示被提名次数
	// }
	// CheckBox checkBox = new CheckBox(checkBoxName);
	// checkBox.getElement().setAttribute("staffid", candidateVo.getStaffid());
	// checkBox.getElement().setAttribute("candidateid", candidateVo.getId());
	// checkBox.getElement().setAttribute("staffName", candidateVo.getName());
	//
	//
	//
	// this.candidate.add(checkBox);
	// }
	//
	// }

	@Override
	public void setAwardNature(String awardNature) {
		this.awardNature.setText(awardNature);
	}

	@Override
	public void setBegindate(String begindate) {
		this.begindate.setText(begindate);
	}

	@Override
	public void setAwarddate(String awarddate) {
		this.awarddate.setText(awarddate);

	}

	@Override
	public void setNominateMessage(String nominateMessage) {
		this.nominateMessage.setText(nominateMessage);
	}

	@Override
	public void setAwardName(String awardName) {
		this.awardName.setText(awardName);
	}

	@Override
	public void setExpectNominateDate(String expectNominateDate) {
		this.expectNominateDate.setText(expectNominateDate);

	}

	@Override
	public void setNominateStaff(String nominateStaff) {
		this.nominateStaff.setText(nominateStaff);
	}

	// @SuppressWarnings("deprecation")
	// @Override
	// public List<NominateCheckBox> getNominateCheckBoxList() {
	// List<NominateCheckBox> idlist = new ArrayList<NominateCheckBox>();
	//
	// for (int i=0;i<candidate.getWidgetCount();i++){
	// Widget widget = candidate.getWidget(i);
	// if (widget instanceof CheckBox){
	// CheckBox checkBox = (CheckBox) widget;
	// if(checkBox.isChecked()==true)
	// {
	// NominateCheckBox box=new NominateCheckBox();
	// box.setStaffId(checkBox.getElement().getAttribute("staffid"));
	// box.setCandidateId(checkBox.getElement().getAttribute("candidateid"));
	// box.setStaffName(checkBox.getElement().getAttribute("staffName"));
	//
	// idlist.add(box);
	// }
	// }
	//
	// }
	//
	// return idlist;
	// }

	@Override
	public void initStaffPanel(Widget w) {
		staffPanel.add(w);
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
		
	}

}
