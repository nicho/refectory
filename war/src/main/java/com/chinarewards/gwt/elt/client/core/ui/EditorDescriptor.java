package com.chinarewards.gwt.elt.client.core.ui;

public interface EditorDescriptor {

	String getEditorId();

	Editor createEditor(String instanceId, Object model);
}
