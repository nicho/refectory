package com.chinarewards.gwt.elt.client.gift.presenter;

import java.util.Date;

import com.chinarewards.gwt.elt.client.gift.model.GiftVo;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public interface GiftPresenter extends Presenter<GiftPresenter.GiftDisplay> {

	public static interface GiftDisplay extends Display {

		public HasValue<String> getName();
//		public Label getNameError();

		public HasValue<String> getSummary();

		public HasValue<String> getDispatchcycle();

		public HasValue<String> getExplains();

		public HasValue<String> getNotes();

		public ListBox getType();

		public HasValue<String> getBrand();

		public HasValue<String> getSource();

		public RadioButton getSupplyinner();
		public RadioButton getSupplyoutter();		

		public HasValue<String> getBusiness();

		public HasValue<String> getAddress();

		public HasValue<String> getTell();

		public HasValue<String> getServicetell();

		public HasValue<String> getPhoto();

		public Image getGiftImage();

		public HasValue<String> getStock();

		public HasValue<String> getIntegral();

		public HasValue<String> getPhone();

		public HasValue<Boolean> getStatus();

		public HasValue<Boolean> getDeleted();

		public DateBox getIndate();

		public HasValueChangeHandlers<Date> getRecorddate();

		public HasValue<String> getRecoduser();

		public HasValueChangeHandlers<Date> getUpdatetime();

		// -----------------------------------------
		public FormPanel getPhotoForm();

		public FileUpload getPhotoUpload();

		public HasClickHandlers getUploadClick();

		public HasClickHandlers getSaveClick();

		public void clear();

		public void initAddGift(GiftVo giftVo);

		public void initEditGift(GiftVo giftVo);

		public HasClickHandlers getBackClick();

		void setBreadCrumbs(Widget breadCrumbs);

	}

	public void initEditor(String giftId, String thisAction);
}
