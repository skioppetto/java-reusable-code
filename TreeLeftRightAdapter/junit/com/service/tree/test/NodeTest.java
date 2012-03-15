package com.service.tree.test;

import com.service.tree.AbstractNode;

public class NodeTest extends AbstractNode {

	String title;

	public NodeTest(String string) {
		this.title = string;

	}

	@Override
	public Object getUID() {
		return title;
	}

	public void setUID(String title) {
		this.title = title;
	}

}
