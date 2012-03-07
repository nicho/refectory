package com.chinarewards.gwt.elt.client.staffInfo.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.staffAdd.request.StaffAddRequest;
import com.chinarewards.gwt.elt.client.staffAdd.request.StaffAddResponse;
import com.chinarewards.gwt.elt.client.staffView.request.StaffViewRequest;
import com.chinarewards.gwt.elt.client.staffView.request.StaffViewResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.util.XmlUtil_GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.inject.Inject;

public class StaffInfoPresenterImpl extends
		BasePresenter<StaffInfoPresenter.StaffInfoDisplay> implements
		StaffInfoPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;


	String staffId;
	boolean colleague=false;
	EltNewPager pager;
	
	@Inject
	public StaffInfoPresenterImpl(EventBus eventBus, StaffInfoDisplay display,
			DispatchAsync dispatch, SessionManager sessionManager, Win win,
 ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
		this.win = win;
		
	}

	@Override
	public void bind() {
		
		init();
		registerHandler(display.getupadateBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						StaffAddRequest request = new StaffAddRequest();
						if (sessionManager.getSession().getStaffId() != null)
						request.setStaffId(sessionManager.getSession().getStaffId());
						request.setSession(sessionManager.getSession());
						request.setPhone(display.getPhone().getValue());
						request.setPhoto(display.getPhoto().getValue());
						request.setDob(display.getDob().getValue());
						dispatch.execute(request,
								new AsyncCallback<StaffAddResponse>() {

									@Override
									public void onFailure(Throwable t) {
										win.alert(t.getMessage());
									}

									@Override
									public void onSuccess(StaffAddResponse resp) {
										win.alert("保存成功");
									}
								});
					}
				}));

	}

	void init() {
		
		dispatch.execute(new StaffViewRequest(sessionManager.getSession().getStaffId()),
				new AsyncCallback<StaffViewResponse>() {

					@Override
					public void onFailure(Throwable t) {
						win.alert(t.getMessage());
					}

					@Override
					public void onSuccess(StaffViewResponse resp) {
						display.setStaffNo(resp.getStaffNo());
						display.setStaffName(resp.getStaffName());
						if(resp.getDepartmentName().indexOf("ROOT")==-1)
						display.setDepartmentName(resp.getDepartmentName());
						display.setJobPosition(resp.getJobPosition());
						display.setLeadership(resp.getLeadership());
						display.setPhone(resp.getPhone());
						display.setEmail(resp.getEmail());
						display.setDob(resp.getDob());
						display.setStaffImage(resp.getPhoto());

						
					}
				});
		// 浏览即上传事件
				registerHandler(display.getPhotoUpload().addChangeHandler(
						new ChangeHandler() {
							@Override
							public void onChange(ChangeEvent arg0) {
								System.out.println("==========="
										+ display.getPhotoUpload());

								display.getStaffImage().setVisible(true);
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
								// win.alert(eventResults);

								if (eventResults != null) {
									eventResults = XmlUtil_GWT
											.replaceSpecialStr(eventResults);

									try {
										// Document doc =
										// XmlUtil_GWT.parseXml(eventResults);
										// String result =
										// XmlUtil_GWT.getSingleNodeText(doc, "result");
										// String info =
										// XmlUtil_GWT.getSingleNodeText(doc, "info");
										String result = XmlUtil_GWT.getNormalNodeText(
												eventResults, "<result>", "</result>");
										String info = XmlUtil_GWT.getNormalNodeText(
												eventResults, "<info>", "</info>");

										if ("SUCCESS".equals(result)) {
											display.getPhoto().setValue(info);
											String giftImageUrl = "imageshow?imageName="
													+ info;
											display.getStaffImage()
													.setUrl(giftImageUrl);
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
	}

	
}
