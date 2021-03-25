package csp.heuristic.orderDomain;

import csp.Assignment;
import csp.CSP;
import csp.Node;

public class LeastConstrainingValue implements OrderDomainValue {
	//Heuristic that orders the domain by the value that appears several times in the assignment to the one that appears the least number of
	
	@Override
	public int[] OrderDomain(Node var, CSP csp, Assignment assignment) {
		//Returns the domain ordered according to the heuristics described above
		int size = var.getDomain().length;
		int[] numberVal = new int[size];
		int[] domain = var.getDomain();
		for (int i = 0; i < size; i++) { //Create an array containing the number of times the value appear in the assignment
			if (domain[i] != 0) {

				for (int j = 0; j < assignment.getValues().length; j++) {
					if (assignment.getValues()[j] == domain[i]) {
						numberVal[i] = numberVal[i] + 1;
					}
				}

			} else {

				numberVal[i] = -1;

			}

		}

		return sortValues(numberVal, domain);
	}

	public int[] sortValues(int[] numberVal, int[] domain) {
		//Having as input the number of times the elements in an assignment appear, it returns the ordered domain according to the heuristics described above
		int[] orderedDomain = new int[numberVal.length];

		int max = 0;

		int maxPosition = -1;

		for (int i = 0; i < numberVal.length; i++) {

			for (int j = 0; j < numberVal.length; j++) {

				if (max <= numberVal[j]) {

					max = numberVal[j];
					maxPosition = j;

				}

			}

			if (maxPosition != -1) {
				numberVal[maxPosition] = -1;
				orderedDomain[i] = domain[maxPosition];
				max = 0;
				maxPosition = -1;
			} else {
				return orderedDomain;
			}

		}

		return orderedDomain;

	}

}
