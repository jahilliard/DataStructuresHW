/**
 * 
 * @author Justin Hilliard <jhilliar>
 * @section [A]
 * 
 */

public class StackQueueSolver {

	public static int lastCustomer(int numPersons, int numToBack) {
		ArrayQueue<Integer> line = new ArrayQueue<Integer>();
		for (Integer i = 1; i <= numPersons; i++){
			line.enqueue(i);
		}
		int count = 0;
		while (count < numPersons-1){
			for (Integer i = 1; i <= numToBack; i++){
				int temp = line.dequeue();
				line.enqueue(temp);
			}
			count++;
			line.dequeue();
		}
		return line.dequeue();
	}

	// Runtime O(n)
	public static boolean areEqual(MyStack<String> stack1, MyStack<String> stack2) {
		ArrayStack<String> aux = new ArrayStack<String>();
		while (!stack1.isEmpty() && !stack2.isEmpty()){
			if (!stack1.peek().equals(stack2.peek()))
				return false;
			else{
				aux.push(stack1.pop());
				aux.push(stack2.pop());
			}
		}
		while (!aux.isEmpty()){
			stack2.push(aux.pop());
			stack1.push(aux.pop());
		}
		return true;
	}

	// Runtime O(n)
	public static MyStack<Integer> duplicateStack(MyStack<Integer> original) {
		MyQueue<Integer> aux = new ArrayQueue<Integer>();
		MyStack<Integer> dup = new ArrayStack<Integer>();
		while (!original.isEmpty()){
			dup.push(original.peek());
			aux.enqueue(original.pop());
		}
		while (!aux.isEmpty()){
			original.push(aux.dequeue());
		}
		return dup;
	}

}