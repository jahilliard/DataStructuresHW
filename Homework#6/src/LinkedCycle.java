/**
 * 
 * @author Justin Hilliard <jhilliar>
 * @section A
 */

import java.util.*;

public class LinkedCycle<E> {
	// DO NOT CHANGE THESE FIELDS
	public Node<E> currNode;
	public int numElements;
	
	public LinkedCycle() {
		currNode = null;
		numElements = 0;
	}

	public int size() {
		return numElements;
	}

	public E get() {
		if (numElements == 0){
			throw new NoSuchElementException();
		}else{
			return currNode.data;
		}
	}

	public void add(E item) {
		if(numElements==0){
			Node<E> ad = new Node<E>(item);
			currNode = ad;
			currNode.next = currNode;
			currNode.prev = currNode;
			numElements++;
		}else{
			Node<E> ad = new Node<E>(item);
			Node<E> temp = currNode.next;
			currNode.next = ad;
			ad.next = temp;
			temp.prev = ad;
			ad.prev = currNode;
			numElements++;
		}	
	}

	public List<E> toList() {
		List<E> info = new ArrayList<E>(); 
		int x=0;
		while (x<numElements){
			currNode=currNode.next;
			info.add(currNode.data);
			x++;
		}
		return info;
	}

	public boolean contains(E item) {
		for (int i=0; i<numElements;i++){
			if (currNode.data.equals(item)){
				return true;
			}
			currNode=currNode.next;
		}
		return false;
	}

	public void scroll(Integer n) {
		if (numElements == 0){
			throw new NoSuchElementException();
		}
		if (n>0 && numElements!=1){
			for (int i=0; i<n; i++){
				currNode = currNode.next;
			}
		}else if (n<0 && numElements!=1){
			n=(-n);
			for (int i=0; i<n; i++){
				currNode = currNode.prev;
			}
		}
	}

	public E remove() {
		if (numElements == 0){
			throw new NoSuchElementException();
		}else if (numElements == 1){
			E temp = currNode.data;
			currNode.next = null;
			currNode.prev = null;
			numElements--;
			return temp;
		}
		E temp = currNode.data;
		Node<E> b4 = currNode.prev;
		Node<E> aft = currNode.next;
		currNode= currNode.prev;
		b4.next = aft;
		aft.prev= b4;
		numElements--;
		return temp;
		
	}
}