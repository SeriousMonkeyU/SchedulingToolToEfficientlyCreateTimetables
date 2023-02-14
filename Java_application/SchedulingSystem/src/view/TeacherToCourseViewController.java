package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import mediator.SchedulingManagerModel;
import mediator.TeacherHolder;
import model.Course;
import model.Teacher;

import java.util.Optional;

/**
 * A class that is controller for <code>TeacherToCourseView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class TeacherToCourseViewController
{
  @FXML Label teacherLabel;
  @FXML ChoiceBox<Integer> semesterList;
  @FXML TextField courseField;
  @FXML Label errorLabel;
  @FXML private TableColumn<CourseTeacherViewModel, Number> semesterColumn;
  @FXML private TableColumn<CourseTeacherViewModel, String> classLetterColumn;
  @FXML private TableColumn<CourseTeacherViewModel, String> courseNameColumn;
  @FXML private TableColumn<CourseTeacherViewModel, Number> ectsColumn;
  @FXML private TableView<CourseTeacherViewModel> courseListTable;

  private Region root;
  private SchedulingManagerModel model;
  private ViewHandler viewHandler;
  private CourseListTeacherViewModel viewModel;
  private TeacherHolder holder;

  /**
   * Empty constructor, its purpose is taken over by <code>init</code> method
   */

  public TeacherToCourseViewController()
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
    holder = TeacherHolder.getInstance();
    this.viewModel = new CourseListTeacherViewModel(model);

    semesterColumn.setCellValueFactory(
        cellData -> cellData.getValue().getSemesterProperty());
    classLetterColumn.setCellValueFactory(
        cellData -> cellData.getValue().getClassLettersProperty());
    courseNameColumn.setCellValueFactory(
        cellData -> cellData.getValue().getCourseNameProperty());
    ectsColumn.setCellValueFactory(
        cellData -> cellData.getValue().getEctsProperty());

    courseListTable.setItems(viewModel.getList());

    semesterList.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
    reset();
  }

  /**
   * Method resetting window in GUI to its default state each time the window is opened again (e.g. resetting text in labels, text fields, refreshing table)
   */


  public void reset()
  {
    Teacher teacher = holder.getTeacher();
    String id = teacher.getId();
    String email = teacher.getEmail();
    viewModel.update(teacher);

    semesterList.valueProperty().setValue(null);
    courseField.setText("");
    errorLabel.setText("");

    teacherLabel.setText(id + " - " + email);
  }
  /**
   * Getter for root
   * @return root of the scene
   */

  public Region getRoot()
  {
    return root;
  }

  @FXML private void assignToCourseButtonPressed()
  {
    try
    {
      int semester = semesterList.getValue();
      String name = courseField.getText();


      boolean assign = confirmationAssign();
      if (assign)
      {
        Course tmp = model.getCourses(name, semester).getCourse(0);
        if (!tmp.hasTeacher())
        {
          tmp.setTeacher(holder.getTeacher());
          model.assignTeacherToCourse(holder.getTeacher(), tmp);
        }
        else
        {
          model.removeCourse(tmp);
          Course course = new Course(semester,name, tmp.getEcts(), holder.getTeacher(), tmp.getClassLetters());
          model.getAllCourses().addCourse(course);
          model.assignTeacherToCourse(holder.getTeacher(), course);
        }
        model.writeToTextFile("Courses.txt", "courseFile");
        viewModel.update(holder.getTeacher());
      }
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText(
          "Please provide proper course name and semester");
      courseField.setText("");
      semesterList.valueProperty().setValue(null);
    }
  }

  @FXML private void unassignFromCourseButtonPressed()
  {
    try
    {
      CourseTeacherViewModel selectedItem = courseListTable.getSelectionModel()
          .getSelectedItem();
      boolean remove = confirmationRemove();
      if (remove)
      {
        Course course = model.getCourseByNameAndTeacher(selectedItem.getCourseNameProperty().get(),
            holder.getTeacher());
        if (model.getCourses(selectedItem.getCourseNameProperty().get(),selectedItem.getSemesterProperty().get()).getNumberOfCourses() > 1)
        {
          model.removeTeacherFromCourse(holder.getTeacher(), course);
          model.removeCourse(model.getCourseByNameAndTeacher(course.getName(),holder.getTeacher()));
        }
        else
        {
          model.removeTeacherFromCourse(holder.getTeacher(), course);
          course.setTeacher(null);
        }
        model.writeToTextFile("Courses.txt", "courseFile");
        viewModel.update(holder.getTeacher());
        courseListTable.getSelectionModel().clearSelection();
      }
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText("Item not found: " + e.getMessage());
    }
  }

  @FXML private void backButtonPressed()
  {
    viewHandler.openView("teacherData");
  }

  private boolean confirmationAssign()
  {
    if (courseField.getText().equals("") || semesterList.getValue() == null)
    {
      return false;
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Assigning selected course to this teacher");
    Optional<ButtonType> result = alert.showAndWait();

    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

  private boolean confirmationRemove()
  {
    int index = courseListTable.getSelectionModel().getSelectedIndex();
    CourseTeacherViewModel selectedItem = courseListTable.getItems().get(index);
    if (index < 0 || index > courseListTable.getItems().size())
    {
      return false;
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(
        "Removing teacher from course {" + selectedItem.getCourseNameProperty()
            .get() + ", semester: " + selectedItem.getSemesterProperty().get()
            + ", class: " + selectedItem.getClassLettersProperty().get() + "}");
    Optional<ButtonType> result = alert.showAndWait();

    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

}
