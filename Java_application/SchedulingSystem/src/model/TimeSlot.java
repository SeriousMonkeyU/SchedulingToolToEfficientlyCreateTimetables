package model;

/**
 * A class representing time slot in the system (time interval).
 * It has 2 public static final instance variables that keep all possible start and end times for time slot
 *
 * @author Group 5
 * @version v1 December 2021
 */
public class TimeSlot
{
  private int numberOfLessons;
  public static final Time[] START_TIMES = {new Time(8,20), new Time(9,15), new Time(10,10), new Time(11,5),
      new Time(12,45), new Time(13,35), new Time(14,30), new Time(15,20),};
  public static final Time[] END_TIMES = {new Time(9,5), new Time(10,0), new Time(10,55), new Time(11,50),
      new Time(13,30), new Time(14,25), new Time(15,15), new Time(16,5),};
  private Time startTime;
  private Time endTime;

  /**
   * 3-arguments constructor
   * @param numberOfLessons number representing number of lessons (length of time slot)
   * @param startTime time at which time slot starts
   * @param endTime time at which time slot ends
   * @throws IllegalArgumentException if number of lessons is smaller than 1, or if <code>startTime</code> or <code>endTime</code> are null
   */

  public TimeSlot(int numberOfLessons, Time startTime, Time endTime)
  {
    if (numberOfLessons < 1)
    {
      throw new IllegalArgumentException("Invalid number of lessons");
    }

    this.numberOfLessons = numberOfLessons;

    if (startTime == null)
    {
      throw new IllegalArgumentException("Invalid start time");
    }

    this.startTime= startTime;

    if (endTime == null)
    {
      throw new IllegalArgumentException("Invalid end time");
    }
    this.endTime = endTime;
  }

  /**
   * Getter for start time
   * @return start time of the time slot
   */

  public Time getStartTime()
  {
    return startTime.copy();
  }

  /**
   * Getter for end time
   * @return end time of the time slot
   */

  public Time getEndTime()
  {
    return endTime.copy();
  }

  /**
   * Getter for number of lessons
   * @return lenght of the time slot (number of lessons)
   */
  public int getNumberOfLessons()
  {
    return numberOfLessons;
  }

  /**
   * Method checking whether one time slot overlaps with another (e.g. time slot 8:30 - 10:00 overlaps with time slot 9:30-9:30,
   * because there is moment in time when both time slots are "active")
   * @param other another time slot that is used as reference when checking for overlap
   * @return true if time slots overlap, otherwise false
   */
  public boolean isDuringOtherTimeslot(TimeSlot other)
  {
    if (this.equals(other))
    {
      return true;
    }
    else if(startTime.isBefore(other.startTime))
    {
      if (other.startTime.isBefore(endTime))
      {
        return true;
      }
      else
      {
        return false;
      }
    }
    else
    {
      if (startTime.isBefore(other.endTime))
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
   * Comparing time slot and <code>obj</code> Object whether they are equal
   * @param obj Object being compared with time slot
   * @return true if time slot and <code>obj</code> are equal, otherwise false
   */

  public boolean equals(Object obj)
  {
    if (!(obj instanceof TimeSlot))
    {
      return false;
    }

    TimeSlot other = (TimeSlot) obj;
    return startTime.equals(other.startTime) && endTime.equals(other.endTime);
  }

  /**
   * Creating string representation of time slot (start time - end time)
   * @return string representation of timeslot
   */
  public String toString()
  {
    return  startTime + " - " + endTime;
  }
}
