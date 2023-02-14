package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.SchedulingManagerModel;
import model.Session;
import model.SessionList;

/**
 * A class responsible for managing content of the table in <code>SessionDataView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class SessionListViewModel
{
  private ObservableList<SessionViewModel> list;
  private SchedulingManagerModel model;

  /**
   * Constructor initializing new list which will contain all the sessions in the system
   * @param model <code>SchedulingManagerModel</code> object
   */

  public SessionListViewModel(SchedulingManagerModel model)
  {
    this.model = model;
    list = FXCollections.observableArrayList();
    update();
  }
  /**
   * Getter for list
   * @return list of rows in table
   */


  public ObservableList<SessionViewModel> getList()
  {
    return list;
  }

  /**
   * Method updating content of the table with all the sessions in the system
   */

  public void update()
  {
    list.clear();
    for (int i = 0; i < model.getAllSessions().getNumberOfSessions(); i++)
    {
      list.add(new SessionViewModel(model.getSession(i)));
    }
  }

  /**
   * Method updating content of the table with sessions that are included in the list passed as parameter
   * @param sessionList list of sessions that will be displayed in the table
   */

  public void update(SessionList sessionList)
  {
    list.clear();

    for (int i = 0; i < sessionList.getNumberOfSessions(); i++)
    {
      list.add(new SessionViewModel(sessionList.getSession(i)));
    }
  }

  /**
   * Method adding sessions to the table
   * @param session session that will be added
   */

  public void add(Session session)
  {
    list.add(new SessionViewModel(session));
  }

  /**
   * Method removing session from the table
   * @param session session that will be removed
   */

  public void remove(Session session)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getDateProperty().get().equals(session.getDate().toString())
          && list.get(i).getStartTimeProperty().get().equals(session.getTimeSlot().getStartTime().toString())
          && list.get(i).getEndTimeProperty().get().equals(session.getTimeSlot().getEndTime().toString())
          && list.get(i).getStudentClassProperty().get().equals(session.getStudentClass().getName()) &&
          list.get(i).getCourseProperty().get().equals(session.getCourse().getName()) )
      {
        list.remove(i);
        break;
      }
    }
  }
}
