package model;

/**
 * A class representing a course in the system
 *
 * @author Group 5
 * @version v1 - December 2021
 */

public class Course
{
  private int semester;
  private String name;
  private int ects;
  private Teacher teacher;
  private String classLetters;

  /**
   * 5-arguments constructor initializing an instance of Course, "assigning" teacher to teacher field
   * @param semester semester on which course is taught
   * @param name name of the course (including name and class e.g. SDJX)
   * @param ects number of ects awarded for a course
   * @param teacher instance of Teacher class representing teacher teaching the course
   * @param classLetters String representing which class is having this course (X,Y,Z or DK)
   * @throws IllegalArgumentException if semester or ects is not a valid number or one of the other parameters is null the exception is thrown
   *
   */
  public Course(int semester, String name, int ects, Teacher teacher, String classLetters){
    if(teacher == null){
      throw new IllegalArgumentException("Invalid teachers for the course");
    }
    this.teacher = teacher;

    if(name == null){
      throw new IllegalArgumentException("Invalid name for the course");
    }
    this.name = name;

    if(ects == 0){
      throw new IllegalArgumentException("Invalid ects for the course");
    }
    this.ects = ects;

    if(semester == 0){
      throw new IllegalArgumentException("Invalid semester of the course");
    }
    this.semester = semester;

    if (classLetters == null)
    {
      throw  new IllegalArgumentException("Invalid classLetters");
    }
    this.classLetters = classLetters;
  }

  /**
   * 4-arguments constructor initializing instance of Course class, setting the teacher field to null
   * @param semester semester on which course is taught
   * @param name name of the course (including name and class e.g. SDJX)
   * @param ects number of ects awarded for a course
   * @param classLetters String representing which class is having this course (X,Y,Z or DK)
   * @throws IllegalArgumentException if semester or ects is not a valid number or one of the other parameters is null the exception is thrown
   */

  public Course(int semester, String name, int ects,String classLetters)
  {
    if(name == null){
      throw new IllegalArgumentException("Invalid name for the course");
    }
    this.name = name;

    if(ects == 0){
      throw new IllegalArgumentException("Invalid ects for the course");
    }
    this.ects = ects;

    if(semester == 0){
      throw new IllegalArgumentException("Invalid semester of the course");
    }
    this.semester = semester;

    if (classLetters == null)
    {
      throw  new IllegalArgumentException("Invalid classLetters");
    }
    this.classLetters = classLetters;
    this.teacher = null;
  }

  /**
   * Method checking whether course has teacher assigned to it
   * @return true if course has teacher, otherwise false
   */
  public boolean hasTeacher()
  {
    return teacher != null;
  }

  /**
   * Setter for teacher
   * @param teacher instance of Teacher class you want to set as teacher of this course
   */

  public void setTeacher(Teacher teacher)
  {
    if (!hasTeacher())
    {
      this.teacher = teacher;
    }
  }

  /**
   * Getter for class letters
   * @return class letters of this course
   */
  public String getClassLetters()
  {
    return classLetters;
  }

  /**
   * Getter for teacher of this course
   * @return teacher of this course or null if no teacher is set
   */

  public Teacher getTeacher()
  {
    return teacher;
  }

  /**
   * Getter for semester
   * @return semester of this course
   */

  public int getSemester() {
    return semester;
  }

  /**
   * Getter for name of the course
   * @return name of the course
   */

  public String getName() {
    return name;
  }

  /**
   * Getter for number of ects
   * @return number of ects
   */

  public int getEcts() {
    return ects;
  }

  /**
   * Checking whether the instance of Course class is the same as some Object
   *
   * @param obj Object which is being compared to instance of Course class
   * @return true if passed Object is the same as instance of Course class, false in any other case
   */

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Course))
    {
      return false;
    }

    Course other = (Course) obj;

    return semester == other.semester && name.equals(other.name) && ects == other.ects && teacher.getId().equals(other.teacher.getId());
  }

  /**
   *  Creating a string with all necessary information about course
   * @return string containing information about course
   */
  public String toString() {
    return "Course : " + semester + "," + name + ", ects=" + ects + ", teacher=" + teacher.getId() ;
  }
}
