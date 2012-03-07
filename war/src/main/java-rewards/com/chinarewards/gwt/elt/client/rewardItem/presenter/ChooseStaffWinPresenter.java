package com.chinarewards.gwt.elt.client.rewardItem.presenter;

import java.util.List;

import com.chinarewards.gwt.elt.client.mvp.DialogPresenter;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public interface ChooseStaffWinPresenter extends
		DialogPresenter<ChooseStaffWinPresenter.ChooseStaffWinDisplay> {

	void setNominee(boolean isLimitByNominee, boolean isChooseAll,
			List<String> orgIds);

	void setStaffOnly(boolean staffOnly);

	public static interface ChooseStaffWinDisplay extends Display {
		HasValue<String> getName();

		HasClickHandlers getSearchBtn();

		HasClickHandlers getResetBtn();

		HasClickHandlers getChooseBtn();

		HasClickHandlers getCancelBtn();

		Widget asWidget();

		/**
		 * 重置查询信息
		 */
		void reset();

		SpecialTextArea<StaffClient> getSpecialTextBox();

		Panel getResultPanel();
		Panel getResultPage();
		String getDeptId();

	}

}
