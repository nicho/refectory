package com.chinarewards.gwt.elt.client.budget.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.budget.editor.CreateBudgetEditorDescriptor;
import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * 
 * @author harry
 * @since 2010-12-16
 */
public class BudgetPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final BudgetPlugin budgetPlugin;
	final CreateBudgetEditorDescriptor budgetEditorDescriptor;


	@Inject
	public BudgetPluginDescriptor(
			final CreateBudgetEditorDescriptor budgetEditorDescriptor	) {
		this.budgetEditorDescriptor = budgetEditorDescriptor;
		budgetPlugin = new BudgetPlugin(this);

		// create budget menu
		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.MENU;
			}

			@Override
			public Object getInstance() {
				return new MenuItem() {

					@Override
					public String getTitle() {
						return "部门预算";
					}

					@Override
					public String getParentMenuId() {
						return "root";
					}

					@Override
					public int getOrder() {
						return MenuConstants.MENU_ORDER_CREATE_BUDGET;
					}

					@Override
					public String getMenuId() {
						return CreateBudgetConstants.MENU_CREATE_BUDGET;
					}

					@Override
					public Image getIcon() {
						return null;
					}

					@Override
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										CreateBudgetConstants.EDITOR_CREATE_BUDGET,
										"EDITOR_CREATE_BUDGET_ID", null);
					}
				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return BudgetPluginDescriptor.this;
			}

		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return budgetEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return BudgetPluginDescriptor.this;
			}

		});

		

		
	}

	@Override
	public String getPluginId() {
		return CreateBudgetConstants.PLUGIN_BUDGET;
	}

	@Override
	public Plugin getInstance() {
		return budgetPlugin;
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
