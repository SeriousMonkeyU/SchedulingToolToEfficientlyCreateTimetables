package model;

/**
 * A class representing student (inherits from Person class)
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class Student extends Person
{
  private int semester;
  private String name;
  private String classLetters;

  /**
   * 4-arguments constructor calling first superclass constructor
   * @param name name of the student
   * @param id student id (6 numbers)
   * @param semester semester at which is student (1,2,3,4,5,6 or 7)
   * @param classLetters class in which the student is (X,Y,Z or DK)
   * @throws IllegalArgumentException if <code>id</code> is not 6 numbers or if the <code>name</code> or <code>classLetters</code> is empty String or null or if <code>semester</code> is invalid number
   */

  public Student(String name, String id, int semester, String classLetters)
  {
    super(id);
    if (id.length() != 6 || !(id.matches("^[0-9]*$")))
    {
      throw new IllegalArgumentException("Student id consists of 6 numbers");
    }
    if (name == null || name.equals(""))
    {
      throw new IllegalArgumentException("Invalid name");
    }
    this.name = name;

    if (semester < 1 || semester > 7)
    {
      throw new IllegalArgumentException("Invalid semester");
    }
    this.semester = semester;

    if (classLetters ==null || classLetters.equals(""))
    {
      throw new IllegalArgumentException("Invalid class");
    }
    this.classLetters = classLetters;
  }

  /**
   * Getter for the name
   * @return name of the student
   */
  public String getName()
  {
    return name;
  }

  /**
   * Getter for semester
   * @return semester of the student
   */
  public int getSemester()
  {
    return semester;
  }

  /**
   * Getter for class letter
   * @return class letters of class to which student belongs
   */
  public String getClassLetters()
  {
    return classLetters;
  }

  /**
   * Creating String with student data
   * @return String with all needed information about student
   */
  public String toString()
  {
    return super.toString() + "\nName: " + name + "\nSemester: " + semester + "\nLetter of a class: " + classLetters;
  }

  /**
   * Comparing student and <code>obj</code> Object whether they are equal (overwrites <code>Person</code> method <code>equals</code>)
   * @param obj object that will be compared with student
   * @return true if student and <code>obj</code> are equal, otherwise false
   */
  public boolean equals (Object obj)
  {
    if(!(obj instanceof Student))
    {
      return false;
    }

    Student other = (Student) obj;
    return super.equals(obj) && name.equals(other.name) && semester == other.semester && classLetters.equals(other.classLetters);
  }

}
