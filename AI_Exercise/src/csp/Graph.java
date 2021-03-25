package csp;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
	
	private LinkedList<Node> nodes = new LinkedList<Node>();
	
	public LinkedList<Node> getNodes() {
		return nodes;
	}
	
	////////////Operations on Nodes//////////////
	
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	public void removeNode(Node node) {
		nodes.remove(node);
	}
	
	public void addLink(Node firstNode, Node secondNode) {
		
		firstNode.addLink(secondNode);
		secondNode.addLink(firstNode);
		
	}
	
	public void removeLink(Node firstNode, Node secondNode) {
	
		firstNode.removeLink(secondNode);
		secondNode.removeLink(firstNode);
	
	
}
	
	public Node getNode(String name) {
		Iterator<Node> iterator = nodes.iterator();
		Node node;
		while(iterator.hasNext()) {
			node = iterator.next();
			if (node.getName().equals(name)) {
				return node;
			}
		}
		return null;
	}
	
	///////////////////////////////////////////////////////
	
	public void print() {
		
		Iterator<Node> iterator = nodes.iterator();
		
		while(iterator.hasNext()) {
			iterator.next().print();
		}
		
	}

}
