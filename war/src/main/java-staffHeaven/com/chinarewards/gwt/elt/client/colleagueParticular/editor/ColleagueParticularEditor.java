package com.chinarewards.gwt.elt.client.colleagueParticular.editor;

import com.chinarewards.gwt.elt.client.colleagueParticular.presenter.ColleagueParticularPresenter;
import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class ColleagueParticularEditor extends AbstractEditor {

	final ColleagueParticularPresenter colleagueParticularPresenter;
	Object model;

	@Inject
	protected ColleagueParticularEditor(ColleagueParticularEditorDescriptor editorDescriptor,
			ColleagueParticularPresenter colleagueParticularPresenter) {
		super(editorDescriptor);
		this.colleagueParticularPresenter = colleagueParticularPresenter;
	}

	@Override
	public Widget asWidget() {
		return colleagueParticularPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		colleagueParticularPresenter.unbind();
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
		if(model!=null && model instanceof OrganicationClient)
		colleagueParticularPresenter.initColleagueParticular((OrganicationClient)model);
		colleagueParticularPresenter.bind();
	}
}
