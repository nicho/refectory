package com.chinarewards.gwt.elt.client.core.view;

import java.util.Date;

import com.chinarewards.gwt.elt.client.core.presenter.StaffPresenter.StaffDisplay;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class StaffWidget extends Composite implements StaffDisplay {

	@UiField
	Panel dock;
	@UiField
	Panel smaillShopWindow;
	@UiField
	Panel rewardPanel;
	@UiField
	Panel rewardItemPanel;
	
	@UiField
	Anchor logBtn;

	@UiField
	InlineLabel message;


	@UiField
	Anchor collectionBtn;
	@UiField
	Anchor more;
	
	
	
	@UiField
	Anchor managementCenter;
	@UiField
	Anchor giftExchange;
	@UiField
	Anchor staffCorner;
	@UiField
	Anchor awardShop;

	@UiField
	Anchor password;
	@UiField
	Anchor staffInfo;
	@UiField
	Anchor viewPoints;
	@UiField
	Anchor winninghistory;
	@UiField
	Anchor participationAwards;
	@UiField
	Anchor otherAwards;
	@UiField
	Anchor exchangeHistory;
	@UiField
	Anchor myMessage;
	@UiField
	Anchor staffHeavenIndex;
	@UiField
	Anchor staffAnchor;
	@UiField
	Anchor corpBroadcastAnchor;
	@UiField
	Anchor gloryAnchor;
	@UiField
	Anchor settingAnchor;
	
	@UiField
	Image photo;
	@UiField
	InlineLabel staffName;
	@UiField
	InlineLabel station;
	@UiField
	InlineLabel deptName;
	@UiField
	InlineLabel integral;
	@UiField
	InlineLabel integral2;
	
	@UiField
	Anchor myWinReward;
	@UiField
	Anchor allReward;
	@UiField
	Anchor effortRewardItem;
	@UiField
	Anchor allRewardItem;
	
	// Set the format of datepicker.
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format_chinese);

	interface StaffWidgetBinder extends UiBinder<Widget, StaffWidget> {
	}

	private static StaffWidgetBinder uiBinder = GWT
			.create(StaffWidgetBinder.class);

	public StaffWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}
	 String styleOn="";
	 String styleNo="";
	 
	 String anchorOn="";
	 String anchorNo="";
	 
	 String styleRewardOn="";
	 String styleRewardNo="";
	 
		private void init() {
			styleRewardOn=myWinReward.getElement().getParentElement().getClassName();

			
			effortRewardItem.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					effortRewardItem.getElement().getParentElement().setClassName(styleRewardOn);						
					allRewardItem.getElement().getParentElement().setClassName(styleRewardNo);						
				}
			});
			allRewardItem.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					effortRewardItem.getElement().getParentElement().setClassName(styleRewardNo);						
					allRewardItem.getElement().getParentElement().setClassName(styleRewardOn);						
				}
			});
			
			
			myWinReward.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					myWinReward.getElement().getParentElement().setClassName(styleRewardOn);						
					allReward.getElement().getParentElement().setClassName(styleRewardNo);						
				}
			});
			allReward.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					myWinReward.getElement().getParentElement().setClassName(styleRewardNo);						
					allReward.getElement().getParentElement().setClassName(styleRewardOn);						
				}
			});
			
			
			anchorOn=this.staffHeavenIndex.getStyleName();
			anchorNo=this.staffAnchor.getStyleName();
			staffHeavenIndex.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					staffHeavenIndex.setStyleName(anchorOn);
					staffAnchor.setStyleName(anchorNo);
					corpBroadcastAnchor.setStyleName(anchorNo);
					gloryAnchor.setStyleName(anchorNo);
					settingAnchor.setStyleName(anchorNo);
					refLeftMenuStyle();
				}
			});
			staffAnchor.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					staffHeavenIndex.setStyleName(anchorNo);
					staffAnchor.setStyleName(anchorOn);
					corpBroadcastAnchor.setStyleName(anchorNo);
					gloryAnchor.setStyleName(anchorNo);
					settingAnchor.setStyleName(anchorNo);
					refLeftMenuStyle();
				}
			});
			corpBroadcastAnchor.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					staffHeavenIndex.setStyleName(anchorNo);
					staffAnchor.setStyleName(anchorNo);
					corpBroadcastAnchor.setStyleName(anchorOn);
					gloryAnchor.setStyleName(anchorNo);
					settingAnchor.setStyleName(anchorNo);
					refLeftMenuStyle();
				}
			});
			gloryAnchor.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					staffHeavenIndex.setStyleName(anchorNo);
					staffAnchor.setStyleName(anchorNo);
					corpBroadcastAnchor.setStyleName(anchorNo);
					gloryAnchor.setStyleName(anchorOn);
					settingAnchor.setStyleName(anchorNo);
					refLeftMenuStyle();
				}
			});
			settingAnchor.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					staffHeavenIndex.setStyleName(anchorNo);
					staffAnchor.setStyleName(anchorNo);
					corpBroadcastAnchor.setStyleName(anchorNo);
					gloryAnchor.setStyleName(anchorNo);
					settingAnchor.setStyleName(anchorOn);
					refLeftMenuStyle();
				}
			});
			
			
			  styleOn=this.viewPoints.getElement().getParentElement().getClassName();
			  
				//默认不选中左侧菜单
			  viewPoints.getElement().getParentElement().setClassName(styleRewardNo);
			  
			  viewPoints.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					viewPoints.getElement().getParentElement().setClassName(styleOn);	
					winninghistory.getElement().getParentElement().setClassName(styleNo);	
					participationAwards.getElement().getParentElement().setClassName(styleNo);	
					otherAwards.getElement().getParentElement().setClassName(styleNo);	
					exchangeHistory.getElement().getParentElement().setClassName(styleNo);	
					myMessage.getElement().getParentElement().setClassName(styleNo);	
					awardShop.getElement().getParentElement().setClassName(styleNo);	

				}
			});
			  winninghistory.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					viewPoints.getElement().getParentElement().setClassName(styleNo);	
					winninghistory.getElement().getParentElement().setClassName(styleOn);
					participationAwards.getElement().getParentElement().setClassName(styleNo);
					otherAwards.getElement().getParentElement().setClassName(styleNo);
					exchangeHistory.getElement().getParentElement().setClassName(styleNo);
					myMessage.getElement().getParentElement().setClassName(styleNo);
					awardShop.getElement().getParentElement().setClassName(styleNo);
				}
			});
			  participationAwards.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					viewPoints.getElement().getParentElement().setClassName(styleNo);	
					winninghistory.getElement().getParentElement().setClassName(styleNo);
					participationAwards.getElement().getParentElement().setClassName(styleOn);
					otherAwards.getElement().getParentElement().setClassName(styleNo);
					exchangeHistory.getElement().getParentElement().setClassName(styleNo);
					myMessage.getElement().getParentElement().setClassName(styleNo);
					awardShop.getElement().getParentElement().setClassName(styleNo);
				}
			});
			  otherAwards.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					viewPoints.getElement().getParentElement().setClassName(styleNo);	
					winninghistory.getElement().getParentElement().setClassName(styleNo);
					participationAwards.getElement().getParentElement().setClassName(styleNo);
					otherAwards.getElement().getParentElement().setClassName(styleOn);
					exchangeHistory.getElement().getParentElement().setClassName(styleNo);
					myMessage.getElement().getParentElement().setClassName(styleNo);
					awardShop.getElement().getParentElement().setClassName(styleNo);
				}
			});
			  exchangeHistory.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					viewPoints.getElement().getParentElement().setClassName(styleNo);	
					winninghistory.getElement().getParentElement().setClassName(styleNo);
					participationAwards.getElement().getParentElement().setClassName(styleNo);
					otherAwards.getElement().getParentElement().setClassName(styleNo);
					exchangeHistory.getElement().getParentElement().setClassName(styleOn);
					myMessage.getElement().getParentElement().setClassName(styleNo);
					awardShop.getElement().getParentElement().setClassName(styleNo);
				}
			});
			  myMessage.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					viewPoints.getElement().getParentElement().setClassName(styleNo);	
					winninghistory.getElement().getParentElement().setClassName(styleNo);
					participationAwards.getElement().getParentElement().setClassName(styleNo);
					otherAwards.getElement().getParentElement().setClassName(styleNo);
					exchangeHistory.getElement().getParentElement().setClassName(styleNo);
					myMessage.getElement().getParentElement().setClassName(styleOn);
					awardShop.getElement().getParentElement().setClassName(styleNo);
				}
			});
			  awardShop.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					viewPoints.getElement().getParentElement().setClassName(styleNo);	
					winninghistory.getElement().getParentElement().setClassName(styleNo);
					participationAwards.getElement().getParentElement().setClassName(styleNo);
					otherAwards.getElement().getParentElement().setClassName(styleNo);
					exchangeHistory.getElement().getParentElement().setClassName(styleNo);
					myMessage.getElement().getParentElement().setClassName(styleNo);
					awardShop.getElement().getParentElement().setClassName(styleOn);
				}
			});
		}
		
	private void refLeftMenuStyle()
	{
		viewPoints.getElement().getParentElement().setClassName(styleNo);	
		winninghistory.getElement().getParentElement().setClassName(styleNo);	
		participationAwards.getElement().getParentElement().setClassName(styleNo);	
		otherAwards.getElement().getParentElement().setClassName(styleNo);	
		exchangeHistory.getElement().getParentElement().setClassName(styleNo);	
		myMessage.getElement().getParentElement().setClassName(styleNo);	
		awardShop.getElement().getParentElement().setClassName(styleNo);	
	}
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getlogBtn() {
		return logBtn;
	}


	@Override
	public Panel  getDock() {
		return dock;
	}


	@Override
	public void setMessage(String userName) {
		String time = dateFormat.format(new Date());
		String msg = "欢迎你，" + userName + "！今天是:" + time;
		message.setText(msg);
	}




	@Override
	public HasClickHandlers getBtnCollection() {
		return collectionBtn;
	}

	@Override
	public HasClickHandlers getManagementCenter() {

		return managementCenter;
	}

	@Override
	public HasClickHandlers getGiftExchange() {
		return giftExchange;
	}

	@Override
	public HasClickHandlers getStaffCorner() {
		return staffCorner;
	}
	@Override
	public void disableManagementCenter() {
		managementCenter.setVisible(false);
		
	}

	@Override
	public void disableGiftExchange() {
		giftExchange.setVisible(false);
		
	}

	@Override
	public void disableStaffCorner() {
		staffCorner.setVisible(false);
		
	}

	@Override
	public HasClickHandlers getAwardShop() {
		return awardShop;
	}
	@Override
	public HasClickHandlers getPassword() {
		return password;
	}
	@Override
	public HasClickHandlers getStaffInfo() {
		return staffInfo;
	}

	@Override
	public HasClickHandlers getViewPoints() {
		return this.viewPoints;
	}

	@Override
	public HasClickHandlers getWinninghistory() {
		return this.winninghistory;
	}

	@Override
	public HasClickHandlers getParticipationAwards() {
		return this.participationAwards;
	}

	@Override
	public HasClickHandlers getOtherAwards() {
		return this.otherAwards;
	}

	@Override
	public HasClickHandlers getExchangeHistory() {
		return this.exchangeHistory;
	}

	@Override
	public HasClickHandlers getMyMessage() {
		return this.myMessage;

	}
	@Override
	public HasClickHandlers getStaffHeavenIndex() {
		return staffHeavenIndex;
	}
	@Override
	public HasClickHandlers getStaffAnchor() {
		return staffAnchor;
	}
	@Override
	public HasClickHandlers getCorpBroadcastAnchor() {
		return corpBroadcastAnchor;
	}
	@Override
	public HasClickHandlers getGloryAnchor() {
		return gloryAnchor;
	}
	@Override
	public HasClickHandlers getSettingAnchor() {
		return settingAnchor;
	}
	@Override
	public void setPhoto(String photo) {
		this.photo.setUrl("imageshow?imageName="+photo);
	}
	@Override
	public void setStaffName(String staffName) {
		this.staffName.setText(staffName);
	}
	@Override
	public void setStation(String station) {
		this.station.setText(station);
	}
	@Override
	public void setDeptName(String deptName) {
		this.deptName.setText(deptName);
	}
	@Override
	public void setIntegral(int integral) {
		this.integral.setText(integral+"");
		this.integral2.setText(integral+"");
	}
	@Override
	public Panel getSmaillShopWindow() {
		return smaillShopWindow;
	}
	@Override
	public HasClickHandlers getMore() {
		return more;
	}
	@Override
	public Panel getRewardPanel() {
		return rewardPanel;
	}
	@Override
	public Panel getRewardItemPanel() {
		return rewardItemPanel;
	}

	@Override
	public HasClickHandlers getMyWinReward() {
		return myWinReward;
	}

	@Override
	public HasClickHandlers getAllReward() {
		return allReward;
	}

	@Override
	public HasClickHandlers getEffortRewardItem() {
		return effortRewardItem;
	}

	@Override
	public HasClickHandlers getAllRewardItem() {
		return allRewardItem;
	}
}
