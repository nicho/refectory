package com.chinarewards.gwt.elt.client.department.presenter;

import java.util.List;

import com.chinarewards.gwt.elt.client.mvp.DialogPresenter;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public interface ChooseLeaderWinPresenter extends
		DialogPresenter<ChooseLeaderWinPresenter.ChooseLeaderWinDisplay> {

	void setNominee(boolean isLimitByNominee, boolean isChooseAll,
			List<String> orgIds);

	void setLeaderOnly(boolean staffOnly);

	public static interface ChooseLeaderWinDisplay extends Display {
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
