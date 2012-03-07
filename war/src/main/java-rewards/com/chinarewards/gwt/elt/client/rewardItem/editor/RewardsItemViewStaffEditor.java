package com.chinarewards.gwt.elt.client.rewardItem.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemViewStaffPresenter;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class RewardsItemViewStaffEditor extends AbstractEditor {

	final RewardsItemViewStaffPresenter rewardsItemViewStaffPresenter;
	Object model;

	@Inject
	protected RewardsItemViewStaffEditor(RewardsItemViewStaffEditorDescriptor editorDescriptor,
			RewardsItemViewStaffPresenter rewardsItemViewStaffPresenter) {
		super(editorDescriptor);
		this.rewardsItemViewStaffPresenter = rewardsItemViewStaffPresenter;
	}

	@Override
	public Widget asWidget() {
		return rewardsItemViewStaffPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		rewardsItemViewStaffPresenter.unbind();
		return true;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {

	}

	public void setModel(Object model){
		rewardsItemViewStaffPresenter.bind();
	 	if(model !=null){
	 		rewardsItemViewStaffPresenter.initWidget((RewardsItemClient) model);	 
	 	}
	}
}
