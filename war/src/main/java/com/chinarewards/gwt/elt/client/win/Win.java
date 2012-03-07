package com.chinarewards.gwt.elt.client.win;


import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.ui.DialogCloseListener;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.ui.DialogBox;
import com.chinarewards.gwt.elt.client.ui.MyDialogBox;
import com.chinarewards.gwt.elt.client.win.alert.AlertDialog;
import com.chinarewards.gwt.elt.client.win.alert.AlertWidget;
import com.chinarewards.gwt.elt.client.win.confirm.ConfirmDialog;
import com.chinarewards.gwt.elt.client.win.confirm.ConfirmHandler;
import com.chinarewards.gwt.elt.client.win.confirm.WinEvent;
import com.chinarewards.gwt.elt.client.win.confirm.WinHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * The class provides some useful method to implement functions like
 * window.alert, confirm in js.
 * 
 * @author yanxin
 * 
 */
public class Win {

	final Provider<ConfirmDialog> dialogProvider;
	final Provider<AlertDialog> alertDialogProvider;
	final EventBus eventBus;
	AlertWidget alertWidget;

	MyDialogBox dialogBox;
	DialogBox alertDialog;

	@Inject
	public Win(Provider<ConfirmDialog> dialogProvider,
			Provider<AlertDialog> alertDialogProvider, EventBus eventBus,
			AlertWidget alertWidget) {
		this.dialogProvider = dialogProvider;
		this.alertDialogProvider = alertDialogProvider;
		this.eventBus = eventBus;
		this.alertWidget = alertWidget;
	}

	public void confirm(String title, String msg, final ConfirmHandler handler) {
		final ConfirmDialog dialog = dialogProvider.get();
		dialog.setTitle(title);
		dialog.setMsg(msg);
		final HandlerRegistration registration = eventBus.addHandler(
				WinEvent.getType(), new WinHandler() {
					@Override
					public void confirm() {
						Platform.getInstance().getSiteManager()
								.closeDialog(dialog);
						handler.confirm();
					}
				});
		Platform.getInstance().getSiteManager()
				.openDialog(dialog, new DialogCloseListener() {
					@Override
					public void onClose(String dialogId, String instanceId) {
						registration.removeHandler();
					}
				});
	}

	public void alert(String msg) {
		final AlertDialog dialog = alertDialogProvider.get();
		dialog.setMsg(msg);
		Platform.getInstance().getSiteManager().openDialog(dialog, null);
	}
	public void alertImage(String url) {
		final AlertDialog dialog = alertDialogProvider.get();
		dialog.setImage(url);
		Platform.getInstance().getSiteManager().openDialog(dialog, null);
		
	}

	public void beginWait(String msg) {
		if (msg == null) {
			msg = " 正在操作中，请稍候...";
		}
		dialogBox = new MyDialogBox();
		dialogBox.setGlassEnabled(true);
		dialogBox.setAnimationEnabled(true);
		dialogBox.setWidth("250px");
		dialogBox.setText(msg);
		dialogBox.center();
		dialogBox.show();
	}

	public void endWait() {
		dialogBox.hide();
	}

	public void alertInLoginWindow(String msg) {
		alertDialog = new DialogBox();
		ScrollPanel panel = new ScrollPanel();
		alertWidget.getOkBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				alertDialog.hide();
			}
		});
		alertWidget.setMsg(msg);
		panel.add(alertWidget);
		alertDialog.setWidget(panel);
		alertDialog.setGlassEnabled(true);
		alertDialog.setAnimationEnabled(true);
		alertDialog.setText("提示信息");
		alertDialog.center();
		alertDialog.show();
	}
}
