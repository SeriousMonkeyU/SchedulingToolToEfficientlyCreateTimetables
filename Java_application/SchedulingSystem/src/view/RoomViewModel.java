package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Room;

/**
 * A class responsible for managing content of one row in the table in <code>AddSessionStep2View</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class RoomViewModel
{
  private StringProperty roomName;
  private IntegerProperty capacity;

  /**
   * Constructor initializing properties based on the room passed as parameter
   * @param room room which is used to initialize properties
   */

  public RoomViewModel(Room room)
  {
    roomName = new SimpleStringProperty(room.getName());
    capacity = new SimpleIntegerProperty(room.getCapacity());
  }

  /**
   * Getter for room name property
   * @return room name property
   */

  public StringProperty getRoomName()
  {
    return roomName;
  }

  /**
   * Getter for capacity property
   * @return capacity property
   */

  public IntegerProperty getCapacity()
  {
    return capacity;
  }
}
