package model;

import java.util.ArrayList;

/**
 * A class representing list of all sessions in the system
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class SessionList
{
  private ArrayList<Session> sessions;

  /**
   * 0-argument constructing initializing new ArrayList of sessions
   */

  public SessionList()
  {
    sessions = new ArrayList<>();
  }

  /**
   * Method conducting a search based on passed parameters (search can be done based on 1 parameter or combinations of 2 parameters or all 3 parameters).
   * Method recognizes which based on which parameters it should conduct search and returns result of search. Purpose of this method is to filter data about session.
   * @param date date on which sessions should happen
   * @param studentClass student class that should be participating in sessions
   * @param nameOfTheRoom name of the room in which session should take place
   * @return <code>SessionList</code> containing sessions that match passed parameters
   */

  public SessionList getSessions(Date date, StudentClass studentClass,
      String nameOfTheRoom)
  {
    String filters = ""; // 1 time unit

    if (date != null) // 1 time unit
    {
      filters += "D"; // 1 time unit
    }
    if (studentClass != null) // 1 time unit
    {
      filters += "C"; // 1 time unit
    }
    if (nameOfTheRoom != null) // 1 time unit
    {
      filters += "N"; // 1 time unit
    }

    // 7 time units to get the right filter combination

    ArrayList<Session> filteredSessions = new ArrayList<>(); // 1 time unit

    switch (filters) // 1 time unit
    {

      // Only the first case will be analysed because it is worst case scenario and time complexity of this algorithm depends on upper bound

      // In analysis letter n represents number of sessions

      // Worst case scenario (all filters)

      case "DCN": // 1 time unit
      {
        for (int i = 0; i < sessions.size(); i++)
        // initialization 1 time unit, n + 1 checks, n increments
        {
          if (sessions.get(i).getDate().equals(date)
              // 1(session.get) + 1(getDate) + 7(equals Date class) time units each iteration
              && sessions.get(i).getStudentClass().equals(studentClass)
              // 1(session.get) + 1(getStudentClass) + 7(equals StudentClass class) time units each iteration
              && sessions.get(i).getRoom().getName().equals(nameOfTheRoom))
          //1(session.get) + 1(getRoom) + 1(getName) + 4(equals) time units each iteration
          {
            filteredSessions.add(sessions.get(i)); // 2 times units each iteration
          }
        }
        break;
      }
      case "DN":
      {
        for (int i = 0; i < sessions.size(); i++)
        {
          if (sessions.get(i).getDate().equals(date) && sessions.get(i)
              .getRoom().getName().equals(nameOfTheRoom))
          {
            filteredSessions.add(sessions.get(i));
          }
        }
        break;
      }
      case "DC":
      {
        for (int i = 0; i < sessions.size(); i++)
        {
          if (sessions.get(i).getDate().equals(date) && sessions.get(i)
              .getStudentClass().equals(studentClass))
          {
            filteredSessions.add(sessions.get(i));
          }
        }
        break;
      }
      case "CN":
      {
        for (int i = 0; i < sessions.size(); i++)
        {
          if (sessions.get(i).getStudentClass().equals(studentClass)
              && sessions.get(i).getRoom().getName().equals(nameOfTheRoom))
          {
            filteredSessions.add(sessions.get(i));
          }
        }
        break;
      }
      case "D":
      {
        for (int i = 0; i < sessions.size(); i++)
        {
          if (sessions.get(i).getDate().equals(date))
          {
            filteredSessions.add(sessions.get(i));
          }
        }
        break;
      }
      case "N":
      {
        for (int i = 0; i < sessions.size(); i++)
        {
          if (sessions.get(i).getRoom().getName().equals(nameOfTheRoom))
          {
            filteredSessions.add(sessions.get(i));
          }
        }
        break;
      }
      case "C":
      {
        for (int i = 0; i < sessions.size(); i++)
        {
          if (sessions.get(i).getStudentClass().equals(studentClass))
          {
            filteredSessions.add(sessions.get(i));
          }
        }
        break;
      }
    }

    // in total 29n + 2 time units to compare data according to filters

    SessionList tempSessionList = new SessionList(); // 1 time unit
    for(int i = 0; i < filteredSessions.size(); i ++) // initialization 1 time unit, n + 1 checks, n increments
    {
      tempSessionList.addSession(filteredSessions.get(i)); // 2 time units each iteration
    }
    return tempSessionList; // 1 time unit

    /*
There are two loops: one to filter data and one to print out, each iterates n times.

T(n) = 7 + 29n + 2 + 4n + 2 + 1 + 1 = 33n + 13
Ignoring constants and coefficients, time complexity of this algorithm is: T(n) = O(n)
*/
  }

  /**
   * Getters for session happening at certain date
   * @param date date on which sessions should happen
   * @return <code>SessionList</code> containing sessions happening at passed date
   */

  public SessionList getSessionsOnDate(Date date)
  {
    SessionList list = new SessionList();

    for (int i = 0; i < getNumberOfSessions(); i++)
    {
      if (date.equals(sessions.get(i).getDate()))
      {
        list.addSession(sessions.get(i));
      }
    }
    return list;
  }

  /**
   * Getter for session located at certain index in ArrayList
   * @param index number representing index
   * @return session at passed index
   */

  public Session getSession(int index)
  {
    return sessions.get(index);
  }

  /**
   * Getter for session equal to passed session
   * @param session session that we want to find in ArrayList
   * @return session equal to passed session or null if such a session does not exist
   */
  public Session getSession(Session session)
  {
    for (int i = 0; i < getNumberOfSessions(); i++)
    {
      if (session.equals(sessions.get(i)))
      {
        return sessions.get(i);
      }
    }
    return null;
  }

  /**
   * Getter for number of sessions
   * @return number of sessions (size of ArrayList)
   */
  public int getNumberOfSessions()
  {
    return sessions.size();
  }

  /**
   * Method adding session to the ArrayList of sessions
   * @param session session that will be added
   * @throws NullPointerException if the passed session is null
   */

  public void addSession(Session session)
  {
    if(session != null)
    {
      sessions.add(session);
    }
    else throw new NullPointerException();
  }

  /**
   * Method removing session equal to passed parameter from ArrayList of sessions
   * @param session session that will be removed
   */
  public void removeSession(Session session)
  {

      for (int i = 0; i < sessions.size(); i++)
      {
        if (session.equals(sessions.get(i)))
        {
          sessions.remove(i);
          break;
        }
    }
  }


}
