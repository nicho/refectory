package com.chinarewards.gwt.elt.client.widget;

import java.util.Comparator;

public interface Sorting<T> {
	public void sortingCurrentPage(Comparator<T> comparator);

	public void sortingAll(String sorting, String direction);
}
