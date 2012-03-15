package com.service.tree;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.service.tree.AbstractNode;
import com.service.tree.INodeContainerFactory;
import com.service.tree.TreeNodeServices;
import com.service.tree.test.NodeRepository;
import com.service.tree.test.NodeSetTest;
import com.service.tree.test.NodeTest;


public class TreeNodeServicesTest {

	INodeContainerFactory<NodeSetTest> factory = new INodeContainerFactory<NodeSetTest>() {
		public NodeSetTest createNode(AbstractNode node) {
			return new NodeSetTest(node);
		}
	};

	NodeSetTest x1_16 = NodeRepository.x1_16;
	NodeSetTest x2_7 = NodeRepository.x2_7;
	NodeSetTest x3_4 = NodeRepository.x3_4;
	NodeSetTest x5_6 = NodeRepository.x5_6;
	NodeSetTest x8_9 = NodeRepository.x8_9;
	NodeSetTest x10_15 = NodeRepository.x10_15;
	NodeSetTest x11_14 = NodeRepository.x11_14;
	NodeSetTest x12_13 = NodeRepository.x12_13;

	private List<NodeSetTest> getOrderedList() {
		List<NodeSetTest> list = new ArrayList<NodeSetTest>();
		list.add(x1_16);
		list.add(x2_7);
		list.add(x3_4);
		list.add(x5_6);
		list.add(x8_9);
		list.add(x10_15);
		list.add(x11_14);
		list.add(x12_13);
		return list;
	}

	
	
	@Test
	public void getNodeTreeAndContainerListTest() {

		TreeNodeServices<NodeSetTest, NodeTest> adapter = new TreeNodeServices<NodeSetTest, NodeTest>(
				factory);
		NodeTest root = adapter.getNodeTree(getOrderedList());
		Assert.assertTrue(x1_16.getNode().getChildren()
				.contains(x2_7.getNode()));
		Assert.assertTrue(x1_16.getNode().getChildren()
				.contains(x8_9.getNode()));
		Assert.assertTrue(x1_16.getNode().getChildren()
				.contains(x10_15.getNode()));
		Assert.assertTrue(x2_7.getNode().getChildren().contains(x3_4.getNode()));
		Assert.assertTrue(x2_7.getNode().getChildren().contains(x5_6.getNode()));
		Assert.assertTrue(x10_15.getNode().getChildren()
				.contains(x11_14.getNode()));
		Assert.assertTrue(x11_14.getNode().getChildren()
				.contains(x12_13.getNode()));

		List<NodeSetTest> valuList = adapter.getNodeContainerList(root, 1);
		Assert.assertEquals(8, valuList.size());
		Assert.assertEquals(valuList.get(0).getLeft(), 1);
		Assert.assertEquals(valuList.get(0).getRight(), 16);
		Assert.assertEquals(valuList.get(1).getLeft(), 2);
		Assert.assertEquals(valuList.get(1).getRight(), 7);
		Assert.assertEquals(valuList.get(2).getLeft(), 3);
		Assert.assertEquals(valuList.get(2).getRight(), 4);
		Assert.assertEquals(valuList.get(3).getLeft(), 5);
		Assert.assertEquals(valuList.get(3).getRight(), 6);
		Assert.assertEquals(valuList.get(4).getLeft(), 8);
		Assert.assertEquals(valuList.get(4).getRight(), 9);
		Assert.assertEquals(valuList.get(5).getLeft(), 10);
		Assert.assertEquals(valuList.get(5).getRight(), 15);
		Assert.assertEquals(valuList.get(6).getLeft(), 11);
		Assert.assertEquals(valuList.get(6).getRight(), 14);
		Assert.assertEquals(valuList.get(7).getLeft(), 12);
		Assert.assertEquals(valuList.get(7).getRight(), 13);

	}

	@Test
	public void countDescendants() {
		TreeNodeServices<NodeSetTest, NodeTest> adapter = new TreeNodeServices<NodeSetTest, NodeTest>(
				factory);
		NodeTest product = NodeRepository.buildNodes();
		Assert.assertEquals(6, adapter.countDescendants(product));
		product.addChild(new NodeTest("MyProduct"));
		Assert.assertEquals(7, adapter.countDescendants(product));

	}

}
