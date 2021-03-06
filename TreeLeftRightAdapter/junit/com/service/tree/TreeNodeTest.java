package com.service.tree;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;



public class TreeNodeTest {

	private TreeNode<NodeTest> tree = new TreeNode<NodeTest>();

	@Before
	public void setDao() {
		this.tree.setContainerNodeDao(new NodeDaoTest());
	}

	@Test
	public void getNodeRootTest() throws TreeNodeException {
		NodeTest r = tree.getNodeRoot();
		Assert.assertNotNull(r);
	}

	@Test
	public void getNodePathTest() {
		List<NodeTest> pp = tree.getNodePath(NodeRepository.x3_4.getNode());
		Assert.assertNotNull(pp);
		Assert.assertEquals(2, pp.size());
		Assert.assertEquals(NodeRepository.x1_16.getNode(), pp.get(0));
		Assert.assertEquals(NodeRepository.x2_7.getNode(), pp.get(1));
	}

	@Test
	public void getSubTree() {
		NodeTest r = tree.getSubTree(NodeRepository.x8_9.getNode());
		Assert.assertNotNull(r);
	}

	@Test
	public void saveProduct() throws TreeNodeException {
		NodeTest parent = NodeRepository.x8_9.getNode();
		NodeTest t = new NodeTest("myNode");
		t.addChild(new NodeTest("myChild1"));
		t.addChild(new NodeTest("myChild2"));
		parent.addChild(t);
		tree.saveProduct(t);
		Assert.assertNotNull(((NodeDaoTest) tree.getContainerNodeDao())
				.getSaved());
	}

}
