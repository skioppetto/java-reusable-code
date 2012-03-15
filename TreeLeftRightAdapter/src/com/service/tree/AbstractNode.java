package com.service.tree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNode {

	private AbstractNode parent;
	private List<AbstractNode> children = new ArrayList<AbstractNode>();

	public abstract Object getUID();

	public AbstractNode getParent() {
		return parent;
	}

	protected void setParent(AbstractNode parent) {
		this.parent = parent;
	}

	public void addChildren(List<AbstractNode> el) {
		for (AbstractNode e : el)
			addChild(e);
	}

	public void addChild(AbstractNode el) {
		if (!children.contains(el)) {
			children.add(el);
			el.setParent(this);
		}
	}

	public List<? extends AbstractNode> getChildren() {
		return children;
	}

	public boolean isLeaf() {
		return children == null || children.isEmpty();
	}

}