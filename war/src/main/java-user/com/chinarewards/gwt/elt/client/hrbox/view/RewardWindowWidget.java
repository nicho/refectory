package com.chinarewards.gwt.elt.client.hrbox.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RewardWindowWidget extends Composite {

	
	@UiField
	Label rewardName;
	

	String shopId;
	private static GloryBroadcastWidgetUiBinder uiBinder = GWT
			.create(GloryBroadcastWidgetUiBinder.class);

	interface GloryBroadcastWidgetUiBinder extends
			UiBinder<Widget, RewardWindowWidget> {
	}

	public RewardWindowWidget(final String shopId, String shopName) {
		initWidget(uiBinder.createAndBindUi(this));
		this.shopId = shopId;
		this.rewardName.setText(shopName);
		

//		if (shopId != null) {
//
//			this.rewardName.addClickHandler(new ClickHandler() {
//				
//				@Override
//				public void onClick(ClickEvent event) {
//					RewardsClient o = new RewardsClient();
//					o.setId(shopId);
//					Platform.getInstance()
//					.getEditorRegistry()
//					.openEditor(
//							DetailsOfAwardConstants.EDITOR_DETAILSOFAWARD_SEARCH,
//							DetailsOfAwardConstants.EDITOR_DETAILSOFAWARD_SEARCH
//									+ shopId, o);
//
//				}
//			});
//		}
	}

}
