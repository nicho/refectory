/**
 * 
 */
package com.chinarewards.gwt.elt.client.shopWindow.plugin;

import com.chinarewards.gwt.elt.client.shopWindow.editor.ShopWindowEditor;
import com.chinarewards.gwt.elt.client.shopWindow.editor.ShopWindowEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NIcho
 * @since 2011年12月9日 
 */
public class ShopWindowPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ShopWindowPluginDescriptor.class).in(Singleton.class);

		bind(ShopWindowEditorDescriptor.class).in(Singleton.class);
		bind(ShopWindowEditor.class);
	}

}
