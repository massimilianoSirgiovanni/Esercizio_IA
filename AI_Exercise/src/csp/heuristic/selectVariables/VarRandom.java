package csp.heuristic.selectVariables;

import java.util.Random;

import csp.Assignment;
import csp.CSP;
import csp.Node;

public class VarRandom implements SelectVariable {
	//Select a variable randomly

	@Override
	public Node selectUnassignedVariable(CSP csp, Assignment assignment) {
		//Following the aforementioned heuristic, it only returns variables that have not yet been assigned a value
		Random rand = new Random();
		int size = csp.getGraph().getNodes().size();
		
		Node selected = null;
		
		Boolean unsigned = false;

		while (!unsigned) {
			
			 selected = csp.getGraph().getNodes().get(rand.nextInt(size));
			 if(!assignment.assignedVariable(selected.getName())) {
				 unsigned = true;
			 }
		}
		
		return selected;

	}

	@Override
	public Node selectVariable(CSP csp) {
		//Following the aforementioned heuristic, it only returns a variable, which may or may not be assigned
		Random rand = new Random();
		int size = csp.getGraph().getNodes().size();

		return csp.getGraph().getNodes().get(rand.nextInt(size));
	}

}
