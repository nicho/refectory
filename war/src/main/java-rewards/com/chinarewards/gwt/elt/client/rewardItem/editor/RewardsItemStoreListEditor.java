package com.chinarewards.gwt.elt.client.rewardItem.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemStoreListPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2011年12月22日 15:36:15
 */
public class RewardsItemStoreListEditor extends AbstractEditor {

	final RewardsItemStoreListPresenter rewardsItemStoreListPresenter;
	Object model;

	@Inject
	protected RewardsItemStoreListEditor(RewardsItemStoreListEditorDescriptor editorDescriptor,
			RewardsItemStoreListPresenter rewardsItemStoreListPresenter) {
		super(editorDescriptor);
		this.rewardsItemStoreListPresenter = rewardsItemStoreListPresenter;
	}

	@Override
	public Widget asWidget() {
		return rewardsItemStoreListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		rewardsItemStoreListPresenter.unbind();
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
		rewardsItemStoreListPresenter.bind();
	}
}
