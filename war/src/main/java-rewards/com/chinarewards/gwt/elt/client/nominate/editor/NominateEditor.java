package com.chinarewards.gwt.elt.client.nominate.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.nominate.presenter.NominatePresenter;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsClient;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2011年12月9日 15:36:15
 */
public class NominateEditor extends AbstractEditor {

	final NominatePresenter nominatePresenter;
	Object model;

	@Inject
	protected NominateEditor(NominateEditorDescriptor editorDescriptor,
			NominatePresenter nominatePresenter) {
		super(editorDescriptor);
		this.nominatePresenter = nominatePresenter;
	}

	@Override
	public Widget asWidget() {
		return nominatePresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		nominatePresenter.unbind();
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
			nominatePresenter.initReward(((RewardsClient) model).getId(),instanceId,((RewardsClient) model).getHeadcountLimit());
		}
		nominatePresenter.bind();
	}
}
