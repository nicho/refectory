package com.chinarewards.gwt.elt.client.colleagueLattice.view;

import com.chinarewards.gwt.elt.client.colleagueParticular.plugin.ColleagueParticularConstants;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.ui.DialogCloseListener;
import com.chinarewards.gwt.elt.client.messageSave.dialog.MessageSaveDialog;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Provider;

public class ColleagueLatticeWidget extends Composite {

	@UiField
	Image photo;
	@UiField
	Anchor staffName;
	@UiField
	InlineLabel deptName;
	
	@UiField
	Anchor quietly;
	@UiField
	Anchor dalliance;
	@UiField
	Anchor email;
	

	private static ColleagueLatticeWidgetUiBinder uiBinder = GWT
			.create(ColleagueLatticeWidgetUiBinder.class);

	interface ColleagueLatticeWidgetUiBinder extends
			UiBinder<Widget, ColleagueLatticeWidget> {
	}

	public ColleagueLatticeWidget(final String staffId,final String staffName,String deptName,String photo,final Provider<MessageSaveDialog> messageSaveDialog) {
		initWidget(uiBinder.createAndBindUi(this));
		this.staffName.setText(staffName);
		this.deptName.setText(deptName);
		this.photo.setUrl("imageshow?imageName="+photo);
	
		this.staffName.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						ColleagueParticularConstants.EDITOR_COLLEAGUEPARTICULAR_SEARCH,
						"EDITOR_COLLEAGUEPARTICULAR_SEARCH_DO_ID", new OrganicationClient(staffId,staffName));
				
			}
		});
		this.photo.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						ColleagueParticularConstants.EDITOR_COLLEAGUEPARTICULAR_SEARCH,
						"EDITOR_COLLEAGUEPARTICULAR_SEARCH_DO_ID", new OrganicationClient(staffId,staffName));
				
			}
		});
		this.quietly.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final MessageSaveDialog dialog = messageSaveDialog.get();
				dialog.initStaff(staffId, staffName);
				Platform.getInstance().getSiteManager().openDialog(dialog, new DialogCloseListener() {
					public void onClose(String dialogId,
							String instanceId) {
						
					}
				});
				
			}
		});
	}

}
