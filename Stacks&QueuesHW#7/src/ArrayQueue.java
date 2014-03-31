/**
 * 
 * @author Justin Hilliard <jhilliar>
 * @section [A]
 */

import java.util.*;

public class ArrayQueue<E> implements MyQueue<E> {

	private E[] dataArray;
	private int front; //index of first item to remove
	private int back;  //index of last item to remove
	private int numOfElements; // for convenience

	public ArrayQueue() {
		dataArray = (E[]) new Object[10];
		numOfElements = 0;
		front = -1;
		back = -1;
	}

	/**
	 * Returns true if this queue no elements.
	 * 
	 * @return true if this queue is empty, false otherwise.
	 * 
	 */
	public boolean isEmpty() {
		if (numOfElements==0)
			return true;
		return false;
	}
	
	

	/**
	 * Returns, but does not remove, the element at the front of this queue.
	 * 
	 * @return The element at the front of this queue.
	 * @throws NoSuchElementException
	 *           if the queue is empty.
	 *           
	 */
	public E peek() {
		if (numOfElements==0)
			throw new NoSuchElementException();
		else 
			return dataArray[front];
	}
	
	/**
	 * Adds the specified element to the back of this queue.
	 * 
	 * @param element
	 *          to add on to the back of this queue.
	 *          
	 */
	public void enqueue(E element) {
		if (numOfElements==0){
			front++;
			back++;
			dataArray[((back)%dataArray.length)] = element;
			numOfElements=1;
		}else {
			if (numOfElements==dataArray.length){
				E[] bigger = (E[])new Object[2 * dataArray.length];
				for(int i = 0; i < dataArray.length; i++)
					bigger[i] = dataArray[i%dataArray.length];
				dataArray = bigger;
				front = 0;
				back = numOfElements-1;
			}
			back = (back+1) % dataArray.length;
			dataArray[back] = element;
			numOfElements++;
		}
	}
	
	

	/**
	 * Removes and returns the element at the front of this queue.
	 * 
	 * @return The element removed from the front of this queue.
	 * @throws NoSuchElementException
	 *           if the queue is empty.
	 *           
	 */
	public E dequeue() {
		if (numOfElements==0)
			throw new NoSuchElementException();
		else{
			E temp = dataArray[front];
			dataArray[front] = null;
			front = (front+1) % dataArray.length;
			numOfElements--;
			return temp;	
		}
	}


	/**
	 * Returns a String representation of this queue in the format described in
	 * the writeup 
	 */
	public String toString() {
		String frontostring = ("front: " + front + " ");
		String backtostring = ("back: " + back + "\n");
		String beg = ("front [");
		String end = (" ] back");
		String elements = ("");
		if (front < back){
			for (int i = front; i <= back; i++){
				elements = elements + (" ") + dataArray[i].toString();
			}
		}
		if (front > back){
			for (int i = front; i < numOfElements; i++){
				elements = elements + (" ") + dataArray[i].toString();
			}
			for (int i = 0; i <= back; i++){
				elements = elements + (" ") + dataArray[i].toString();
			}
		}
		String Total = frontostring + backtostring + beg + elements + end;
		return Total;
	}

}
