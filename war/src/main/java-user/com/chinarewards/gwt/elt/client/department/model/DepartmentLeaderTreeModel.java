package com.chinarewards.gwt.elt.client.department.model;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentConstants;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentLeaderPresenter.DepartmentLeaderDisplay;
import com.chinarewards.gwt.elt.client.ui.HyperLinkCell;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

public class DepartmentLeaderTreeModel implements TreeViewModel {
	private ListDataProvider<DepartmentNode> categoryDataProvider;
	private Cell<DepartmentNode> rootNodeCell;// 格式模版（一级 无前置复选框）
	private Cell<DepartmentNode> nodeCell;// 格式模版
	final List<DepartmentNode> nodeList;// 数据
	DepartmentLeaderDisplay departmentListDisplay;
	String corporationId;
	String departmentIds;

	public DepartmentLeaderTreeModel(List<DepartmentNode> nodeList,
			String corporationId, DepartmentLeaderDisplay departmentListDisplay) {
		this.corporationId = corporationId;
		this.nodeList = nodeList;
		this.departmentListDisplay = departmentListDisplay;

		setDataProvider();

		buildRootNodeCell();
		
		buildNodeCell();
		
	}

	private void buildRootNodeCell() {
		List<HasCell<DepartmentNode, ?>> nodeRow = new ArrayList<HasCell<DepartmentNode, ?>>();
		nodeRow = buildRootDataRow(nodeRow);

		rootNodeCell = new CompositeCell<DepartmentNode>(nodeRow) {
			@Override
			public void render(Context context, DepartmentNode value,
					SafeHtmlBuilder sb) {
				sb.appendHtmlConstant("<table><tbody><tr>");
				super.render(context, value, sb);
				sb.appendHtmlConstant("</tr></tbody></table>");
			}

			@Override
			protected Element getContainerElement(Element parent) {//<td></td>触发事件
//				System.out.println("==========================="+parent.getFirstChildElement().getInnerHTML());
				return parent.getFirstChildElement().getFirstChildElement()
						.getFirstChildElement();
			}

			@Override
			protected <X> void render(Context context, DepartmentNode value,
					SafeHtmlBuilder sb, HasCell<DepartmentNode, X> hasCell) {
				Cell<X> cell = hasCell.getCell();
				sb.appendHtmlConstant("<td>");

				cell.render(context, hasCell.getValue(value), sb);
				sb.appendHtmlConstant("</td>");
			}
		};

	}

	private void buildNodeCell() {
		List<HasCell<DepartmentNode, ?>> nodeRow = new ArrayList<HasCell<DepartmentNode, ?>>();
		nodeRow = buildDataRow(nodeRow);

		nodeCell = new CompositeCell<DepartmentNode>(nodeRow) {
			@Override
			public void render(Context context, DepartmentNode value,
					SafeHtmlBuilder sb) {
				sb.appendHtmlConstant("<table border='0'><tbody><tr>");
				super.render(context, value, sb);
				sb.appendHtmlConstant("</tr></tbody></table>");
			}

			@Override
			protected Element getContainerElement(Element parent) {
				return parent.getFirstChildElement().getFirstChildElement()
						.getFirstChildElement();
			}

			@Override
			protected <X> void render(Context context, DepartmentNode value,
					SafeHtmlBuilder sb, HasCell<DepartmentNode, X> hasCell) {
				Cell<X> cell = hasCell.getCell();
				sb.appendHtmlConstant("<td>");

				cell.render(context, hasCell.getValue(value), sb);
				sb.appendHtmlConstant("</td>");
			}
		};

	}

	/**
	 * 构造一级部门数据行模版
	 * */
	private List<HasCell<DepartmentNode, ?>> buildRootDataRow(
			List<HasCell<DepartmentNode, ?>> nodeRow) {
		// 部门名称
		nodeRow.add(new HasCell<DepartmentNode, String>() {
			private HyperLinkCell cell = new HyperLinkCell();

			public Cell<String> getCell() {
				return cell;
			}

			public FieldUpdater<DepartmentNode, String> getFieldUpdater() {
				return new FieldUpdater<DepartmentNode, String>() {
					@Override
					public void update(int index, DepartmentNode object,
							String value) {
						// Window.alert(object.getDepartmentId() + "--- 部门名称");
						DepartmentClient client = new DepartmentClient();
						client.setId(object.getDepartmentId());
						client.setThisAction(DepartmentConstants.ACTION_DEPARTMENT_EDIT_DEPT);
						openEditPage(client);
					}
				};
			}

			public String getValue(DepartmentNode object) {
				return object.getDepartmentName();
			}
		});

		// 操作
		nodeRow.add(new HasCell<DepartmentNode, String>() {
			private HyperLinkCell cell = new HyperLinkCell();

			public Cell<String> getCell() {
				return cell;
			}

			public FieldUpdater<DepartmentNode, String> getFieldUpdater() {
				return new FieldUpdater<DepartmentNode, String>() {
					@Override
					public void update(int index, DepartmentNode object,
							String value) {
						DepartmentClient client = new DepartmentClient();
						client.setId(object.getDepartmentId());
						client.setThisAction(DepartmentConstants.ACTION_DEPARTMENT_EDIT_DEPT);
						openEditPage(client);
					}
				};
			}

			public String getValue(DepartmentNode object) {
				return "操作";
			}
		});

		return nodeRow;
	}

