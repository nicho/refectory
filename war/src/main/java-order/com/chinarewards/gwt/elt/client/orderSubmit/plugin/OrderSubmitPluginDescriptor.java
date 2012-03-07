/**
 * 
 */
package com.chinarewards.gwt.elt.client.orderSubmit.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.orderSubmit.editor.OrderSubmitEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月1日 13:37:23
 */
public class OrderSubmitPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final OrderSubmitPlugin OrderSubmitPlugin;
	final OrderSubmitEditorDescriptor orderSubmitEditorDescriptor;

	@Inject
	public OrderSubmitPluginDescriptor(
			final OrderSubmitEditorDescriptor orderSubmitEditorDescriptor) {
		this.orderSubmitEditorDescriptor = orderSubmitEditorDescriptor;
		OrderSubmitPlugin = new OrderSubmitPlugin(this);

		/**
		 * Search user menu
		 */
		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.MENU;
			}

			@Override
			public Object getInstance() {
				return new MenuItem() {

					@Override
					public int getOrder() {
						return MenuConstants.MENU_ORDER_ORDERSUBMIT_SEARCH;
					}

					@Override
					public String getMenuId() {
						return OrderSubmitConstants.MENU_ORDERSUBMIT_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "订单提交";
					}

					@Override
					public void execute() {

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										OrderSubmitConstants.EDITOR_ORDERSUBMIT_SEARCH,
										"EDITOR_ORDERSUBMIT_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return OrderSubmitPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return orderSubmitEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return OrderSubmitPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return OrderSubmitConstants.PLUGIN_ORDERSUBMIT;
	}

	@Override
	public Plugin getInstance() {
		return OrderSubmitPlugin;
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
