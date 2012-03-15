package com.service.tree;

import junit.framework.Assert;

import org.junit.Test;

import com.service.tree.AbstractNode;
import com.service.tree.NodeContainerVisitAction;
import com.service.tree.NodeContainerVisitor;


public class NodeContainerVisitorTest {

	@Test
	public void testVisitProduct() {
		 NodeTest node = NodeRepository.buildNodes();
		NodeContainerVisitor visitor = new NodeContainerVisitor();
		visitor.visitTree(node, new NodeContainerVisitAction() {
			public void visitUp(AbstractNode product) {
				Assert.assertTrue(product.getUID().toString().endsWith("**DOWN**"));
				((NodeTest)product).setUID(product.getUID().toString()+"**UP**");
				Assert.assertTrue(product.getUID().toString().endsWith("**UP**"));
			}

			public void visitDown(AbstractNode product) {
				Assert.assertTrue(!product.getUID().toString().endsWith("**DOWN**"));
				Assert.assertTrue(!product.getUID().toString().endsWith("**UP**"));
				((NodeTest)product).setUID(product.getUID().toString()+"**DOWN**");
				Assert.assertTrue(product.getUID().toString().endsWith("**DOWN**"));
			}
		});
		
	}
}
