package com.chinarewards.gwt.elt.client.budget.util;

import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;
import com.chinarewards.gwt.elt.client.budget.presenter.CorpBudgetPresenter.CorpBudgetDisplay;

/**
 * @author yanrui
 * */
public class CorpBudgetAdapterClient {
	/**
	 * 封装表单属性
	 * */
	public static CorpBudgetVo adapterDisplay(CorpBudgetDisplay display) {
		CorpBudgetVo corpBudgetVo = new CorpBudgetVo();
		
		corpBudgetVo.setBudgetTitle(display.getBudgetTitle().getValue());
		
		int selectedIndex = display.getMoneyType().getSelectedIndex();
		corpBudgetVo.setMoneyType(display.getMoneyType().getValue(selectedIndex));
		corpBudgetVo.setBudgetAmount(Double.valueOf(display.getBudgetAmount().getValue()));
		corpBudgetVo.setBudgetIntegral(Double.valueOf(display.getBudgetIntegral().getValue()));

		corpBudgetVo.setBeginDate(display.getBeginDate().getValue());
		corpBudgetVo.setEndDate(display.getEndDate().getValue());

//		System.out.println("===adapterDisplay:"+corpBudgetVo.getBudgetTitle());
		return corpBudgetVo;
	}
}
