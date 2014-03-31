/**
 * 
 * @author Justin Hilliard <jhilliar>
 * @section [A]
 */

import java.util.*;

public class ArrayStack<E> implements MyStack<E> {

	private E[] dataArray;
	private int top;

	/**
	 * Creates an empty stack.
	 * 
	 */
	public ArrayStack() {
		dataArray = (E[]) new Object[10];
		top = -1;
	}

	/**
	 * Determines if the stack is empty or not.
	 * 
	 * @return true if the stack is empty or false otherwise
	 * 
	 */
	public boolean isEmpty() {
		if (top == -1)
			return true;
		else
			return false;
	}

	/**
	 * Returns but does not remove the top element of the stack if the stack is
	 * not empty.
	 * 
	 * @return The top element of the stack
	 * @throws NoSuchElementException
	 *             if the stack is empty
	 * 
	 */
	public E peek() {
		if (top == -1)
			throw new NoSuchElementException();
		else
			return dataArray[top];

	}

	/**
	 * Pushes the given element on this stack
	 * 
	 * @param element
	 *            The element of type E to push on the stack.
	 * 
	 */
	public void push(E element) {
		if (top + 1 == dataArray.length) {
			E[] bigger = (E[]) new Object[2 * dataArray.length];
			for (int i = 0; i < dataArray.length; i++)
				bigger[i] = dataArray[i];
			dataArray = bigger;
		}
		top++;
		dataArray[top] = element;
	}

	/**
	 * Returns and removes the top element of the stack if the stack is not
	 * empty.
	 * 
	 * @return The top element of the stack
	 * @throws NoSuchElementException
	 *             if the stack is empty
	 * 
	 */
	public E pop() {
		if (top == -1)
			throw new NoSuchElementException();
		E begElement = dataArray[top];
		dataArray[top] = null;
		top--;
		return begElement;
	}

	/**
	 * Returns a String representation of the stack in the following format top
	 * [ 3 5 ] bottom
	 * 
	 */
	public String toString() {
		String beg = ("top [");
		String back = (" ] bottom");
		String elements = ("");
		for (int i = top; i >= 0; i--) {
			elements = elements + (" ") + dataArray[i].toString();
		}
		String Total = beg + elements + back;
		return Total;

	}

}
