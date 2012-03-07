package com.chinarewards.gwt.elt.client.enterprise.plugin;

import com.chinarewards.gwt.elt.client.enterprise.editor.EnterpriseEditor;
import com.chinarewards.gwt.elt.client.enterprise.editor.EnterpriseEditorDescriptor;
import com.chinarewards.gwt.elt.client.enterprise.editor.IntegralPriceEditor;
import com.chinarewards.gwt.elt.client.enterprise.editor.IntegralPriceEditorDescriptor;
import com.chinarewards.gwt.elt.client.enterprise.editor.MailSetEditor;
import com.chinarewards.gwt.elt.client.enterprise.editor.MailSetEditorDescriptor;
import com.chinarewards.gwt.elt.client.enterprise.editor.PeriodEditor;
import com.chinarewards.gwt.elt.client.enterprise.editor.PeriodEditorDescriptor;
import com.chinarewards.gwt.elt.client.sample2.Sample2Editor;
import com.chinarewards.gwt.elt.client.sample2.Sample2EditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class EnterprisePluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EnterprisePluginDescriptor.class).in(Singleton.class);
		bind(EnterpriseEditorDescriptor.class).in(Singleton.class);
		bind(EnterpriseEditor.class);

		bind(IntegralPricePluginDescriptor.class).in(Singleton.class);
		bind(IntegralPriceEditorDescriptor.class).in(Singleton.class);
		bind(IntegralPriceEditor.class);

		bind(PeriodPluginDescriptor.class).in(Singleton.class);
		bind(PeriodEditorDescriptor.class).in(Singleton.class);
		bind(PeriodEditor.class);

		bind(Sample2EditorDescriptor.class).in(Singleton.class);
		bind(Sample2Editor.class);

		bind(MailSetPluginDescriptor.class).in(Singleton.class);
		bind(MailSetEditorDescriptor.class).in(Singleton.class);
		bind(MailSetEditor.class);
	}

}
