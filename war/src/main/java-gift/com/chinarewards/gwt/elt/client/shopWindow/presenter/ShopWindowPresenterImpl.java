package com.chinarewards.gwt.elt.client.shopWindow.presenter;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.awardShop.plugin.AwardShopListConstants;
import com.chinarewards.gwt.elt.client.awardShop.request.SearchAwardShopRequest;
import com.chinarewards.gwt.elt.client.awardShop.request.SearchAwardShopResponse;
import com.chinarewards.gwt.elt.client.awardShopLattice.view.AwardShopLatticeWidget;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.client.gift.model.GiftCriteria;
import com.chinarewards.gwt.elt.client.gift.model.GiftCriteria.GiftStatus;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.shopWindow.presenter.ShopWindowPresenter.ShopWindowDisplay;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.util.DateTool;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.inject.Inject;

public class ShopWindowPresenterImpl extends BasePresenter<ShopWindowDisplay>
		implements ShopWindowPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;
	int row;
	int col;

	@Inject
	public ShopWindowPresenterImpl(EventBus eventBus, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,
			ShopWindowDisplay display, Win win) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;

	}

	@Override
	public void bind() {
		init();

	}

	private void init() {

		GiftCriteria criteria = new GiftCriteria();
		criteria.setStatus(GiftStatus.SHELVES);
		// 查询参数....待添加
		dispatch.execute(new SearchAwardShopRequest(criteria, sessionManager
				.getSession().getCorporationId(), sessionManager.getSession()
				.getUserRoles(), sessionManager.getSession().getToken()),
				new AsyncCallback<SearchAwardShopResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchAwardShopResponse response) {

						List<GiftClient> giftList = response.getResult();
						int index = 0;
						Grid grid = new Grid(row, col);

						// Add images to the grid
						int numRows = grid.getRowCount();
						int numColumns = grid.getColumnCount();
						for (int row = 0; row < numRows; row++) {
							for (int col = 0; col < numColumns; col++) {
								if (index < giftList.size()) {
									GiftClient clint = giftList.get(index);
									grid.setWidget(
											row,
											col,
											new AwardShopLatticeWidget(clint
													.getName(), clint
													.getIntegral() + "", DateTool.dateToString(clint.getIndate()) + "", clint
													.getPhoto(), clint.getId())
													.asWidget());
									index++;
								} else {
									grid.setWidget(row, col,
											new AwardShopLatticeWidget("无数据",
													"无数据", "无数据", "无数据", null)
													.asWidget());
								}
							}
						}

						// Return the panel
						grid.ensureDebugId("cwGrid");

						display.getResultPanel().clear();
						display.getResultPanel().add(grid);

					}

				});
		display.getMore().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						AwardShopListConstants.EDITOR_AWARDSHOPLIST_SEARCH,
						"EDITOR_AWARDSHOPLIST_SEARCH_DO_ID", null);
				
			}
		});
	}

	@Override
	public void initShopWindow(int row, int col) {
		this.row=row;
		this.col=col;
	}

}
