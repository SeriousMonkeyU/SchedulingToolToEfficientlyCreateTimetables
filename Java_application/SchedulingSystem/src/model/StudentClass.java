package model;


/**
 * A class representing class of students in the system
 *
 * @author Group 5
 * @version v1 December 2021
 */
public class StudentClass
{
  private int semester;
  private String classLetters;

  /**
   * 2-arguments constructor
   * @param semester number representing semester of the student class
   * @param classLetters class letters of the student class (e.g. DK or X)
   * @throws IllegalArgumentException if symbol is null or semester is not a valid number
   */

  public StudentClass(int semester, String classLetters){

    if(semester < 1 || semester > 7){
      throw new IllegalArgumentException("Invalid semester");
    }
    this.semester = semester;

    this.classLetters = classLetters;

  }

  /**
   * Comparing student class and <code>o</code> Object whether they are equal
   * @param o obj that will be compared with student class
   * @return true if student class and <code>o</code> are equal, otherwise false
   */
  public boolean equals(Object o) {
    if (!(o instanceof StudentClass)) {
      return false;
    }
    StudentClass aClass = (StudentClass) o;
    return semester == aClass.semester && classLetters.equals(aClass.classLetters);
  }

  /**
   * Getter for symbol
   * @return symbol of a class (e.g. X or DK)
   */
  public String getClassLetters() {
    return classLetters;
  }

  /**
   * Getter for semester of the class
   * @return number representing semester
   */
  public int getSemester() {
    return semester;
  }

  /**
   * Method returning full name of the class (semester + symbol)
   * @return String with concatenated semester and symbol (e.g. 1X or 2DK)
   */
  public String getName()
  {
    return semester + "" + classLetters;
  }


}
