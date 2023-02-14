package model;

import java.util.ArrayList;

/**
 * A class representing list of all students in system
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class StudentList
{
  private ArrayList<Student> students;

  /**
   * 0-arguments constructor initializing new ArrayList of students
   */

  public StudentList ()
  {
    this.students = new ArrayList<>();
  }

  /**
   * Method adding student to ArrayList
   * @param student student that will be added
   */

  public void addStudent (Student student)
  {
    students.add(student);
  }

  /**
   * Method searching for student with specific id and then removing him from ArrayList
   * @param id id of a student that will be removed
   */
  public void removeStudent(String id)
  {

    for (int i = 0; i < getNumberOfStudents(); i++)
    {
      if (id.equals(students.get(i).getId()))
      {
        students.remove(i);
        break;
      }
    }
  }

  /**
   * Method that conducts a filter on list of student and return filtered list, criteria of filtering are passed as parameters and can be
   * in any combination (e.g. filtering by semester and name at the same time). Method recognizes which parameters it should use in filtering based on their values.
   * @param name name that filtered students should have
   * @param semester semester on which filtered students should be
   * @param classLetter class letters (e.g. DK) of class that filtered students should be part of
   * @return <code>StudentList</code> containing students that match criteria of the filtering
   */
  public StudentList getStudents(String name, int semester, String classLetter)
  {
    StudentList list = new StudentList();
    boolean nameCheck = name != null;
    boolean semesterCheck = semester != -1;
    boolean classCheck= classLetter != null;

    if (nameCheck && classCheck && semesterCheck)
    {
      for (int i = 0; i < getNumberOfStudents(); i++)
      {
        if (name.equals(students.get(i).getName()) && semester == students.get(i).getSemester()
            && classLetter.equals(students.get(i).getClassLetters()))
        {
          list.addStudent(students.get(i));
        }
      }
      return list;
    }
    else if (nameCheck)
    {
      for (int i = 0; i < getNumberOfStudents(); i++)
      {
        if (name.equals(students.get(i).getName()))
        {
          list.addStudent(students.get(i));
        }
      }
      return list;
    }
    else if (classCheck && semesterCheck)
    {
      for (int i = 0; i < getNumberOfStudents(); i++)
      {
        if (semester == students.get(i).getSemester() && classLetter.equals(students.get(i).getClassLetters()))
        {
          list.addStudent(students.get(i));
        }
      }
      return list;
    }
    else if (classCheck)
    {
      for (int i = 0; i < getNumberOfStudents(); i++)
      {
        if (classLetter.equals(students.get(i).getClassLetters()))
        {
          list.addStudent(students.get(i));
        }
      }
      return list;
    }
    else if (semesterCheck)
    {
      for (int i = 0; i < getNumberOfStudents(); i++)
      {
        if (semester == students.get(i).getSemester())
        {
          list.addStudent(students.get(i));
        }
      }
      return list;
    }
    else
    {
      return null;
    }

  }

  /**
   * Getter for number of students
   * @return number of students (size of ArrayList)
   */
  public int getNumberOfStudents()
  {
    int count = 0;
    for (int i=0; i<students.size(); i++)
    {
      count++;
    }
    return count;
  }

  /**
   * Getter for student located at specific index in ArrayList
   * @param index number representing index
   * @return student at index passed as parameter
   */

  public Student getStudent(int index)
  {
    return students.get(index);
  }

  /**
   * Getter for student equal to passed parameter
   * @param student student that we want to find in ArrayList
   * @return student from list equal to passed parameter or null if such a student does not exist
   */
  public Student getStudent(Student student)
  {
    for (int i = 0; i < getNumberOfStudents(); i++)
    {
      if (student.equals(students.get(i)))
      {
        return students.get(i);
      }
    }
    return null;
  }
}
