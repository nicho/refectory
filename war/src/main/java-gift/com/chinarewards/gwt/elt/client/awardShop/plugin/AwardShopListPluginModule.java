/**
 * 
 */
package com.chinarewards.gwt.elt.client.awardShop.plugin;

import com.chinarewards.gwt.elt.client.awardShop.editor.AwardShopListEditor;
import com.chinarewards.gwt.elt.client.awardShop.editor.AwardShopListEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NIcho
 * @since 2011年12月9日 
 */
public class AwardShopListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(AwardShopListPluginDescriptor.class).in(Singleton.class);

		bind(AwardShopListEditorDescriptor.class).in(Singleton.class);
		bind(AwardShopListEditor.class);
	}

}
