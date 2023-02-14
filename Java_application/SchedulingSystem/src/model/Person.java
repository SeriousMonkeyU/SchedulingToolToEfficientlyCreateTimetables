package model;

import java.util.ArrayList;

/**
 * Abstract class representing person in the system
 *
 * @author Group 5
 * @version v1 December 2021
 */

public abstract class Person
{
  private String id;
  private ArrayList<Course> courses;

  /**
   * 1-argument constructor initializing new ArrayList of courses and initializing id variable
   * @param id unique id of a person
   */

  public Person(String id)
  {
    this.id = id;
    this.courses = new ArrayList<>();
  }

  /**
   * Getter for id
   * @return id of a person
   */

  public String getId()
  {
    return id;
  }

  /**
   * Method removing course from ArrayList of courses
   * @param course course object chosen for removal
   */

  public void removeFromCourse(Course course)
  {
    courses.remove(course);
  }

  /**
   * Method adding course to ArrayList of courses
   * @param course course object that will be added to ArrayList of courses
   */
  public void assignToCourse(Course course)
  {
    courses.add(course);
  }

  /**
   * Getter for size of ArrayList of courses
   * @return number of courses assigned to person
   */
  public int getNumberOfCourses()
  {
    return courses.size();
  }

  /**
   * Getter for course at specific index
   * @param index number representing index of course
   * @return course object located at specific index in ArrayList
   */
  public Course getCourse(int index)
  {
    return courses.get(index);
  }

  /**
   * Checking whether person and <code>obj</code> are equal
   * @param obj object that will be compared with person
   * @return true if person and <code>obj</code> are equal, otherwise false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Person))
    {
      return false;
    }

    Person other = (Person) obj;

    return id.equals(other.id);

  }

  /**
   * Creating string with information about person
   * @return string with necessary information about person
   */
  public String toString()
  {
    String s = "\nID: " + id + "\nCourses: ";

    for (int i = 0; i < courses.size(); i++)
    {
      s += courses.get(i);
    }

    return s;
  }

}
