package com.chinarewards.gwt.elt.client.smallControl.view;

import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.detailsOfGift.model.DetailsOfGiftClient;
import com.chinarewards.gwt.elt.client.detailsOfGift.plugin.DetailsOfGiftConstants;
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

public class SmallShopWindowWidget extends Composite {

	@UiField
	Image shopPhoto;
	@UiField
	Anchor shopName;
	@UiField
	InlineLabel integral;

	String shopId;
	private static GloryBroadcastWidgetUiBinder uiBinder = GWT
			.create(GloryBroadcastWidgetUiBinder.class);

	interface GloryBroadcastWidgetUiBinder extends
			UiBinder<Widget, SmallShopWindowWidget> {
	}

	public SmallShopWindowWidget(final String shopId, String shopName,
			String integral, String shopPhoto) {
		initWidget(uiBinder.createAndBindUi(this));
		this.shopId = shopId;
		this.shopName.setText(shopName);
		this.integral.setText(integral);
		this.shopPhoto.setUrl("imageshow?imageName=" + shopPhoto);

		if (shopId != null) {

			this.shopName.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Platform.getInstance()
							.getEditorRegistry()
							.openEditor(
									DetailsOfGiftConstants.EDITOR_DETAILSOFGIFT_SEARCH,
									"EDITOR_DETAILSOFGIFT_SEARCH_DO_ID",
									new DetailsOfGiftClient(shopId));

				}
			});
		}
	}

}
