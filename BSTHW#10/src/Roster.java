/*
 * DO NOT CHANGE THIS CLASS
 */
import java.util.*;

public class Roster {
	public Student root;
	public int numStudents;

	public Roster() {
		root = null;
		numStudents = 0;
	}

	public int size() {
		return numStudents;
	}

	/**
	 * This method returns a Set<String> representing the courses for the
	 * student with the supplied name. If there are no students matching that
	 * name, this method should return an empty Set. This method must find the
	 * Student using a binary search (log(n) time). You will not get credit if
	 * your search method is not binary.
	 * 
	 * This method must be recursive.
	 * 
	 * You may find it beneficial to use a helper method.
	 */
	public Set<String> getCoursesOf(String name) {
		if (root == null)  return new HashSet<String>();
		Set<String> courses = getCoursesHelper(name, root);
		if (courses==null) return new HashSet<String>();
		return courses;
	}
	
	public Set<String> getCoursesHelper(String name, Student root) {
		if (root == null) return new HashSet<String>();
		else if (name.compareTo(root.name)==0) return root.courses;
		else if (name.compareTo(root.name)>0) return getCoursesHelper(name, root.right);
		else return getCoursesHelper(name, root.left);
	}

	/**
	 * This method adds first searches for a student in with the given name in
	 * the Roster. The search must be iterative (no recursion), and it must run
	 * in log(n) time - it must be a binary search.
	 * 
	 * If the Student does not exist, a new Student is created and inserted in
	 * the correct spot in the tree. Don't forget to update numStudents!
	 * 
	 * Next, the course is created from the given course parameter.
	 */
	public void addCourse(String name, String course) {
		if (root==null) {
			root= new Student (name);
			root.courses.add(course);
			numStudents++;
		}
		Student currstu = root;
		while (currstu != null){
			if (currstu.name.compareTo(name)==0){
				currstu.courses.add(course);
				break;
			}
			else if (currstu.name.compareTo(name)>0){
				if (currstu.left==null){
					currstu.left = new Student(name);
					currstu.left.courses.add(course);
					numStudents++;
					break;
				}
				else currstu = currstu.left;
			}
			else if (currstu.name.compareTo(name)<0){
				if (currstu.right==null){
					currstu.right = new Student(name);
					currstu.right.courses.add(course);
					numStudents++;
					break;
				}
				else currstu = currstu.right;
			}
		}
	}

	/**
	 * This method recursively removes the given course parameter from all
	 * Students in the Roster. It must use recursion and should not modify the
	 * structure of the tree.
	 * 
	 * You may find it beneficial to use a helper method.
	 */
	public void dropCourseFromAll(String course) {
		dropHelper (course, root);
	}
	
	public void dropHelper (String course, Student root) {
		if (root == null){
			return;
		}
		else {
			if (root.courses.contains(course)) 
				root.courses.remove(course);
			dropHelper(course, root.left);
			dropHelper(course, root.right);
		}
	}

	/**
	 * This method counts the number of Students enrolled in the given course
	 * parameter. If there are no Students, this method should return 0.
	 * 
	 * This method must use recursion.
	 */
	public int countStudents(String course) {
		int numEnrolled = countHelper (course, root);
		return numEnrolled;
	}
	
	public int countHelper (String course, Student root) {
		if (root == null){
			return 0;
		}
		else{
			if (root.courses.contains(course)) 
				return 1 + countHelper(course, root.left) + countHelper(course, root.right);
			else
				return 0 + countHelper(course, root.left) + countHelper(course, root.right);
		}
	}

