/**
 *    
 * @author Justin Hilliard <jhilliar>
 * @section A
 */

// YOU MAY NOT IMPORT ANY ADDITIONAL
// CLASSES OR PACKAGES
import java.util.*;

public class BinaryTrees {
  public static boolean allDecreasing(TreeNode<Integer> t){
	  if (t.left == null && t.right == null){
		  return true;
	  } else if (t.left == null && t.right != null){
		  if (t.data >= t.right.data)
			  return allDecreasing(t.right);
		  else return false;
	  } else if (t.right == null && t.left != null){
		  if (t.data >= t.left.data)
			  return allDecreasing(t.left);
		  else return false;
	  } else{
		  if (t.data >= t.right.data && t.data >= t.left.data)
			  return allDecreasing(t.right) && allDecreasing(t.left);
		  else return false;
	  }
  }

  public static int countLeavesAtLevel(TreeNode<Integer> t, int level){
	  if(t == null) return 0;
	  
	  if(level == 0) {
		  if(t.right == null && t.left == null) return 1;
		  return 0;
	  }
	  
	  return countLeavesAtLevel(t.left, level-1) + countLeavesAtLevel(t.right, level-1);
	  
  }

  public static List<Integer> interiorNodesList(TreeNode<Integer> t){
	  List<Integer> list = new ArrayList<Integer>();
	  if (t.left != null || t.right != null){
		  list.add(t.data);
	  }
	  if (t.left != null)
		  list.addAll(interiorNodesList(t.left));
	  if (t.right != null)
		  list.addAll(interiorNodesList(t.right));
	  return list;
  }	

  public static boolean isPerfect(TreeNode<Integer> t){
	  if (t.right == null && t.left != null){
		  return false;
	  }
	  else if (t.right != null && t.left == null){
		  return false;
	  }
	  else if (t.right != null && t.left != null){
		 return isPerfect(t.right) && isPerfect(t.left);
	  }
	  return true;
  }

  public static boolean isSubset(TreeNode<String> t1, TreeNode<String> t2){
	  List<String> t1data = new ArrayList<String>();
	  List<String> t2data = new ArrayList<String>();
	  t1data = SubsetHelper(t1);
	  t2data = SubsetHelper(t2);
	  if (t2data.containsAll(t1data)) return true;
	  else return false;
	
  }
  
  private static List<String> SubsetHelper (TreeNode<String> t){
	  ArrayList<String> tdata = new ArrayList<String>();
	  tdata.add(t.data);
	  if (t.left != null){  
		  tdata.addAll(SubsetHelper(t.left));
	  }
	  if (t.right != null){
		  tdata.addAll(SubsetHelper(t.right));
	  }
	  return tdata;
  }

  public static TreeNode<Integer> removeSomeParents(TreeNode<Integer> t){
	  if(t==null){
		  return t;
	  }
	  TreeNode<Integer> modify = CopyTree(t);
	  TreeNode<Integer> tmp = removeSomeParentsHelper(modify);
	  return tmp;
	  
  }	
  
  public static TreeNode<Integer> removeSomeParentsHelper(TreeNode<Integer> t){
	  if (t.right == null && t.left != null){
		  t = t.left;
		  return removeSomeParentsHelper(t);
	  }
	  if (t.right != null && t.left == null){
		  t = t.right;
		  return removeSomeParentsHelper(t);
	  }
	  if (t.right != null && t.left != null){
		  t.left = removeSomeParentsHelper(t.left);
		  t.right = removeSomeParentsHelper(t.right);
	  }
	  return t;
  }
  
  public static TreeNode<Integer> CopyTree(TreeNode<Integer> t){
	  if(t != null){
		  TreeNode<Integer> copy = new TreeNode<Integer>(t.data);
		  if (t.right == null && t.left != null){
			  copy.left = CopyTree(t.left);
		  }
		  if (t.right != null && t.left == null){
			  copy.right = CopyTree(t.right);
		  }
		  if (t.right != null && t.left != null){
			  copy.right = CopyTree(t.right);
			  copy.left = CopyTree(t.left);
		  }
		  return copy;
	  }
	  return t;
	  
  }
  
  public static TreeNode<String> CopyTreeString(TreeNode<String> t){
	  TreeNode<String> copy = new TreeNode<String>(t.data);
	  if (t.right == null && t.left != null){
		  copy.left = CopyTreeString(t.left);
	  }
	  if (t.right != null && t.left == null){
		  copy.right = CopyTreeString(t.right);
	  }
	  if (t.right != null && t.left != null){
		  copy.right = CopyTreeString(t.right);
		  copy.left = CopyTreeString(t.left);
	  }
	  return copy;
  }
  
  public static TreeNode<String> insertAfter(TreeNode<String> t, String target, String newString){
	  if (t.data.equals(target)){
		  if (t.right == null && t.left != null){
			  t.right = new TreeNode<String> (newString);
			  return t;
		  }if (t.right != null && t.left != null){
			  return t;
		  }else {
			  t.left = new TreeNode<String> (newString);
			  return t;
		  }
	  }
	  else
		  if (t.right == null && t.left != null){
			  t.left = insertAfter(t.left, target, newString);
		  }
		  if (t.right != null && t.left == null){
			  t.right = insertAfter(t.right, target, newString);
		  }
		  if (t.right != null && t.left != null){
			  t.left = insertAfter(t.left, target, newString);
			  t.right =insertAfter(t.right, target, newString);
		  }
		  return t;
  }	

  public static boolean areEqual(TreeNode<String> t1, TreeNode<String> t2){
	  if (t1 == null && t2==null){
		  return true;
	  }if (t2==null){
		  return false;
	  }if (t1 == null){
		  return false;
	  }if (!t1.data.equals(t2.data)){
		  return false;
	  }else{
		 return areEqual(t1.left, t2.left) && areEqual(t1.right, t2.right);
	  }
  }

  public static TreeNode<String> createTree(int n, String s){
    if (n<1) return null;
    if (n==1) {
    	TreeNode<String> t = new TreeNode<String>(s);
    	return t;
    }else{
    	int count = n-1;
    	TreeNode<String> top = new TreeNode<String>(s);
    	TreeNode<String> mod = top;
    	while (count > 0){
    		int i = (int)(100*Math.random());
    		if (i<33 && count > 1){
    			mod.right = new TreeNode<String>(s);
    			mod.left = new TreeNode<String>(s);
    			int x = (int)(100*Math.random());
    			if (x>50)mod=mod.left;
    			else mod=mod.right;
    			count-=2;
    		}else if(i<66){
    			mod.left = new TreeNode<String>(s);
    			mod = mod.left;
    			count--;
    		}else{
    			mod.right = new TreeNode<String>(s);
    			mod = mod.right;
    			count--;
    		}
    	}
    	return top;

    }
  }	
  
  public static TreeNode<Integer> mirror(TreeNode<Integer> t){
    if (t.left==null){
    	t.right=null;
    	return t;
    }else {
    	t.right = copyLeft(t.left);
    	return t;
    }
  }
  
  public static TreeNode<Integer> copyLeft (TreeNode<Integer> t){
	  TreeNode<Integer> copy = new TreeNode<Integer>(t.data);
	  if (t.right == null && t.left != null){
		  copy.left = CopyTree(t.right);
	  }
	  if (t.right != null && t.left == null){
		  copy.right = CopyTree(t.left);
	  }
	  if (t.right != null && t.left != null){
		  copy.right = CopyTree(t.left);
		  copy.left = CopyTree(t.right);
	  }
	  return copy;
  }

}