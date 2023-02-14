package model;

import java.util.ArrayList;

/**
 * A class representing teacher in the system (inherits from Person class)
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class Teacher extends Person
{
  private String email;

  /**
   * 1-argument constructor setting an email based on id (id+@via.dk)
   * @param id teacher's id (3-5 letters)
   * @throws IllegalArgumentException if the id is invalid
   */

  public Teacher(String id)
  {
    super(id);
    if (id.length() < 3 || id.length() > 5 || !(id.matches("^[a-zA-Z]*$")))
    {
      throw new IllegalArgumentException("Teacher id consists of 3-5 letters");
    }
    this.email = id.toLowerCase() + "@via.dk";
  }

  /**
   * Getter for email
   * @return email of the teacher
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * Creating string with the information about teacher
   * @return String with all needed information about teacher
   */
  public String toString()
  {
    return super.toString() + " " + email;
  }

  /**
   * Comparing teacher and <code>obj</code> Object whether they are equal
   * @param obj object that will be compared with teacher
   * @return true if teacher and <code>obj</code> are equal, otherwise false
   */

  public boolean equals (Object obj)
  {
    if (!(obj instanceof Teacher))
    {
      return false;
    }

    Teacher other = (Teacher) obj;

    return super.equals(obj) && email.equals(other.email);
  }
}
