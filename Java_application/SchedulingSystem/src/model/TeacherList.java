package model;

import java.util.ArrayList;

/**
 * A class representing list of all teachers in system
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class TeacherList
{
  private ArrayList<Teacher> teachers;

  /**
   * 0-argument constructor initializing new ArrayList of teachers
   */

  public TeacherList()
  {
    this.teachers = new ArrayList<>();
  }

  /**
   * Method adding teacher to ArrayList
   * @param teacher teacher that will be added
   */

  public void addTeacher(Teacher teacher)
  {
    teachers.add(teacher);
  }

  /**
   * Method removing teacher equal to passed as parameter teacher from ArrayList
   * @param teacher teacher that should be removed
   */

  public void removeTeacher(Teacher teacher)
  {
    teachers.remove(teacher);
  }

  /**
   * Method removing teacher with specific id from ArrayList
   * @param id id of the teacher that should be removed
   */

  public void removeTeacher(String id)
  {

    for (int i = 0; i < getNumberOfTeachers(); i++)
    {
      if (id.equals(teachers.get(i).getId()))
      {
        teachers.remove(i);
        break;
      }
    }
  }

  /**
   * Getter for teacher with the id as the one passed as parameter
   * @param id id of the teacher
   * @return teacher with the id equal to <code>id</code> or null if such teacher does not exist
   */

  public Teacher getTeacherById(String id)
  {
    for (int i=0; i < teachers.size(); i++)
    {
      if(id.equals(teachers.get(i).getId()))
      {
        return teachers.get(i);
      }
    }
    return null;
  }

  /**
   * Method filtering the list of teacher based on any combination of 2 factors - their id and semester of coursed that they are teaching. Method returns filtered list of teachers
   * @param id id of the teacher
   * @param semester semester of course that the teacher should be teaching
   * @return <code>TeacherList</code> containing only teachers that match the filtering criteria
   */
  public TeacherList getTeachers(String id, int semester)
  {
    boolean idCheck = id != null;
    boolean semesterCheck = semester != -1;
    TeacherList list = new TeacherList();

    if (idCheck && semesterCheck)
    {
      for (int i = 0; i < getTeacherById(id).getNumberOfCourses();i++)
      {
        if (getTeacherById(id).getCourse(i).getSemester() == semester)
        {
          list.addTeacher(getTeacherById(id));
          return list;
        }
      }
      return null;
    }
    else if (idCheck)
    {
      list.addTeacher(getTeacherById(id));
      return list;
    }
    else if (semesterCheck)
    {
      for (int i = 0; i< getNumberOfTeachers(); i++)
      {
        for (int j = 0; j < teachers.get(i).getNumberOfCourses(); j++)
        {
          if (teachers.get(i).getCourse(j).getSemester() == semester)
          {
            list.addTeacher(teachers.get(i));
            break;
          }
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
   * Method checking whether the list contains certain teacher
   * @param teacher teacher that we want to whether they are on the list
   * @return true if teacher equal to teacher passed as parameter is on the list, otherwise false
   */
  public boolean containsTeacher(Teacher teacher)
  {
    for (int i = 0; i < getNumberOfTeachers(); i++)
    {
      if (teachers.get(i).equals(teacher))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Getter for number of teachers
   * @return number of teachers (size of ArrayList)
   */

  public int getNumberOfTeachers()
  {
    return teachers.size();
  }

  /**
   * Getter for teacher located at specified index in ArrayList
   * @param index number representing index
   * @return teacher located at index passed as parameter
   */
  public Teacher getTeacher (int index)
  {

    return teachers.get(index);
  }

  /**
   * Getter for teacher from list equal to certain teacher
   * @param teacher teacher that we want to find in ArrayList
   * @return teacher equal to teacher passed as parameter or null if such a teacher does not exist
   */

  public Teacher getTeacher(Teacher teacher)
  {
    for (int i = 0; i < getNumberOfTeachers(); i++)
    {
      if (teacher.equals(teachers.get(i)))
      {
        return teachers.get(i);
      }
    }
    return null;
  }
}
