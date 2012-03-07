/**
 * 
 */
package com.chinarewards.gwt.elt.client.department.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.department.editor.DepartmentEditorDescriptor;
import com.chinarewards.gwt.elt.client.department.model.DepartmentClient;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author yanrui
 * @since
 */
public class DepartmentPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final DepartmentPlugin departmentPlugin;
	final DepartmentEditorDescriptor departmentEditorDescriptor;

	@Inject
	public DepartmentPluginDescriptor(final DepartmentEditorDescriptor departmentEditorDescriptor) {
		this.departmentEditorDescriptor = departmentEditorDescriptor;
		departmentPlugin = new DepartmentPlugin(this);

		/**
		 * 部门
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
//						return MenuConstants.MENU_ORDER_DEPARTMENT_ADD;
						return 0;
					}

					@Override
					public String getMenuId() {
						return DepartmentConstants.MENU_DEPARTMENT_ADD;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "编辑组织机构";
					}

					@Override
					public void execute() {
						DepartmentClient departmentClient = new DepartmentClient();
						departmentClient.setThisAction(DepartmentConstants.ACTION_DEPARTMENT_EDIT_CORP);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(DepartmentConstants.EDITOR_DEPARTMENT_EDIT,
										DepartmentConstants.ACTION_DEPARTMENT_EDIT_CORP,
										departmentClient);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DepartmentPluginDescriptor.this;
			}
		});

		
		ext.add(new Extension() {
			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return departmentEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DepartmentPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return DepartmentConstants.PLUGIN_DEPARTMENT;
	}

	@Override
	public Plugin getInstance() {
		return departmentPlugin;
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
