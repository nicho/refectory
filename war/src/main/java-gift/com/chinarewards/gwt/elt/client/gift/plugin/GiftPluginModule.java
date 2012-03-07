/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.plugin;

import com.chinarewards.gwt.elt.client.gift.editor.GiftEditor;
import com.chinarewards.gwt.elt.client.gift.editor.GiftEditorDescriptor;
import com.chinarewards.gwt.elt.client.gift.editor.GiftViewEditor;
import com.chinarewards.gwt.elt.client.gift.editor.GiftViewEditorDescriptor;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftPluginDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author yanrui
 * @since
 */
public class GiftPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(GiftPluginDescriptor.class).in(Singleton.class);

		bind(GiftEditorDescriptor.class).in(Singleton.class);		
		bind(GiftEditor.class);
		
		bind(GiftViewEditorDescriptor.class).in(Singleton.class);
		bind(GiftViewEditor.class);
	}

}
