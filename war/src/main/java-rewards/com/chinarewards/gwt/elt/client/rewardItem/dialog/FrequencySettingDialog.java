package com.chinarewards.gwt.elt.client.rewardItem.dialog;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractDialog;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.FrequencySettingPresenter;
import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class FrequencySettingDialog extends AbstractDialog {

	final Provider<FrequencySettingPresenter> presenterProvider;

	FrequencySettingPresenter presenter;

	@Inject
	public FrequencySettingDialog(
			Provider<FrequencySettingPresenter> presenterProvider) {
		super("frequency.setting", "frequency.setting");
		this.presenterProvider = presenterProvider;
		presenter = presenterProvider.get();
		presenter.setDialog(this);

		init();
	}

	public void init() {
		setTitle("频率设定");

		presenter.bind();
	}

	@Override
	public Widget asWidget() {
		return presenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		presenter.unbind();
		return true;
	}

	public void initFrequency(FrequencyClient frequency, boolean show) {
		presenter.initFrequency(frequency, show);
	}
	
	public void initNewFrequency() {
		presenter.initNewFrequency();
	}
	
}
