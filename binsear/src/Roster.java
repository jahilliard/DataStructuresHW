/*
 *    15-121 Homework #10 Spring 2011
 * 
 *    Binary Search Trees
 * 
 *    Roster Class
 * 
 *    FINISH THE IMPLEMENTATION OF THE FOLLOWING METHODS
 * 
 *    NAME: Ryan Kwok
 *    ANDREW-ID: rkwok
 *    SECTION: A
 * 
 */

import java.util.*;

public class Roster
{
  //please leave as public, to help us grade your work
  public Student root;
  public int numStudents;
  
  public Roster()
  {
    root = null;
    numStudents = 0;
  }
  
  public int getNumStudents()
  {
    return numStudents;
  }
  
  //the implementation of this method MUST USE RECURSION, NO LOOPS!!
  public Set<String> getCoursesOf(String aName)
  {
    if (root == null)
    {
      return new HashSet<String>();
    }
    else
    {
      Set<String> temp = getCoursesOfHelper(aName, root);
      if (temp == null)
      {
        return new HashSet<String>();
      }
      else
      {
        return temp;
      }
    }
  }
  
  public Set<String> getCoursesOfHelper(String aName, Student root)
  {
    if (root == null)
    {
      return null;
    }
    else
    {
      if (root.getName().compareTo(aName) == 0)
      {
        return root.getCourses();
      }
      else if (root.getName().compareTo(aName) < 0)
      {
        return getCoursesOfHelper(aName, root.getRight());
      }
      else
      {
        return getCoursesOfHelper(aName, root.getLeft());
      }
    }
  }
  
  //the implementation of this method MUST USE LOOPS, NO RECURSION!!
  public void addCourse(String aName, String aCourse)
  {
    if (root == null)
    {
      root = new Student(aName);
      root.getCourses().add(aCourse);
      numStudents++;
    }
    else
    {
      Student temp = root;
      while (temp != null)
      {
        if (temp.getName().equals(aName))
        {
          temp.getCourses().add(aCourse);
          return;
        }
        else if (temp.getName().compareTo(aName) < 0)
        {
          if (temp.getRight() != null)
          {
            temp = temp.getRight();
          }
          else
          {
            temp.setRight(new Student(aName));
            temp.getRight().getCourses().add(aCourse);
            numStudents++;
            return;
          }
        }
        else 
        {
          if (temp.getLeft() != null)
          {
            temp = temp.getLeft();
          }
          else
          {
            temp.setLeft(new Student(aName));
            temp.getLeft().getCourses().add(aCourse);
            numStudents++;
            return;
          }
        }
      }
    }
  }
  
  public void dropCourseFromAll(String aCourse)
  {
    dropCourseFromAll(aCourse, root);
  }
  
  public void dropCourseFromAll(String aCourse, Student root)
  {
    if (root != null)
    {
      if (root.getCourses().contains(aCourse))
      {
        root.getCourses().remove(aCourse);
      }
      dropCourseFromAll(aCourse, root.getLeft());
      dropCourseFromAll(aCourse, root.getRight());
    }
  }
  
  public int countStudents(String aCourse)
  {
    return countStudentsHelper(aCourse, root);
  }
  
  public int countStudentsHelper(String aCourse, Student root)
  {
    if (root == null)
    {
      return 0;
    }
    else
    {
      if (root.getCourses().contains(aCourse))
      {
        return 1 + countStudentsHelper(aCourse, root.getRight()) + countStudentsHelper(aCourse, root.getLeft());
      }
      else
      {
        return countStudentsHelper(aCourse, root.getRight()) + countStudentsHelper(aCourse, root.getLeft());
      }
    }
  }
  
  public void deleteStudent(String aName)
  {
    deleteStudentHelper(aName, root);
  }
  
  public void deleteStudentHelper(String aName, Student root)
  {
    if (root != null)
    {
      Student temp = root;
      Student temp2 = temp;
      while ((temp.getName().compareTo(aName) != 0))
      {
        temp2 = temp;
        if (temp.getName().compareTo(aName) < 0)
        {
          if (temp.getRight() != null)
          {
            temp = temp.getRight();
          }
          else
          {
            return;
          }
        }
        else
        {
          if (temp.getLeft() != null)
          {
            temp = temp.getLeft();
          }
          else
          {
            return;
          }
        }
      }
      if (temp != null)
      {
        if (temp.getRight() == null)
        {
          if (temp2.getRight().equals(temp))
          {
            temp2.setRight(temp.getLeft());
          }
          else
          {
            temp2.setLeft(temp.getLeft());
          }
          numStudents--;
        }
        else if (temp.getRight().getLeft() == null)
        {
          temp.getRight().setLeft(temp.getLeft());
          if (temp2.getRight().equals(temp))
          {
            temp2.setRight(temp.getRight());
          }
          else
          {
            temp2.setLeft(temp.getRight());
          }
          numStudents--;
        }
        else
        {
          Student temp3 = temp.getRight();
          Student temp4 = temp3;
          while (temp3.getLeft() != null)
          {
            temp4 = temp3;
            temp3 = temp3.getLeft();
          }
          temp3.setLeft(temp.getLeft());
          temp4.setLeft(temp3.getRight());
          temp3.setRight(temp.getRight());
          if (temp2.equals(temp))
          {
            root = temp3;
            return;
          }
          else
          {
            if (temp2.getRight().equals(temp))
            {
              temp2.setRight(temp3);
            }
            else
            {
              temp2.setLeft(temp3);
            }
          }
        }
      }
    }
  }
  
  public String toString()
  {
    return toStringHelper(root);
  }
  
  public String toStringHelper(Student root)
  {
    String courses = "";
    if (root != null)
    {
      courses += toStringHelper(root.getLeft());
      courses += printCourses(root) + "\n";
      courses += toStringHelper(root.getRight());
    }
    return courses;
  }
  
  public String printCourses(Student root)
  {
    String courses = root.getName();
    if (root.getCourses().isEmpty())
    {
      return courses += ": no courses taken";
    }
    else
    {
      courses += " has taken:";
      Iterator<String> course = root.getCourses().iterator();
      while (course.hasNext())
      {
        String temp = course.next();
        courses += " " + temp;
        if (course.hasNext())
        {
          courses += ", ";
        }
      }
    }
    return courses;
  }
  
  //THE FOLLOWING TWO METHODS ARE PROVIDED SO THAT YOU CAN DISPLAY A BST
  //THESE METHODS USE THE CLASS TreeDisplay AND THE CLASS Student
  //DO NOT MODIFY THESE METHODS
  
  public void display()
  {
    new TreeDisplay<String>().setRoot(copy(root));
  }
  
  private static TreeNode<String> copy(Student node)
  {
    if (node == null) return null;
    return new TreeNode<String>(node.getName() + ":" + node.getCourses(),
                                copy(node.getLeft()), copy(node.getRight()));
  }
}