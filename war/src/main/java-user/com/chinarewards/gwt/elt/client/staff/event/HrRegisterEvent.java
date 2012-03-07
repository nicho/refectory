package com.chinarewards.gwt.elt.client.staff.event;


import com.google.gwt.event.shared.GwtEvent;

public class HrRegisterEvent extends GwtEvent<HrRegisterHandler>{


	private static Type<HrRegisterHandler> TYPE = new Type<HrRegisterHandler>();

	final String staffId;

	public static Type<HrRegisterHandler> getType() {
		return TYPE;
	}

	public HrRegisterEvent(String staffId) {
		this.staffId = staffId;
	}

	@Override
	protected void dispatch(HrRegisterHandler handler) {
		handler.onHRStaffCreated(this);
	}

	@Override
	public Type<HrRegisterHandler> getAssociatedType() {
		return getType();
	}

	public String getStaffId() {
		return staffId;
	}}
