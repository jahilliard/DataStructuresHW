/**
 * 
 * @author Justin Hilliard <jhilliar>
 * @section A
 */

// YOU MAY NOT IMPORT ANY ADDITIONAL
// CLASSES OR PACKAGES

import java.util.ArrayList;


public class LinkedList {

	private int numElements;
	public Node head;
	
	public LinkedList() {
		numElements = 0;
		head = null;
	}
	
	/**
	 * The add method takes one String parameter, creates a Node
	 *   from that String, and inserts the Node at the beginning
	 *   of the LinkedList
	 */
	public void add(String newData) {
		// DO NOT CHANGE THIS METHOD
		Node node = new Node(newData);
		node.next = head;
		head = node;
		numElements++;
	}
	
	/**
	 * The size method returns the number of elements in the
	 *   LinkedList in O(1) time
	 */
	public int size() {
		// DO NOT CHANGE THIS METHOD
		return numElements;
	}
	
	/**
	 * The concatenate method combines all the Nodes in the list
	 * 	 into a single String. DO NOT USE A SEPARATOR BETWEEN NODES
	 */
	public String concatenate() {
		// loop over each node in the list and 
		// concatenate into a single string
		String concatenate = "";
		Node nodref = head;
		while (nodref != null){
			String temp = nodref.data;
			concatenate += temp;
			nodref = nodref.next;
		}
		return concatenate;
	}
	
	/**
	 * The insertAfter method searches through the LinkedList looking
	 *   for Nodes whose data (String) matches the findData parameter.
	 *   It then creates a new Node and inserts the newly created node
	 *   AFTER the FIRST occurrence of findData
	 * 
	 * If there are multiple occurrences of findData, ONLY THE CLOSEST
	 *   TO THE FRONT will be used
	 * 
	 * If there are no occurrences of findData, the LinkedList should
	 *   remain unchanged
	 */
	public void insertAfter(String insertData, String findData) {
		// write you code for insertAfter using the specification above
		Node compare = new Node(findData);
		Node nodref = head;
		while (nodref != null){
			if (nodref.equals(compare)){
				Node adder = new Node(insertData);
				adder.next = nodref.next;
				nodref.next = adder; 
				numElements++;
				return;
			}
			nodref = nodref.next;
		}
		return;
	}
	
	/**
	 * Similar to insertAfter, the insertAt method loops through the
	 *   LinkedList to the specified index and inserts AFTER the node
	 *   at that index.
	 * 
	 * If the index is greater than the number of nodes in the list, 
	 *   this method should do nothing
	 * 
	 * You may assume that index is a positive whole number
	 * 
	 * Remember that in Computer Science, we begin counting from 0
	 */
	public void insertAt(String insertData, int index) {
		// write you code for insertAt using the specification above
		if(index==0){
			add(insertData);
			return;
		}
		if(index<=size()){
			Node nodref = head;
			for (int count = 0; count<index-1; count++){
				nodref=nodref.next;
			}
			Node adder = new Node(insertData);
			adder.next = nodref.next;
			nodref.next = adder; 
			numElements++;
			return;
		}
	}
	
	/**
	 * The buildList method constructs a LinkedList from the given
	 *   ArrayList. The LinkedList's data MUST BE IN THE SAME ORDER
	 *   AS THE ORIGINAL ArrayList
	 * 
	 * You must use the add method defined for this Class.
	 */
	public void buildList(ArrayList<String> arrayList) {
		// write your code for buildList using the specification above
		for(int counter=arrayList.size()-1; counter >= 0 ; counter--){
			String temp = arrayList.get(counter);
			add(temp);
		}
	}
	