	/**
	 * This method deletes the Student with the given name in the Roster. You
	 * can assume that the name only occurs once in the Roster.
	 * 
	 * Don't forget to adjust numElements
	 */
	public void deleteStudent(String aName) {
		Student toRemove = findStuToRemove (aName, root);
		Student parentOfToRemove = findParentofStuToRemove (toRemove, root);
		Student Replacement = findReplacement (toRemove);
		Student parentOfReplacement = findParentofStuToRemove (Replacement, root);
		if (toRemove == null) return;
		else{
			if (parentOfToRemove==null){
				Replacement.left = toRemove.left;
				Replacement.right = toRemove.right;
				if (Replacement == parentOfReplacement.left){
					parentOfReplacement.left = null;
				}
				if (Replacement == parentOfReplacement.right){
					parentOfReplacement.right = null;
				}
			}
			else if (toRemove.left==null && toRemove.right==null){
				if (toRemove == parentOfToRemove.left){
					parentOfToRemove.left = Replacement;
				}
				if (toRemove == parentOfToRemove.right){
					parentOfToRemove.right = Replacement;
				}
			}
			else {
				if (Replacement == parentOfReplacement.left){
					parentOfReplacement.left = null;
				}
				if (Replacement == parentOfReplacement.right){
					parentOfReplacement.right = null;
				}
				Replacement.left = toRemove.left;
				Replacement.right = toRemove.right;
				if (toRemove == parentOfToRemove.left){
					parentOfToRemove.left = Replacement;
				}
				if (toRemove == parentOfToRemove.right){
					parentOfToRemove.right = Replacement;
				}
			}
		}
	}
	
	public Student findReplacement (Student toRemove) {
		Student studentSuccessor = toRemove.right;
		if (studentSuccessor==null) {
			studentSuccessor = toRemove.left;
			if (studentSuccessor == null) return null;
			while (studentSuccessor.right != null){
				studentSuccessor = studentSuccessor.right;
			}
		}
		if (studentSuccessor != null) {
			while (studentSuccessor.left != null){
				studentSuccessor = studentSuccessor.left;
			}
		}
		
		return studentSuccessor;
	}
	
	public Student findParentofStuToRemove (Student toRemove, Student root) {
		if (root.name.equals(toRemove.name)) return null;
		if ((root.left  != null && root.left.name.equals(toRemove.name) || 
			 root.right != null && root.right.name.equals(toRemove.name))) return root;
		else if (toRemove.name.compareTo(root.name)<0)  return findParentofStuToRemove (toRemove, root.left);
		else return findParentofStuToRemove (toRemove, root.right);
	}
	
	public Student findStuToRemove (String aName, Student root) {
		if (root == null) return null;
		else if (aName.compareTo(root.name)<0) return findStuToRemove(aName, root.left);
		else if (aName.compareTo(root.name)>0)  return findStuToRemove(aName, root.right);
		else return root;
	}

	/**
	 * The toString method returns a singleSstring containing all the Students,
	 * in alphabetical order by name, and their courses. If the tree is empty
	 * (there are no students) then this method returns the string:
	 * "There are no students registered." On the other hand, the returned
	 * string must display the all the data using the following format:
	 * 
	 * Alexander has taken: 15-110, 15-121, 99-101, 21-120 Carlos has taken:
	 * 99-101 Catherine has taken: 15-110, 15-121, 76-101, 21-127, 21-120
	 * Eduardo: no courses taken John has taken: 15-110, 76-101 Mary has taken:
	 * 21-120, 21-122, 15-110, 76-101
	 * 
	 * When printing the string, the values should be in alphabetical order and
	 * only one student per line.
	 */
	public String toString() {
		return stringHelper(root);
	}
	
	public String stringHelper(Student root){
		String toReturn = "";
		if (root != null){
			toReturn += stringHelper(root.left);
			toReturn += root.name;
			if (root.courses.isEmpty()) toReturn += ": no courses taken" + "\n";
			else {
				toReturn += " has taken: ";
				int commaCount= root.courses.size();
				for (String course: root.courses){
					commaCount--;
					if (commaCount>0){
					toReturn += course + ", ";
					}
					else{
						toReturn += course + " ";
					}
				}
				toReturn += "\n";
			}
			toReturn += stringHelper(root.right);
		}
		return toReturn;
	}
	
	/*
	 * Do NOT change anything below this line
	 */
	public void display() {
		new TreeDisplay<String>().setRoot(copy(root));
	}

	private static TreeNode<String> copy(Student node) {
		if (node == null)
			return null;
		return new TreeNode<String>(node.name + ":" + node.courses,
				copy(node.left), copy(node.right));
	}
}