package com.chinarewards.gwt.elt.model.ChoosePanel;

import java.io.Serializable;

public class InitChoosePanelParam implements Serializable {

	private static final long serialVersionUID = 1L;
	String topName;
	String chooseBtnName;
	boolean iscleanStaffTextAreaPanel;
	InitChooseListParam initChooseListParam;
	public InitChooseListParam getInitChooseListParam() {
		return initChooseListParam;
	}
	public void setInitChooseListParam(InitChooseListParam initChooseListParam) {
		this.initChooseListParam = initChooseListParam;
	}
	public boolean getIscleanStaffTextAreaPanel() {
		return iscleanStaffTextAreaPanel;
	}
	public void setIscleanStaffTextAreaPanel(boolean iscleanStaffTextAreaPanel) {
		this.iscleanStaffTextAreaPanel = iscleanStaffTextAreaPanel;
	}
	public String getTopName() {
		return topName;
	}
	public void setTopName(String topName) {
		this.topName = topName;
	}
	public String getChooseBtnName() {
		return chooseBtnName;
	}
	public void setChooseBtnName(String chooseBtnName) {
		this.chooseBtnName = chooseBtnName;
	}
}
