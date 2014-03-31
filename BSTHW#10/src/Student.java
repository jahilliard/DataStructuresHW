/*
 * DO NOT CHANGE THIS CLASS
 */
import java.util.HashSet;
import java.util.Set;

public class Student {
	public String name;				//unique all-lowercase student ID
	public Set<String> courses;		//courses taken - must use a HashSet implementation
	public Student left;			// subtree of students alphabetically before this one
	public Student right;			// subtree of students alphabetically after this one

	public Student(String name) {
		this.name = name;
		this.courses = new HashSet<String>();
		this.left = null;
		this.right = null;
	}

	public boolean equals(Student s) {
		return name.equals(s.name) && courses.containsAll(s.courses) && s.courses.containsAll(courses);
	}
}