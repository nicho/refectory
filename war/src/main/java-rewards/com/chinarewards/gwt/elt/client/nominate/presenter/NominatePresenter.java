package com.chinarewards.gwt.elt.client.nominate.presenter;

import java.util.List;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.model.nominate.JudgeParamVo;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public interface NominatePresenter extends
		Presenter<NominatePresenter.NominateDisplay> {

	public void initReward(String rewardModel, String instanceId, int headcount);

	public static interface NominateDisplay extends Display {

		public HasClickHandlers getNominateClickHandlers();

		public void setName(String name);

		public void setExplain(String explain);

		public void setCondition(String condition);

		public void setIntegral(String integral);

		public void setRecordName(String recordName);

		public void setNumber(String number);

		public void setJudge(List<JudgeParamVo> nominate);

		public void setAwardNature(String awardNature);

		public void setBegindate(String begindate);

		public void setAwarddate(String awarddate);

		public void setNominateMessage(String nominateMessage);

		public void setExpectNominateDate(String expectNominateDate);

		public void setNominateStaff(String nominateStaff);

		public void setAwardName(String awardName);

		public void setAwardAmt(String awardAmt);

		// public List<NominateCheckBox> getNominateCheckBoxList();
		// public void setCandidate(List<CandidateParamVo> candidate);

		public void initStaffPanel(Widget w);
		void setBreadCrumbs(Widget breadCrumbs);
	}
}
