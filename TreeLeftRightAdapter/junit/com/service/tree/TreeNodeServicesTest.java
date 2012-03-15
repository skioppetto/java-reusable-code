package com.service.tree;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;



public class TreeNodeServicesTest {

	
	NodeContainer<NodeTest> x1_16 = NodeRepository.x1_16;
	NodeContainer<NodeTest> x2_7 = NodeRepository.x2_7;
	NodeContainer<NodeTest> x3_4 = NodeRepository.x3_4;
	NodeContainer<NodeTest> x5_6 = NodeRepository.x5_6;
	NodeContainer<NodeTest> x8_9 = NodeRepository.x8_9;
	NodeContainer<NodeTest> x10_15 = NodeRepository.x10_15;
	NodeContainer<NodeTest> x11_14 = NodeRepository.x11_14;
	NodeContainer<NodeTest> x12_13 = NodeRepository.x12_13;

	private List<NodeContainer<NodeTest>> getOrderedList() {
		List<NodeContainer<NodeTest>> list = new ArrayList<NodeContainer<NodeTest>>();
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

		TreeNodeServices<NodeTest> adapter = new TreeNodeServices<NodeTest>();
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

		List<NodeContainer<NodeTest>> valuList = adapter.getNodeContainerList(root, 1);
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
		TreeNodeServices<NodeTest> adapter = new TreeNodeServices<NodeTest>();
		NodeTest product = NodeRepository.buildNodes();
		Assert.assertEquals(6, adapter.countDescendants(product));
		product.addChild(new NodeTest("MyProduct"));
		Assert.assertEquals(7, adapter.countDescendants(product));

	}

}
