package com.chinarewards.gwt.elt.client.rewardItem.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemViewPresenter;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2011年12月22日 15:36:15
 */
public class RewardsItemViewEditor extends AbstractEditor {

	final RewardsItemViewPresenter rewardsItemViewPresenter;
	Object model;

	@Inject
	protected RewardsItemViewEditor(RewardsItemViewEditorDescriptor editorDescriptor,
			RewardsItemViewPresenter rewardsItemViewPresenter) {
		super(editorDescriptor);
		this.rewardsItemViewPresenter = rewardsItemViewPresenter;
	}

	@Override
	public Widget asWidget() {
		return rewardsItemViewPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		rewardsItemViewPresenter.unbind();
		return true;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {

	}

	public void setModel(String instanceId, Object model){
		rewardsItemViewPresenter.bind();
	 	if(model !=null){
	 		rewardsItemViewPresenter.initInstanceId(instanceId,(RewardsItemClient) model);
	 
	 	}
	}
}
