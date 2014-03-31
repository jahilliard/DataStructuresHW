/**
 *
 * @author Justin Hilliard <jhilliar>
 * @section A
 */

// YOU MAY NOT IMPORT ANY ADDITIONAL
// CLASSES OR PACKAGES
import java.util.ArrayList;

public class Recursion {

  /**
   * The count method searches through each Node in the LinkedList
   *   and returns the number of Nodes whose data equals the findData
   *   parameter. If there are no matches, this method should just
   *   return 0
   *
   *   THIS METHOD MUST BE RECURSIVE
   *
   * @param head - reference to the front Node of the LinkedList
   * @param findData - the data we are searching for in a node
   * @return - the number of Nodes that match findData
   */
  public static int count(Node head, String findData) {
	  if (head == null) {
		return 0;
	  }
	  if (head.data.equalsIgnoreCase(findData)){
		return 1 + count(head.next,findData);
	  }else{
		return 0 + count(head.next,findData);
	  }
  }

  /**
   * The isReverse method takes two String parameters and determines if
   *   string1 is the reverse of string2. string1 is the reverse of string2
   *   if every character is in the opposite position, mirrored across the
   *   center of the string. Here are some examples:
   *
   *   string1 = "foo";
   *   string2 = "oof";
   *   isReverse(string1, string2); // true
   *
   *   string1 = "cookie";
   *   string2 = "eikooc"
   *   isReverse(string1, string2); // true
   *
   *   string1 = "abc";
   *   string2 = "def";
   *   isReverse(string1, string2); // false
   *
   *   string1 = "";
   *   string2 = "";
   *   isReverse(string1, string2); // true
   *
   *   THIS METHOD MUST BE RECURSIVE
   *
   * @param string1 - the first string to compare
   * @param string2 - the second string to compare
   * @return - true if string1 is th reverse of string2, false otherwise
   */
  public static boolean isReverse(String string1, String string2) { 
	  if(string1.length() != string2.length()) return false;
	  if(string1.length() == 0) return true;
	  
	  char char1 = string2.charAt(string2.length()-1);
	  char char2 = string1.charAt(0);

	  if(char1 != char2) return false;
	  return isReverse(string1.substring(1), string2.substring(0, string2.length()-1));
  }

  public static int countStrings(Integer n) {
	if (n==1){
		return 3;
	}else if (n==2){
		return 8;
	}else if (n>=3) {
		return 2*(countStrings(n-1)) + 2*(countStrings(n-2));
	}
    return 0;
  }

  /**
   * The insertAfter method recursively searches the LinkedList looking
   *   for Nodes whose data (String) matches the findData parameter.
   *   It then creates a new Node and inserts the newly created node
   *   AFTER the FIRST occurrence of findData
   *
   * If there are multiple occurrences of findData, ONLY THE CLOSEST
   *   TO THE FRONT will be used
   *
   * If there are no occurrences of findData, the LinkedList should
   *   remain unchanged
   *
   *   THIS METHOD MUST BE RECURSIVE
   *
   * @param head - reference to the first Node of the LinkedList
   * @param findData - the data we are searching for
   * @param newData - the data we are inserting
   */
  public static void insertAfter(Node head, String insertData, String findData) {
	  if (head.data.equals(findData)){
		  Node y = new Node(insertData);
		  y.next = head.next;
		  head.next = y;
		  return;
	  }else if(head!=null){
		  insertAfter(head.next, insertData, findData);
	  }
	  return;
  }

  /**
   * The itAddsUp method returns true if all the elements in the ArrayList
   *  list add up (sum) to the specified target
   */
  public static boolean itAddsUp(ArrayList<Integer> list, Integer target) {
	  if(list.size() == 0) return target == 0;
	  int x = list.get(0);
	  ArrayList<Integer> tmp = new ArrayList<Integer>(list.subList(1, list.size()));
	  return itAddsUp(tmp, target-x);
  }

