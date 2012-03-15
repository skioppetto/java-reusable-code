package com.service.tree;


public class NodeContainer<X extends AbstractNode> {

	private int left;
	private int right;
	private X node;

	protected NodeContainer(X child) {
		this.node = child;
	}

	protected NodeContainer(X nodeTest, int left, int right) {
		this.node = nodeTest;
		this.left = left;
		this.right = right;
	}

	public X getNode() {
		return node;
	}

	protected void setNode(X node) {
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
