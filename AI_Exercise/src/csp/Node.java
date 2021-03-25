package csp;

import java.util.Iterator;
import java.util.LinkedList;

public class Node {

	private final String name;

	private int[] domain;

	private LinkedList<Node> links = new LinkedList<Node>();

	
	public Node(String name, int[] domain) {
		//Constructor
		
		this.name = name;
		
		this.domain = new int[domain.length];
		
		this.setDomain(domain);
	}
	
	
	/////////Getters and Setters/////////
	public String getName() {
		return name;
	}

	public int[] getDomain() {
		return domain;
	}
	
	public void setDomain(int [] domain) {
		for(int i = 0; i<domain.length; i++) {
			this.domain[i]= domain[i];
		}
	}

	public LinkedList<Node> getLinks() {
		return links;
	}
	
	//////////////////////////////////////

	public void addLink(Node node) {
		links.add(node);
	}

	public void removeLink(Node node) {
		links.remove(node);
	}
	
	//////////Operations on domain/////////////

	public int domainElementNumber() {
		//Returns the number of elements, other than 0, present in the domain
		
		int sum = 0;
		for (int i = 0; i < domain.length; i++) {
			if (domain[i] != 0) {
				sum++;
			}
		}
		return sum;

	}

	public void removeElementFromDomain(int val) {
		//Removes the element passed to it in input from the domain, replacing it with a 0
		for (int i = 0; i < domain.length; i++) {
			if (domain[i] == val) {
				domain[i] = 0;
			}
		}

	}
	
	public boolean verifyElementInDomain(int val) {
		//Check if an item is contained within the domain
		for (int i = 0; i < domain.length; i++) {
			if (domain[i] == val) {
				return true;
			}
		}
		
		return false;
		
	}

	public void print() {
		System.out.println("");
		System.out.print("Node: " + name);
		System.out.print(" -->  Links: ");
		Iterator<Node> iterator = links.iterator();

		while (iterator.hasNext()) {
			System.out.print(iterator.next().getName() + " ");
		}

		System.out.println("");
	}

}
