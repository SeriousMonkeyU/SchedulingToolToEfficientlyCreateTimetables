package model;

/**
 * A class representing time in the system (hour and minute)
 *
 * @author Group 5
 * @version v1 December 2021
 */
public class Time
{
  private int hour;
  private int minute;

  /**
   * 2-arguments constructor
   * @param hour number representing hour
   * @param minute number representing minute
   * @throws IllegalArgumentException if <code>hour</code> is smaller than 0 or greater than 23 or if <code>minute</code> is smaller than 0 or greater than 59
   */

  public Time(int hour, int minute)
  {
    if (hour < 0 || hour >23)
    {
      throw new IllegalArgumentException("Invalid hour");
    }

    this.hour = hour;

    if (minute < 0 || minute >59)
    {
      throw new IllegalArgumentException("Invalid minute");
    }

    this.minute = minute;

  }

  /**
   * Getter for hour
   * @return number representing hour in the time
   */
  public int getHour()
  {
    return hour;
  }

  /**
   * Getter for minute
   * @return number representing minute in the time
   */

  public int getMinute()
  {
    return minute;
  }

  /**
   * Method copying and returning Time object
   * @return copy of Time object
   */
  public Time copy()
  {
    Time other = new Time(this.hour, this.minute);
    return other;
  }

  /**
   * Method checking whether one time is before another time (e.g. 7:30 is before 8:20)
   * @param other another Time object
   * @return true if time is before <code>other</code> time, otherwise false
   */
  public boolean isBefore(Time other)
  {
    if (hour < other.hour)
    {
      return true;

    }
    else if (hour > other.hour)
    {
      return false;
    }
    else
    {
      if (minute < other.minute)
      {
        return true;
      }
      else
      {
        return false;
      }
    }
  }

  /**
   * Comparing time and obj Object whether they are equal
   * @param obj Object compared with time
   * @return true if time and <code>obj</code> are equal, otherwise false
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Time))
    {
      return false;
    }

    Time other = (Time)obj;
    return hour == other.hour && minute == other.minute;
  }

  /**
   * Returning time in format "hour:minute"
   * @return string representation of time
   */
  public String toString()
  {
    String s = "";

    if (hour < 10)
    {
      s += "0";
    }
    s += hour + ":";
    if (minute < 10)
    {
      s += "0";
    }
    s += minute;
    return s;
  }
}
