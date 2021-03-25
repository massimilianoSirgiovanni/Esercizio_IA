package csp;

import java.util.Iterator;

public class Assignment {

	private String[] variables;
	private int[] values;

	private int numberVariables;

	public Assignment(CSP csp) {
		//Constructor
		numberVariables = csp.getGraph().getNodes().size();

		variables = new String[numberVariables];
		values = new int[numberVariables];

		Iterator<Node> nodes = csp.getGraph().getNodes().iterator();
		int i = 0;
		while (nodes.hasNext()) {
			variables[i] = nodes.next().getName();
			i++;
		}

	}

	//////////Getters///////////////////////////////
	public String[] getVariables() {
		return variables;
	}

	public int[] getValues() {
		return values;
	}
	////////////////////////////////////////////////

	public void changeValue(String var, int value) {
		//Modify the value assigned to a variable
		for (int i = 0; i < numberVariables; i++) {
			if (variables[i].equals(var)) {
				values[i] = value;
			}
		}

	}

	public boolean Complete() {
		//Check if the assignment is complete
		for (int i = 0; i < numberVariables; i++) {
			if (values[i] == 0) {
				return false;
			}
		}

		return true;

	}

	public boolean assignedVariable(String var) {
		//Check if the variable has already had an assignment or not
		for (int i = 0; i < numberVariables; i++) {
			if (variables[i].equals(var)) {
				if (values[i] != 0) {
					return true;
				}
			}
		}

		return false;

	}

	public int getValueByVar(String var) {
		//Returns the value assigned to a variable, passed to it as an argument
		int val = 0;

		for (int i = 0; i < numberVariables; i++) {
			if (variables[i].equals(var)) {
				val = values[i];
				return val;
			}
		}

		return -1;

	}

	public boolean isSolution(CSP csp) {
		//Check if the assignment is a solution
		Iterator<Node> nodes = csp.getGraph().getNodes().iterator();

		Node currentNode;

		while (nodes.hasNext()) {

			currentNode = nodes.next();

			if (!this.consistentValue(currentNode, this.getValueByVar(currentNode.getName()))) {
				return false;
			}

		}

		return true;

	}

	public boolean consistentValue(Node node, int value) {
		//Check if the value "value" is consistent for the node passed as an argument
		Iterator<Node> iterator = node.getLinks().iterator();

		while (iterator.hasNext()) {
			if (this.getValueByVar(iterator.next().getName()) == value) {
				return false;
			}
		}

		return true;
	}

	public void print() {

		for (int i = 0; i < numberVariables; i++) {
			System.out.println("[" + variables[i] + "] -->" + " [" + values[i] + "] ");

		}

	}

}
