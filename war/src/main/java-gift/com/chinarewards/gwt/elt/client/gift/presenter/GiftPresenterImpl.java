package com.chinarewards.gwt.elt.client.gift.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.gift.model.GiftVo;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftConstants;
import com.chinarewards.gwt.elt.client.gift.request.EditGiftRequest;
import com.chinarewards.gwt.elt.client.gift.request.EditGiftResponse;
import com.chinarewards.gwt.elt.client.gift.request.SearchGiftByIdRequest;
import com.chinarewards.gwt.elt.client.gift.request.SearchGiftByIdResponse;
import com.chinarewards.gwt.elt.client.gift.util.GiftAdapterClient;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.chinarewards.gwt.elt.util.XmlUtil_GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.inject.Inject;

public class GiftPresenterImpl extends BasePresenter<GiftPresenter.GiftDisplay>
		implements GiftPresenter {
	String instanceId;// 修改时传过来的ID

	private String thisAction;
	private String giftId;
	//
	private final DispatchAsync dispatcher;
	private final ErrorHandler errorHandler;
	private final SessionManager sessionManager;

	private final Win win;

	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public GiftPresenterImpl(EventBus eventBus, GiftDisplay display,
			DispatchAsync dispatcher, ErrorHandler errorHandler,
			SessionManager sessionManager, Win win,
			BreadCrumbsPresenter breadCrumbs) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		// 绑定事件
		init();

		if (GiftConstants.ACTION_GIFT_ADD.equals(thisAction)) {
			breadCrumbs.loadChildPage("新建礼品");
			initSave();
		} else if (GiftConstants.ACTION_GIFT_EDIT.equals(thisAction)) {
			initEdit();
			breadCrumbs.loadChildPage("编辑礼品");
		} else {
			win.alert("未定义的方法");
		}

		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
	}

	private void init() {
		// 保存事件
		registerHandler(display.getSaveClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						if (!validateSubmit()) {
							return;
						}

						GiftVo giftVo = GiftAdapterClient
								.adapterDisplay(display);

						if (GiftConstants.ACTION_GIFT_ADD.equals(thisAction)) {
							giftVo.setId(null);
							doSave(giftVo);
						} else if (GiftConstants.ACTION_GIFT_EDIT
								.equals(thisAction)) {
							giftVo.setId(giftId);
							doEdit(giftVo);
						} else {
							win.alert("未定义的方法");
						}
					}

					private void doSave(GiftVo gift) {
						dispatcher.execute(new EditGiftRequest(gift,
								sessionManager.getSession()),
								new AsyncCallback<EditGiftResponse>() {
									@Override
									public void onFailure(Throwable t) {
										errorHandler.alert(t.toString());
									}

									@Override
									public void onSuccess(
											EditGiftResponse response) {
										win.alert("添加成功");
										// if(instanceId!=null||!instanceId.equals(""))
										Platform.getInstance()
												.getEditorRegistry()
												.openEditor(
														GiftConstants.EDITOR_GIFTLIST_SEARCH,
														GiftConstants.ACTION_GIFT_LIST,
														instanceId);
									}
								});
					}

					private void doEdit(GiftVo gift) {
						if (Window.confirm("确定修改?")) {
							dispatcher.execute(new EditGiftRequest(gift,
									sessionManager.getSession()),
									new AsyncCallback<EditGiftResponse>() {
										@Override
										public void onFailure(Throwable t) {
											win.alert("修改失败");
											Platform.getInstance()
													.getEditorRegistry()
													.closeEditor(
															GiftConstants.EDITOR_GIFT_EDIT,
															instanceId);
										}

										@Override
										public void onSuccess(
												EditGiftResponse arg0) {
											win.alert("修改成功");
											Platform.getInstance()
													.getEditorRegistry()
													.openEditor(
															GiftConstants.EDITOR_GIFTLIST_SEARCH,
															GiftConstants.ACTION_GIFT_LIST,
															instanceId);
										}
									});
						}
					}

				}));

		// 浏览即上传事件
		registerHandler(display.getPhotoUpload().addChangeHandler(
				new ChangeHandler() {
					@Override
					public void onChange(ChangeEvent arg0) {						
						System.out.println("==========="+display.getPhotoUpload());
						
						display.getGiftImage().setVisible(true);
						display.getPhotoForm().setAction("fileupload");
						display.getPhotoForm().submit();
					}
				}));

		// 上传图片按钮事件
		registerHandler(display.getUploadClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						display.getPhotoForm().setAction("fileupload");
						display.getPhotoForm().submit();
					}
				}));

		// 文件上传后回调
		display.getPhotoForm().addSubmitCompleteHandler(
				new SubmitCompleteHandler() {
					@Override
					public void onSubmitComplete(SubmitCompleteEvent event) {
						String eventResults = event.getResults();
						System.out.println("submitComplete event.getResults:"
								+ eventResults);
//						win.alert(eventResults);

						if (eventResults != null) {
							eventResults=XmlUtil_GWT.replaceSpecialStr(eventResults);
							
							try {
//								Document doc = XmlUtil_GWT.parseXml(eventResults);
//								String result = XmlUtil_GWT.getSingleNodeText(doc, "result");
//								String info = XmlUtil_GWT.getSingleNodeText(doc, "info");
								String result=XmlUtil_GWT.getNormalNodeText(eventResults, "<result>","</result>");
								String info=XmlUtil_GWT.getNormalNodeText(eventResults, "<info>", "</info>");
										
								if ("SUCCESS".equals(result)) {
									display.getPhoto().setValue(info);
									String giftImageUrl = "imageshow?imageName="
											+ info;
									display.getGiftImage().setUrl(giftImageUrl);
								} else {
									win.alert("上传图片异常<br>" + info);
								}
							} catch (Exception e) {
								e.printStackTrace();
								win.alert("上传图片异常，请重试" + e.getMessage());
								return;
							}
						}
					}
				});

		registerHandler(display.getBackClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						breadCrumbs.getGoHistory();
					}
				}));		
	
	}

	// 验证方法
	private boolean validateSubmit() {
		boolean flag = true;
		StringBuilder errorMsg = new StringBuilder();
		if (display.getName().getValue() == null
				|| "".equals(display.getName().getValue().trim())) {
			errorMsg.append("请填写礼品名称!<br>");
			flag = false;
		}

		if (display.getStock() == null
				|| StringUtil.valueOf(display.getStock().getValue()) == null
				|| StringUtil.valueOf(display.getStock().getValue()) < 0) {
			errorMsg.append("请正确填写礼品库存!<br>");
			flag = false;
		}
		if (display.getIndate().getValue() == null|| "".equals(display.getIndate().getValue())) {
			errorMsg.append("有效期不能为空<br>");
			flag = false;
		}
		if (display.getPhoto().getValue().length() == 0) {// 数据实体
			if (display.getPhotoUpload().getFilename().length() == 0) {
				errorMsg.append("请选择图片文件!<br>");
				flag = false;
			} else if (!display.getPhotoUpload().getFilename().endsWith(".jpg")
					&& !display.getPhotoUpload().getFilename().endsWith(".gif")) {
				errorMsg.append("请确认图片格式,仅支持JPG和GIF!<br>");
				flag = false;
			}
		} else {// 浏览
			// errorMsg.append("请选择图片文件!<br>");
			// flag = false;
		}

		if (!flag) {
			win.alert(errorMsg.toString());
		}

		return flag;
	}

	private void initEdit() {
		dispatcher.execute(new SearchGiftByIdRequest(giftId),
				new AsyncCallback<SearchGiftByIdResponse>() {
					@Override
					public void onFailure(Throwable arg0) {
						errorHandler.alert("查询出错!");
						Platform.getInstance()
								.getEditorRegistry()
								.closeEditor(GiftConstants.EDITOR_GIFT_EDIT,
										instanceId);
					}

					@Override
					public void onSuccess(SearchGiftByIdResponse response) {
						GiftVo giftVo = response.getGift();
						clear();
						display.initEditGift(giftVo);
					}
				});
	}

	private void initSave() {
		display.initAddGift(new GiftVo());
	}

	private void clear() {
		display.clear();
	}

	public void setId(String id) {
		this.giftId = id;
	}

	@Override
	public void initEditor(String giftId, String thisAction) {
		this.giftId = giftId;
		this.thisAction = thisAction;
	}

}
