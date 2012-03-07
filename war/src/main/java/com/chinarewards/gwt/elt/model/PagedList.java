package com.chinarewards.gwt.elt.model;

import java.io.Serializable;
import java.util.List;

public interface PagedList<T> extends Serializable {

	int getTotal();

	List<T> getList();
}