	/**
	 * 构造数据行模版
	 * */
	private List<HasCell<DepartmentNode, ?>> buildDataRow(
			List<HasCell<DepartmentNode, ?>> nodeRow) {

		// 选择框
		nodeRow.add(new HasCell<DepartmentNode, Boolean>() {
			private CheckboxCell cell = new CheckboxCell(true, false);

			public Cell<Boolean> getCell() {
				return cell;
			}

			public FieldUpdater<DepartmentNode, Boolean> getFieldUpdater() {
				return new FieldUpdater<DepartmentNode, Boolean>() {
					@Override
					public void update(int index, DepartmentNode object,
							Boolean checked) {
						// Window.alert(object.getDepartmentId()+"--"+object.getDepartmentName());

						departmentIds = departmentListDisplay
								.getCurrentDepartmentId().getValue();
						departmentIds = updateDepartmentIdsAsChecked(object,
								departmentIds);

						departmentListDisplay.getCurrentDepartmentId()
								.setValue(departmentIds);

					}
				};
			}

			public Boolean getValue(DepartmentNode object) {
				// return isChecked(object, departmentIds);
				return false;
			}
		});

		// 部门名称
		nodeRow.add(new HasCell<DepartmentNode, String>() {
			private HyperLinkCell cell = new HyperLinkCell();

			public Cell<String> getCell() {
				return cell;
			}

			public FieldUpdater<DepartmentNode, String> getFieldUpdater() {
				return new FieldUpdater<DepartmentNode, String>() {
					@Override
					public void update(int index, DepartmentNode object,
							String value) {
						// Window.alert(object.getDepartmentId() + "--- 部门名称");
						DepartmentClient client = new DepartmentClient();
						client.setId(object.getDepartmentId());
						client.setThisAction(DepartmentConstants.ACTION_DEPARTMENT_EDIT_DEPT);
						openEditPage(client);
					}
				};
			}

			public String getValue(DepartmentNode object) {
				return object.getDepartmentName();
			}
		});

		// 操作
		nodeRow.add(new HasCell<DepartmentNode, String>() {
			private HyperLinkCell cell = new HyperLinkCell();

			public Cell<String> getCell() {
				return cell;
			}

			public FieldUpdater<DepartmentNode, String> getFieldUpdater() {
				return new FieldUpdater<DepartmentNode, String>() {
					@Override
					public void update(int index, DepartmentNode object,
							String value) {
						DepartmentClient client = new DepartmentClient();
						client.setId(object.getDepartmentId());
						client.setThisAction(DepartmentConstants.ACTION_DEPARTMENT_EDIT_DEPT);
						openEditPage(client);
					}
				};
			}

			public String getValue(DepartmentNode object) {
				return "操作";
			}
		});

		return nodeRow;
	}

	public <T> NodeInfo<?> getNodeInfo(T node) {
		if (node == null) {
			return new DefaultNodeInfo<DepartmentNode>(categoryDataProvider,
					rootNodeCell);
		} else if (node instanceof DepartmentNode) {
			DepartmentNode departmentNode = (DepartmentNode) node;
			List<DepartmentNode> nodeList = new ArrayList<DepartmentNode>();

			for (DepartmentNode tempNode : this.nodeList) {
				if (departmentNode.getDepartmentId().equals(
						tempNode.getParentId()))
					nodeList.add(tempNode);
			}
			ListDataProvider<DepartmentNode> dataProvider = new ListDataProvider<DepartmentNode>(
					nodeList);

			return new DefaultNodeInfo<DepartmentNode>(dataProvider, nodeCell);

		}
		String type = node.getClass().getName();
		throw new IllegalArgumentException("Unsupported object type: " + type);
	}

	public boolean isLeaf(Object value) {
		if (value instanceof DepartmentNode) {
			return ((DepartmentNode) value).isLeaf();
		} else {
			return false;
		}
	}

	// 如果 node.getId 在ids中存在，则返回true
	private Boolean isChecked(DepartmentNode node, String departmentIds) {
		if (node != null) {
			String thisId = node.getDepartmentId();
			boolean isExists = StringUtil.containsExistString(departmentIds,
					thisId);

			return isExists;
		}
		return false;
	}

	// currentId存在 则删除， 无 则加入
	private String updateDepartmentIdsAsChecked(DepartmentNode node,
			String departmentIds) {
		if (node != null) {
			String thisId = node.getDepartmentId();
			boolean isExists = StringUtil.containsExistString(departmentIds,
					thisId);

			if (isExists) {
				departmentIds = StringUtil.removeCellString(departmentIds,
						thisId);
			} else {
				departmentIds = StringUtil.appendString(departmentIds, thisId,
						",");
			}
		}
		System.out.println("==========update selected Ids:" + departmentIds);
		return departmentIds;
	}

	private void setDataProvider() {
		// 一级部门加载
		if (nodeList != null) {
			categoryDataProvider = new ListDataProvider<DepartmentNode>();
			List<DepartmentNode> categoryList = categoryDataProvider.getList();
			// 顶级
			DepartmentNode rootDepartmentNode = null;
			for (DepartmentNode tempNode : nodeList) {
				if (("ROOT_DEPT" + corporationId).equals(tempNode
						.getDepartmentName()))
					rootDepartmentNode = tempNode;
			}
			// 一级部门
			for (DepartmentNode tempNode : nodeList) {
				if (rootDepartmentNode != null) {
					if (rootDepartmentNode.getDepartmentId().equals(
							tempNode.getParentId())) {
						categoryList.add(tempNode);
					}
				}

			}
		} else {
			categoryDataProvider = new ListDataProvider<DepartmentNode>();
			// 无数据时
		}
	}

	private void openEditPage(DepartmentClient client) {
		Platform.getInstance()
				.getEditorRegistry()
				.openEditor(DepartmentConstants.EDITOR_DEPARTMENT_EDIT,
						"EDITOR_DEPARTMENT_EDIT", client);
	}
}