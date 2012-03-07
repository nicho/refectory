package com.chinarewards.gwt.elt.client.rewardItem.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemCreatePresenter;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2011年12月9日 15:36:15
 */
public class RewardsItemEditor extends AbstractEditor {

	final RewardsItemCreatePresenter rewardsItemPresenter;
	Object model;

	@Inject
	protected RewardsItemEditor(RewardsItemEditorDescriptor editorDescriptor,
			RewardsItemCreatePresenter rewardsItemPresenter) {
		super(editorDescriptor);
		this.rewardsItemPresenter = rewardsItemPresenter;
	}

	@Override
	public Widget asWidget() {
		return rewardsItemPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		rewardsItemPresenter.unbind();
		return true;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {

	}

	public void setModel(String instanceId, Object model) {
		if (model != null) {
			if (model instanceof RewardsItemClient)
				rewardsItemPresenter.initInstanceId(instanceId,(RewardsItemClient) model);
			else
				rewardsItemPresenter.initInstanceId(instanceId,null);
		}
		rewardsItemPresenter.bind();

	}
}
