package com.chinarewards.gwt.elt.client.mvp;

public interface Presenter<D extends Display> {

	void bind();

	void unbind();

	D getDisplay();

}