package com.service.tree;

import java.util.ArrayList;
import java.util.List;

import com.service.tree.INodeContainerDao;
import com.service.tree.NodeContainer;

public class NodeDaoTest implements INodeContainerDao<NodeTest> {

	private List<NodeContainer<NodeTest>> saved;

	public List<NodeContainer<NodeTest>> getAllNodeContainers() {
		return NodeRepository.getOrderedLeftList();
	}

	public void startTransaction() {

	}

	public List<NodeContainer<NodeTest>> getRightMostProducts(int minRightValue) {
		List<NodeContainer<NodeTest>> ret = new ArrayList<NodeContainer<NodeTest>>();
		for (NodeContainer<NodeTest> s : NodeRepository.getOrderedRightList())
			if (s.getRight() > minRightValue)
				ret.add(s);
		return ret;
	}

	public NodeContainer<NodeTest> getContainerByNodeUID(Object uid) {
		for (NodeContainer<NodeTest> s : NodeRepository.getOrderedLeftList())
			if (s.getNode().getUID().equals(uid))
				return s;
		return null;
	}

	public List<NodeContainer<NodeTest>> getLeftMostProducts(int minLeftValue) {
		List<NodeContainer<NodeTest>> ret = new ArrayList<NodeContainer<NodeTest>>();
		for (NodeContainer<NodeTest> s : NodeRepository.getOrderedLeftList())
			if (s.getLeft() > minLeftValue)
				ret.add(s);
		return ret;
	}

	public void saveOrUpdateProducts(
			List<NodeContainer<NodeTest>> containerNodes) {
		this.setSaved(containerNodes);
	}

	public NodeContainer<NodeTest> getMaxRightDescendant(
			NodeContainer<NodeTest> parent) {
		NodeContainer<NodeTest> last = null;
		for (NodeContainer<NodeTest> s : NodeRepository.getOrderedRightList()) {
			if (parent.getRight() == parent.getRight())
				break;
			last = s;
		}
		return last;
	}

	public void commitTransaction() {

	}

	public List<NodeContainer<NodeTest>> getNodeContainersBetween(int left,
			int right) {
		List<NodeContainer<NodeTest>> values = new ArrayList<NodeContainer<NodeTest>>();
		for (NodeContainer<NodeTest> s : NodeRepository.getOrderedLeftList())
			if (s.getLeft() >= left && s.getRight() <= right)
				values.add(s);
		return values;
	}

	public List<NodeContainer<NodeTest>> getNodeContainersExternal(int left,
			int right) {
		List<NodeContainer<NodeTest>> values = new ArrayList<NodeContainer<NodeTest>>();
		for (NodeContainer<NodeTest> s : NodeRepository.getOrderedLeftList())
			if (s.getLeft() < left && s.getRight() > right)
				values.add(s);
		return values;
	}

	public List<NodeContainer<NodeTest>> getSaved() {
		return saved;
	}

	public void setSaved(List<NodeContainer<NodeTest>> saved) {
		this.saved = saved;
	}

}
