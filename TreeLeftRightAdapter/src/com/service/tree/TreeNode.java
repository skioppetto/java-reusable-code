package com.service.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<Y extends NodeContainer<X>, X extends AbstractNode> {

	private TreeNodeServices<Y, X> services;
	private INodeContainerDao<Y, X> containerNodeDao;

	public TreeNode(INodeContainerFactory<Y> factory) {
		services = new TreeNodeServices<Y, X>(factory);
	}

	public X getNodeRoot() throws TreeNodeException {
		List<Y> list = containerNodeDao.getAllNodeContainers();
		if (list == null || list.isEmpty())
			throw new TreeNodeException("root not found");
		return services.getNodeTree(list);
	}

	public List<X> getNodePath(X node) {
		Y nodeContainer = containerNodeDao.getContainerByNodeUID(node.getUID());
		List<X> retList = new ArrayList<X>();
		if (nodeContainer != null) {
			List<Y> list = containerNodeDao.getNodeContainersExternal(
					nodeContainer.getLeft(), nodeContainer.getRight());
			for (Y nc : list)
				retList.add(nc.getNode());
		}
		return retList;
	}

	public X getSubTree(X parent) {
		Y parentContainer = containerNodeDao.getContainerByNodeUID(parent
				.getUID());
		List<Y> list = containerNodeDao.getNodeContainersBetween(
				parentContainer.getLeft(), parentContainer.getRight());
		return services.getNodeTree(list);
	}

	public void saveProduct(X p) throws TreeNodeException {
		/* parent check */
		if (p.getParent() == null)
			throw new TreeNodeException("Need to append to a parent");
		containerNodeDao.startTransaction();
		final Y parent = containerNodeDao.getContainerByNodeUID(p.getParent()
				.getUID());
		if (parent == null)
			throw new TreeNodeException("Need to append to a saved parent");
		/* load nodes to be updated */
		List<Y> rightMost = containerNodeDao.getRightMostProducts(parent
				.getRight() - 1);
		List<Y> leftMost = containerNodeDao.getLeftMostProducts(parent
				.getRight() - 1);
		List<Y> toBeUpdated = new ArrayList<Y>();
		int incr = (1 + services.countDescendants(p)) * 2;
		/* setting increment (total nodes to be added) */
		for (Y r : rightMost) {
			r.setRight(r.getRight() + incr);
		}
		toBeUpdated.addAll(rightMost);
		for (Y r : leftMost) {
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
		Y rightDesc = containerNodeDao.getMaxRightDescendant(parent);
		int start = Math.max(parent.getLeft() + 1,
				(rightDesc != null) ? rightDesc.getRight() : 0 + 1);
		toBeUpdated.addAll(services.getNodeContainerList(p, start));
		containerNodeDao.saveOrUpdateProducts(toBeUpdated);
		containerNodeDao.commitTransaction();
	}

	public void setContainerNodeDao(INodeContainerDao<Y, X> dao) {
		this.containerNodeDao = dao;
	}

	public INodeContainerDao<Y, X> getContainerNodeDao() {
		return containerNodeDao;
	}
	
	

}
