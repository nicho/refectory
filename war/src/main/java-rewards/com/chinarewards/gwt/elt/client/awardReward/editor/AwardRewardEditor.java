package com.chinarewards.gwt.elt.client.awardReward.editor;

import com.chinarewards.gwt.elt.client.awardReward.presenter.AwardRewardPresenter;
import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsClient;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2011年12月9日 15:36:15
 */
public class AwardRewardEditor extends AbstractEditor {

	final AwardRewardPresenter awardRewardPresenter;
	Object model;

	@Inject
	protected AwardRewardEditor(AwardRewardEditorDescriptor editorDescriptor,
			AwardRewardPresenter awardRewardPresenter) {
		super(editorDescriptor);
		this.awardRewardPresenter = awardRewardPresenter;
	}

	@Override
	public Widget asWidget() {
		return awardRewardPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		awardRewardPresenter.unbind();
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
		if (model instanceof RewardsClient) {
			awardRewardPresenter.initReward(((RewardsClient) model).getId(),instanceId,((RewardsClient) model).getHeadcountLimit());
		}
		awardRewardPresenter.bind();
	}
}
