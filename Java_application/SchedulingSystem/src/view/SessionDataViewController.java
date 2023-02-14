package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import mediator.SchedulingManagerModel;
import mediator.SessionHolder;
import model.*;

/**
 * A class that is controller for <code>SessionDataView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class SessionDataViewController
{
  @FXML private TableView<SessionViewModel> sessionListTable;
  @FXML private TableColumn<SessionViewModel, String> dateColumn;
  @FXML private TableColumn<SessionViewModel, String> startTimeColumn;
  @FXML private TableColumn<SessionViewModel, String> endTimeColumn;
  @FXML private TableColumn<SessionViewModel, String> roomColumn;
  @FXML private TableColumn<SessionViewModel, String> studentClassColumn;
  @FXML private TableColumn<SessionViewModel, String> courseColumn;
  @FXML private Label errorLabel;
  @FXML private ChoiceBox<String> studentClassList;
  @FXML private ChoiceBox<String> roomList;
  @FXML private TextField dayField;
  @FXML private TextField monthField;
  @FXML private TextField yearField;

  private Region root;
  private SchedulingManagerModel model;
  private ViewHandler viewHandler;
  private SessionListViewModel viewModel;

  /**
   * Empty constructor, its purpose is taken over by <code>init</code> method
   */

  public SessionDataViewController()
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
    this.viewModel = new SessionListViewModel(model);


    dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
    startTimeColumn.setCellValueFactory(cellData -> cellData.getValue()
        .getStartTimeProperty());
    endTimeColumn.setCellValueFactory(cellData -> cellData.getValue()
        .getEndTimeProperty());
    roomColumn.setCellValueFactory(cellData -> cellData.getValue()
        .getRoomProperty());
    studentClassColumn.setCellValueFactory(cellData -> cellData.getValue().getStudentClassProperty());
    courseColumn.setCellValueFactory(cellData -> cellData.getValue()
        .getCourseProperty());

    sessionListTable.setItems(viewModel.getList());


    for (int i = 0; i < model.getAllRooms().getNumberOfRooms();i++)
    {
      roomList.getItems().add(model.getAllRooms().getRoom(i).getName());
    }

    studentClassList.getItems().addAll("1X","2X","3X","4X","5X","6X","7X",
        "1Y","2Y","3Y","4Y","5Y","6Y","7Y","1Z","2Z","3Z","4Z","5Z","6Z","7Z","1DK","2DK","3DK","4DK","5DK","6DK","7DK");

    errorLabel.setText("");
    reset();
  }

  /**
   * Method resetting window in GUI to its default state each time the window is opened again (e.g. resetting text in labels, text fields, refreshing table)
   */


  public void reset()
  {
    studentClassList.valueProperty().setValue(null);
    roomList.valueProperty().setValue(null);
    errorLabel.setText("");
    dayField.setText("");
    monthField.setText("");
    yearField.setText("");
    viewModel.update();
    sessionListTable.getSelectionModel().clearSelection();




  }

  /**
   * Getter for root
   * @return root of the scene
   */

  public Region getRoot()
  {
    return root;
  }

  @FXML private void addButtonPressed()
  {
    viewHandler.openView("step1");
  }

  @FXML private void removeButtonPressed()
  {
    try
    {
      SessionViewModel selectedItem = sessionListTable.getSelectionModel()
          .getSelectedItem();

      Course course = model.getCourseByName(selectedItem.getCourseProperty().get());

      String[] dateString = selectedItem.getDateProperty().get().split("/");
      Date date = new Date(Integer.parseInt(dateString[0]),Integer.parseInt(dateString[1]),Integer.parseInt(dateString[2]));

      Room room = model.getRoomByName(selectedItem.getRoomProperty().get());

      int semester = Integer.parseInt(selectedItem.getStudentClassProperty().get().substring(0,1));
      String classLetter = selectedItem.getStudentClassProperty().get().substring(1);
      StudentClass studentClass = new StudentClass(semester,classLetter);

      int hourStartTime = Integer.parseInt(selectedItem.getStartTimeProperty().get().split(":")[0]);
      int minuteStartTime = Integer.parseInt(selectedItem.getStartTimeProperty().get().split(":")[1]);

      int hourEndTime = Integer.parseInt(selectedItem.getEndTimeProperty().get().split(":")[0]);
      int minuteEndTime = Integer.parseInt(selectedItem.getEndTimeProperty().get().split(":")[1]);

      TimeSlot timeSlot = new TimeSlot(2,new Time(hourStartTime,minuteStartTime), new Time(hourEndTime,minuteEndTime));


      Session session = new Session(date,room,timeSlot,studentClass,course);

      SessionHolder holder = SessionHolder.getInstance();
      holder.setSession(model.getSession(session));

      viewHandler.openView("removeSession");
      }
    catch (Exception e)
    {
      errorLabel.setText("Please select session before moving to the next window");
    }
  }

  @FXML private void resetFilterButtonPressed()
  {
    reset();
    viewModel.update();
  }


  @FXML private void filterButtonPressed()
  {
    try
    {
      Date date;
      if (dayField.getText().equals("") || monthField.getText().equals("") || yearField.getText().equals(""))
      {
        date = null;
      }
      else
      {
        int day = Integer.parseInt(dayField.getText());
        int month = Integer.parseInt(monthField.getText());
        int year = Integer.parseInt(yearField.getText());
        date = new Date(day,month,year);
      }

      String roomName= null;

      if (roomList.getValue() != null)
      {
         roomName = roomList.getValue();
      }

      StudentClass studentClass = null;
      if (studentClassList.getValue() != null)
      {
        int semester = Integer.parseInt(studentClassList.getValue().substring(0,1));
        String classLetter = studentClassList.getValue().substring(1);
        studentClass = new StudentClass(semester,classLetter);
      }


      SessionList list = model.getSessions(date,studentClass,roomName);
      viewModel.update(list);
      errorLabel.setText("");
      studentClassList.valueProperty().setValue(null);
      roomList.valueProperty().setValue(null);
      errorLabel.setText("");
      dayField.setText("");
      monthField.setText("");
      yearField.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText("Fill in at least one filter to filter content of the table");
      studentClassList.valueProperty().setValue(null);
      roomList.valueProperty().setValue(null);
      errorLabel.setText("");
      dayField.setText("");
      monthField.setText("");
      yearField.setText("");
      viewModel.update();
    }
  }

  @FXML private void backButtonPressed()
  {
    viewHandler.openView("navigation");
  }
}
