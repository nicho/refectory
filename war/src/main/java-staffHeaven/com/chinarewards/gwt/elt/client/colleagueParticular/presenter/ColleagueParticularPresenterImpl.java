package com.chinarewards.gwt.elt.client.colleagueParticular.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.corpBroadcast.presenter.CorpBroadcastPresenter;
import com.chinarewards.gwt.elt.client.gloryBroadcast.presenter.GloryBroadcastPresenter;
import com.chinarewards.gwt.elt.client.messageSave.presenter.MessageSavePresenter;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.staffView.presenter.StaffViewPresenter;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.view.constant.CssStyleConstants;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class ColleagueParticularPresenterImpl extends
		BasePresenter<ColleagueParticularPresenter.ColleagueParticularDisplay> implements
		ColleagueParticularPresenter {

//	private final DispatchAsync dispatch;
//	private final SessionManager sessionManager;
//	private final Win win;
	final ErrorHandler errorHandler;
	String staffId;
	String staffName;
	GloryBroadcastPresenter gloryBroadcastPresenter;
	CorpBroadcastPresenter corpBroadcastPresenter;
	MessageSavePresenter messageSavePresenter;
	StaffViewPresenter staffViewPresenter;
	@Inject
	public ColleagueParticularPresenterImpl(EventBus eventBus,
			ColleagueParticularDisplay display, DispatchAsync dispatch,
			SessionManager sessionManager,Win win,ErrorHandler errorHandler,GloryBroadcastPresenter gloryBroadcastPresenter,CorpBroadcastPresenter corpBroadcastPresenter,MessageSavePresenter messageSavePresenter,StaffViewPresenter staffViewPresenter) {
		super(eventBus, display);
	//	this.dispatch = dispatch;
	//	this.sessionManager = sessionManager;
		this.errorHandler=errorHandler;
	//	this.win=win;
		this.gloryBroadcastPresenter=gloryBroadcastPresenter;
		this.corpBroadcastPresenter=corpBroadcastPresenter;
		this.messageSavePresenter=messageSavePresenter;
		this.staffViewPresenter=staffViewPresenter;
	}

	@Override
	public void bind() {
		init();
		staffViewPresenter.initStaffView_Colleague(staffId,true);
		staffViewPresenter.bind();
		display.getResultPanel().clear();
		display.getResultPanel().add(staffViewPresenter.getDisplay().asWidget());
		
		display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().addClassName(CssStyleConstants.paddingtop0);
		display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().setClassName(CssStyleConstants.hidden);
		display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
		display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
		display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().addClassName(CssStyleConstants.floatleftwidth);
		display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
		display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
		display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
		display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
		display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
		display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
	}
	
	private void init()
	{
		display.getStaffView().addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				unbindAll();
				staffViewPresenter.initStaffView_Colleague(staffId,true);
				staffViewPresenter.bind();
				display.getResultPanel().clear();
				display.getResultPanel().add(staffViewPresenter.getDisplay().asWidget());
				
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().addClassName(CssStyleConstants.paddingtop0);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().setClassName(CssStyleConstants.hidden);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().addClassName(CssStyleConstants.floatleftwidth);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
			}
		});
		display.getStaffBroadcast().addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				unbindAll();
				corpBroadcastPresenter.initStaffBroadcast(staffId);
				corpBroadcastPresenter.bind();
				display.getResultPanel().clear();
				display.getResultPanel().add(corpBroadcastPresenter.getDisplay().asWidget());
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().setClassName(CssStyleConstants.marginleft0);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().addClassName(CssStyleConstants.marginright0);				
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().setClassName(CssStyleConstants.marginleft0);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getFirstChildElement().setClassName(CssStyleConstants.marginright0padding);
				
			}
		});
		display.getStaffGlory().addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				unbindAll();
				gloryBroadcastPresenter.initGloryBroadcast(staffId);
				gloryBroadcastPresenter.bind();
				display.getResultPanel().clear();
				display.getResultPanel().add(gloryBroadcastPresenter.getDisplay().asWidget());
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().setClassName(CssStyleConstants.marginleft0text);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().setClassName(CssStyleConstants.marginright0);
			}
		});
		display.getSendMessage().addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				unbindAll();
				messageSavePresenter.initBroadcastStaff(staffId,staffName);
				messageSavePresenter.bind();
				display.getResultPanel().clear();
				display.getResultPanel().add(messageSavePresenter.getDisplay().asWidget());
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().addClassName(CssStyleConstants.margin0);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().setClassName(CssStyleConstants.border0);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
				display.getResultPanel().getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().getNextSiblingElement().getNextSiblingElement().getNextSiblingElement().setClassName(CssStyleConstants.hidden);
						
			}
		});
	}

	@Override
	public void initColleagueParticular(OrganicationClient client) {
			this.staffId=client.getId();
			this.staffName=client.getName();
	}
	private void unbindAll()
	{
		gloryBroadcastPresenter.unbind();
		corpBroadcastPresenter.unbind();
		messageSavePresenter.unbind();
		staffViewPresenter.unbind();
	}
	



}
