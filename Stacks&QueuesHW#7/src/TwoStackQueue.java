/**
 * 
 * @author Justin Hilliard <jhilliar>
 * @section [A]
 */

import java.util.*;

public class TwoStackQueue<E> implements MyQueue<E> {

	private MyStack<E> out;
	private MyStack<E> in;

	public TwoStackQueue() {
		in = new ArrayStack<E>();
		out = new ArrayStack<E>();
	}

	/**
	 * Returns true if this queue no elements.
	 * 
	 * @return true if this queue is empty, false otherwise.
	 * 
	 */
	public boolean isEmpty() {
		return (in.isEmpty() && out.isEmpty());
	}

	/**
	 * Adds the specified element to the end of the queue.
	 * 
	 * @param The
	 *          element to add on to the end of the queue.
	 *          
	 */
	public void enqueue(E element) {
		in.push(element);
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
		if (in.isEmpty() && out.isEmpty()) throw new NoSuchElementException();
		else{
			if (out.isEmpty() == false){
				return out.pop();
			}
			while (in.isEmpty() == false)
				out.push(in.pop());
			E temp = out.pop();
			return temp;
		}
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
		if (in.isEmpty() && out.isEmpty()) throw new NoSuchElementException();
		else{
			if (out.isEmpty() == false){
				return out.peek();
			}
			while (in.isEmpty() == false)
				out.push(in.pop());
			E temp = out.peek();
			return temp;
		}
	}

	/**
	 * Returns a String representation of this queue. If the queue will dequeue
	 * values 5 7 8 in that order, and the out stack contains one value, the
	 * string will have following format.
	 * 
	 * front [ 5 | 7 8 ] back
	 * 
	 */
	public String toString() {
		ArrayStack<E> temp = new ArrayStack<E>();
		String front = "" ;
		String back = "" ;
		while (!in.isEmpty()){
			temp.push(in.peek());
			front = front + in.pop().toString() + " ";
		}
		while (!temp.isEmpty()){
			in.push(temp.pop());
		}
		while (!out.isEmpty()){
			temp.push(out.peek());
			back = back + out.pop().toString() + " ";
		}
		while (!temp.isEmpty()){
			out.push(temp.pop());
		}
		return "front [ " + front + "| " + back + "] back";
			
	}

}
