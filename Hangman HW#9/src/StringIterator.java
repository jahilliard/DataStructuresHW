/**
 * 
 * @author Justin Hilliard <jhilliar>
 * @section [A]
 */

// YOU MAY NOT IMPORT ANY ADDITIONAL
// CLASSES OR PACKAGES
import java.util.*;

public class StringIterator implements Iterator<String> {
	private String string;
	private int index;

	/*
	 * Do not change this method
	 */
	public StringIterator(String s) {
		this.string = s;
		this.index = 0;
	}

	/**
	 * Complete the hasNext() method, which returns true if there is
	 * an additional character remaining in the string. This method
	 * should return false if the "pointer" is at the last item index
	 * of the String string.
	 */
	public boolean hasNext() {
		return (string.length() > index);
	}

	/**
	 * Complete the next() method, which returns the character (as a
	 * String) at the current index and then advances the index
	 * appropriately. This method should NOT perform any checks or
	 * validations ensuring the current index is valid. 
	 */
	public String next() {
		String tmp = new String();
		tmp = tmp + string.charAt(index);
		index++;
		return tmp;
	}

	/**
	 * Do not implement this method!
	 */
	public void remove() {
		return; // do not implement this method
	}
}
