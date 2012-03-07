package com.chinarewards.gwt.elt.client.shopWindow.view;

import com.chinarewards.gwt.elt.client.awardShopLattice.presenter.AwardShopLatticePresenter.AwardShopLatticeDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

public class ShopWindowLatticeWidget extends Composite implements
		AwardShopLatticeDisplay {
	@UiField
	InlineLabel awardName;
	@UiField
	InlineLabel integral;

	
	@UiField
	Image photo;

	
	private static ShopWindowLatticeWidgetUiBinder uiBinder = GWT
			.create(ShopWindowLatticeWidgetUiBinder.class);

	interface ShopWindowLatticeWidgetUiBinder extends
			UiBinder<Widget, ShopWindowLatticeWidget> {
	}

	public ShopWindowLatticeWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	public ShopWindowLatticeWidget(final String awardName,final String integral,String indate,String photo,final String id) {
		initWidget(uiBinder.createAndBindUi(this));
		this.awardName.setText(awardName);
		this.integral.setText(integral);

		this.photo.setUrl("imageshow?imageName="+photo);
//		photo.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				win.alert("礼品名称:"+awardName+"-----积分:"+integral+"ID:"+id);
//				
//			}
//		});
	}

}
