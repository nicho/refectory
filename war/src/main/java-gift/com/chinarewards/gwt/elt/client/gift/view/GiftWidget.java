package com.chinarewards.gwt.elt.client.gift.view;

import java.util.Date;
import java.util.Map.Entry;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.gift.model.GiftType;
import com.chinarewards.gwt.elt.client.gift.model.GiftVo;
import com.chinarewards.gwt.elt.client.gift.presenter.GiftPresenter.GiftDisplay;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;

public class GiftWidget extends Composite implements GiftDisplay {

	// --------vo
	@UiField
	TextBox name;
	@UiField
	TextArea summary;
	@UiField
	TextArea dispatchcycle;
	@UiField
	TextArea explains;
	@UiField
	TextArea notes;
	@UiField
	ListBox type;
	@UiField
	TextBox brand;
	@UiField
	TextBox photo;
	@UiField
	TextBox integral;
	@UiField
	TextBox stock;
	@UiField
	RadioButton supplyinner;
	@UiField
	RadioButton supplyoutter;
	@UiField
	TextBox business;
	@UiField
	TextBox address;
	@UiField
	TextBox tell;
	@UiField
	TextBox servicetell;
	@UiField
	DateBox indate;

	// @UiField
	// TextBox status;// boolean
	// @UiField
	// TextBox deleted;// boolean
	// @UiField
	// DateBox indate;
	// @UiField
	// DateBox recorddate;
	// @UiField
	// TextBox recorduser;
	// @UiField
	// DateBox updatetime;
	// ---end vo

	// @UiField
	// Label nameError;
	@UiField
	Label integralError;
	@UiField
	Label stockError;
	@UiField
	Label indateError;

	@UiField
	Button back;

	@UiField
	Button save;

	@UiField
	Image giftImage;
	@UiField
	FormPanel photoForm;
	@UiField
	FileUpload photoUpload;
	@UiField
	Button photoUploadBtn;

