package com.chinarewards.gwt.elt.client.detailsOfGift.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.detailsOfGift.model.DetailsOfGiftClient;
import com.chinarewards.gwt.elt.client.detailsOfGift.presenter.DetailsOfGiftPresenter.DetailsOfGiftDisplay;
import com.chinarewards.gwt.elt.client.detailsOfGift.request.DetailsOfGiftRequest;
import com.chinarewards.gwt.elt.client.detailsOfGift.request.DetailsOfGiftResponse;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.orderConfirmation.model.OrderConfirmationClient;
import com.chinarewards.gwt.elt.client.orderConfirmation.plugin.OrderConfirmationConstants;
import com.chinarewards.gwt.elt.client.shopWindow.presenter.ShopWindowPresenter;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class DetailsOfGiftPresenterImpl extends
		BasePresenter<DetailsOfGiftDisplay> implements DetailsOfGiftPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;
	final ShopWindowPresenter shopWindowPresenter;
	DetailsOfGiftClient orderVo;

	@Inject
	public DetailsOfGiftPresenterImpl(EventBus eventBus,
			DispatchAsync dispatch, ErrorHandler errorHandler,
			SessionManager sessionManager, DetailsOfGiftDisplay display, Win win,ShopWindowPresenter shopWindowPresenter) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
		this.shopWindowPresenter=shopWindowPresenter;

	}

	@Override
	public void bind() {
		init();

	}

	private void init() {
		dispatch.execute(new DetailsOfGiftRequest(orderVo.getGiftId()),
				new AsyncCallback<DetailsOfGiftResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(final DetailsOfGiftResponse response) {
						
						display.setGiftName(response.getGiftName());
						display.setGiftNo(response.getGiftNo().substring(response.getGiftNo().length()-5));
						display.setBrand(response.getBrand());
						display.setType(response.getType());
						display.setStock(response.getStock());
						display.setIntegral(response.getIntegral());
						display.setSummary(response.getSummary());
						display.setExplains(response.getExplains());
						display.setNotes(response.getNotes());
						display.setDispatchcycle(response.getDispatchcycle());
						display.setBusiness(response.getBusiness());
						display.setServicetell(response.getServicetell());
						display.setGiftPhoto(response.getGiftPhoto());
						
						display.getPhotoImage().addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								win.alertImage(response.getGiftPhoto());
								
							}
						});
					}

				});
		display.getExchangeBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						OrderConfirmationConstants.EDITOR_ORDERCONFIRMATION_SEARCH,
						"EDITOR_ORDERCONFIRMATION_SEARCH_DO_ID", new OrderConfirmationClient(orderVo.getGiftId()));
				
			}
		});
		shopWindowPresenter.initShopWindow(1, 4);
		shopWindowPresenter.bind();
		display.setShopWindow(shopWindowPresenter.getDisplay().asWidget());

	}

	@Override
	public void initDetailsOfGift(DetailsOfGiftClient orderVo) {
		this.orderVo = orderVo;

	}

}
