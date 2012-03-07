package com.chinarewards.gwt.elt.client.orderHistory.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.orderHistory.editor.OrderHistoryViewEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class OrderHistoryViewPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final OrderHistoryViewPlugin orderHistoryViewPlugin;
	final OrderHistoryViewEditorDescriptor orderHistoryViewEditorDescriptor;

	@Inject
	public OrderHistoryViewPluginDescriptor(
			final OrderHistoryViewEditorDescriptor orderHistoryViewEditorDescriptor) {
		this.orderHistoryViewEditorDescriptor = orderHistoryViewEditorDescriptor;
		orderHistoryViewPlugin = new OrderHistoryViewPlugin(this);

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return orderHistoryViewEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return OrderHistoryViewPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return OrderHistoryConstants.PLUGIN_ORDERHISTORY;
	}

	@Override
	public Plugin getInstance() {
		return orderHistoryViewPlugin;
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
