package com.chinarewards.gwt.elt.client.department.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.department.editor.DepartmentLeaderEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class DepartmentLeaderPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final DepartmentLeaderPlugin departmentLeaderPlugin;
	final DepartmentLeaderEditorDescriptor departmentListEditorDescriptor;

	@Inject
	public DepartmentLeaderPluginDescriptor(
			final DepartmentLeaderEditorDescriptor departmentListEditorDescriptor) {
		this.departmentListEditorDescriptor = departmentListEditorDescriptor;
		this.departmentLeaderPlugin = new DepartmentLeaderPlugin(this);

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
						return MenuConstants.MENU_ORDER_DEPARTMENTLIST_SEARCH;
					}

					@Override
					public String getMenuId() {
						return DepartmentLeaderConstants.MENU_DEPARTMENTLEADER_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "部门组织结构";
					}

					@Override
					public void execute() {

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										DepartmentLeaderConstants.EDITOR_DEPARTMENTLEADER_SEARCH,
										"EDITOR_DEPARTMENLEADER_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DepartmentLeaderPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return departmentListEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DepartmentLeaderPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return DepartmentLeaderConstants.PLUGIN_DEPARTMENTLEADER;
	}

	@Override
	public Plugin getInstance() {
		return departmentLeaderPlugin;
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
