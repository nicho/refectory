/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.plugin;

import com.chinarewards.gwt.elt.client.gift.editor.GiftListEditor;
import com.chinarewards.gwt.elt.client.gift.editor.GiftListEditorDescriptor;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftListPluginDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NIcho
 * @since 2011年12月9日 
 */
public class GiftListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(GiftListPluginDescriptor.class).in(Singleton.class);

		bind(GiftListEditorDescriptor.class).in(Singleton.class);
		bind(GiftListEditor.class);
	}

}
