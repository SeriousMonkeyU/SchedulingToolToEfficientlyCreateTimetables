package model;

import java.util.ArrayList;

/**
 * A class containing list of all courses created in system
 *
 * @author Group 5
 * @version v1 - December 2021
 */

public class CourseList
{
  private ArrayList<Course> courses;

  /**
   * Constructor initializing empty ArrayList of courses
   */

  public CourseList(){
    this.courses = new ArrayList<>();
  }

  /**
   * Method adding course to ArrayList
   * @param course instance of Course class we are adding
   */

  public void addCourse(Course course)
  {
    courses.add(course);
  }

  /**
   * Method removing specific course from ArrayList
   * @param course instance of Course class we want to remove
   */
  public void removeCourse(Course course){
    courses.remove(course);
  }

  /**
   * Getter for number of courses in ArrayList
   * @return size of the ArrayList
   */

  public int getNumberOfCourses(){
    return courses.size();
  }

  /**
   * Getter for course at specific index in array list
   * @param index int representing index of the item
   * @return course at passed index
   */
  public Course getCourse(int index){

    return courses.get(index);
  }

  /**
   * Getter for course matching course we passed as parameter
   * @param course instance of Course class for which we are looking in ArrayList
   * @return course identical with passed course or null if such course does not exist in ArrayList
   */

  public Course getCourse(Course course)
  {
    for (int i = 0; i < getNumberOfCourses(); i++)
    {
      if (course.equals(courses.get(i)))
      {
        return courses.get(i);
      }
    }
    return null;
  }

  /**
   * Getter for course with specific name
   * @param name String representing name of the course
   * @return course with the same name as String passed as parameter or null if such a course does not exist
   */

  public Course getCourseByName(String name)
  {
    for (int i = 0; i < getNumberOfCourses(); i++)
    {
      if (name.equals(courses.get(i).getName()))
      {
        return courses.get(i);
      }
    }
    return null;
  }

  /**
   * Getter for course with specific name and teacher
   * @param name String representing name of the course
   * @param teacher instance of Teacher class
   * @return course with the name and teacher same as values passed in parameters or null if such a course does not exist
   */

  public Course getCourseByNameAndTeacher(String name, Teacher teacher)
  {
    for (int i = 0; i < getNumberOfCourses(); i++)
    {
      if (name.equals(courses.get(i).getName()) && teacher.getId().equals(courses.get(i).getTeacher().getId()))
      {
        return courses.get(i);
      }
    }
    return null;
  }

  /**
   * Method returning CourseList with courses that have same semester and name as values passed as parameters
   * @param name String representing name of course
   * @param semester int representing the number of semester (1,2,3,4,5,6 or 7)
   * @return course list with courses fulfilling requirements
   */

  public CourseList getCourses(String name, int semester){
    CourseList newlist = new CourseList();

    boolean nameCheck = name != null;
    boolean semesterCheck = semester != -1;

    if (nameCheck && semesterCheck)
    {
      for (int i=0; i<courses.size(); i++){
        if(courses.get(i).getName().equals(name) && courses.get(i).getSemester() == semester){
          newlist.addCourse(courses.get(i));
        }
      }
      return newlist;
    }
    else if (nameCheck)
    {
      for (int i=0; i<courses.size(); i++){
        if(courses.get(i).getName().equals(name)){
          newlist.addCourse(courses.get(i));
        }
      }
      return newlist;
    }
    else if (semesterCheck)
    {
      for (int i=0; i<courses.size(); i++){
        if(courses.get(i).getSemester() == semester){
          newlist.addCourse(courses.get(i));
        }
      }
      return newlist;
    }
    else
    {
      return null;
    }


  }
}
