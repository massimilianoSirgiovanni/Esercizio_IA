package csp.heuristic.orderDomain;

import csp.Assignment;
import csp.CSP;
import csp.Node;

public class SameOrderDomain implements OrderDomainValue{
	//Returns the domain in the same order in which it appears in the node

	@Override
	public int[] OrderDomain(Node var, CSP csp, Assignment assignment) {
		//Returns the domain ordered according to the heuristics described above
		return var.getDomain();
		
	}

}
