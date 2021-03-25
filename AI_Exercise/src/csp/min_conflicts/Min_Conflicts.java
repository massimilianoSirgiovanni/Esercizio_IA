package csp.min_conflicts;

import java.util.Iterator;
import java.util.Random;

import csp.Assignment;
import csp.CSP;
import csp.Node;
import csp.heuristic.selectVariables.SelectVariable;

public class Min_Conflicts {

	public Assignment minConflicts(CSP csp, int maxSteps, SelectVariable selectVar) {
		//Method for implementing the algorithm
		int val;
		
		Node currentNode;

		Assignment current = randomAssignment(csp);

		for (int i = 0; i <= maxSteps; i++) {
			
			if(current.isSolution(csp)) {
				return current;  
			}
			
			
			
			currentNode = selectVar.selectVariable(csp);  //Using the "Strategy" design pattern
			

			val = valueMinConflicts(currentNode, csp, current, current.getValueByVar(currentNode.getName()));
			
			current.changeValue(currentNode.getName(), val);

		}

		return null;

	}
	


	public Assignment randomAssignment(CSP csp) {
		//Generate a random assignment
		Random rand = new Random();

		Assignment current = new Assignment(csp);

		Iterator<Node> variables = csp.getGraph().getNodes().iterator();
		Node currentNode;

		int tmp = 0;

		int[] domain = csp.getDomain();

		while (variables.hasNext()) {

			currentNode = variables.next();
			tmp = rand.nextInt(domain.length);

			current.changeValue(currentNode.getName(), domain[tmp]);
			
		}

		return current;

	}

	public int valueMinConflicts(Node node, CSP csp, Assignment current, int val) {
		//Returns the value that causes the least number of conflicts
		int newVal = val;
		int min = conflicts(csp, current);

		int tmpMin;

		int tmpVal;

		for (int i = 0; i < node.getDomain().length; i++) {

			tmpVal = node.getDomain()[i];
			current.changeValue(node.getName(), tmpVal);
			tmpMin = conflicts(csp, current);
			if (min >= tmpMin) {

				min = tmpMin;
				newVal = tmpVal;

			}
		}

		return newVal;

	}

	public int conflicts(CSP csp, Assignment current) {
		//Returns the number of conflicts in a given assignment
		int n = 0;

		Iterator<Node> variables = csp.getGraph().getNodes().iterator();
		Node currentNode;

		while (variables.hasNext()) {
			currentNode = variables.next();
			n = n + conflictsNode(currentNode, current);

		}

		n = n / 2; // In the graph, the arcs appear twice, once for each node connected by an arc

		return n;
	}

	public int conflictsNode(Node node, Assignment current) {
		//Returns the number of conflicts that a single variable generates, with an assigned value
		int n = 0;
		int val = current.getValueByVar(node.getName());
		Iterator<Node> links = node.getLinks().iterator();
		while (links.hasNext()) {
			if (current.getValueByVar(links.next().getName()) == val) {
				n++;
			}

		}

		return n;

	}

}
