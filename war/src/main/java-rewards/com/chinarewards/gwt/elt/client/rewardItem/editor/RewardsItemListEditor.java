package com.chinarewards.gwt.elt.client.rewardItem.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2011年12月22日 15:36:15
 */
public class RewardsItemListEditor extends AbstractEditor {

	final RewardsItemListPresenter rewardsItemListPresenter;
	Object model;

	@Inject
	protected RewardsItemListEditor(RewardsItemListEditorDescriptor editorDescriptor,
			RewardsItemListPresenter rewardsItemListPresenter) {
		super(editorDescriptor);
		this.rewardsItemListPresenter = rewardsItemListPresenter;
	}

	@Override
	public Widget asWidget() {
		return rewardsItemListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		rewardsItemListPresenter.unbind();
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
		rewardsItemListPresenter.bind();
	}
}
