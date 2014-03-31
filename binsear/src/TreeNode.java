/**
 * 	15-121 Homework #10   Spring 2011	
 * 
 * 	TreeNode<E> class, used by TreeDisplay class
 * 	Do not modify this class. You can ignore all of the code below.
 * 	Additionally, DO NOT USE THIS CLASS IN YOUR SOLUTION TO THIS HOMEWORK!
 */

public class TreeNode<E>
{
	public E data;
	public TreeNode<E> left;
	public TreeNode<E> right;

	public TreeNode(E data)
	{
		this.data = data;
		left = null;
		right = null;
	}

	public TreeNode(E data, TreeNode<E> left, TreeNode<E> right)
	{
		this.data = data;
		this.left = left;
		this.right = right;
	}
}