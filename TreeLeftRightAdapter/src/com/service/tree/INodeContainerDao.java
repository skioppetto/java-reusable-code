package com.service.tree;

import java.util.List;

public interface INodeContainerDao< X extends AbstractNode> {

	List<NodeContainer<X>> getAllNodeContainers();

	void startTransaction();

	List<NodeContainer<X>> getRightMostProducts(int minRightValue);

	NodeContainer<X> getContainerByNodeUID(Object uid);

	List<NodeContainer<X>> getLeftMostProducts(int minLeftValue);

	void saveOrUpdateProducts(List<NodeContainer<X>> containerNodes);

	NodeContainer<X> getMaxRightDescendant(NodeContainer<X> parent);

	void commitTransaction();

	List<NodeContainer<X>> getNodeContainersBetween(int left, int right);

	List<NodeContainer<X>> getNodeContainersExternal(int left, int right);
}
