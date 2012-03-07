package com.chinarewards.gwt.elt.client.rewardItem.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinarewards.gwt.elt.client.mvp.BaseDialogPresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.event.FrequencySettingEvent;
import com.chinarewards.gwt.elt.client.rewards.model.DayFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.MonthFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.WeekFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.YearFrequencyClient;
import com.chinarewards.gwt.elt.util.DateTool;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;

/**
 * 
 * 
 * @since 0.2.0
 */
public class FrequencySettingPresentImpl extends
		BaseDialogPresenter<FrequencySettingPresenter.FrequencySettingDisplay>
		implements FrequencySettingPresenter {

	

	@Inject
	public FrequencySettingPresentImpl(EventBus eventBus,
			FrequencySettingDisplay display) {
		super(eventBus, display);
		
	}

	public void bind() {
		
		// OK button to confirm the settings.
		registerHandler(display.getOkClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						FrequencyClient frequency = display.getFrequency();
						if (frequency == null) {
							Window.alert("频率设定有误");
							return;
						}
						frequency=filtrateFrequencyClient(frequency);
						// fire event!
						eventBus.fireEvent(new FrequencySettingEvent(frequency));
						closeDialog();
					}
				}));
		registerHandler(display.getCancelClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						closeDialog();
					}
				}));
		
		
		
	}
	//当用户选择的是年平率时处理月份的最大值
	private FrequencyClient filtrateFrequencyClient(FrequencyClient frequency){
		 
		if(frequency instanceof YearFrequencyClient){
			YearFrequencyClient f =(YearFrequencyClient)frequency;
			int month=f.getYearMonth();
			int day=f.getYearMonthDay();
			
			if(day<1){
				f.setYearMonthDay(1);
			}else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
				if(day>31){
					f.setYearMonthDay(31);
				}
			}else if(month==2){
				if(day>29){
					f.setYearMonthDay(28);
				}
			}else{
				if(day>30){
					f.setYearMonthDay(30);
				}
			}
			display.setFrequency(f, true);
			return f;
		}
		return frequency;
	}

	// 初始化频率
	public void initFrequency(FrequencyClient frequency, boolean show) {
		
		display.setFrequency(frequency, show);
		
	}
	
	public void initNewFrequency() {

		// XXX use DateTimeProvider
		Date now = new Date();
		
		// daily panel
		{
			DayFrequencyClient f = new DayFrequencyClient(1);
			display.setFrequency(f, true);
		}
		
		// weekly panel
		{
			int weekDay = DateTool.getWeekDayOfDate(now);
			List<Integer> wkDays = new ArrayList<Integer>();
			wkDays.add(weekDay);
			
			WeekFrequencyClient f = new WeekFrequencyClient(1, wkDays);
			display.setFrequency(f, false);
		}
		
		// monthly frequency
		{
			MonthFrequencyClient f = new MonthFrequencyClient(1, 1);
			display.setFrequency(f, false);
		}
		
		// yearly frequency
		{
			YearFrequencyClient f = new YearFrequencyClient(1,
					DateTool.getMonthOfDate(now) + 1, 1);
			display.setFrequency(f, false);
		}
	}
	
}
