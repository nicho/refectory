/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftConstants;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftViewPlugin;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftViewPluginDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.chinarewards.gwt.elt.client.gift.editor.GiftViewEditorDescriptor;
import com.google.inject.Inject;

/**
 * @author yanrui
 * @since 2012-1-16
 */
public class GiftViewPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final GiftViewPlugin giftViewPlugin;
	final GiftViewEditorDescriptor giftViewEditorDescriptor;

	@Inject
	public GiftViewPluginDescriptor(
			final GiftViewEditorDescriptor giftViewEditorDescriptor) {
		this.giftViewEditorDescriptor = giftViewEditorDescriptor;
		giftViewPlugin = new GiftViewPlugin(this);

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return giftViewEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return GiftViewPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return GiftConstants.PLUGIN_GIFT;
	}

	@Override
	public Plugin getInstance() {
		return giftViewPlugin;
	}

	@Override
	public Set<ExtensionPoint> getExtensionPoints() {
		return null;
	}

	@Override
	public Set<Extension> getExtensions() {
		return ext;
	}

}
