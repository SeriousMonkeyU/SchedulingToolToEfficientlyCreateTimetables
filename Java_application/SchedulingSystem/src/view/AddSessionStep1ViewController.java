package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import mediator.SchedulingManagerModel;
import mediator.SessionHolder;
import mediator.TimeSlotHolder;
import model.*;



import java.util.ArrayList;

/**
 * A class that is controller for <code>AddSessionStep1View</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class AddSessionStep1ViewController
{
  @FXML private TableView<TimeSlotViewModel> timeSlotListTable;
  @FXML private TableColumn<TimeSlotViewModel, String> startTimeColumn;
  @FXML private TableColumn<TimeSlotViewModel, String> endTimeColumn;
  @FXML private Label errorLabel;
  @FXML private Label messageLabel;
  @FXML private ChoiceBox<Integer> lengthList;
  @FXML private ChoiceBox<String> studentClassList;
  @FXML private TextField dayField;
  @FXML private TextField monthField;
  @FXML private TextField yearField;
  @FXML private TextField courseField;

  private Region root;
  private SchedulingManagerModel model;
  private ViewHandler viewHandler;
  private TimeSlotListViewModel viewModel;

  /**
   * Empty constructor, its purpose is taken over by <code>init</code> method
   */

  public AddSessionStep1ViewController()
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
    this.viewModel = new TimeSlotListViewModel(model);

    startTimeColumn.setCellValueFactory(cellData -> cellData.getValue()
        .getStartTimeProperty());
    endTimeColumn.setCellValueFactory(cellData -> cellData.getValue()
        .getEndTimeProperty());

    timeSlotListTable.setItems(viewModel.getList());

    studentClassList.getItems().addAll("1X","2X","3X","4X","5X","6X","7X",
        "1Y","2Y","3Y","4Y","5Y","6Y","7Y","1Z","2Z","3Z","4Z","5Z","6Z","7Z","1DK","2DK","3DK","4DK","5DK","6DK","7DK");

    lengthList.getItems().addAll(2,3,4,6,8);

    reset();
  }

  /**
   * Method resetting window in GUI to its default state each time the window is opened again (e.g. resetting text in labels, text fields, refreshing table)
   */

  public void reset()
  {
    viewModel.update();
    lengthList.valueProperty().setValue(null);
    studentClassList.valueProperty().setValue(null);
    errorLabel.setText("");
    dayField.setText("");
    monthField.setText("");
    yearField.setText("");
    courseField.setText("");
    messageLabel.setText("");
    timeSlotListTable.getSelectionModel().clearSelection();
  }

  /**
   * Getter for root
   * @return root of the scene
   */

  public Region getRoot()
  {
    return root;
  }

  @FXML private void searchButtonPressed()
  {
    try
    {
      int day = Integer.parseInt(dayField.getText());
      int month = Integer.parseInt(monthField.getText());
      int year = Integer.parseInt(yearField.getText());
      Date date = new Date(day,month,year);
      if (date.isBefore(new Date()))
      {
        throw new IllegalArgumentException("Session cannot be created for past date");
      }
      Course course = model.getCourseByName(courseField.getText());
      if (course == null)
      {
        throw new IllegalStateException("");
      }
      int length = lengthList.getValue();
      String classSymbol = studentClassList.getValue();
      int semester = Integer.parseInt(classSymbol.substring(0,1));
      String classLetter = classSymbol.substring(1);

      ArrayList<TimeSlot> availableTimeSlots = new ArrayList<>();

      for (int i = 0; i < TimeSlot.START_TIMES.length; i++)
      {
        if (i + length - 1 < TimeSlot.START_TIMES.length)
        {
          availableTimeSlots.add(new TimeSlot(length,TimeSlot.START_TIMES[i],TimeSlot.END_TIMES[i + length - 1]));
        }
      }

      SessionList listOnDate = model.getSessionsOnDate(date);

      ArrayList<TimeSlot> availableTimeSlotsFiltered = new ArrayList<>();
      ArrayList<Integer> indexes = new ArrayList<>();

      for (int i = 0; i < listOnDate.getNumberOfSessions(); i++)
      {
        if (course.equals(listOnDate.getSession(i).getCourse())
            || classSymbol.equals(listOnDate.getSession(i).getStudentClass().getName())
        || course.getTeacher().equals(listOnDate.getSession(i).getCourse().getTeacher()))
        {
          for (int j = 0; j < availableTimeSlots.size(); j++)
          {
            if (availableTimeSlots.get(j).isDuringOtherTimeslot(listOnDate.getSession(i).getTimeSlot()) && !indexes.contains(j))
            {
              indexes.add(j);
            }
          }
        }
      }

      for (int i = 0; i < availableTimeSlots.size(); i++)
      {
        if (!indexes.contains(i))
        {
          availableTimeSlotsFiltered.add(availableTimeSlots.get(i));
        }
      }

      viewModel.update(availableTimeSlotsFiltered);
      SessionHolder sessionHolder = SessionHolder.getInstance();
      sessionHolder.setSession(new Session(date, new StudentClass(semester,classLetter),course));
    }
    catch (Exception e)
    {
      reset();
      errorLabel.setText("Fill all fields with correct data: " + e.getMessage());
    }
  }

  @FXML private void nextButtonPressed()
  {
    try
    {

        TimeSlotViewModel selectedItem = timeSlotListTable.getSelectionModel()
            .getSelectedItem();
        String startTimeString = selectedItem.getStartTimeProperty().get();
        String endTimeString = selectedItem.getEndTimeProperty().get();

        int hourStartTime = Integer.parseInt(startTimeString.split(":")[0]);
        int minuteStartTime = Integer.parseInt(startTimeString.split(":")[1]);

        int hourEndTime = Integer.parseInt(endTimeString.split(":")[0]);
        int minuteEndTime = Integer.parseInt(endTimeString.split(":")[1]);

        TimeSlot timeSlot = new TimeSlot(lengthList.getValue(),new Time(hourStartTime,minuteStartTime), new Time(hourEndTime,minuteEndTime));

        TimeSlotHolder timeHolder = TimeSlotHolder.getInstance();
        timeHolder.setTimeSlot(timeSlot);

        viewHandler.openView("step2");

    }
    catch (Exception e)
    {
      errorLabel.setText("Before you go to the next step, please select time slot");
    }
  }


  @FXML private void backButtonPressed()
  {
    viewHandler.openView("sessionData");
  }
}
