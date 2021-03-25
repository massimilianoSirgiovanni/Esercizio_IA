package csp.backtrack;

import csp.Assignment;
import csp.CSP;
import csp.Node;
import csp.heuristic.orderDomain.OrderDomainValue;
import csp.heuristic.selectVariables.SelectVariable;

public class Backtracking_Search_for_CSP {

	public Assignment BacktrackingSearch(CSP csp, SelectVariable selectVariable, OrderDomainValue domain) {

		Assignment assignment = new Assignment(csp);  //Create an empty assignment on the CSP passed in input

		return Backtrack(assignment, csp, selectVariable, domain); //The recursive function starts

	}

	public Assignment Backtrack(Assignment assignment, CSP csp, SelectVariable selectVariable,
			OrderDomainValue domain) {
		//Recursive function for finding the solution
		Node node;

		Assignment result;

		if (assignment.Complete()) {  //If the assignment is complete, the solution has been found
			return assignment;
		}
		node = selectVariable.selectUnassignedVariable(csp, assignment); //Using the "Strategy" design pattern

		int[] varDomain = domain.OrderDomain(node, csp, assignment);  //Using the "Strategy" design pattern

		for (int i = 0; i < varDomain.length; i++) {

			int val = varDomain[i];

			if (val != 0) {  //If the value chosen should be zero, an element of the domain has been selected which has been deleted from an inference, therefore the next element must be selected

				if (assignment.consistentValue(node, val)) {

					assignment.changeValue(node.getName(), val);
					if (csp.checkInference(node, val)) {
						csp.Inference(node, val, assignment);
						result = Backtrack(assignment, csp, selectVariable, domain);
						if (result != null) {
							return result;
						}
					}

					assignment.changeValue(node.getName(), 0);
				}

			}
		}

		return null;
	}

}
