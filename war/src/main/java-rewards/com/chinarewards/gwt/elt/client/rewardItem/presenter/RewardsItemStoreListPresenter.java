package com.chinarewards.gwt.elt.client.rewardItem.presenter;

import java.util.Date;
import java.util.Map;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 奖项库搜索维护页面
 * 
 * @author lw
 * @since 0.2.0 2012-01-13
 */
public interface RewardsItemStoreListPresenter extends	Presenter<RewardsItemStoreListPresenter.RewardsItemStoreListDisplay> {

	public static interface RewardsItemStoreListDisplay extends Display {
		/**
		 * 展示列表的panel
		 * 
		 * @return
		 */
		public Panel getDataContainer();
		
		public HasValue<String> getSearchName();
		
		public Panel getDataPager() ;
		
		public Panel getDepartmentPanel();
		
		public boolean getChooseSubDepartment();
		
		public HasClickHandlers getSearchClick();
		
		public HasClickHandlers getAddBut();
		
		public HasValue<Date> getCreateTime();
		public HasValue<Date> getCreateTimeEnd();
          
		void setBreadCrumbs(Widget breadCrumbs);		
		
		void initialize();
		void setDataCount(String text);
		public void initDepart(Map<String, String> map);
		String getDepart();
	}
}
