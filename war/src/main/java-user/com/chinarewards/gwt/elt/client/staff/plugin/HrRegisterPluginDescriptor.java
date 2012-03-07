/**
 * 
 */
package com.chinarewards.gwt.elt.client.staff.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.chinarewards.gwt.elt.client.staff.editor.HrRegisterEditorDescriptor;
import com.google.inject.Inject;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class HrRegisterPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final HrRegisterPlugin userPlugin;
	final HrRegisterEditorDescriptor hrRegisterEditorDescriptor;

	@Inject
	public HrRegisterPluginDescriptor(
			final HrRegisterEditorDescriptor hrRegisterEditorDescriptor) {
		this.hrRegisterEditorDescriptor = hrRegisterEditorDescriptor;
		userPlugin = new HrRegisterPlugin(this);

//		/**
//		 * Search user menu
//		 */
//		ext.add(new Extension() {
//
//			@Override
//			public String getExtensionPointId() {
//				return PluginConstants.MENU;
//			}
//
//			@Override
//			public Object getInstance() {
//				return new MenuItem() {
//
//					@Override
//					public int getOrder() {
//						return MenuConstants.MENU_ORDER_Hr_SEARCH;
//					}
//
//					@Override
//					public String getMenuId() {
//						return HrRegisterConstants.MENU_HRREGISTER_SEARCH;
//					}
//
//					@Override
//					public String getParentMenuId() {
//						return null;
//					}
//
//					@Override
//					public String getTitle() {
//						return "注册";
//					}
//
//					@Override
//					public void execute() {
//						Platform.getInstance()
//								.getEditorRegistry()
//								.openEditor(
//										HrRegisterConstants.EDITOR_HRREGISTER_SEARCH,
//										"EDITOR_HR_SEARCH_DO_ID", null);
//					}
//
//					@Override
//					public Image getIcon() {
//						return null;
//					}
//
//				};
//			}
//
//			@Override
//			public PluginDescriptor getPluginDescriptor() {
//				return HrRegisterPluginDescriptor.this;
//			}
//
//		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return hrRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return HrRegisterPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return HrRegisterConstants.PLUGIN_HRREGISTER;
	}

	@Override
	public Plugin getInstance() {
		return userPlugin;
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
