package model;

/**
 * A class representing session in the system (basically foundation of whole system)
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class Session
{
  public Date date;
  public Room room;
  public TimeSlot timeSlot;
  public StudentClass studentClass;
  public Course course;

  /**
   * 5-argument constructor initializing all instance variables
   * @param date date on which session takes place (cannot be past date)
   * @param room room in which session takes place
   * @param timeSlot time slot during which session happens
   * @param studentClass student class participating in the session
   * @param course course being taught during the session
   * @throws IllegalArgumentException if any of parameters is null
   */

  public Session(Date date, Room room, TimeSlot timeSlot, StudentClass studentClass,
      Course course)
  {
    if (date == null)
    {
      throw new IllegalArgumentException("Invalid date");
    }
      this.date = date.copy();
    if (room == null)
    {
      throw new IllegalArgumentException("Invalid room");
    }
      this.room = room;
    if (timeSlot == null)
    {
      throw new IllegalArgumentException("Invalid time slot");
    }
      this.timeSlot = timeSlot;
    if (studentClass == null)
    {
      throw new IllegalArgumentException("Invalid class");
    }
      this.studentClass = studentClass;
    if (course == null)
    {
      throw new IllegalArgumentException("Invalid course");
    }
      this.course = course;



  }

  /**
   * 3-arguments constructor initializing room and time slot to null (they can be set later with setters)
   * @param date date on which session takes place (cannot be past date)
   * @param studentClass student class participating in the session
   * @param course course being taught during the session
   * @throws IllegalArgumentException if any of parameters is null or if the date is before today's date
   */


  public Session(Date date, StudentClass studentClass,
      Course course)
  {
    if (date == null || date.isBefore(new Date()))
    {
      throw new IllegalArgumentException("Invalid date");
    }
      this.date = date.copy();
      this.room = null;
      this.timeSlot = null;
    if (studentClass == null)
    {
      throw new IllegalArgumentException("Invalid class");
    }
      this.studentClass = studentClass;
    if (course == null)
    {
      throw new IllegalArgumentException("Invalid course");
    }
      this.course = course;

  }

  /**
   * Getter for a room
   * @return room in which session takes place
   */
  public Room getRoom()
  {
    return room;
  }

  /**
   * Getter for a date
   * @return date on which session happens
   */

  public Date getDate()
  {
    return date;
  }

  /**
   * Getter for timeslot
   * @return timeslot during which session happens
   */

  public TimeSlot getTimeSlot()
  {
    return timeSlot;
  }

  /**
   * Getter for student class
   * @return student class which participates in session
   */

  public StudentClass getStudentClass()
  {
    return studentClass;
  }

  /**
   * Getter for course
   * @return course which is taught during the session
   */

  public Course getCourse()
  {
    return course;
  }

  /**
   * Setter for a room
   * @param room room that will be set as instance variable
   */

  public void setRoom(Room room)
  {
    this.room = room;
  }

  /**
   * Setter for date
   * @param date date that will be set as instance variable
   */
  public void setDate(Date date)
  {
    this.date = date;
  }

  /**
   * Setter for time slot
   * @param timeSlot time slot that will be set as instance variable
   */
  public void setTimeSlot(TimeSlot timeSlot)
  {
    this.timeSlot = timeSlot;
  }

  /**
   * Comparing session and <code>obj</code> Object whether they are equal
   * @param obj object that will be compared with session
   * @return true if session and <code>obj</code> are equal, otherwise false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Session))
    {
      return false;
    }

    Session other = (Session) obj;
    return date.equals(other.date) && room.equals(other.room)
        && timeSlot.equals(other.timeSlot) && studentClass.equals(
        other.studentClass) && course.equals(other.course);
  }

  /**
   * Creating String with information about session
   * @return String with all need information about session
   */
  public String toString()
  {
    return date + " " + room + " " + timeSlot + " " + studentClass + " " + course;
  }
}
