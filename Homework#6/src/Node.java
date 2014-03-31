/*
 * DO NOT CHANGE THIS CLASS
 */
public class Node<E> {
	public Node<E> prev;
	public E data;
	public Node<E> next;

	public Node(E data) {
		this.data = data;
	}
	
	public boolean equals(Node<E> otherNode) {
		return this.data.equals(otherNode.data);
	}
}