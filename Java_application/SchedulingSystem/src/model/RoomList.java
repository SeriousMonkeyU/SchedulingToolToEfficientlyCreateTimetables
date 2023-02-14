package model;

import java.util.ArrayList;

/**
 * A class representing list of all rooms in the system
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class RoomList
{
  private ArrayList<Room> rooms;

  /**
   * 0-argument constructor initializing new ArrayList of rooms
   */
  public RoomList()
  {
    this.rooms = new ArrayList<>();

  }

  /**
   * Method adding room the ArrayList
   * @param room room object that will be added to list of rooms
   */
  public void addRoom(Room room)
  {
    rooms.add(room);
  }

  /**
   * Getter for number of rooms
   * @return size of ArrayList of rooms (number of rooms)
   */
  public int getNumberOfRooms()
  {
    return rooms.size();
  }

  /**
   * Getter for a room located at specific index in ArrayList
   * @param index number representing index
   * @return room located at specific index
   */

  public Room getRoom(int index)
  {
    return rooms.get(index);
  }

  /**
   * Getter for room equal to room passed as parameter
   * @param room room object for which we are looking in ArrayList
   * @return room equal to <code>room</code>, it there is no such a room returns null
   */
  public Room getRoom(Room room)
  {
    for (int i = 0; i < getNumberOfRooms(); i++)
    {
      if (room.equals(rooms.get(i)))
      {
        return rooms.get(i);
      }
    }
    return null;
  }

  /**
   * Method searching for rooms that are not reserved at given date and timeslot, the search is conducted with use of loops and with use of the property
   * of the system that indexes of dates and timeslots that represent one reservation are the same
   * @param date date at which rooms should be not reserved
   * @param timeslot timeslot at which rooms should not be reserved
   * @return <code>RoomList</code> containing only rooms that do not have reservation at given date and timeslot
   */

  public RoomList getAllAvailableRoomsOnDateAndTimeSlot(Date date, TimeSlot timeslot)
  {
    RoomList list = new RoomList(); // 1 time unit

    // C – constant value representing number of rooms in building (this number does not change in the system as number of rooms in certain building does not fluctuate)

    // n – number of sessions in the system

    for (int i = 0; i < getNumberOfRooms(); i++) // initialization 1 time unit, C + 1 checks, C increments
    {
      int timeCheck = rooms.get(i).getReservedTimeSlots().indexOf(timeslot); // 4 + C time units each iteration of outer loop

      boolean check = true; // 1 times unit each iteration of outer loop

      for (int k = 0; k < rooms.get(i).getReservedTimeSlots().size(); k++) // initialization 1 time unit, n + 1 checks, n increments
      {
        for (int j = 0; j < rooms.get(i).getReservedDates().size(); j++) // initialization 1 time unit, n + 1 checks, n increments
        {
          if (date.equals(rooms.get(i).getReservedDates().get(j)) && timeslot.equals(rooms.get(i).getReservedTimeSlots().get(k))) // 7(equals Date class) + 3 + 15(equals TimeSlot class) + 3 = 28 time units each iteration of inner loop
          {
            if (k == j) // 1 time unit each iteration of inner loop
            {
              check = false; // 1 time unit each iteration of inner loop
              break; // 1 time unit each iteration of inner loop
            }
          }

        }
      }

      if (timeCheck == -1 || check) // 2 time units each iteration of outer loop
      {
        list.addRoom(rooms.get(i)); // 2 time units each iteration of outer loop
      }
    }
      return list; // 1 time unit

      /*
There are three loops nested in each other. The first loop runs constant number of times, considering the fact that number of rooms in the building does not change under normal circumstances.
The second loop itself runs n times as well as the third loop.

Worst case scenario assumes that the inner loop always breaks only on its last iteration

T(n) = 1 + C + 1 + C + 5C + C^2 + 4C + 2 + 4n + 2 + 31C*n^2 + 1 =
	7 + 11C + 4n + C^2 + 31C*n^2
and ignoring constants and coefficients and less significant expressions, time complexity of this method is:
T(n) = O(n^2)
*/
  }

  /**
   * Getter for a room with specific name
   * @param name name of the room
   * @return room with name passed as parameter or null if such name does not exist
   */
  public Room getRoomByName(String name)
  {
    for (int i = 0; i < getNumberOfRooms(); i++)
    {
      if (name.equals(rooms.get(i).getName()))
      {
        return rooms.get(i);
      }
    }
    return null;
  }
}
