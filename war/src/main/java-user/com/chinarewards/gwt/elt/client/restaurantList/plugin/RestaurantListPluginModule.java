/**
 * 
 */
package com.chinarewards.gwt.elt.client.restaurantList.plugin;

import com.chinarewards.gwt.elt.client.restaurantList.editor.RestaurantListEditor;
import com.chinarewards.gwt.elt.client.restaurantList.editor.RestaurantListEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class RestaurantListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RestaurantListPluginDescriptor.class).in(Singleton.class);

		bind(RestaurantListEditorDescriptor.class).in(Singleton.class);
		bind(RestaurantListEditor.class);
	}

}
