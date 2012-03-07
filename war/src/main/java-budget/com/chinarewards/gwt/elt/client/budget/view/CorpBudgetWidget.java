package com.chinarewards.gwt.elt.client.budget.view;

import java.util.Map.Entry;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;
import com.chinarewards.gwt.elt.client.budget.presenter.CorpBudgetPresenter.CorpBudgetDisplay;
import com.chinarewards.gwt.elt.client.enterprise.model.MoneyType;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.util.StringUtil;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;

public class CorpBudgetWidget extends Composite implements CorpBudgetDisplay {

	// --------vo
	@UiField
	TextBox budgetTitle;
	@UiField
	ListBox moneyType;
	@UiField
	TextBox budgetAmount;
	@UiField
	TextBox budgetIntegral;
	@UiField
	Hidden integralPrice;
	
	@UiField
	DateBox beginDate;
	@UiField
	DateBox endDate;
	
	@UiField
	Hidden period;  
	@UiField
	DateBox periodBeginDate;
	

	@UiField
	Button save;
	@UiField
	Button periodBtn;

	@UiField
	Button back;
	@UiField
	Panel breadCrumbs;

	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);

	interface CorpBudgetWidgetBinder extends UiBinder<Widget, CorpBudgetWidget> {

	}

	private static CorpBudgetWidgetBinder uiBinder = GWT
			.create(CorpBudgetWidgetBinder.class);

	@Inject
	public CorpBudgetWidget(DispatchAsync dispatch, ErrorHandler errorHandler,
			SessionManager sessionManager) {
		initWidget(uiBinder.createAndBindUi(this));

		beginDate.setFormat(new DateBox.DefaultFormat(dateFormat));
		endDate.setFormat(new DateBox.DefaultFormat(dateFormat));

	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void initEditCorpBudget(CorpBudgetVo corpBudgetVo) {
		budgetTitle.setValue(corpBudgetVo.getBudgetTitle());	
		
//		System.out.println("======initEdit:"+corpBudgetVo.getBudgetTitle());
		
		if(corpBudgetVo.getMoneyType()==null){
			initMoneyTypeSelect("RMB");
		}else{
			initMoneyTypeSelect(corpBudgetVo.getMoneyType());
		}
		
		budgetAmount.setText(corpBudgetVo.getBudgetAmount() + "");
		budgetIntegral.setText(corpBudgetVo.getBudgetIntegral() + "");
//		beginDate.setValue(corpBudgetVo.getBeginDate());			
//		endDate.setValue(corpBudgetVo.getEndDate());
		
	}

	private void initMoneyTypeSelect(String selectedValue) {
		moneyType.clear();
		int selectIndex = 0;
		int i = 0;
		for (Entry<String, String> entry : MoneyType.map.entrySet()) {
			String keyValue = entry.getKey();
			// System.out.println(entry.getKey() + ": " + entry.getValue());
			moneyType.addItem(entry.getValue(), entry.getKey());
			if (selectedValue != null && StringUtil.trim(selectedValue) != ""
					&& StringUtil.trim(keyValue) != "") {
				if (selectedValue.equals(keyValue)) {
					selectIndex = i;
				}
			}
			i++;
		}
		moneyType.setSelectedIndex(selectIndex);
	}
	
	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
	}

	@Override
	public void clear() {

	}

	@Override
	public HasClickHandlers getSaveClick() {
		return save;
	}

	@Override
	public HasClickHandlers getBackClick() {
		return back;
	}

	@Override
	public ListBox getMoneyType() {
		return moneyType;
	}

	@Override
	public TextBox getBudgetAmount() {
		return budgetAmount;
	}

	@Override
	public TextBox getBudgetIntegral() {
		return budgetIntegral;
	}

	@Override
	public DateBox getBeginDate() {
		return beginDate;
	}

	@Override
	public DateBox getEndDate() {
		return endDate;
	}

	@Override
	public HasClickHandlers getPeriodBtnClick() {
		return periodBtn;
	}

	@Override
	public HasValue<String> getBudgetTitle(){
		return budgetTitle;
	}

	@Override
	public Hidden getIntegralPrice() {
		return integralPrice;
	}


	@Override
	public Hidden getPeriod() {
		return period;
	}

	@Override
	public DateBox getPeriodBeginDate() {
		return periodBeginDate;
	}
}
