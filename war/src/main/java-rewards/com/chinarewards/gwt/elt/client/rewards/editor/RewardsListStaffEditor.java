package com.chinarewards.gwt.elt.client.rewards.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.rewards.presenter.RewardsListStaffPresenter;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class RewardsListStaffEditor extends AbstractEditor {

	final RewardsListStaffPresenter rewardsListPresenter;
	Object model;

	@Inject
	protected RewardsListStaffEditor(
			RewardsListStaffEditorDescriptor editorDescriptor,
			RewardsListStaffPresenter rewardsListPresenter) {
		super(editorDescriptor);
		this.rewardsListPresenter = rewardsListPresenter;
	}

	@Override
	public Widget asWidget() {
		return rewardsListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		rewardsListPresenter.unbind();
		return true;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {

	}

	public void setModel(Object model) {
		this.model = model;
		if (model instanceof RewardsPageClient) {
			rewardsListPresenter.initRewardsList(((RewardsPageClient) model)
					.getPageType());
		}
		rewardsListPresenter.bind();
	}
}
