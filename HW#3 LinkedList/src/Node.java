/*
 * DO NOT CHANGE THIS CLASS
 */
public class Node {
	public Node next;
	public String data;
	
	/**
	 * Creates a new node from the given String
	 * 
	 * @param data
	 */
	public Node(String data) {
		this.next = null;
		this.data = data;
	}
	
	/**
	 * A helper method to determine if two Nodes
	 *   are equal
	 *   
	 * @param otherNode
	 * @return
	 */
	public boolean equals(Node otherNode) {
		return this.data.equals(otherNode.data);
	}
	
	/**
	 * A simple method for printing out a Node
	 */
	public String toString() {
		return data;
	}
}