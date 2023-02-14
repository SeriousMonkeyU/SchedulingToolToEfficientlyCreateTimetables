package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import mediator.SchedulingManagerModel;
import mediator.SessionHolder;
import mediator.TimeSlotHolder;
import model.Date;
import model.Room;
import model.RoomList;
import model.Session;

/**
 * A class that is controller for <code>AddSessionStep3View</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class AddSessionStep3ViewController
{

  @FXML private Label sessionLabel;

  private Region root;
  private SchedulingManagerModel model;
  private ViewHandler viewHandler;
  private SessionHolder sessionHolder;

  /**
   * Empty constructor, its purpose is taken over by <code>init</code> method
   */
  public AddSessionStep3ViewController()
  {

  }
  /**
   * Method initializing all settings of the window the first time it is loaded, serves also a kind of constructor for class
   *
   * @param viewHandler view handler coordinating all windows and controllers
   * @param model <code>SchedulingManagerModel</code> object
   * @param root root of the scene
   */

  public void init(ViewHandler viewHandler, SchedulingManagerModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    sessionHolder = SessionHolder.getInstance();



    reset();
  }

  /**
   * Method resetting window in GUI to its default state each time the window is opened again (e.g. resetting text in labels, text fields, refreshing table)
   */

  public void reset()
  {
    Session session = sessionHolder.getSession();
    sessionLabel.setText("Date: " + session.getDate() + " Time: " + session.getTimeSlot() + " Class: " +
        session.getStudentClass().getName() + " Room: " + session.getRoom().getName() + " Course: " + session.getCourse().getName() + " Length: " + session.getTimeSlot().getNumberOfLessons());

  }

  /**
   * Getter for root
   * @return root of the scene
   */

  public Region getRoot()
  {
    return root;
  }

  @FXML private void dateButtonPressed()
  {
    model.addSession(sessionHolder.getSession());
    model.writeScheduleToXmlFile();
    viewHandler.openView("sessionData");
  }

  @FXML private void semesterButtonPressed()
  {
    Session session = sessionHolder.getSession();

    while(true)
    {
      Session session1 = new Session(session.getDate(),session.getRoom(),session.getTimeSlot(),session.getStudentClass(),session.getCourse());
      model.addSession(session1);
      session.getDate().stepForward(7);
      if (!session.getDate().isBefore(new Date(15,6,2022)))
      {
        break;
      }
    }
    viewHandler.openView("sessionData");
  }

  @FXML private void cancelButtonPressed()
  {
    viewHandler.openView("sessionData");
  }
}
