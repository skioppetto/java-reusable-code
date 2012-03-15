package com.service.tree.test;

import java.util.ArrayList;
import java.util.List;

import com.service.tree.AbstractNode;


public class NodeRepository {

	public static NodeSetTest x1_16 = new NodeSetTest(new NodeTest("1-16"), 1, 16);
	public static NodeSetTest x2_7 = new NodeSetTest(new NodeTest("2-7"), 2, 7);
	public static NodeSetTest x3_4 = new NodeSetTest(new NodeTest("3-4"), 3, 4);
	public static NodeSetTest x5_6 = new NodeSetTest(new NodeTest("5-6"), 5, 6);
	public static NodeSetTest x8_9 = new NodeSetTest(new NodeTest("8-9"), 8, 9);
	public static NodeSetTest x10_15 = new NodeSetTest(new NodeTest("10-15"), 10, 15);
	public static NodeSetTest x11_14 = new NodeSetTest(new NodeTest("11-14"), 11, 14);
	public static NodeSetTest x12_13 = new NodeSetTest(new NodeTest("12-13"), 12, 13);

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

	static List<NodeSetTest> getOrderedLeftList() {
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

	static List<NodeSetTest> getOrderedRightList() {
		List<NodeSetTest> list = new ArrayList<NodeSetTest>();
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