  /**
   * The removeDuplicates method removes all consecutive, duplicate
   *   characters from string. Here are some examples:
   *
   *   removeDuplicates("pizza"); // returns "piza"
   *   removeDuplicates("shell"); // returns "shel"
   *   removeDuplicates("mississippi"); // returns "misisipi"
   *   removeDuplicates("boooooooooooooooooo"); // returns "bo"
   *   removeDuplicates("desk"); // returns "desk"
   *
   *   THIS METHOD MUST BE RECURSIVE
   *
   * @param string
   * @return
   */
  public static String removeDuplicates(String string) {
	  if(string.length() < 2) return string;
	  
	  char x = string.charAt(0);
	  char y = string.charAt(1);
	  
	  if(x == y){
		  String substr = string.substring(1, string.length());
		  return removeDuplicates(substr);
	  } else {
		  String substr = string.substring(1, string.length());
		  return "" + x + removeDuplicates(substr);
	  }
  }

  /**
   * This method returns a String with all the numbers from 1 to n,
   *   separated by dashes("-"). The string must start with the values
   *   of the even integers in descending order first and then follow
   *   with the odd integers in ascending order. This method does not
   *   print anything at all. Assume n is positive. You may NOT use helper
   *   methods to solve this problem; write a single method to solve this
   *   problem. Here are some examples:
   *
   *   stringNumbers(4); // returns "4-2-1-3"
   *   stringNumbers(1); // returns "1"
   *   stringNumbers(7); // returns "6-4-2-1-3-5-7"
   *
   *   THIS METHOD MUST BE RECURSIVE
   */
  public static String stringNumbers(Integer n) {
	  if(n == 0) return "";
	  if(n == 1) return "1";
	  if(n%2 == 0){
		  return n + "-" + stringNumbers(n-1);
	  } else {
		  return stringNumbers(n-1) + "-" + n;
	  }
  }

  /**
   * For every consecutive pair of strings the method removes the longer
   *   of the two from the list. If there is a tie (strings with the same
   *   length), it removes the first of the pair. If there are an odd number
   *   of strings in the list, the final value remains in the list. You may
   *   not create new ArrayLists or arrays to solve this problem. Here are
   *   some examples:
   *
   *   ["foo", "zaps", "caps", "place"] -> ["foo", "caps"]
   *   ["fad", "fod", "zip", "zap"] -> ["fod", "zap"]
   *   ["foo"] -> ["foo"]
   *   ["foo", "caps", "fact"] -> ["foo", "fact"]
   *
   * Recall that ArrayLists are passed by reference.
   *
   *   THIS METHOD MUST BE RECURSIVE
   *
   * @param list
   */
  public static void removeLongerOfPairs(ArrayList<String> list) {
	  removeLongerOfPairsHelper(list, 0);
  }
  
  private static void removeLongerOfPairsHelper(ArrayList<String> list, int index) {
	  if(list.size() < 2 || index + 1 > list.size()-1) return;
	  if(list.get(index).length() >= list.get(index+1).length()) {
		  list.remove(index);
	  } else {
		  list.remove(index+1);
	  }
	  removeLongerOfPairsHelper(list, index+1);
  }

  /**
   * This method recursively removes all Nodes from a LinkedList whose data
   *   is exactly equal to the length parameter. All other Nodes should remain
   *   in their same relative positions.
   *
   * If there are no Nodes with data of length() length, this method should
   *   leave the list completely unchanged
   *
   *   THIS METHOD MUST BE RECURSIVE
   *
   * @param head
   * @param length
   * @return
   */
  public static Node removeAll(Node head, Integer length) {
	  if (head == null){
		  return null;
	  }else if (head.data.length()==length){
		  head.next = removeAll(head.next, length);
		  head = head.next;
		  return head;
	  }else{
		  head.next = removeAll(head.next, length);
		  return head;
	  }
  }
  
}