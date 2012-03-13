package com.chinarewards.gwt.elt.client.core;

import com.chinarewards.gwt.elt.client.core.impl.CorePluginDescriptor;
import com.chinarewards.gwt.elt.client.core.impl.GinPluginManager;
import com.chinarewards.gwt.elt.client.core.impl.InMemoryMenuRoleStore;
import com.chinarewards.gwt.elt.client.core.impl.InMemoryPluginSet;
import com.chinarewards.gwt.elt.client.core.ui.EditorRegistry;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.elt.client.core.ui.SiteManager;
import com.chinarewards.gwt.elt.client.core.ui.impl.ButtonMenuProcessor;
import com.chinarewards.gwt.elt.client.core.ui.impl.SimpleEditorRegistry;
import com.chinarewards.gwt.elt.client.core.ui.impl.SimpleSiteManager;
import com.chinarewards.gwt.elt.client.dictionaryList.plugin.DictionaryListPluginDescriptor;
import com.chinarewards.gwt.elt.client.dishesList.plugin.DishesListPluginDescriptor;
import com.chinarewards.gwt.elt.client.dishesTypeList.plugin.DishesTypeListPluginDescriptor;
import com.chinarewards.gwt.elt.client.orderList.plugin.OrderListPluginDescriptor;
import com.chinarewards.gwt.elt.client.restaurantList.plugin.RestaurantListPluginDescriptor;
import com.chinarewards.gwt.elt.client.userList.plugin.UserListPluginDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

public class PlatformModule extends AbstractGinModule {

	InMemoryPluginSet pluginSet = null;

	@Override
	protected void configure() {
		bind(Platform.class).in(Singleton.class);
		bind(PluginManager.class).to(GinPluginManager.class)
				.in(Singleton.class);

		bind(MenuProcessor.class).to(ButtonMenuProcessor.class).in(
				Singleton.class);
		bind(SiteManager.class).to(SimpleSiteManager.class).in(Singleton.class);
		bind(EditorRegistry.class).to(SimpleEditorRegistry.class).in(
				Singleton.class);

		bind(MenuRoleStore.class).to(InMemoryMenuRoleStore.class).in(
				Singleton.class);

		// ---- PLUGINS DEFINE BELOW (1) ----
		bind(CorePluginDescriptor.class).in(Singleton.class);

	}

	@Provides
	@Named("admin")
	PluginSet providePluginSet(
	// ---- PLUGINS DEFINE BELOW (2) ----
			CorePluginDescriptor core, // core
			DictionaryListPluginDescriptor dictionaryList,
			RestaurantListPluginDescriptor restaurantList,
			UserListPluginDescriptor userList,
			DishesListPluginDescriptor dishesList,
			DishesTypeListPluginDescriptor dishesTypeList,
			OrderListPluginDescriptor orderList
	) {

		if (pluginSet == null) {
			pluginSet = new InMemoryPluginSet();
			pluginSet.registerPlugin(core);
			pluginSet.registerPlugin(dictionaryList);
			pluginSet.registerPlugin(restaurantList);
			pluginSet.registerPlugin(userList);
			pluginSet.registerPlugin(dishesList);
			pluginSet.registerPlugin(dishesTypeList);
			pluginSet.registerPlugin(orderList);

		}

		return pluginSet;
	}

}
