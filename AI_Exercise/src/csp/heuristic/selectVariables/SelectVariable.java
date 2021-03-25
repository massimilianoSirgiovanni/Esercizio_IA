package csp.heuristic.selectVariables;

import csp.Assignment;
import csp.CSP;
import csp.Node;

public interface SelectVariable {
	//Strategy interface for selecting variables
	
	public Node selectUnassignedVariable(CSP csp, Assignment assignment);
	
	public Node selectVariable(CSP csp);

}
