/**
 * 
 */
package com.chinarewards.gwt.elt.client.restaurantList.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.dictionaryList.editor.DictionaryListEditor;
import com.chinarewards.gwt.elt.client.restaurantList.plugin.RestaurantListConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class RestaurantListEditorDescriptor implements EditorDescriptor {

	final Provider<DictionaryListEditor> editProvider;

	@Inject
	RestaurantListEditorDescriptor(Provider<DictionaryListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return RestaurantListConstants.EDITOR_RESTAURANTLIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		DictionaryListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("员工列表");
		e.setModel(model);
		return e;
	}

}
