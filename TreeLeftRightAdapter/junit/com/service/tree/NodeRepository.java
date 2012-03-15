package com.service.tree;

import java.util.ArrayList;
import java.util.List;

import com.service.tree.AbstractNode;
import com.service.tree.NodeContainer;

public class NodeRepository {

	public static NodeContainer<NodeTest> x1_16 = new NodeContainer<NodeTest>(
			new NodeTest("1-16"), 1, 16);
	public static NodeContainer<NodeTest> x2_7 = new NodeContainer<NodeTest>(
			new NodeTest("2-7"), 2, 7);
	public static NodeContainer<NodeTest> x3_4 = new NodeContainer<NodeTest>(
			new NodeTest("3-4"), 3, 4);
	public static NodeContainer<NodeTest> x5_6 = new NodeContainer<NodeTest>(
			new NodeTest("5-6"), 5, 6);
	public static NodeContainer<NodeTest> x8_9 = new NodeContainer<NodeTest>(
			new NodeTest("8-9"), 8, 9);
	public static NodeContainer<NodeTest> x10_15 = new NodeContainer<NodeTest>(
			new NodeTest("10-15"), 10, 15);
	public static NodeContainer<NodeTest> x11_14 = new NodeContainer<NodeTest>(
			new NodeTest("11-14"), 11, 14);
	public static NodeContainer<NodeTest> x12_13 = new NodeContainer<NodeTest>(
			new NodeTest("12-13"), 12, 13);

	public static NodeTest buildNodes() {
		NodeTest root = new NodeTest("1_16");
		AbstractNode x2_7n = new NodeTest("x2_7");
		x2_7n.addChild(new NodeTest("x3_4"));
		x2_7n.addChild(new NodeTest("x5_6"));
		root.addChild(x2_7n);
		root.addChild(new NodeTest("x8_9"));
		AbstractNode x10_15n = new NodeTest("x10_15");
		AbstractNode x11_14n = new NodeTest("x12_13");
		x10_15n.addChild(x11_14n);
		root.addChild(x10_15n);
		return root;
	}

	static List<NodeContainer<NodeTest>> getOrderedLeftList() {
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

	static List<NodeContainer<NodeTest>> getOrderedRightList() {
		List<NodeContainer<NodeTest>> list = new ArrayList<NodeContainer<NodeTest>>();
		list.add(x3_4);
		list.add(x5_6);
		list.add(x2_7);
		list.add(x8_9);
		list.add(x12_13);
		list.add(x11_14);
		list.add(x10_15);
		list.add(x1_16);
		return list;
	}

}
