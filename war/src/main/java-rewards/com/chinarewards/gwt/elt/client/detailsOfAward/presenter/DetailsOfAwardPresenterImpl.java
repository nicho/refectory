package com.chinarewards.gwt.elt.client.detailsOfAward.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.chooseStaff.presenter.ChooseStaffPanelPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.detailsOfAward.request.DetailsOfAwardInitRequest;
import com.chinarewards.gwt.elt.client.detailsOfAward.request.DetailsOfAwardInitResponse;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.dialog.ChooseStaffListDialog;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsCriteria.RewardsStatus;
import com.chinarewards.gwt.elt.client.rewards.plugin.RewardsListConstants;
import com.chinarewards.gwt.elt.model.ChoosePanel.InitChooseListParam;
import com.chinarewards.gwt.elt.model.ChoosePanel.InitChoosePanelParam;
import com.chinarewards.gwt.elt.model.rewards.RewardPageType;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.chinarewards.gwt.elt.util.DateTool;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DetailsOfAwardPresenterImpl extends
		BasePresenter<DetailsOfAwardPresenter.DetailsOfAwardDisplay> implements
		DetailsOfAwardPresenter {

	private final DispatchAsync dispatcher;
	private String awardsId;
	private String pageMenuName;
	private RewardsStatus rewardStatus;
	// private String instanceId;
	private final BreadCrumbsPresenter breadCrumbs;
	private final ChooseStaffPanelPresenter staffPanel;
	private final Provider<ChooseStaffListDialog> chooseStaffDialogProvider;
	@Inject
	public DetailsOfAwardPresenterImpl(EventBus eventBus,
			DetailsOfAwardDisplay display, DispatchAsync dispatcher,
			ChooseStaffPanelPresenter staffPanel,BreadCrumbsPresenter breadCrumbs,Provider<ChooseStaffListDialog> chooseStaffDialogProvider) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.staffPanel = staffPanel;
		this.breadCrumbs=breadCrumbs;
		this.chooseStaffDialogProvider=chooseStaffDialogProvider;
	}

	@Override
	public void bind() {
		breadCrumbs.loadChildPage(pageMenuName);
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		
		init();
		InitChoosePanelParam initChooseParam = new InitChoosePanelParam();
		initChooseParam.setTopName("待提名人：");
		initChooseParam.setChooseBtnName("更多...");
		initChooseParam.setIscleanStaffTextAreaPanel(true);


		InitChooseListParam initChooseListParam = new InitChooseListParam();
		initChooseListParam.setCancelBtnText("关闭");
		initChooseListParam.setHiddenChooseBtn(true);
		initChooseListParam.setHiddenSpecialBoxPanel(true);
		initChooseParam.setInitChooseListParam(initChooseListParam);
		staffPanel.initChoosePanel(initChooseParam);
		staffPanel.setRewardId(awardsId);
		// 候选人面板显示
		staffPanel.bind();
		display.initStaffPanel(staffPanel.getDisplay().asWidget());

		registerHandler(display.getreturnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {

						RewardPageType pagetype;
						if (rewardStatus == RewardsStatus.REWARDED)
							pagetype = RewardPageType.DETAILSOFAWARDPAGE;
						else
							pagetype = RewardPageType.APPLYREWARDLIST;

						RewardsPageClient rpc = new RewardsPageClient();
						rpc.setPageType(pagetype);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										RewardsListConstants.EDITOR_REWARDSLIST_SEARCH,
										"EDITOR_REWARDSLIST_" + pagetype, rpc);
					}
				}));
	}

	/**
	 * 加载初始化数据
	 */
	private void init() {
		// 根据传入ID初始化提名页面

		dispatcher.execute(new DetailsOfAwardInitRequest(awardsId),
				new AsyncCallback<DetailsOfAwardInitResponse>() {
					public void onFailure(Throwable t) {
						Window.alert(t.getMessage());
					}

					@Override
					public void onSuccess(DetailsOfAwardInitResponse response) {

						display.setName(response.getRewardName());
						display.setExplain(response.getDefinition());
						display.setCondition(response.getStandard());
						int total = (int) (response.getTotalAmtLimit() );
						display.setIntegral(total + "");
						display.setRecordName(response.getCreatedStaffName());
						display.setNumber(response.getHeadcountLimit() + "");
						int amt = (int) (response.getAwardAmt() );
						display.setAwardAmt(amt + "");
						display.setJudge(response.getJudgeList());

						display.setAwardNature(response.getAwardMode());
						display.setBegindate(DateTool.dateToString(response
								.getCreatedAt()));
						display.setAwarddate(DateTool.dateToString(response
								.getExpectAwardDate()));
						display.setNominateMessage("领导提名");// wating.........
						display.setNominateStaff(response.getJudgeList());
						display.setExpectNominateDate(DateTool
								.dateToString(response.getExpectNominateDate()));
						display.setAwardName(response.getAwardingStaffName());
						display.setWinners(response.getWinnerList());
						display.setCandidate(response.getCandidateList());

					}
				});
		registerHandler(display.getMoreCandidateClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						final ChooseStaffListDialog dialog = chooseStaffDialogProvider
								.get();
						InitChooseListParam initChooseListParam = new InitChooseListParam();
						initChooseListParam.setCancelBtnText("关闭");
						initChooseListParam.setHiddenChooseBtn(true);
						initChooseListParam.setHiddenSpecialBoxPanel(true);
						if (initChooseListParam != null)
							dialog.initChooseList(initChooseListParam);
						dialog.setRewardId(awardsId);
						dialog.setNominee(false, true, null);// The key is the
																// first
																// parameter(false).
				
						Platform.getInstance().getSiteManager()
								.openDialog(dialog, null);
					}
				}));
	}

	@Override
	public void initReward(String rewardId, String instanceId, int headcount,
			RewardsStatus status) {
		// 加载数据
		this.awardsId = rewardId;
		this.rewardStatus = status;
		if (status == RewardsStatus.NEW)
		{
			this.pageMenuName="待颁奖详细";
		}
		else if (status == RewardsStatus.PENDING_NOMINATE)
		{
			this.pageMenuName="提名详细";
		}
		else
		{
			this.pageMenuName="已颁奖详细";
		}
		display.setPageTitle(pageMenuName);
	}

}
