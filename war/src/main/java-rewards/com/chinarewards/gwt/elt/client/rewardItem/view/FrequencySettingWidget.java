package com.chinarewards.gwt.elt.client.rewardItem.view;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.gwt.elt.client.rewardItem.presenter.FrequencySettingPresenter.FrequencySettingDisplay;
import com.chinarewards.gwt.elt.client.rewards.model.DayFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.MonthFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.WeekFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.YearFrequencyClient;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * XXX too many logic in the display. A display should retain dummy.
 * 
 * @since 0.2.0
 */
public class FrequencySettingWidget extends Composite implements
		FrequencySettingDisplay {

	@UiField
	RadioButton radioDay;

	@UiField
	RadioButton radioWeek;

	@UiField
	RadioButton radioMonth;

	@UiField
	RadioButton radioYear;

//	@UiField
//	Panel panel;

	@UiField
	Panel dayPanel;

	@UiField
	TextBox dayInterval;

	@UiField
	Panel weekPanel;

	@UiField
	TextBox weekInterval;
	
	@UiField
	CheckBox sunday;

	@UiField
	CheckBox monday;

	@UiField
	CheckBox tuesday;

	@UiField
	CheckBox wednesday;

	@UiField
	CheckBox thursday;

	@UiField
	CheckBox friday;

	@UiField
	CheckBox saturday;
	
	// --- month fields --- //

	@UiField
	Panel monthPanel;

	@UiField
	TextBox monthInterval;
	
	@UiField
	TextBox monthFreqDayofMonth;

	// --- year fields --- //

	@UiField
	Panel yearPanel;

	@UiField
	TextBox yearInterval;

	@UiField
	ListBox yearFreqMonth;
	
	@UiField
	TextBox yearFreqDayOfMonth;

	@UiField
	Button ok;

	@UiField
	Button cancel;

	/**
	 * Choose:<br/>
	 * 1.Day radio: -- frequencyText<br/>
	 * 2.Week radio: -- frequencyText, dayText<br/>
	 * 3.Month radio: -- frequencyText, dayText<br/>
	 * 4.Year radio:-- frequencyText, monthText, dayText
	 */
//	TextBox dayFrequencyText;
//
//	TextBox weekFrequencyText;
//
//	TextBox monthFrequencyText;
//
//	TextBox monthDayText;
//
//	TextBox yearFrequencyText;
//
//	ListBox yearMonthList;
//
//	TextBox yearMonthDayText;
//
	private String textBoxWidth = "50px";
//
//	List<Integer> weekDays = new ArrayList<Integer>();
//	String dayInt = "1";
//	String weekInt = "1";
//	String monthInt = "1";
//	String monthDay = "1";
//	String yearInt = "1";
//	String yearMonth = "1";
//	String yearMonthDay = "1";

	protected final List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
	
	interface FrequencySettingWidgetBinder extends
			UiBinder<Widget, FrequencySettingWidget> {
	}

	private static FrequencySettingWidgetBinder uiBinder = GWT
			.create(FrequencySettingWidgetBinder.class);

	public FrequencySettingWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		radioDay.setValue(true);
		doInit();
//		showDayPanel(false, 0);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	public RadioButton getRadioDay() {
		return radioDay;
	}

	public RadioButton getRadioWeek() {
		return radioWeek;
	}

	public RadioButton getRadioMonth() {
		return radioMonth;
	}

	public RadioButton getRadioYear() {
		return radioYear;
	}


	/**
	 * 信息有误的时候，return null
	 */
	@Override
	public FrequencyClient getFrequency() {
		
		if (radioDay.getValue()) {
			DayFrequencyClient dayFrequency = new DayFrequencyClient();
			if (dayInterval.getText() != null
					&& !"".equals(dayInterval.getText().trim())) {
				try {
					int frequencyInt = Integer.parseInt(dayInterval
							.getText().trim());
					if (frequencyInt > 0) {
						dayFrequency.setInterval(frequencyInt);
						return dayFrequency;
					}
				} catch (Exception e) {
				}
			}
			
		} else if (radioWeek.getValue()) {
			WeekFrequencyClient weekFrequency = new WeekFrequencyClient();
			if (weekInterval.getText() != null
					&& !"".equals(weekInterval.getText().trim())) {
				try {
					int frequencyInt = Integer.parseInt(weekInterval
							.getText().trim());
					List<Integer> weekDays = getSelectedWeekdays();
					if (frequencyInt > 0 && weekDays != null
							&& weekDays.size() > 0) {
						weekFrequency.setInterval(frequencyInt);
						weekFrequency.setWeekDays(weekDays);
						return weekFrequency;
					}
				} catch (Exception e) {
					// XXX should not mute the error
				}
			}

		} else if (radioMonth.getValue()) {
			
			MonthFrequencyClient monthFrequency = new MonthFrequencyClient();
			if (monthInterval.getText() != null
					&& !"".equals(monthInterval.getText().trim())
					&& monthFreqDayofMonth.getText() != null
					&& !"".equals(monthFreqDayofMonth.getText().trim())) {
				try {
					int frequencyInt = Integer.parseInt(monthInterval
							.getText().trim());
					int monthDayInt = Integer.parseInt(monthFreqDayofMonth.getText()
							.trim());
					if (frequencyInt > 0 && monthDayInt > 0
							&& monthDayInt <= 31) {
						monthFrequency.setMonthDay(monthDayInt);
						monthFrequency.setInterval(frequencyInt);

						return monthFrequency;
					}
				} catch (Exception e) {
				}
			}
		} else if (radioYear.getValue()) {
			YearFrequencyClient yearFrequency = new YearFrequencyClient();
			String month = yearFreqMonth.getValue(yearFreqMonth
					.getSelectedIndex());
			if (yearInterval.getText() != null
					&& !"".equals(yearInterval.getText().trim())
					&& month != null && !"".equals(month)
					&& yearFreqDayOfMonth.getText() != null
					&& !"".equals(yearFreqDayOfMonth.getText().trim())) {
				try {
					int frequencyInt = Integer.parseInt(yearInterval
							.getText().trim());
					int monthInt = Integer.parseInt(month.trim());
					int monthDayInt = Integer.parseInt(yearFreqDayOfMonth.getValue()
							.trim());
					if (frequencyInt > 0 && monthDayInt > 0
//							&& monthDayInt <= 31
							) {

						yearFrequency.setInterval(frequencyInt);
						yearFrequency.setYearMonth(monthInt);
						yearFrequency.setYearMonthDay(monthDayInt);

						return yearFrequency;
					}
				} catch (Exception e) {
				}
			}
		}

		return null;
	}

	@Override
	public HasClickHandlers getOkClick() {
		return ok;
	}

	@Override
	public HasClickHandlers getCancelClick() {
		return cancel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	@Override
	protected void onLoad() {
		super.onLoad();
	}
	
	/**
	 * Initialize this widget, setup any initial data, setup internal listener.
	 */
	protected void doInit() {
		
		// initialize the month list for year frequency which will never change.
		for (int i=0; i < 12; i++) {
			// 1 based month
			this.yearFreqMonth.addItem("" + (i + 1), "" + (i + 1));
		}
		
		// setup click handlers
		
		// for Day Frequency
		handlerRegistrations.add(this.radioDay.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				FrequencySettingWidget.this.dayPanel.setVisible(true);
				FrequencySettingWidget.this.weekPanel.setVisible(false);
				FrequencySettingWidget.this.monthPanel.setVisible(false);
				FrequencySettingWidget.this.yearPanel.setVisible(false);
			}
		}));
		
		handlerRegistrations.add(this.radioWeek.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				FrequencySettingWidget.this.dayPanel.setVisible(false);
				FrequencySettingWidget.this.weekPanel.setVisible(true);
				FrequencySettingWidget.this.monthPanel.setVisible(false);
				FrequencySettingWidget.this.yearPanel.setVisible(false);
			}
		}));
		
		handlerRegistrations.add(this.radioMonth.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				FrequencySettingWidget.this.dayPanel.setVisible(false);
				FrequencySettingWidget.this.weekPanel.setVisible(false);
				FrequencySettingWidget.this.monthPanel.setVisible(true);
				FrequencySettingWidget.this.yearPanel.setVisible(false);
			}
		}));

		handlerRegistrations.add(this.radioYear.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				FrequencySettingWidget.this.dayPanel.setVisible(false);
				FrequencySettingWidget.this.weekPanel.setVisible(false);
				FrequencySettingWidget.this.monthPanel.setVisible(false);
				FrequencySettingWidget.this.yearPanel.setVisible(true);
			}
		}));

		
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Widget#onUnload()
	 */
	@Override
	protected void onUnload() {

		for (HandlerRegistration hReg : handlerRegistrations) {
			hReg.removeHandler();
		}
		
		super.onUnload();
	}
	
	
	public void setFrequency(FrequencyClient frequency, boolean show) {
		Panel targetPanel = null;
		RadioButton rb = null;
		
		if (frequency instanceof DayFrequencyClient) {
			
			DayFrequencyClient f = (DayFrequencyClient) frequency;
			this.dayInterval.setValue("" + f.getInterval());
			targetPanel = dayPanel;
			rb = radioDay;
			
		} else if (frequency instanceof WeekFrequencyClient) {
			
			WeekFrequencyClient f = (WeekFrequencyClient)frequency;
			this.weekInterval.setValue("" + f.getInterval());
			// weekdays as well.
			setWeekdaysToUi(f.getWeekDays());
			targetPanel = weekPanel;
			rb = radioWeek;
			
		} else if (frequency instanceof MonthFrequencyClient) {
			
			MonthFrequencyClient f = (MonthFrequencyClient)frequency;
			this.monthInterval.setValue("" + f.getInterval());
			this.monthFreqDayofMonth.setValue((f.getMonthDay() == null) ? null : "" + f.getMonthDay());
			targetPanel = monthPanel;
			rb = radioMonth;
			
		} else if (frequency instanceof YearFrequencyClient) {
			
			YearFrequencyClient f = (YearFrequencyClient)frequency;
			this.yearInterval.setValue("" + f.getInterval());
			//this.yearFreqMonth.setItemSelected(3, true);
			this.yearFreqMonth.setSelectedIndex(f.getYearMonth() - 1);
			this.yearFreqDayOfMonth.setValue("" + f.getYearMonthDay());
			targetPanel = yearPanel;
			rb = radioYear;
			
		}
		
		List<Panel> allPanels = new ArrayList<Panel>();
		allPanels.add(dayPanel);
		allPanels.add(weekPanel);
		allPanels.add(monthPanel);
		allPanels.add(yearPanel);
		
		if (show) {
			for (Panel p : allPanels) {
				if (targetPanel != p) 
					p.setVisible(false);
			}
			if (!targetPanel.isVisible()) {
				targetPanel.setVisible(true);
			}
			// Set radio btn to false(not choose state, IE can choose two or
			// more.)
			clearAllRadioBtn();
			if (rb != null) {
				rb.setValue(true);
			}
		}

	}
	
	private void clearAllRadioBtn() {
		radioDay.setValue(false);
		radioWeek.setValue(false);
		radioMonth.setValue(false);
		radioYear.setValue(false);
	}
	
	protected void setWeekdaysToUi(List<Integer> weekdays) {
		
		monday.setValue(false);
		tuesday.setValue(false);
		wednesday.setValue(false);
		thursday.setValue(false);
		friday.setValue(false);
		saturday.setValue(false);
		sunday.setValue(false);
		
		for (Integer wd : weekdays) {
			
			switch (wd) {
			case 1:
				monday.setValue(true);
				break;
			case 2:
				tuesday.setValue(true);
				break;
			case 3:
				wednesday.setValue(true);
				break;
			case 4:
				thursday.setValue(true);
				break;
			case 5:
				friday.setValue(true);
				break;
			case 6:
				saturday.setValue(true);
				break;
			case 7:
				sunday.setValue(true);
				break;
			}
			
		}
		
	}
	
	protected List<Integer> getSelectedWeekdays() {
		
		List<Integer> ret = new ArrayList<Integer>();
		if (Boolean.TRUE.equals(monday.getValue())) {
			ret.add(1);
		}
		if (Boolean.TRUE.equals(tuesday.getValue())) {
			ret.add(2);
		}
		if (Boolean.TRUE.equals(wednesday.getValue())) {
			ret.add(3);
		}
		if (Boolean.TRUE.equals(thursday.getValue())) {
			ret.add(4);
		}
		if (Boolean.TRUE.equals(friday.getValue())) {
			ret.add(5);
		}
		if (Boolean.TRUE.equals(saturday.getValue())) {
			ret.add(6);
		}
		if (Boolean.TRUE.equals(sunday.getValue())) {
			ret.add(7);
		}
		
		return ret;
	}

}