	/**
	 * The equals method is a very important method in Java. Here, 
	 *   you will be constructing your own equals method. Two
	 *   LinkedLists are considered "equal" if they contain all the
	 *   same nodes, in the same order
	 * 
	 * Method stubs have been provided to help you code this method.
	 *   YOU MUST USE THE ALGORITHMS/PROCESSES DESCRIBED IN THE STUBS
	 *   OR YOU WILL RECEIVE A 0
	 */
	public boolean equals(LinkedList otherList) {
		if (this.size()==otherList.size()){
			Node compthis = this.head;
			Node compother = otherList.head;
			while (compthis!=null){
				if (!compthis.equals(compother)){
					return false;
				}
				compthis = compthis.next;
				compother = compother.next;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Complete the bringToFront method which takes an index as a parameter.
	 *   The Node at that index is moved to the front (made the first item)
	 *   in the LinkedList. The rest of the Nodes should remain in their
	 *   same relative position
	 *   
	 * If the given index is larger than the number of Nodes in the
	 *   LinkedList, this method should do nothing.
	 */
	public void bringToFront(int index) {
		if (index<this.size()){
			Node front = this.head;
			Node oneBack = new Node("");
			for (int counter=0; counter < index; counter++){
				oneBack = front;
				front = front.next;
			}
			oneBack.next=front.next;
			front.next = head;
			head = front;
		}
	}
	
	/**
	 * The makeCircular method points the last item in the LinkedList to
	 *   the HEAD of the LinkedList, making it circular
	 */
	public void makeCircular() {
		// write your code for makeCircular using the specification above
		Node noderef = head; 
		if (this.size()==1){
			noderef.next = head;
			return;
		}
		if(this.size()==0){
			return;
		}
		
		for(int counter=0; counter < this.size()-1; counter++){
			noderef = noderef.next;
		}
		noderef.next = head; 
	}
	
	/**
	 * The removeAll method removes all Nodes from the LinkedList whose 
	 *   String length (data) is exactly equal to the length parameter
	 * 
	 * If more than one occurrence of a String of length exist in the 
	 *   LinkedList, ALL OCCURRENCES SHOULD BE REMOVED
	 * 
	 * If no Strings of length exist, THE LINKED LIST SHOULD REMAIN
	 *   UNCHANGED
	 */
	public void removeAll(int length) {
		// write your code here for removeAll using the specification above
		while(head.data.length()==length){
			head=head.next;
		}
		Node noderef = head.next;
		Node oneBack = head;
		while (oneBack!=null){
			if (noderef.data.length()==length){
				oneBack.next = noderef.next;
				numElements--;
			}
			oneBack = oneBack.next;
			noderef = noderef.next;
		}
	}
	
	/**
	 * The alphabetize method alphabetizes (0-9-A-Z) the order of the Nodes in 
	 *   the LinkedList IN PLACE. YOU MAY NOT CREATE ANY NEW LISTS!
	 */
	public void alphabetize() {
		// write you code for alphabetize using the specifications above
		if (numElements==0){
			return;
		}
		Node nodref = head;
		Node nodref1 = head.next;
		int count = 0;
		boolean x1 = false;
		
		while(nodref!=null){
			nodref1=nodref.next;
			x1 = false;
			while(nodref1!=null){
				int boo = nodref.data.compareTo(nodref1.data);
				System.out.println(this + "\n" + nodref + " " + nodref1+ " " + boo +"\n");
				
				if (boo>0){
					this.add(nodref1.data);
					numElements--;
					Node x = head;
					for(int counter=0; counter<=count; counter++){
						x=x.next;
					}
					nodref.next = nodref.next.next;
					x1 = true;
				}
				nodref1 = nodref1.next;
			}
			System.out.println(count + " " + head + " " + x1);
			count++;
			if(x1!=true){
				nodref = nodref.next;
			}
			else
				nodref = head;
		}
	}
		

		
		
		
	
	
	
	/**
	 * The reverse method reverses the order of all the Nodes in the LinkedList
	 *   IN PLACE! YOU MAY NOT CREATE ANY NEW LISTS!
	 */
	public void reverse() {
		// write you code for reverse using the specifications above		
		Node nodeRef = head;
		head = null;
		
		while(nodeRef != null) {
			Node tmp = nodeRef;
			nodeRef = nodeRef.next;
			tmp.next = head;
			head = tmp;
		}
	}
	
	/**
	 * Pretty print LinkedLists
	 */
	public String toString() {
		// DO NOT MODIFY THIS METHOD
		StringBuffer sb = new StringBuffer();
		
		if(head == null) {
			sb.append("[HEAD] -> null -> [TAIL]");
		} else {
			Node nodeRef = head;
			sb.append("[HEAD] -> ");
			while(nodeRef != null) {
				sb.append(nodeRef.data);
				sb.append(" -> ");
				nodeRef = nodeRef.next;
			}
			sb.append("[TAIL]");
		}
		
		return sb.toString();
	}

public static void main(String [ ] args){
	int i =(int) Math.random();	
	System.out.print(i);
}

}
