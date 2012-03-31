package com.service.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class TreeNodeServices<X extends AbstractNode> {

	public X getNodeTree(List<? extends NodeContainer<X>> nodes) {
		Stack<NodeContainer<X>> stack = new Stack<NodeContainer<X>>();
		if (nodes.isEmpty())
			return null;
		stack.push(nodes.get(0));
		for (int i = 1; i < nodes.size(); i++) {
			NodeContainer<X> el = nodes.get(i);
			if (el.getRight() > stack.peek().getRight()) {
				List<AbstractNode> node = new ArrayList<AbstractNode>();
				node.add(el.getNode());
				while (el.getRight() > stack.peek().getRight()) {
					el = stack.pop();
					node.add(el.getNode());
				}
				Collections.reverse(node);
				for (AbstractNode n : node)
					stack.peek().getNode().addChild(n);
			} else
				stack.push(el);
		}
		while (stack.size() > 1) {
			AbstractNode el = stack.pop().getNode();
			stack.peek().getNode().addChild(el);
		}
		return stack.pop().getNode();
	}

	public List<NodeContainer<X>> getNodeContainerList(X node,
			final int startLeft) {
		NodeContainerVisitor visitor = new NodeContainerVisitor();
		final List<NodeContainer<X>> toSave = new ArrayList<NodeContainer<X>>();
		final Stack<NodeContainer<X>> nodeStack = new Stack<NodeContainer<X>>();
		visitor.visitTree(node, new NodeContainerVisitAction() {
			private int count = startLeft;

			public void visitUp(AbstractNode child) {
				NodeContainer<X> n = nodeStack.pop();
				n.setRight(count++);
			}

			@SuppressWarnings("unchecked")
			public void visitDown(AbstractNode child) {
				NodeContainer<X> n = new NodeContainer<X>((X) child);
				nodeStack.push(n);
				n.setLeft(count++);
				toSave.add(n);
			}
		});
		return toSave;
	}

	public int countDescendants(AbstractNode node) {
		int count = 0;
		if (!node.isLeaf()) {
			for (AbstractNode child : node.getChildren())
				count += countDescendants(child);
			count += node.getChildren().size();
		}
		return count;
	}
}
