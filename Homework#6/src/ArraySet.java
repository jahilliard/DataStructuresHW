/**
 * 
 * @author Justin Hilliard <jhilliar>
 * @section A
 */

public class ArraySet<E extends Comparable<? super E>> {
	// leave these fields public so that we can test your code
	public E[] data;
	public int numElements;

	/**
	 * The constructor creates a new Generic Array and ensures each
	 * Object in the array is of the same type.
	 */
	@SuppressWarnings("unchecked")
	public ArraySet() {
		data = (E[])new Comparable<?>[2];
		numElements = 0;
	}
	

	/**
	 * This method returns the total number of elements that are 
	 * current in the ArraySet.
	 */
	public int size() {
		return numElements;
	}

	/**
	 * This method adds an object to the ArraySet if and only if 
	 * the item is not already contained in the set. If necessary, this 
	 * method will (delegate) the doubling of the size of the array.
	 * 
	 * When inserting, all values must be inserted in ascending order
	 * 
	 * If adding was successful, this method should return true. If not, 
	 * or if the item was already in the list, this method should return false.
	 */
	public boolean add(E item) {		
		if (this.contains(item)){
			return false;
		}
		
		if (data.length == numElements){
			doubleArray();
		}
		
		if (numElements == 0){
			data[0] = item;
			numElements++;
			return true;
		}
		
		int index = 0;
		while(data[index] != null && item.compareTo(data[index]) > 0) {
			index++;
		}
		
		for(int i = numElements; i > index; i--) {
			data[i] = data[i-1];
		}
		data[index] = item;
		numElements++;
		return true;
	}

	/**
	 * The contains method determines if a given value exists in the ArraySet. 
	 * It must run in O(log n) time.
	 */
	public boolean contains(E item) {
		return binarySearch(item) != -1;
	}

	/**
	 * Return the index of the search item in O(log n) time 
	 * @param item - the search item
	 * @return the index of the search item, or -1 if not found
	 */
	private int binarySearch(E item) {
		int low, high, middle;
		low = 0;
		high = numElements - 1;
		middle = 0;
		while(low <= high){
			middle = (low + high) / 2;
			if(item.equals(data[middle])){
				return middle;
			}
			else if (item.compareTo(data[middle]) < 0){
				high = middle - 1;
			}
			else
			{
				low = middle + 1;
			}

		}
		
		return -1; 
	}
	
	/**
	 * The remove method behaves opposite of the add method. It will remove a value 
	 * from the ArraySet if and only if the value exists in the ArraySet. 
	 * 
	 * This method should NOT decrease the size of the Array!
	 * 
	 * If removal was successful, this method should return true. Otherwise, false.
	 */
	public boolean remove(E item) {
		if (!this.contains(item)){
			return false;
		}
		for(int i = 0; i < numElements; i++){
			if (data[i].equals(item)){ 
				int x = i;
				while (x <= numElements-2){
			      data[x] = data[x+1];
			      x++;
			    }
			}
		}
		numElements--;
		return true; 
	}

	// DO NOT TOUCH THIS METHOD
	// THIS IS PROVIDED FOR YOUR DEBUGGING
	public String toString() {
		String s = "[";
		for (int i = 0; i < numElements - 1; i++)
			s += data[i] + ", ";
		if (numElements > 0)
			s += data[numElements - 1];
		return s + "]";
	}
	
	/**
	 * This method doubles the size of the array. You should 
	 * consider using it in your code...
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void doubleArray() {
		E[] bigger = (E[])new Comparable<?>[2 * data.length];
		for(int i = 0; i < data.length; i++)
			bigger[i] = data[i];
		data = bigger;
	}
}