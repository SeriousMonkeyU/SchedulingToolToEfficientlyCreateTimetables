package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Session;

/**
 * A class responsible for managing content of one row in the table in <code>SessionDataView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class SessionViewModel
{
  private StringProperty dateProperty;
  private StringProperty startTimeProperty;
  private StringProperty endTimeProperty;
  private StringProperty roomProperty;
  private StringProperty studentClassProperty;
  private StringProperty courseProperty;

  /**
   * Constructor initializing properties based on the session passed as parameter
   * @param session session which is used to initialize properties
   */

  public SessionViewModel(Session session)
  {
    dateProperty = new SimpleStringProperty(session.getDate().toString());
    startTimeProperty = new SimpleStringProperty(session.getTimeSlot().getStartTime().toString());
    endTimeProperty = new SimpleStringProperty(session.getTimeSlot().getEndTime().toString());
    roomProperty = new SimpleStringProperty(session.getRoom().getName());
    studentClassProperty = new SimpleStringProperty(session.getStudentClass().getName());
    courseProperty = new SimpleStringProperty(session.getCourse().getName());
  }

  /**
   * Getter for date property
   * @return date property
   */

  public StringProperty getDateProperty()
  {
    return dateProperty;
  }

  /**
   * Getter for start time property
   * @return start time property
   */

  public StringProperty getStartTimeProperty()
  {
    return startTimeProperty;
  }

  /**
   * Getter for end time property
   * @return end time property
   */

  public StringProperty getEndTimeProperty()
  {
    return endTimeProperty;
  }

  /**
   * Getter for room property
   * @return room property
   */

  public StringProperty getRoomProperty()
  {
    return roomProperty;
  }

  /**
   * Getter for student class property
   * @return student class property
   */

  public StringProperty getStudentClassProperty()
  {
    return studentClassProperty;
  }

  /**
   * Getter for course property
   * @return course property
   */

  public StringProperty getCourseProperty()
  {
    return courseProperty;
  }



}
