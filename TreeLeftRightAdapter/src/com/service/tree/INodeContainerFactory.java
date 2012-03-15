package com.service.tree;

public interface INodeContainerFactory<X extends NodeContainer<?>> {

	public X createNode (AbstractNode node);
	
}
