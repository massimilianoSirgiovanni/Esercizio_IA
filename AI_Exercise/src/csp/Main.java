package csp;

import csp.backtrack.Backtracking_Search_for_CSP;
import csp.heuristic.orderDomain.LeastConstrainingValue;
import csp.heuristic.orderDomain.OrderDomainValue;
import csp.heuristic.orderDomain.SameOrderDomain;
import csp.heuristic.selectVariables.FIFOVariable;
import csp.heuristic.selectVariables.MoreConstrainedVariables;
import csp.heuristic.selectVariables.SelectVariable;
import csp.heuristic.selectVariables.VarRandom;
import csp.min_conflicts.Min_Conflicts;

public class Main {

	public static void main(String[] args) {

		int[] domain = { 1, 2, 3 };

		//CREATION OF THE CSP
		
		Node WA = new Node("WA", domain);
		Node NT = new Node("NT", domain);
		Node Q = new Node("Q", domain);
		Node SA = new Node("SA", domain);
		Node NSW = new Node("NSW", domain);
		Node V = new Node("V", domain);
		Node T = new Node("T", domain);

		Graph map = new Graph();
		map.addNode(WA);
		map.addNode(NT);
		map.addNode(Q);
		map.addNode(SA);
		map.addNode(NSW);
		map.addNode(V);
		map.addNode(T);

		map.addLink(WA, SA);
		map.addLink(WA, NT);
		map.addLink(NT, SA);
		map.addLink(Q, SA);
		map.addLink(NSW, SA);
		map.addLink(V, SA);
		map.addLink(V, NSW);
		map.addLink(NSW, Q);
		map.addLink(NT, Q);

		map.print();

		CSP csp = new CSP(map, domain);

		System.out.println("");
		
		/////////////////////////////////////////////////////////////////////////////////

		SelectVariable fifo = new FIFOVariable();

		OrderDomainValue orderDomain = new SameOrderDomain();

		Backtracking_Search_for_CSP backtracking = new Backtracking_Search_for_CSP();

		long startTime = System.currentTimeMillis();

		Assignment assignment = backtracking.BacktrackingSearch(csp, fifo, orderDomain);

		long endTime = System.currentTimeMillis();

		long duration = (endTime - startTime);

		System.out.println("------------BACKTRACKING ALGORITHM----------------");

		System.out.println("");

		if (assignment == null) {
			System.out.println("NO SOLUTION WAS FOUND!");
		} else {
			assignment.print();
		}
		
		

		System.out.println("");

		System.out.println("Execution time: " + duration + " milliseconds");

		System.out.println("");

		System.out.println("--------------------------------------------------");

		System.out.println("");
		
		SelectVariable randVar = new VarRandom();

		long startTimeRand = System.currentTimeMillis();

		Assignment assignmentRand = backtracking.BacktrackingSearch(csp, randVar, orderDomain);

		long endTimeRand = System.currentTimeMillis();

		long durationRand = (endTimeRand - startTimeRand);

		System.out.println("------------BACKTRACKING ALGORITHM (RANDOM)----------------");

		System.out.println("");

		if (assignmentRand == null) {
			System.out.println("NO SOLUTION WAS FOUND!");
		} else {
			assignmentRand.print();
		}
		
		

		System.out.println("");

		System.out.println("Execution time: " + durationRand + " milliseconds");

		System.out.println("");

		System.out.println("--------------------------------------------------");

		System.out.println("");

		csp.reinitializesNodeDomain();

		SelectVariable moreConstrained = new MoreConstrainedVariables();

		OrderDomainValue LeastConstrVal = new LeastConstrainingValue();

		long startTimeBetterHeuristics = System.currentTimeMillis();

		Assignment assign = backtracking.BacktrackingSearch(csp, moreConstrained, LeastConstrVal);

		long endTimeBetterHeuristics = System.currentTimeMillis();

		long durationBetterHeuristics = (endTimeBetterHeuristics - startTimeBetterHeuristics);

		System.out.println("------------BACKTRACKING ALGORITHM (Better Heuristics)----------------");

		System.out.println("");

		if (assign == null) {
			System.out.println("NO SOLUTION WAS FOUND!");
		} else {
			assign.print();
		}

		System.out.println("");

		System.out.println("Execution time: " + durationBetterHeuristics + " milliseconds");

		System.out.println("");

		System.out.println("--------------------------------------------------");

		System.out.println("");

		csp.reinitializesNodeDomain();

		System.out.println("--------------MIN CONFLICTS------------------");

		System.out.println("");

		Min_Conflicts minConflicts = new Min_Conflicts();

		long startTimeMinC = System.currentTimeMillis();

		Assignment assignmentMinC = minConflicts.minConflicts(csp, 100, randVar);

		long endTimeMinC = System.currentTimeMillis();

		long durationMinC = (endTimeMinC - startTimeMinC);

		if (assignmentMinC == null) {
			System.out.println("NO SOLUTION WAS FOUND!");
		} else {
			assignmentMinC.print();
		}

		System.out.println("");

		System.out.println("Execution time: " + durationMinC + " milliseconds");

		System.out.println("");

		System.out.println("--------------------------------------------------");


	}

}
