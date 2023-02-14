package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import mediator.SchedulingManagerModel;
import mediator.StudentHolder;
import model.*;

import java.util.Optional;

/**
 * A class that is controller for <code>CourseDataView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class CourseDataViewController
{
  @FXML private TableView<CourseViewModel> courseListTable;
  @FXML private TableColumn<CourseViewModel, Number> semesterColumn;
  @FXML private TableColumn<CourseViewModel, String> nameColumn;
  @FXML private TableColumn<CourseViewModel, Number> ectsColumn;
  @FXML private TableColumn<CourseViewModel, String> teacherColumn;
  @FXML private Label errorLabel;
  @FXML private TextField nameField;
  @FXML private ChoiceBox<Integer> semesterList;

  private Region root;
  private SchedulingManagerModel model;
  private ViewHandler viewHandler;
  private CourseListViewModel viewModel;

  /**
   * Empty constructor, its purpose is taken over by <code>init</code> method
   */

  public CourseDataViewController()
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

  public void init(ViewHandler viewHandler, SchedulingManagerModel model,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    this.viewModel = new CourseListViewModel(model);

    semesterColumn.setCellValueFactory(
        cellDate -> cellDate.getValue().getSemesterProperty());
    nameColumn.setCellValueFactory(
        cellData -> cellData.getValue().getNameProperty());
    ectsColumn.setCellValueFactory(
        cellData -> cellData.getValue().getEctsProperty());
    teacherColumn.setCellValueFactory(
        cellData -> cellData.getValue().getTeacherProperty());

    courseListTable.setItems(viewModel.getList());

    semesterList.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
    errorLabel.setText("");
  }

  /**
   * Method resetting window in GUI to its default state each time the window is opened again (e.g. resetting text in labels, text fields, refreshing table)
   */


  public void reset()
  {
    errorLabel.setText("");
    nameField.setText("");
    semesterList.valueProperty().setValue(null);
    viewModel.update();
    courseListTable.getSelectionModel().clearSelection();
  }

  /**
   * Getter for root
   * @return root of the scene
   */

  public Region getRoot()
  {
    return root;
  }

  @FXML private void removeButtonPressed()
  {
    try
    {
      CourseViewModel selectedItem = courseListTable.getSelectionModel()
          .getSelectedItem();
      boolean remove = confirmation();
      if (remove)
      {
        String courseName = selectedItem.getNameProperty().get();
        Teacher teacher = model.getTeacherById(
            selectedItem.getTeacherProperty().get());
        Course course = new Course(selectedItem.getSemesterProperty().get(),
            selectedItem.getNameProperty().get(),
            selectedItem.getEctsProperty().get(), teacher,
            courseName.substring(courseName.length()-1));
        model.removeCourse(course);
        viewModel.remove(course);
        courseListTable.getSelectionModel().clearSelection();
        model.writeToTextFile("Courses.txt", "courseFile");
      }
    }
    catch (Exception e)
    {
      errorLabel.setText("Item not found: " + e.getMessage());
    }
  }

  @FXML private void filterButtonPressed()
  {
    try
    {
      String nameFilter = nameField.getText();
      if(nameFilter.equals(""))
      {
        nameFilter = null;
      }
      int semesterFilter;
      if (semesterList.getValue() != null)
      {
        semesterFilter = semesterList.getValue();
      }
      else
      {
        semesterFilter = -1;
      }

      CourseList list = model.getCourses(nameFilter, semesterFilter);
      viewModel.update(list);
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText("Fill in at least one filter to filter content of the table");
      viewModel.update();
    }
  }

  @FXML private void backButtonPressed()
  {
    viewHandler.openView("navigation");
  }

  @FXML private void resetFilterButton()
  {
    reset();
  }


  private boolean confirmation()
  {
    int index = courseListTable.getSelectionModel().getSelectedIndex();
    CourseViewModel selectedItem = courseListTable.getItems().get(index);
    if(index < 0 || index > courseListTable.getItems().size())
    {
      return false;
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Removing course {" + selectedItem.getNameProperty().get() + ", semester: " + selectedItem.getSemesterProperty().get() + "}");
    Optional<ButtonType> result = alert.showAndWait();

    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }
}
