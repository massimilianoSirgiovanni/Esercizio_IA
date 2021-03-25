package csp.heuristic.selectVariables;

import java.util.Iterator;

import csp.Assignment;
import csp.CSP;
import csp.Node;

public class FIFOVariable implements SelectVariable {
	//Select the variables in the same order in which they are inserted into the graph

	@Override
	public Node selectUnassignedVariable(CSP csp, Assignment assignment) {
		//Following the aforementioned heuristic, it only returns variables that have not yet been assigned a value
		Iterator<Node> iterator = csp.getGraph().getNodes().iterator();
		while(iterator.hasNext()){
			Node tmp = iterator.next();
			if(!assignment.assignedVariable(tmp.getName())) {
				return tmp;
			}
		}
		
		return null;

	}

	@Override
	public Node selectVariable(CSP csp) {
		
		System.out.println("ERROR: The selectVariable method of the MoreConstrainedVariables class, was not implemented because it is useless, it would always return the same node");
		System.out.println("try another instance of the SelectVariable interface");
		 
		return null;
	}

}
