package com.service.tree.test;

import com.service.tree.AbstractNode;
import com.service.tree.NodeContainer;

public class NodeSetTest extends NodeContainer<NodeTest> {

	String title;

	public NodeSetTest(NodeTest node, int i, int j) {
		super (node);
		setLeft(i);
		setRight(j);
	}

	public NodeSetTest(AbstractNode node) {
		super((NodeTest) node);
		this.title = node.toString();

	}

}
