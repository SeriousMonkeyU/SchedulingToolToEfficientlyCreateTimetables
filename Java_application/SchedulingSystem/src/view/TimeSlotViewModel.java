package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.TimeSlot;

/**
 * A class responsible for managing content of one row in the table in <code>AddSessionStep1View</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class TimeSlotViewModel
{
  private StringProperty startTimeProperty;
  private StringProperty endTimeProperty;

  /**
   * Constructor initializing properties based on the time slot passed as parameter
   * @param timeSlot time slot which is used to initialize properties
   */

  public TimeSlotViewModel(TimeSlot timeSlot)
  {
    startTimeProperty = new SimpleStringProperty(timeSlot.getStartTime().toString());
    endTimeProperty = new SimpleStringProperty(timeSlot.getEndTime().toString());

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
}
