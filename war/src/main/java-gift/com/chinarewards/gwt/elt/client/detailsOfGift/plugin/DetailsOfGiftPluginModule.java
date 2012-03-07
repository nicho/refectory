/**
 * 
 */
package com.chinarewards.gwt.elt.client.detailsOfGift.plugin;

import com.chinarewards.gwt.elt.client.detailsOfGift.editor.DetailsOfGiftEditor;
import com.chinarewards.gwt.elt.client.detailsOfGift.editor.DetailsOfGiftEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NIcho
 * @since 2012年2月1日 13:38:07
 */
public class DetailsOfGiftPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DetailsOfGiftPluginDescriptor.class).in(Singleton.class);

		bind(DetailsOfGiftEditorDescriptor.class).in(Singleton.class);
		bind(DetailsOfGiftEditor.class);
	}

}