	@UiField
	Panel breadCrumbs;

	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);

	interface GiftWidgetBinder extends UiBinder<Widget, GiftWidget> {

	}

	private static GiftWidgetBinder uiBinder = GWT
			.create(GiftWidgetBinder.class);

	@Inject
	public GiftWidget(DispatchAsync dispatch, ErrorHandler errorHandler,
			SessionManager sessionManager) {
		initWidget(uiBinder.createAndBindUi(this));
		indate.setFormat(new DateBox.DefaultFormat(dateFormat));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void initEditGift(GiftVo giftVo) {
		name.setText(giftVo.getName());
		summary.setText(giftVo.getSummary());
		dispatchcycle.setText(giftVo.getDispatchcycle());
		explains.setText(giftVo.getExplains());
		notes.setText(giftVo.getNotes());
		initTypeSelect(giftVo.getType());
		brand.setText(giftVo.getBrand());
		photo.setText(giftVo.getPhoto());
		if (giftVo.getPhoto().indexOf(".") > 0) {
			giftImage.setUrl("imageshow?imageName=" + giftVo.getPhoto());
			giftImage.setVisible(true);
		}
		integral.setText(giftVo.getIntegral() + "");
		stock.setText(giftVo.getStock() + "");

		business.setText(giftVo.getBusiness());
		address.setText(giftVo.getAddress());
		tell.setText(giftVo.getTell());
		servicetell.setText(giftVo.getServicetell());

		if (giftVo.getSource() != null) {
			if (StringUtil.trim(giftVo.getSource()).equals("inner")) {
				supplyinner.setValue(true);
				business.setEnabled(false);
				address.setEnabled(false);
				tell.setEnabled(false);
			}
			if (StringUtil.trim(giftVo.getSource()).equals("outter")) {
				supplyoutter.setValue(true);
			}
		}

		indate.setValue(giftVo.getIndate());

	}

	@Override
	public void initAddGift(GiftVo giftVo) {
		initTypeSelect("");
		supplyinner.setValue(false);
		supplyoutter.setValue(true);

		registerSource();
	}

	private void initTypeSelect(String selectedValue) {
		type.clear();
		int selectIndex = 0;
		int i = 0;
		for (Entry<String, String> entry : GiftType.map.entrySet()) {
			String keyValue = entry.getKey();
			// System.out.println(entry.getKey() + ": " + entry.getValue());
			type.addItem(entry.getValue(), entry.getKey());
			if (selectedValue != null && StringUtil.trim(selectedValue) != ""
					&& StringUtil.trim(keyValue) != "") {
				if (selectedValue.equals(keyValue)) {
					selectIndex = i;
				}
			}
			i++;
		}
		type.setSelectedIndex(selectIndex);
	}

	private void registerSource() {
		supplyinner.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue()) {
					business.setEnabled(false);
					address.setEnabled(false);
					tell.setEnabled(false);

					business.setValue("");
					address.setValue("");
					tell.setValue("");
				}
			}
		});

		supplyoutter.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue()) {
					business.setEnabled(true);
					address.setEnabled(true);
					tell.setEnabled(true);
				}
			}
		});

		// name.addValueChangeHandler(new ValueChangeHandler<String>() {
		// @Override
		// public void onValueChange(ValueChangeEvent<String> arg0) {
		// if (name.getValue() == null
		// || "".equals(name.getValue().trim())) {
		// nameError.setText("请填写礼品名称!<br>");
		// // win.alert("222");
		// }
		//
		// }
		// });
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
	}

	@Override
	public HasValue<String> getName() {
		return name;
	}

	@Override
	public void clear() {

	}

	@Override
	public HasValue<String> getExplains() {
		return explains;
	}

	@Override
	public ListBox getType() {
		return type;
	}

	@Override
	public HasValue<String> getSource() {
		// return source;
		return null;
	}

	@Override
	public HasValue<String> getBusiness() {
		// return null;
		return business;
	}

	@Override
	public HasValue<String> getAddress() {
		// return null;
		return address;
	}

	@Override
	public HasValue<String> getTell() {
		return tell;
	}

	@Override
	public HasValue<String> getStock() {
		return stock;
	}

	@Override
	public HasValue<String> getPhone() {
		return null;
		// return phone;
	}

	@Override
	public HasValue<Boolean> getStatus() {
		return null;
		// return status;
	}

	@Override
	public HasValue<Boolean> getDeleted() {
		return null;
		// return deleted;
	}

	@Override
	public DateBox getIndate() {
		return indate;
	}

	@Override
	public HasValueChangeHandlers<Date> getRecorddate() {
		return null;
		// return recorddate;
	}

	@Override
	public HasValue<String> getRecoduser() {
		return null;
		// return recoduser;
	}

	@Override
	public HasValueChangeHandlers<Date> getUpdatetime() {
		return null;
		// return updatetime;
	}

	@Override
	public FileUpload getPhotoUpload() {
		return photoUpload;
	}

	@Override
	public FormPanel getPhotoForm() {
		return photoForm;
	}

	@Override
	public HasClickHandlers getSaveClick() {
		return save;
	}

	@Override
	public HasClickHandlers getUploadClick() {
		return photoUploadBtn;
	}

	@Override
	public HasValue<String> getPhoto() {
		return photo;
	}

	@Override
	public Image getGiftImage() {
		return giftImage;
	}

	@Override
	public HasValue<String> getIntegral() {
		return integral;
	}

	@Override
	public HasClickHandlers getBackClick() {
		return back;
	}

	@Override
	public HasValue<String> getSummary() {
		return summary;
	}

	@Override
	public HasValue<String> getDispatchcycle() {
		return dispatchcycle;
	}

	@Override
	public HasValue<String> getNotes() {
		return notes;
	}

	@Override
	public HasValue<String> getBrand() {
		return brand;
	}

	@Override
	public HasValue<String> getServicetell() {
		return servicetell;
	}

	@Override
	public RadioButton getSupplyinner() {
		return supplyinner;
	}

	@Override
	public RadioButton getSupplyoutter() {
		return supplyoutter;
	}

	// @Override
	// public Label getNameError() {
	// return nameError;
	// }
}
