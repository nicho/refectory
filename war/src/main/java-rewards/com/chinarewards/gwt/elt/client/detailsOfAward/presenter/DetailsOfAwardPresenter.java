package com.chinarewards.gwt.elt.client.detailsOfAward.presenter;

import java.util.List;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsCriteria.RewardsStatus;
import com.chinarewards.gwt.elt.model.awardReward.WinnerParamVo;
import com.chinarewards.gwt.elt.model.nominate.CandidateParamVo;
import com.chinarewards.gwt.elt.model.nominate.JudgeParamVo;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public interface DetailsOfAwardPresenter extends
		Presenter<DetailsOfAwardPresenter.DetailsOfAwardDisplay> {

	public void initReward(String rewardModel, String instanceId, int headcount,RewardsStatus rewardsStatus);

	public static interface DetailsOfAwardDisplay extends Display {

		public HasClickHandlers getreturnClickHandlers();
		public HasClickHandlers getMoreCandidateClickHandlers();

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

		public void setNominateStaff(List<JudgeParamVo> judge);

		public void setAwardName(String awardName);

		public void setAwardAmt(String awardAmt);

		public void initStaffPanel(Widget w);
		
		public void setWinners(List<WinnerParamVo> winners);
		public void setCandidate(List<CandidateParamVo> cand);
		
		public void setPageTitle(String titleName);
		void setBreadCrumbs(Widget breadCrumbs);
	}
}
