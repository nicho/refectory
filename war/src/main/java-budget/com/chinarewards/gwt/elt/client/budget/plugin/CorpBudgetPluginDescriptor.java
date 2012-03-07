package com.chinarewards.gwt.elt.client.budget.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.budget.editor.CorpBudgetEditorDescriptor;
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
 * @author yanrui
 * @since
 */
public class CorpBudgetPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final CorpBudgetPlugin CorpBudgetPlugin;
	final CorpBudgetEditorDescriptor corpBudgetEditorDescriptor;

	@Inject
	public CorpBudgetPluginDescriptor(
			final CorpBudgetEditorDescriptor corpBudgetEditorDescriptor) {
		this.corpBudgetEditorDescriptor = corpBudgetEditorDescriptor;
		CorpBudgetPlugin = new CorpBudgetPlugin(this);

		/**
		 * 整体预算
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
						return MenuConstants.MENU_ORDER_CORPBUDGET_EDIT;
					}

					@Override
					public String getMenuId() {
						return CorpBudgetConstants.MENU_CORPBUDGET_EDIT;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "整体预算";
					}

					@Override
					public void execute() {
						
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										CorpBudgetConstants.EDITOR_CORPBUDGET_EDIT,
										CorpBudgetConstants.ACTION_CORPBUDGET_EDIT,
										null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return CorpBudgetPluginDescriptor.this;
			}
		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return corpBudgetEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return CorpBudgetPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return CorpBudgetConstants.PLUGIN_CORPBUDGET;
	}

	@Override
	public Plugin getInstance() {
		return CorpBudgetPlugin;
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
