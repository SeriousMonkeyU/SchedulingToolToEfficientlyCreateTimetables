package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.SchedulingManagerModel;
import model.RoomList;


/**
 * A class responsible for managing content of the table in <code>AddSessionStep2View</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class RoomListViewModel
{
  private ObservableList<RoomViewModel> list;
  private SchedulingManagerModel model;

  /**
   * Constructor initializing new list which will contain all available rooms at specific time slot
   * @param model <code>SchedulingManagerModel</code> object
   */

  public RoomListViewModel(SchedulingManagerModel model)
  {
    this.model = model;
    list = FXCollections.observableArrayList();
    update();

  }

  /**
   * Getter for list
   * @return list of rows in table
   */

  public ObservableList<RoomViewModel> getList()
  {
    return list;
  }

  /**
   * Method clearing content of the table
   */

  public void update()
  {
    list.clear();
  }

  /**
   * Method updating content of the table with rooms that were included in the list passed as parameter
   * @param rooms list of rooms that will be displayed in the table
   */

  public void update(RoomList rooms)
  {
    list.clear();

    for (int i = 0; i < rooms.getNumberOfRooms(); i++)
    {
      list.add(new RoomViewModel(rooms.getRoom(i)));
    }
  }
}
