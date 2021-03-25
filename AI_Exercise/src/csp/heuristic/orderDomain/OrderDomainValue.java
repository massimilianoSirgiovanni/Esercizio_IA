package csp.heuristic.orderDomain;

import csp.Assignment;
import csp.CSP;
import csp.Node;

public interface OrderDomainValue {
	//Strategy interface for ordering operations on domains
	
	public int[] OrderDomain(Node var, CSP csp, Assignment assignment);

}
