package com.service.tree;

import java.util.List;

public interface INodeContainerDao<Y extends NodeContainer<X>, X extends AbstractNode> {

	List<Y> getAllNodeContainers();

	void startTransaction();

	List<Y> getRightMostProducts(int minRightValue);

	Y getContainerByNodeUID(Object uid);

	List<Y> getLeftMostProducts(int minLeftValue);

	void saveOrUpdateProducts(List<Y> containerNodes);

	Y getMaxRightDescendant(Y parent);

	void commitTransaction();

	List<Y> getNodeContainersBetween(int left, int right);

	List<Y> getNodeContainersExternal(int left, int right);
}
