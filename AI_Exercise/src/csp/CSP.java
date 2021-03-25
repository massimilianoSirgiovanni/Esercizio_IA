package csp;

import java.util.Iterator;

public class CSP {

	private Graph graph;

	private int[] domain;

	public CSP(Graph graph, int[] domain) {
		//Constructor
		this.graph = graph;

		this.domain = new int[domain.length];

		for (int i = 0; i < domain.length; i++) {
			this.domain[i] = domain[i];
		}

	}

	//////////Getters//////////////////////

	public Graph getGraph() {
		return graph;
	}
	
	public int[] getDomain() {
		return domain;
	}
	/////////////////////////////////////
	
	public void reinitializesNodeDomain() {
		//Reinitializes the domain of all nodes in the graph, resetting it to the original domain
		Iterator<Node> iterator = graph.getNodes().iterator();
		while(iterator.hasNext()) {
			iterator.next().setDomain(domain);
		}
		
	}
	
	//Operations to make inference
	

	public boolean checkInference(Node var, int val) {
		//Check that the inference is feasible
		Iterator<Node> linkedNodes = var.getLinks().iterator();

		Node currentNode;

		while (linkedNodes.hasNext()) {
			currentNode = linkedNodes.next();
			if (currentNode.domainElementNumber() <= 1 && currentNode.verifyElementInDomain(val)) {
				return false;
			}
		}

		return true;

	}

	public void Inference(Node var, int val, Assignment assignment) {
		//Inference the value "val" on all nodes connected to "var"
		Iterator<Node> linkedNodes = var.getLinks().iterator();
		Node currentNode;

		while (linkedNodes.hasNext()) {
			currentNode = linkedNodes.next();
			if (!assignment.assignedVariable(currentNode.getName())) {
				currentNode.removeElementFromDomain(val);
			}
		}
	}

}
