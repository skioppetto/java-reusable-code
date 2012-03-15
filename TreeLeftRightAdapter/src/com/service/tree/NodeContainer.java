package com.service.tree;

public class NodeContainer<X extends AbstractNode> {

	protected int left;
	protected int right;
	protected X node;

	
	public NodeContainer(X child) {
		this.node = child;
	}

	public X getNode() {
		return node;
	}

	public void setNode(X node) {
		this.node = node;
	}

	public int getRight() {
		return right;
	};

	protected void setRight(int i) {
		this.right = i;
	};

	public void setLeft(int i) {
		this.left = i;
	}

	public int getLeft() {
		return left;
	};

}
