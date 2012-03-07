package com.chinarewards.gwt.elt.client.detailsOfAward.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.detailsOfAward.presenter.DetailsOfAwardPresenter;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsClient;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2011年12月22日 10:02:40
 */
public class DetailsOfAwardEditor extends AbstractEditor {

	final DetailsOfAwardPresenter detailsOfAwardPresenter;
	Object model;

	@Inject
	protected DetailsOfAwardEditor(DetailsOfAwardEditorDescriptor editorDescriptor,
			DetailsOfAwardPresenter detailsOfAwardPresenter) {
		super(editorDescriptor);
		this.detailsOfAwardPresenter = detailsOfAwardPresenter;
	}

	@Override
	public Widget asWidget() {
		return detailsOfAwardPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		detailsOfAwardPresenter.unbind();
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
			detailsOfAwardPresenter.initReward(((RewardsClient) model).getId(),instanceId,((RewardsClient) model).getHeadcountLimit(),((RewardsClient) model).getStatus());
		}
		detailsOfAwardPresenter.bind();
	}
}
