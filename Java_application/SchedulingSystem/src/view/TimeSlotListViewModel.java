package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.SchedulingManagerModel;
import model.Course;
import model.CourseList;
import model.Student;
import model.TimeSlot;

import java.util.ArrayList;

/**
 * A class responsible for managing content of the table in <code>AddSessionStep1View</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class TimeSlotListViewModel
{
  private ObservableList<TimeSlotViewModel> list;
  private SchedulingManagerModel model;

  /**
   * Constructor initializing new list which will contain all available timeslots based on the criteria specified by user in the window
   * @param model <code>SchedulingManagerModel</code> object
   */

  public TimeSlotListViewModel(SchedulingManagerModel model)
  {
    this.model = model;
    list = FXCollections.observableArrayList();
    update();

  }

  /**
   * Getter for list
   * @return list of rows in table
   */

  public ObservableList<TimeSlotViewModel> getList()
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
   * Method updating content of the table with time slots that were included in the list passed as parameter
   * @param timeslots list of time slots that will be displayed in the table
   */

  public void update(ArrayList<TimeSlot> timeslots)
  {
    list.clear();

    for (int i = 0; i < timeslots.size(); i++)
    {
      list.add(new TimeSlotViewModel(timeslots.get(i)));
    }
  }



}


