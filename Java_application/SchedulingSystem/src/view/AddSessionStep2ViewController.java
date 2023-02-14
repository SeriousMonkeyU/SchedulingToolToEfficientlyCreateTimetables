package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import mediator.SchedulingManagerModel;
import mediator.SessionHolder;
import mediator.TimeSlotHolder;
import model.*;

/**
 * A class that is controller for <code>AddSessionStep2View</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class AddSessionStep2ViewController
{
  @FXML private TableView<RoomViewModel> roomListTable;
  @FXML private TableColumn<RoomViewModel, String> roomColumn;
  @FXML private TableColumn<RoomViewModel, Number> capacityColumn;
  @FXML private Label errorLabel;
  @FXML private Label sizeLabel;
  @FXML private Label sessionLabel;



  private Region root;
  private SchedulingManagerModel model;
  private ViewHandler viewHandler;
  private RoomListViewModel viewModel;
  private SessionHolder sessionHolder;
  private TimeSlotHolder timeHolder;
  private int size;

  /**
   * Empty constructor, its purpose is taken over by <code>init</code> method
   */
  public AddSessionStep2ViewController()
  {

  }

  /**
   * Method initializing all settings of the window the first time it is loaded, serves also a kind of constructor for class
   * Initializing values in the table located in window
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
    this.viewModel = new RoomListViewModel(model);

    roomColumn.setCellValueFactory(cellData -> cellData.getValue()
        .getRoomName());
    capacityColumn.setCellValueFactory(cellData -> cellData.getValue()
        .getCapacity());

    roomListTable.setItems(viewModel.getList());


    sessionHolder = SessionHolder.getInstance();
    timeHolder = TimeSlotHolder.getInstance();


    reset();
  }

  /**
   * Method resetting window in GUI to its default state each time the window is opened again (e.g. resetting text in labels, text fields, refreshing table)
   */

  public void reset()
  {
    errorLabel.setText("");
    Session session = sessionHolder.getSession();
    size = model.getStudents(null,session.getStudentClass().getSemester(),session.getStudentClass().getClassLetters()).getNumberOfStudents();
    RoomList availableRooms = model.getAllAvailableRoomsOnDateAndTimeSlot(session.getDate(),timeHolder.getTimeSlot());
    sizeLabel.setText("Class has " + size + " students, remember to select room with right capacity");
    viewModel.update(availableRooms);
    sessionLabel.setText("Date: " + session.getDate() + " Time: " + timeHolder.getTimeSlot() + " Class: " +
        session.getStudentClass().getName() + " Course: " + session.getCourse().getName() + " Length: " + timeHolder.getTimeSlot().getNumberOfLessons());
    roomListTable.getSelectionModel().clearSelection();
  }

  /**
   * Getter for root
   * @return root of the scene
   */

  public Region getRoot()
  {
    return root;
  }

  @FXML private void reserveButtonPressed()
  {
    try
    {
      RoomViewModel selectedItem = roomListTable.getSelectionModel()
          .getSelectedItem();

      String name = selectedItem.getRoomName().get();
      int capacity = selectedItem.getCapacity().get();
      if (capacity < size)
      {
        throw new IllegalArgumentException("");
      }

      Room room = model.getRoomByName(name);
      Session session = sessionHolder.getSession();
      session.setRoom(room);
      session.setTimeSlot(timeHolder.getTimeSlot());
      sessionHolder.setSession(session);

      viewHandler.openView("step3");
    }
    catch (Exception e)
    {
      errorLabel.setText("Before you go to the next step, please select the room with right capacity");
    }
  }


  @FXML private void backButtonPressed()
  {
    viewHandler.openView("step1");
  }
}
