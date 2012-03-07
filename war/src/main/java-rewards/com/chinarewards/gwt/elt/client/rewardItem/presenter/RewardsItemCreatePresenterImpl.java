package com.chinarewards.gwt.elt.client.rewardItem.presenter;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;
import com.chinarewards.gwt.elt.client.budget.request.InitCorpBudgetRequest;
import com.chinarewards.gwt.elt.client.budget.request.InitCorpBudgetResponse;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.ui.DialogCloseListener;
import com.chinarewards.gwt.elt.client.frequency.CalculatorSelectFactory;
import com.chinarewards.gwt.elt.client.frequency.NextRewardsDateCalculator;
import com.chinarewards.gwt.elt.client.frequency.RewardStartDateCalculator;
import com.chinarewards.gwt.elt.client.frequency.RewardStartDateCalculator.RewardDateInfo;
import com.chinarewards.gwt.elt.client.frequency.SimpleRewardDateInfo;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.dialog.ChooseStaffWinDialog;
import com.chinarewards.gwt.elt.client.rewardItem.dialog.FrequencySettingDialog;
import com.chinarewards.gwt.elt.client.rewardItem.event.ChooseStaffEvent;
import com.chinarewards.gwt.elt.client.rewardItem.event.FrequencySettingEvent;
import com.chinarewards.gwt.elt.client.rewardItem.handler.ChooseStaffHandler;
import com.chinarewards.gwt.elt.client.rewardItem.handler.FrequencySettingHandler;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.chinarewards.gwt.elt.client.rewardItem.request.CreateRewardsItemRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.CreateRewardsItemResponse;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemByIdRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemByIdResponse;
import com.chinarewards.gwt.elt.client.rewards.model.DayFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.MonthFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.EveryoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsBaseInfo;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsTypeClient;
import com.chinarewards.gwt.elt.client.rewards.model.SpecialCondition;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.client.rewards.model.WeekFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.YearFrequencyClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.client.win.confirm.ConfirmHandler;
import com.chinarewards.gwt.elt.util.DateTool;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class RewardsItemCreatePresenterImpl extends
		BasePresenter<RewardsItemCreatePresenter.RewardsItemDisplay> implements
		RewardsItemCreatePresenter {
	String instanceId;//修改时传过来的ID
	boolean isItemStore = false;//是否是奖项库
	boolean isEnabled = false;
	 double remainCount;//剩余的总积分
	/**
	 * 是否为修改页，默认为false
	 */
	private boolean isEditPage = false;
	private String rewardsItemId;
	/** 上一次颁奖时间 **/
	private Date lastRewardsDate;
	/**
	 * 选择员工模块
	 */
	private final ChooseStaffBlockPresenter staffBlock;
	private final DispatchAsync dispatcher;
	private final ErrorHandler errorHandler;
	private final Provider<FrequencySettingDialog> freProvider;
	private final Win win;
	private final SessionManager sessionManager;
	private final RewardStartDateCalculator startDateCalculator;
	private final CalculatorSelectFactory factory;
	private final BreadCrumbsPresenter breadCrumbs;
	RewardsItemClient item;
	// 保存上一次修改正确的结束时间
		private Date endDateCopy;
		// 保存下次公布时间副本
		private Date nextPublishCopy;
		// 保存下次公布时间和下次颁奖相隔多少天,默认间隔一天
		private int day = 1;
		// 保存最初的开始时间
		private Date startDateCopy;

		// 不正规修改颁奖时间记录是否有过提示,默认是没有
		private boolean okAndNo = false;
		// 保存最近一次颁奖时间
		private Date rewardsDateCopuy;
		// 保存修改前一次的下次颁奖日期
		private Date nextRewardsDateCopy;
		
		private  Date tmdays;//提前提名的日期

		// 用来判断修改前是否有平率
		private boolean isFrequency = false;
		private final Provider<ChooseStaffWinDialog> chooseStaffDialogProvider;
	@Inject
	public RewardsItemCreatePresenterImpl(EventBus eventBus,ChooseStaffBlockPresenter staffBlock,
			RewardsItemDisplay display,DispatchAsync dispatcher,ErrorHandler errorHandler,Provider<FrequencySettingDialog> freProvider
			,RewardStartDateCalculator startDateCalculator,CalculatorSelectFactory factory,SessionManager sessionManager,BreadCrumbsPresenter breadCrumbs
			,Provider<ChooseStaffWinDialog> chooseStaffDialogProvider,Win  win) {
		super(eventBus, display);
		this.dispatcher=dispatcher;
		this.staffBlock = staffBlock;
		this.errorHandler = errorHandler;
		this.freProvider = freProvider;
		this.startDateCalculator = startDateCalculator;
		this.factory = factory;
		this.sessionManager = sessionManager;
		this.chooseStaffDialogProvider = chooseStaffDialogProvider;
		this.win = win;
		this .breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		//绑定事件
		 init();
		 initBudget();
		//候选人面板显示
		staffBlock.bind();
		display.initStaffBlock(staffBlock.getDisplay().asWidget());
		
		if(instanceId.equals(RewardsItemConstants.EDITOR_REWARDSITEMSTORE))
		{
			breadCrumbs.loadChildPage("创建奖项模板");
			display.setRewardButtonDisplay(false);
			display.setRewardBackButtonDisplay(false);
			
		}else{
			breadCrumbs.loadChildPage("创建奖项");
			display.setRewardButtonDisplay(true);
			display.setRewardBackButtonDisplay(true);
		}
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		
		
	}
	private void init(){
		//频率设置
		registerHandler(display.getFrequencySettingClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						FrequencySettingDialog dialog = freProvider.get();
						// load default values
						dialog.initNewFrequency();

						if (display.getFrequencyObj() != null) {
							dialog.initFrequency(display.getFrequencyObj(),
									true);
						}
						final HandlerRegistration registration = eventBus
								.addHandler(FrequencySettingEvent.getType(),
										new FrequencySettingHandler() {
											@Override
											public void setting(FrequencyClient frequency) {
												doSettingFrequency(frequency);
											}
										});
						        Platform.getInstance().getSiteManager()
								.openDialog(dialog, new DialogCloseListener() {
									public void onClose(String dialogId,String instanceId) {
										registration.removeHandler();
									}
								});
					}
				}));
		
		// handles the event when the start time is clicked.
					registerHandler(display.getStartTimeChangeHandler()
							.addValueChangeHandler(new ValueChangeHandler<Date>() {
								@Override
								public void onValueChange(ValueChangeEvent<Date> e) {

									Date startDate = display.getStartTime().getValue();
									FrequencyClient frequency = display.getFrequencyObj();
									// 没设定频率的时候，终止！
									if (frequency == null) {
										return;
									}

									Date currentDate = new Date();
									RewardDateInfo info = new SimpleRewardDateInfo(startDate, currentDate,lastRewardsDate);
									// The begin time which to cal
									// the next rewarded time.
									Date startTime = startDateCalculator.calculateStartDate(info);
									NextRewardsDateCalculator cal = factory.getCalculator();
									Date nextRewardsDate = cal.calNextRewardsDate(startTime, frequency);
									display.getStartTime().setValue(nextRewardsDate);
									display.getNextRewardsTime().setValue(nextRewardsDate);

									
									// 修改下次公布时间
									modifyNextPublishTime();
								}
							}));

					

					// 下次公布时间值的改变事件
					registerHandler(display.getNextPublishTime().addValueChangeHandler(
							new ValueChangeHandler<Date>() {
								@Override
								public void onValueChange(ValueChangeEvent<Date> e) {
									Date nextPublishDate = e.getValue();
									Date nextRewardsDate = display.getNextRewardsTime().getValue();
									if (nextRewardsDate.getTime() <= nextPublishDate.getTime()) {
										display.getNextPublishTime().setValue(nextPublishCopy);
									} else {
										nextPublishCopy = nextRewardsDate;
										//day = DateTool.getIntervalDays(nextPublishDate,	nextRewardsDate);
									}
								}
							}));

					registerHandler(display.getNextRewardsTime().addValueChangeHandler(
							new ValueChangeHandler<Date>() {
								@Override
								public void onValueChange(ValueChangeEvent<Date> e) {
									Date rewardsDate = display.getNextRewardsTime().getValue();

									modifyNextRewardsSetDate(rewardsDate);
									if (rewardsDateCopuy != null) {
										modifyNextPublishTimeClew();
									}

								}
							}));
					//提名人按钮事件
					registerHandler(display.getChooseStaffBtnClick().addClickHandler(
							new ClickHandler() {
								@Override
								public void onClick(ClickEvent arg0) {
									final ChooseStaffWinDialog dialog = chooseStaffDialogProvider.get();
									dialog.setNominee(false, true, null);
									final HandlerRegistration registration = eventBus.addHandler(ChooseStaffEvent.getType(),new ChooseStaffHandler() {
														@Override
														public void chosenStaff(List<StaffClient> list) {
															for (StaffClient r : list) {
																
																if (!display.getSpecialTextArea().containsItem(r)) {
																	
																	display.getSpecialTextArea().addItem(r);
																}
															}
														}
													});

									       Platform.getInstance().getSiteManager()
											.openDialog(dialog, new DialogCloseListener() {
												public void onClose(String dialogId,String instanceId) {
													registration.removeHandler();
												}
											});
								}
							}));
			//保存事件
			registerHandler(display.getSaveClick().addClickHandler(
					new ClickHandler() {
						@Override
						public void onClick(ClickEvent arg0) {
							// validate first!
							if (!validateFirst()) {
								return;
							}
							
							RewardsItemClient rewardsItem = new RewardsItemClient();
							// 基本信息
							rewardsItem.setName(display.getRewardsName().getValue().trim());
							rewardsItem.setType(new RewardsTypeClient(display.getRewardsType(), ""));//
							rewardsItem.setDefinition(display.getDefinition().getValue().trim());
							rewardsItem.setStandard(display.getStandard().getValue().trim());
							// 候选限额
							if(display.getPeopleSizeLimit().getValue()!=null&& !display.getPeopleSizeLimit().getValue().equals(""))
							rewardsItem.setSizeLimit(Integer.parseInt(display.getPeopleSizeLimit().getValue()));
							
							//定义设置奖项和入帐的部门，现为同一部门，从session中得到
							rewardsItem.setBuilderDept(sessionManager.getSession().getDepartmentId());
							rewardsItem.setAccountDept(sessionManager.getSession().getDepartmentId());
	
							// 候选人信息
							rewardsItem.setParticipateInfo(staffBlock.getparticipateInfo());
							//开始时间
							rewardsItem.setStartTime(display.getStartTime().getValue());
							//提名人的信息,自动奖没有提名信息
							 rewardsItem.setTmInfo(getNominateInfo());
							//是否是周期性选择
							rewardsItem.setPeriodEnable(display.getEnableCbx().getValue());
							//总积分
							if(display.getTotalJF()!=null&&!display.getTotalJF().toString().equals(""))
							rewardsItem.setTotalJF(Integer.parseInt(display.getTotalJF().toString()));
							//每人积分
							
							rewardsItem.setRewardsFrom(Integer.parseInt(display.getRewardsFrom().toString()));
							
							// 周期性频率信息启用
							if (display.getEnableCbx().getValue() ) {
								if(display.getTmday()!=null&&!display.getTmday().toString().equals("")){//把周期性的提名提前的天数放在一个rewardsItem
								 rewardsItem.setTmdays(display.getTmday().intValue());
								}else{
									 rewardsItem.setTmdays(0);
								}
								if(display.getAutoCbx().getValue()==true){
									rewardsItem.setNextPublishTime(display.getStartTime().getValue());
								}else{
									rewardsItem.setNextPublishTime(display.getNextPublishTime().getValue());
								}
								rewardsItem.setExpectAwardDate(display.getNextRewardsTime().getValue());
								rewardsItem.setNextTime(display.getNextRewardsTime().getValue());//下次颁奖的时间
								rewardsItem.setFrequency(display.getFrequencyObj());//周期频率设置
								
								rewardsItem	.setAuto(display.getAutoCbx().getValue());
								rewardsItem.setRewardsUnit(display.getRewardsUnit());
								rewardsItem.setHasSpecialCondition(display.getSpecialCbx().getValue());
								
								rewardsItem.setGeneratedRewards(false);
								if (display.getSpecialCbx().getValue()&& display.getBirthRadio().getValue()) {
									rewardsItem.setCondition(SpecialCondition.birth);
								}
							}else{// 一次性质
								rewardsItem.setFrequency(null);//周期频率设置为null
								rewardsItem.setNextPublishTime(display.getStartTime().getValue());
								rewardsItem.setExpectAwardDate(display.getExpectTime().getValue());//期望的时间
								if(display.getTmdays()!=null&&!display.getTmdays().toString().equals(""))//把一次性的提名提前的天数放在一个rewardsItem
									 rewardsItem.setTmdays(display.getTmdays().intValue());
								else
									 rewardsItem.setTmdays(0);
								}
	
						
							if (!isEditPage) {
								rewardsItem.setId("");
								doSave(rewardsItem,false);
							} else {
								rewardsItem.setId(rewardsItemId);
								doEdit(rewardsItem,false);//修改功能
							}
						}
					}));
		
	        	  //保存奖项库事件
				  registerHandler(display.getSaveStoreClick().addClickHandler(
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent arg0) {
								// validate first!
								if (!validateFirst()) {
									return;
								}
								
								RewardsItemClient rewardsItemStore = new RewardsItemClient();
								// 基本信息
								rewardsItemStore.setName(display.getRewardsName().getValue().trim());
								rewardsItemStore.setType(new RewardsTypeClient(display.getRewardsType(), ""));//
								rewardsItemStore.setDefinition(display.getDefinition().getValue().trim());
								rewardsItemStore.setStandard(display.getStandard().getValue().trim());
								// 候选限额
								if(display.getPeopleSizeLimit().getValue()!=null&& !display.getPeopleSizeLimit().getValue().equals(""))
								rewardsItemStore.setSizeLimit(Integer.parseInt(display.getPeopleSizeLimit().getValue()));
								
								//定义设置奖项和入帐的部门，现为同一部门，从session中得到
								rewardsItemStore.setBuilderDept(sessionManager.getSession().getDepartmentId());
								rewardsItemStore.setAccountDept(sessionManager.getSession().getDepartmentId());

								// 候选人信息
								rewardsItemStore.setParticipateInfo(staffBlock.getparticipateInfo());
								//开始时间
								rewardsItemStore.setStartTime(display.getStartTime().getValue());
								//提名人的信息,自动奖没有提名信息
								 rewardsItemStore.setTmInfo(getNominateInfo());
								//是否是周期性选择
								rewardsItemStore.setPeriodEnable(display.getEnableCbx().getValue());
								//总积分
								if(display.getTotalJF()!=null&&!display.getTotalJF().toString().equals(""))
								rewardsItemStore.setTotalJF(Integer.parseInt(display.getTotalJF().toString()));
								//每人积分
								
								rewardsItemStore.setRewardsFrom(Integer.parseInt(display.getRewardsFrom().toString()));
								
								// 周期性频率信息启用
								if (display.getEnableCbx().getValue() ) {
									if(display.getTmday()!=null&&!display.getTmday().toString().equals("")){//把周期性的提名提前的天数放在一个rewardsItemStore
									 rewardsItemStore.setTmdays(display.getTmday().intValue());
									}else{
										 rewardsItemStore.setTmdays(0);
									}
									if(display.getAutoCbx().getValue()==true){
										rewardsItemStore.setNextPublishTime(display.getStartTime().getValue());
									}else{
										rewardsItemStore.setNextPublishTime(display.getNextPublishTime().getValue());
									}
									rewardsItemStore.setExpectAwardDate(display.getNextRewardsTime().getValue());
									rewardsItemStore.setNextTime(display.getNextRewardsTime().getValue());//下次颁奖的时间
									rewardsItemStore.setFrequency(display.getFrequencyObj());//周期频率设置
									
									rewardsItemStore	.setAuto(display.getAutoCbx().getValue());
									rewardsItemStore.setRewardsUnit(display.getRewardsUnit());
									rewardsItemStore.setHasSpecialCondition(display.getSpecialCbx().getValue());
									
									rewardsItemStore.setGeneratedRewards(false);
									if (display.getSpecialCbx().getValue()&& display.getBirthRadio().getValue()) {
										rewardsItemStore.setCondition(SpecialCondition.birth);
									}
								}else{// 一次性质
									rewardsItemStore.setFrequency(null);//周期频率设置为null
									rewardsItemStore.setNextPublishTime(display.getStartTime().getValue());
									rewardsItemStore.setExpectAwardDate(display.getExpectTime().getValue());//期望的时间
									if(display.getTmdays()!=null&&!display.getTmdays().toString().equals(""))//把一次性的提名提前的天数放在一个rewardsItemStore
										 rewardsItemStore.setTmdays(display.getTmdays().intValue());
									else
										 rewardsItemStore.setTmdays(0);
									}
							
								if (!isEditPage) {
									rewardsItemStore.setId("");
									doSave(rewardsItemStore,true);//true是保存到奖项库
								} else {
									rewardsItemStore.setId(rewardsItemId);
									doEdit(rewardsItemStore,true);//修改功能
								}
							}
						}));
				 registerHandler(display.getBackClick().addClickHandler(//奖项返回
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent arg0) {
								
								Platform.getInstance()
								.getEditorRegistry()
								.openEditor(RewardsItemConstants.EDITOR_REWARDSITEM_List,
										"EDITOR_REWARDSITEM_List_DO_ID", instanceId);

			               }
		
		        }));
				 //下面为奖项库按钮
				 registerHandler(display.getBackStoreClick().addClickHandler(
						 
							new ClickHandler() {
								@Override
								public void onClick(ClickEvent arg0) {
									 Platform.getInstance()
									.getEditorRegistry()
									.openEditor(RewardsItemConstants.EDITOR_REWARDSITEMSTORE_LIST,
											RewardsItemConstants.EDITOR_REWARDSITEMSTORE, instanceId);

				               }
			
			    }));
	     }
		public ParticipateInfoClient getNominateInfo(){
			ParticipateInfoClient nominateInfo = null;
			List<OrganicationClient> orgs = new ArrayList<OrganicationClient>();
		     if(display.getAutoCbx().getValue()==false){	//自动奖时没有提名人ID 
				for (String orgId : display.getNominateIds()) {
					 orgs.add(new OrganicationClient(orgId, ""));
				}
		       }
			nominateInfo = new SomeoneClient(orgs);
			return nominateInfo;
		 }
		private void initBudget(){
			   
			dispatcher.execute(new InitCorpBudgetRequest(sessionManager.getSession()),
						new AsyncCallback<InitCorpBudgetResponse>() {
				          	@Override
							public void onFailure(Throwable arg0) {
								errorHandler.alert("查询财年周期出错!");
								
							}

							@Override
							public void onSuccess(InitCorpBudgetResponse response) {
								 List<CorpBudgetVo> list = response.getResult();
								 CorpBudgetVo vo = new CorpBudgetVo();
								 if(list.size()>0){
									   vo = list.get(0);
									   display.setRemainCount((vo.getBudgetIntegral()-vo.getUseIntegeral())+"");
									   display.setTitle(vo.getBudgetTitle());
									   remainCount = vo.getBudgetIntegral()-vo.getUseIntegeral();
										
								 }
									
									
								
							}

						});
			 
		   }
		private void doSave(RewardsItemClient rewardsItem,final boolean itemStore) {
			dispatcher.execute(new CreateRewardsItemRequest(rewardsItem,sessionManager.getSession(),itemStore),
			new AsyncCallback<CreateRewardsItemResponse>() {
				@Override
				public void onFailure(Throwable t) {
					errorHandler.alert(t.toString());
				}

				@Override
				public void onSuccess(CreateRewardsItemResponse response) {
					win.alert("添加成功");
				//	if(instanceId!=null||!instanceId.equals(""))
					if(itemStore==false)
					{
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								RewardsItemConstants.EDITOR_REWARDSITEM_List,
								"EDITOR_REWARDSITEM_List_DO_ID", instanceId);
					}
					else
					{
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								RewardsItemConstants.EDITOR_REWARDSITEMSTORE_LIST,
								RewardsItemConstants.EDITOR_REWARDSITEMSTORE, instanceId);
					}

				}
			});
		}
	  private void doEdit(final RewardsItemClient rewardsItem,final boolean itemStore) {
		win.confirm("修改提示", "确定修改吗？", new ConfirmHandler() {
			
	@Override
	public void confirm() {
		dispatcher.execute(new CreateRewardsItemRequest(rewardsItem,sessionManager.getSession(),itemStore),
				new AsyncCallback<CreateRewardsItemResponse>() {
					@Override
					public void onFailure(Throwable t) {
						win.alert("修改失败");

					}
                   
					@Override
					public void onSuccess(CreateRewardsItemResponse arg0) {
						win.alert("修改成功");
						if(itemStore==false)
						{
							Platform.getInstance()
							.getEditorRegistry()
							.openEditor(
									RewardsItemConstants.EDITOR_REWARDSITEM_List,
									"EDITOR_REWARDSITEM_List_DO_ID", instanceId);
						}
						else
						{
							Platform.getInstance()
							.getEditorRegistry()
							.openEditor(
									RewardsItemConstants.EDITOR_REWARDSITEMSTORE_LIST,
									RewardsItemConstants.EDITOR_REWARDSITEMSTORE, instanceId);
						}
					}
				});
				
			}
		});
			
	}
	// setting a new frequency.
		private void doSettingFrequency(FrequencyClient frequency) {
			display.showFrequencyInfo(frequency);
			Date startDate = display.getStartTime().getValue();
			
			Date currentDate = new Date();
			// 开始时间设为依据时间
			RewardDateInfo info = new SimpleRewardDateInfo(startDate, currentDate, lastRewardsDate);
			Date startTime = startDateCalculator.calculateStartDate(info);
			// 初始化时默认开始时间是当日时间
			if (startTime == null) {
				startTime = new Date();
			}
			NextRewardsDateCalculator cal = factory.getCalculator();
			Date nextRewardsDate = cal.calNextRewardsDate(startTime, frequency);
			startDate = startDate == null ? new Date() : startDate;
			if (!isEditPage || (startDate.getTime() > nextRewardsDate.getTime())) {
				// 开始时间和下次颁奖时间一样
				display.getStartTime().setValue(nextRewardsDate);
			} else if (!isEditPage
					|| (nextRewardsDate.getTime() >= startDateCopy.getTime())) {
				if (isFrequency) {
					display.getStartTime().setValue(startDateCopy);
				} else {
					display.getStartTime().setValue(nextRewardsDate);
				}
			} else if (isEditPage&& nextRewardsDate.getTime() <= startDateCopy.getTime()) {
				display.getStartTime().setValue(nextRewardsDate);
			}

			display.getNextRewardsTime().setValue(nextRewardsDate);
			
			modifyNextPublishTime();

		}

		private void modifyNextRewardsSetDate(Date rewardsDate) {
			FrequencyClient frequency = display.getFrequencyObj();
			boolean toD = false;
			// 没设定频率的时候，终止！
			if (frequency == null) {
				return;
			}
			
			Date currentDate = new Date();
			RewardDateInfo info = new SimpleRewardDateInfo(rewardsDate, currentDate, lastRewardsDate);
			// The begin time which to cal
			// the next rewarded time.
			Date startTime = startDateCalculator.calculateStartDate(info);
			NextRewardsDateCalculator cal = factory.getCalculator();
			Date nextRewardsDate = cal.calNextRewardsDate(startTime, frequency);
			display.getNextRewardsTime().setValue(nextRewardsDate);

			rewardsDate = display.getNextRewardsTime().getValue();
			if (rewardsDate.getTime() < startDateCopy.getTime()) {
				display.getStartTime().setValue(rewardsDate);
			} else {
				display.getStartTime().setValue(startDateCopy);
			}

			if (toD) {
				
				endDateCopy = nextRewardsDate;
			}


			// 修改下次公布时间
			modifyNextPublishTime();
		}
		// 修改下次颁奖时间
		private void modifyNextPublishTime() {
			Date startDate = display.getNextRewardsTime().getValue();
			//Date date = DateTool.addSomeDay(startDate, 0 - day);
			display.getNextPublishTime().setValue(startDate);
			nextPublishCopy = startDate;
			startDateCopy = startDate;
		
		}
		
		private void modifyNextPublishTimeClew() {
			DateTimeFormat format = DateTimeFormat.getFormat(ViewConstants.date_format);
			Date NextRewardsTimeDate = display.getNextRewardsTime().getValue();
			if ((NextRewardsTimeDate.getTime() <= rewardsDateCopuy.getTime())&& !okAndNo) {
				okAndNo = true;
				Window.confirm("建议下次颁奖时间应该在上次颁奖时间(" + format.format(rewardsDateCopuy)
								+ ")之后！<br/>按“确定”回复到以前的设置！<br/>按“取消”使用新的时间！");

			} else if (NextRewardsTimeDate.getTime() > rewardsDateCopuy.getTime()) {
				okAndNo = false;
				nextRewardsDateCopy = NextRewardsTimeDate;

			}
		}
		
		//奖项验证方法
				private boolean validateFirst() {
					boolean flag = true;
					Date currentDate = new Date();
					StringBuilder errorMsg = new StringBuilder();
					if (display.getRewardsName().getValue() == null
							|| "".equals(display.getRewardsName().getValue().trim())) {
						errorMsg.append("请填写奖项名称!<br>");

						flag = false;
					}
					
				
					// 员工选择
					if (staffBlock.getDisplay().isSomeone().getValue() == true) {
						if (staffBlock.getDisplay().getRealOrginzationIds() == null) {
							errorMsg.append("请选择候奖员工!<br>");
							flag = false;

						}
						if (staffBlock.getDisplay().getRealOrginzationIds() != null
								&& staffBlock.getDisplay().getRealOrginzationIds().size() == 0) {
							errorMsg.append("请选择候奖员工!<br>");
							flag = false;

						}
					}

					if (display.getRewardsType() == null|| "".equals(display.getRewardsType().trim())) {
						errorMsg.append("请选择奖项类型!<br>");
						flag = false;
					}
					
						
					if (display.getTotalJF() == null|| display.getTotalJF().intValue() <= 0) {
						errorMsg.append("总积分额度出错，总积分要是正整数!<br>");
						flag = false;
					}else if (display.getRewardsFrom() == null|| display.getRewardsFrom().intValue() <= 0) {
							errorMsg.append("每人得奖积分额度出错，积分要是正整数!<br>");
							flag = false;
					 } 
															
					if (display.getRewardsFrom() != null) {
						if (display.getRewardsFrom().intValue() == 0) {
							errorMsg.append("每人得奖积分“0”!<br>");
							flag = false;
						}
						if (display.getRewardsFrom().intValue() > display.getTotalJF().intValue()) {
							errorMsg.append("总积分不能小于个人得分!<br>");
							flag = false;
						}
					}

					
				if (staffBlock.getDisplay().isSomeone().getValue() == true) {   //选择部分人员
					
					if (display.getPeopleSizeLimit().getValue() == null||"".equals(display.getPeopleSizeLimit().getValue().trim())) {
						 errorMsg.append("请正确填写获奖名额(正整数)!<br>");
						 flag = false;
					  }
					if (display.getPeopleSizeLimit().getValue() != null&& !"".equals(display.getPeopleSizeLimit().getValue().trim())){	
						int limitPeople = Integer.parseInt(display.getPeopleSizeLimit().getValue());
						if (limitPeople == 0 || limitPeople < 0) {
							errorMsg.append("请正确填写获奖名额(正整数)!<br>");
							flag = false;
						}
					}
				}
				if (display.getStartTime().getValue() == null|| "".equals(display.getStartTime().getValue())) {
					errorMsg.append("开始时间不能为空<br>");
					flag = false;
				}else if(isEnabled==false){
					Date date = DateTool.addSomeDay(currentDate, 0 - 1);
				   if (date.getTime()>display.getStartTime().getValue().getTime()) {
					errorMsg.append("开始日期不能小于当前日期<br>");
					flag = false;
				   }
				}else if(isEnabled==true){
					   if (currentDate.getTime()>display.getStartTime().getValue().getTime()) {
						errorMsg.append("奖项已激活，开始日期要大于当前日期<br>");
						flag = false;
					   }
				}
                   //周期性
					if (display.getEnableCbx().getValue()==true) {
						
						if (display.getNextRewardsTime().getValue() == null|| "".equals(display.getNextRewardsTime().getValue())) {
							errorMsg.append("下次颁奖时间不能为空!<br>");
							flag = false;
						}else if(display.getStartTime().getValue().getTime()>display.getNextRewardsTime().getValue().getTime()){
							errorMsg.append("开始时间要小于或等于下次颁奖时间<br>");
							flag = false;
						}
						if (display.getNominateIds().size()>0&& (display.getTmday()==null||display.getTmday().equals("")|| display.getTmday().intValue() <0)) {
							errorMsg.append(" 要提前提名的天数是正整数!<br>");
							flag = false;
					     }
						// 生日奖校验
						if (display.getSpecialCbx().getValue()
								&& display.getBirthRadio().getValue()) {
							if (display.getFrequencyObj() == null) {
								errorMsg.append("请选择频率!<br>");
								flag = false;
							} else if (display.getFrequencyObj() instanceof WeekFrequencyClient) {
								errorMsg.append("生日奖不能为按周的频率!<br>");
								flag = false;
							} else if (display.getFrequencyObj() instanceof YearFrequencyClient) {
								errorMsg.append("生日奖不能为按年的频率!<br>");
								flag = false;
							} else if (display.getFrequencyObj() instanceof DayFrequencyClient
									&& display.getFrequencyObj().getInterval() > 200) {
								errorMsg.append("生日奖按日的频率，最大只能设到每200日<br>");
								flag = false;
							} else if (display.getFrequencyObj() instanceof MonthFrequencyClient
									&& display.getFrequencyObj().getInterval() > 12) {
								errorMsg.append("生日奖按月的频率，最大只能设到每12月<br>");
								flag = false;
							}
						} else {
							// 普通有频率奖校验
							if (display.getFrequencyObj() == null) {
								errorMsg.append("请选择频率!<br>");
								flag = false;
							} else if (display.getFrequencyObj() instanceof WeekFrequencyClient
									&& display.getFrequencyObj().getInterval() > 20) {
								errorMsg.append("按周的频率，最大只能设到每20周!<br>");
								flag = false;
							} else if (display.getFrequencyObj() instanceof YearFrequencyClient
									&& display.getFrequencyObj().getInterval() > 5) {
								errorMsg.append("按年的频率，最大只能设到每5年!<br>");
								flag = false;
							} else if (display.getFrequencyObj() instanceof DayFrequencyClient
									&& display.getFrequencyObj().getInterval() > 200) {
								errorMsg.append("按日的频率，最大只能设到每200日<br>");
								flag = false;
							} else if (display.getFrequencyObj() instanceof MonthFrequencyClient
									&& display.getFrequencyObj().getInterval() > 36) {
								errorMsg.append("按月的频率，最大只能设到每36月<br>");
								flag = false;
							}
						}
					
						if (!display.getAutoCbx().getValue()) {//不是自动奖
							if (display.getNextPublishTime().getValue() == null) {
								errorMsg.append("请填写下一次公布颁奖时间!<br>");
								flag = false;
							} else if (display.getNextPublishTime().getValue() != null
									&& display.getNextRewardsTime().getValue() != null
									&& display.getNextPublishTime().getValue().getTime()>display.getNextRewardsTime().getValue().getTime()) {
								errorMsg.append("下一次公布奖励时间必小于或等于下一次颁奖时间!<br>");
								flag = false;
							}
						}
					}else{//一次性
						if (display.getExpectTime().getValue() == null|| "".equals(display.getExpectTime().getValue())) {
							errorMsg.append("预计颁奖时间不能为空!<br>");
							flag = false;
						}else if(display.getStartTime().getValue()!=null && display.getStartTime().getValue().getTime()>display.getExpectTime().getValue().getTime()){
							errorMsg.append("开始时间要小于预计颁奖时间<br>");
							flag = false;
						}
						if (display.getNominateIds().size()>0&& (display.getTmdays()==null||display.getTmdays().equals("")|| display.getTmdays().intValue() < 0))
						{	
							errorMsg.append(" 要提前提名的天数是正整数!<br>");
							flag = false;
					     }
					
//						if(display.getNominateIds().size()==0){
//							errorMsg.append("请选择提名人!<br>");
//							flag = false;
//						}
					}
					if (!flag) {
				    	win.alert(errorMsg.toString());
					}
					return flag;
				}
        
				@Override//修改时读取数据
				public void initInstanceId(String instanceId,RewardsItemClient item) {
					this.instanceId = instanceId;
					if(item!=null)
					initDataToEditRewardsItem( item,instanceId);
				}
				
				private void initDataToEditRewardsItem(final RewardsItemClient item,final String instanceId) {
					String id = item.getId();
					isEditPage  = true;
					if(instanceId.equals(RewardsItemConstants.EDITOR_REWARDSITEMSTORE))
						isItemStore = true;
					
					{
						dispatcher.execute(new SearchRewardsItemByIdRequest(id,isItemStore),
								new AsyncCallback<SearchRewardsItemByIdResponse>() {
									@Override
									public void onFailure(Throwable arg0) {
										errorHandler.alert("查询出错!");
										Platform.getInstance()
										.getEditorRegistry()
										.closeEditor(RewardsItemConstants.EDITOR_REWARDSITEM_ADD,instanceId);
									}

									@Override
									public void onSuccess(SearchRewardsItemByIdResponse response) {
										RewardsItemClient item = response.getRewardsItem();
										clear();
										if(isItemStore==false){
											breadCrumbs.loadChildPage("修改奖项");
										    										    
								        }else{
								        	breadCrumbs.loadChildPage("修改奖项模板");
								      
								        } 
										display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
										display.showRewardsItem(item,isItemStore);//显示奖项
                                        
										// 初始前一次颁奖时间
										rewardsDateCopuy = item.getLastRewardedDate();
										// 初始结束时间副本
										endDateCopy = item.getEndTime();
										// 初始下次公布时间副本
										nextPublishCopy = item.getNextPublishTime();
										// 记录最初的开始时间
										Date stD = item.getStartTime();
										if (stD == null)
											stD = new Date();
										startDateCopy = stD;
										// 记录第一次赋值的颁奖时间
										nextRewardsDateCopy = item.getNextTime();
										if (nextPublishCopy != null
												&& nextRewardsDateCopy != null) {
											day = DateTool.getIntervalDays(nextPublishCopy,
													nextRewardsDateCopy);
										}
										// 用了记录修改前是否有平率
										if (item.getFrequency() != null) {
											isFrequency = true;
										}
										//display.showHistoryBtn();
										staffBlock.getDisplay().showParticipateInfo(item.getBaseInfo());
										setId(item.getId());
										lastRewardsDate = item.getLastRewardedDate();
										isEnabled = item.isEnabled();

									}

								});
					}

				}
				// clear
				private void clear() {
					lastRewardsDate = null;
					
					RewardsBaseInfo baseInfo = new RewardsBaseInfo();
					baseInfo.setParticipateInfo(new EveryoneClient());
					staffBlock.getDisplay().showParticipateInfo(baseInfo);
					display.clear();

				}

				public void setId(String id) {
					this.rewardsItemId = id;
					isEditPage = true;
				}
}
