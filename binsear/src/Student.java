/**
 *  15-121 Homework #10 Spring 2011
 * 
 *  Binary Search Trees
 *  Student class, to be used by Roster class
 *  The "courses" field is a Set, which must be implemented with a HashSet,
 *   containing the completed courses.
 */

import java.util.HashSet;
import java.util.Set;

public class Student
{
 private String name;     // unique all-lowercase student ID
 private Set<String> courses; // courses taken -- must use a HashSet implementation
 private Student left;     // subtree of students alphabetically before this one
 private Student right;    // subtree of students alphabetically after this one

 public Student(String aname)
 {
  name = aname;
  courses = new HashSet<String>();
  left = null;
  right = null;
 }

 public String getName(){
  return name;
 }

 public Set<String> getCourses(){
  return courses;
 }

 public Student getLeft(){
  return left;
 }

 public Student getRight(){
  return right;
 }

 public void setLeft(Student newLeft){
  left = newLeft;
 }

 public void setRight(Student newRight){
  right = newRight;
 }
}