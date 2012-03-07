package com.chinarewards.gwt.elt.model.ChoosePanel;

import java.io.Serializable;

public class InitChooseListParam implements Serializable {
	private static final long serialVersionUID = 1L;
	boolean hiddenSpecialBoxPanel;
	boolean hiddenChooseBtn;
	String cancelBtnText;
	public boolean isHiddenSpecialBoxPanel() {
		return hiddenSpecialBoxPanel;
	}
	public void setHiddenSpecialBoxPanel(boolean hiddenSpecialBoxPanel) {
		this.hiddenSpecialBoxPanel = hiddenSpecialBoxPanel;
	}
	public boolean isHiddenChooseBtn() {
		return hiddenChooseBtn;
	}
	public void setHiddenChooseBtn(boolean hiddenChooseBtn) {
		this.hiddenChooseBtn = hiddenChooseBtn;
	}
	public String getCancelBtnText() {
		return cancelBtnText;
	}
	public void setCancelBtnText(String cancelBtnText) {
		this.cancelBtnText = cancelBtnText;
	}

}
