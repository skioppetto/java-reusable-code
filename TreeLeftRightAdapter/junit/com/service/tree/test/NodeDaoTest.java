package com.service.tree.test;

import java.util.ArrayList;
import java.util.List;

import com.service.tree.INodeContainerDao;


public class NodeDaoTest implements INodeContainerDao<NodeSetTest, NodeTest> {

	private List<NodeSetTest> saved;

	public List<NodeSetTest> getAllNodeContainers() {
		return NodeRepository.getOrderedLeftList();
	}

	public void startTransaction() {

	}

	public List<NodeSetTest> getRightMostProducts(int minRightValue) {
		List<NodeSetTest> ret = new ArrayList<NodeSetTest>();
		for (NodeSetTest s : NodeRepository.getOrderedRightList())
			if (s.getRight() > minRightValue)
				ret.add(s);
		return ret;
	}

	public NodeSetTest getContainerByNodeUID(Object uid) {
		for (NodeSetTest s : NodeRepository.getOrderedLeftList())
			if (s.getNode().getUID().equals(uid))
				return s;
		return null;
	}

	public List<NodeSetTest> getLeftMostProducts(int minLeftValue) {
		List<NodeSetTest> ret = new ArrayList<NodeSetTest>();
		for (NodeSetTest s : NodeRepository.getOrderedLeftList())
			if (s.getLeft() > minLeftValue)
				ret.add(s);
		return ret;
	}

	public void saveOrUpdateProducts(List<NodeSetTest> containerNodes) {
		this.setSaved(containerNodes);
	}

	public NodeSetTest getMaxRightDescendant(NodeSetTest parent) {
		NodeSetTest last = null;
		for (NodeSetTest s : NodeRepository.getOrderedRightList()) {
			if (parent.getRight() == parent.getRight())
				break;
			last = s;
		}
		return last;
	}

	public void commitTransaction() {

	}

	public List<NodeSetTest> getNodeContainersBetween(int left, int right) {
		List<NodeSetTest> values = new ArrayList<NodeSetTest>();
		for (NodeSetTest s : NodeRepository.getOrderedLeftList())
			if (s.getLeft() >= left && s.getRight() <= right)
				values.add(s);
		return values;
	}

	public List<NodeSetTest> getNodeContainersExternal(int left, int right) {
		List<NodeSetTest> values = new ArrayList<NodeSetTest>();
		for (NodeSetTest s : NodeRepository.getOrderedLeftList())
			if (s.getLeft() < left && s.getRight() > right)
				values.add(s);
		return values;
	}

	public List<NodeSetTest> getSaved() {
		return saved;
	}

	public void setSaved(List<NodeSetTest> saved) {
		this.saved = saved;
	}

}
