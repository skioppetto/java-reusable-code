package com.service.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<X extends AbstractNode> {

	private TreeNodeServices<X> services;
	private INodeContainerDao<X> containerNodeDao;

	public TreeNode() {
	}

	public X getNodeRoot() throws TreeNodeException {
		List<NodeContainer<X>> list = containerNodeDao.getAllNodeContainers();
		if (list == null || list.isEmpty())
			throw new TreeNodeException("root not found");
		return services.getNodeTree(list);
	}

	public List<X> getNodePath(X node) {
		NodeContainer<X> nodeContainer = containerNodeDao
				.getContainerByNodeUID(node.getUID());
		List<X> retList = new ArrayList<X>();
		if (nodeContainer != null) {
			List<NodeContainer<X>> list = containerNodeDao
					.getNodeContainersExternal(nodeContainer.getLeft(),
							nodeContainer.getRight());
			for (NodeContainer<X> nc : list)
				retList.add(nc.getNode());
		}
		return retList;
	}

	public X getSubTree(X parent) {
		NodeContainer<X> parentContainer = containerNodeDao
				.getContainerByNodeUID(parent.getUID());
		List<NodeContainer<X>> list = containerNodeDao
				.getNodeContainersBetween(parentContainer.getLeft(),
						parentContainer.getRight());
		return services.getNodeTree(list);
	}

	public void saveProduct(X p) throws TreeNodeException {
		/* parent check */
		if (p.getParent() == null)
			throw new TreeNodeException("Need to append to a parent");
		containerNodeDao.startTransaction();
		final NodeContainer<X> parent = containerNodeDao
				.getContainerByNodeUID(p.getParent().getUID());
		if (parent == null)
			throw new TreeNodeException("Need to append to a saved parent");
		/* load nodes to be updated */
		List<NodeContainer<X>> rightMost = containerNodeDao
				.getRightMostProducts(parent.getRight() - 1);
		List<NodeContainer<X>> leftMost = containerNodeDao
				.getLeftMostProducts(parent.getRight() - 1);
		List<NodeContainer<X>> toBeUpdated = new ArrayList<NodeContainer<X>>();
		int incr = (1 + services.countDescendants(p)) * 2;
		/* setting increment (total nodes to be added) */
		for (NodeContainer<X> r : rightMost) {
			r.setRight(r.getRight() + incr);
		}
		toBeUpdated.addAll(rightMost);
		for (NodeContainer<X> r : leftMost) {
			int incLeft = r.getLeft() + incr;
			if (toBeUpdated.contains(r))
				rightMost.get(rightMost.indexOf(r)).setLeft(incLeft);
			else {
				r.setLeft(incLeft);
				toBeUpdated.add(r);
			}

		}
		containerNodeDao.saveOrUpdateProducts(toBeUpdated);
		/* add new nodes */
		NodeContainer<X> rightDesc = containerNodeDao
				.getMaxRightDescendant(parent);
		int start = Math.max(parent.getLeft() + 1,
				(rightDesc != null) ? rightDesc.getRight() : 0 + 1);
		toBeUpdated.addAll(services.getNodeContainerList(p, start));
		containerNodeDao.saveOrUpdateProducts(toBeUpdated);
		containerNodeDao.commitTransaction();
	}

	public void setContainerNodeDao(INodeContainerDao<X> dao) {
		this.containerNodeDao = dao;
	}

	public INodeContainerDao<X> getContainerNodeDao() {
		return containerNodeDao;
	}

}
