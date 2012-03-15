package com.service.tree;

public class NodeContainerVisitor {

	public void visitTree(AbstractNode nodeSet, NodeContainerVisitAction action) {
		if (nodeSet != null) {
			action.visitDown(nodeSet);
			for (AbstractNode child : nodeSet.getChildren()) {
				visitTree(child, action);
			}
			action.visitUp(nodeSet);
		}
	}

}
