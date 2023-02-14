package model;

import java.util.ArrayList;

/**
 * A class representing a room in the system
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class Room
{

  private String name;
  private int capacity;
  private String mergedRoomName;
  private ArrayList<Date> reservedDates;
  private  ArrayList<TimeSlot> reservedTimeSlots;

  /**
   * 2-arguments constructor initializing new ArrayLists of <code>Timeslot</code> and <code>Date</code> and setting at start the mergedRoomName is null (no merged room)
   * @param name name of the room (e.g. C.05.16a)
   * @param capacity number capacity of the room (how many sitting places are there)
   * @throws IllegalArgumentException if name is null or capacity is negative number
   */

  public Room(String name, int capacity)
  {
    mergedRoomName = null;
    reservedDates = new ArrayList<>();
    reservedTimeSlots = new ArrayList<>();

    if (name == null)
    {
      throw new IllegalArgumentException("Invalid name of the room");
    }
    this.name = name;

    if (capacity < 1)
    {
      throw new IllegalArgumentException("Invalid capacity of the room");
    }
    this.capacity =capacity;
  }

  /**
   * Getter for name
   * @return name of the room
   */
  public String getName()
  {
    return name;
  }

  /**
   * Getter for name of merged room
   * @return name of the merged room (if there is no room merged, method will return null)
   */
  public String getMergedRoomName()
  {
    return mergedRoomName;
  }

  /**
   * Getter for capacity
   * @return capacity of the room
   */
  public int getCapacity()
  {
    return capacity;
  }

  /**
   * Setter for mergedRoomName
   * @param roomName name of the room that will be set as name of the merged room
   */
  public void setMergedRoom(String roomName)
  {
    this.mergedRoomName = roomName;
  }

  /**
   * Method adding new reservation of the room at particular date and timeslot by adding them to corresponding ArrayLists at the same indexes
   * @param date date of the new reservation
   * @param timeslot timeslot during which room will be booked
   */
  public void setReservation(Date date, TimeSlot timeslot)
  {
    reservedDates.add(date);
    reservedTimeSlots.add(timeslot);
  }

  /**
   * Method removing a reservation / freeing the room at given date and timeslot by finding the looping through ArrayLists of dates and timeslots
   * until we find items with the same indexes (it is known that date and timeslot of reservation have same indexes) that corresponds to parameters and then removing them (which is the same as removing reservation)
   * @param date date on which the reservation we want to remove is set
   * @param timeSlot timeslot at which we want the room to be "free" again
   */
  public void removeReservation(Date date, TimeSlot timeSlot)
  {
    for (int i = 0; i < reservedDates.size(); i++)
    {
      for (int j =0; j < reservedTimeSlots.size(); j++)
      {
        if (j == i && date.equals(reservedDates.get(i)) && timeSlot.equals(reservedTimeSlots.get(j)))
        {
          reservedDates.remove(i);
          reservedTimeSlots.remove(j);
          break;
        }
      }
    }
  }

  /**
   * Getter for number of reservations
   * @return number of reservations
   */
  public int numberOfReservations()
  {
    return reservedDates.size();
  }

  /**
   * Getter for reserved dates
   * @return ArrayList with all dates on which room is reserved
   */
  public ArrayList<Date> getReservedDates()
  {
    return reservedDates;
  }

  /**
   * Getter for reserved timeslots
   * @return ArrayList with all timeslots during which room is reserved
   */

  public ArrayList<TimeSlot> getReservedTimeSlots()
  {
    return reservedTimeSlots;
  }

  /**
   * Checking  oom and <code>obj</code> whether they are equal
   * @param obj object we are comparing with the room
   * @return true if room and <code>obj</code> are equal, othewise false
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof  Room))
    {
      return false;
    }

    Room other = (Room) obj;

    if (mergedRoomName == null)
    {
      if (other.mergedRoomName == null && capacity == other.capacity && name.equals(other.name))
      {
        for (int i = 0; i < numberOfReservations(); i++)
        {
          if (!(reservedDates.get(i).equals(other.reservedDates.get(i)) && reservedTimeSlots.get(i).equals(other.reservedTimeSlots.get(i))))
          {
            return false;
          }
        }
        return true;
      }
    }
    else {
      if(mergedRoomName.equals(other.mergedRoomName) && capacity == other.capacity && name.equals(other.name))
      {
        for (int i = 0; i < numberOfReservations(); i++)
        {
          if (!(reservedDates.get(i).equals(other.reservedDates.get(i)) && reservedTimeSlots.get(i).equals(other.reservedTimeSlots.get(i))))
          {
            return false;
          }
        }
        return true;
      }
    }
    return false;
  }

  /**
   * Creating string with room data
   * @return string with all necessary information about room
   */
  @Override public String toString()
  {
    return "Room{" + "name='" + name + '\'' + ", capacity=" + capacity
        + ", mergedRoomName='" + mergedRoomName + '\'' + ", reservedDates="
        + reservedDates + ", reservedTimeSlots=" + reservedTimeSlots + '}';
  }
}
