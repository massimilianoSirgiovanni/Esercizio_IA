package csp.heuristic.selectVariables;

import java.util.Iterator;

import csp.Assignment;
import csp.CSP;
import csp.Node;

public class MoreConstrainedVariables implements SelectVariable {
	//Select the variable that is most constrained within the graph
	
	@Override
	public Node selectUnassignedVariable(CSP csp, Assignment assignment) {
		//Following the aforementioned heuristic, it only returns variables that have not yet been assigned a value
		Node MoreConstrNode = null;
		int maxConstr = 0;
		
		Iterator<Node> iterator = csp.getGraph().getNodes().iterator();
		while(iterator.hasNext()){
			Node tmp = iterator.next();
			if(!assignment.assignedVariable(tmp.getName())) {
				if(maxConstr <= tmp.getLinks().size()) {
					MoreConstrNode = tmp;
					maxConstr = MoreConstrNode.getLinks().size();
					
				}
			}
		}
		
		return MoreConstrNode;
	}

	@Override
	public Node selectVariable(CSP csp) {
		
		System.out.println("ERROR: The selectVariable method of the MoreConstrainedVariables class, was not implemented because it is useless, it would always return the same node");
		System.out.println("try another instance of the SelectVariable interface");
		
		return null;
		
	}

}
