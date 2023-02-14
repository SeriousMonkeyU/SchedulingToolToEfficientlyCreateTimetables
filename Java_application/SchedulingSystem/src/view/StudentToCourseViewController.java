package view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import mediator.SchedulingManagerModel;
import mediator.StudentHolder;
import model.Course;
import model.Student;

import java.util.Optional;

/**
 * A class that is controller for <code>StudentToCourseView</code> window in GUI
 * @author Group 5
 * @version v1 December 2021
 */

public class StudentToCourseViewController
{

  @FXML Label studentLabel;
  @FXML ChoiceBox<Integer> semesterList;
  @FXML TextField courseField;
  @FXML Label errorLabel;
  @FXML private TableColumn<CourseStudentViewModel, Number> semesterColumn;
  @FXML private TableColumn<CourseStudentViewModel, String> nameColumn;
  @FXML private TableColumn<CourseStudentViewModel, String> teacherNameColumn;
  @FXML private TableView<CourseStudentViewModel> courseListTable;

  private Region root;
  private SchedulingManagerModel model;
  private ViewHandler viewHandler;
  private CourseListStudentViewModel viewModel;
  private StudentHolder holder;

  /**
   * Empty constructor, its purpose is taken over by <code>init</code> method
   */

  public StudentToCourseViewController()
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
     holder = StudentHolder.getInstance();
    Student student = holder.getStudent();
    this.viewModel = new CourseListStudentViewModel(model,student);

    nameColumn.setCellValueFactory(cellData -> cellData.getValue().getCourseNameProperty());
    teacherNameColumn.setCellValueFactory(cellData -> cellData.getValue()
        .getTeacherNameProperty());
    semesterColumn.setCellValueFactory(cellData -> cellData.getValue()
        .getSemesterProperty());

    courseListTable.setItems(viewModel.getList());
    semesterList.getItems().addAll(1,2,3,4,5,6,7);
    reset();

  }

  /**
   * Method resetting window in GUI to its default state each time the window is opened again (e.g. resetting text in labels, text fields, refreshing table)
   */


  public void reset()
  {
    Student student = holder.getStudent();
    String name = student.getName();
    String id = student.getId();
    int semester = student.getSemester();
    String classLetter = student.getClassLetters();
    viewModel.update(student);

    semesterList.valueProperty().setValue(null);
    courseField.setText("");
    errorLabel.setText("");

    studentLabel.setText(name + " - " + id + ", class: " + semester + classLetter);
  }

  /**
   * Getter for root
   * @return root of the scene
   */
  public Region getRoot()
  {
    return root;
  }



  @FXML private void backButtonPressed()
  {
    viewHandler.openView("studentData");
  }

  @FXML private void assignToCourseButtonPressed()
  {
    try
    {
      int semester = semesterList.getValue();
      String name = courseField.getText();
      Course course = model.getCourses(name, semester).getCourse(0);

      boolean assign = confirmationAssign();
      if (assign)
      {
        model.assignStudentToCourse(holder.getStudent(),course);
        model.writeToTextFile("Students.txt","studentFile");
        viewModel.update(holder.getStudent());
      }
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText("Please provide name and semester of the course");
      courseField.setText("");
      semesterList.valueProperty().setValue(null);
    }
  }

  @FXML private void unassignFromCourseButtonPressed()
  {
    try
    {
      CourseStudentViewModel selectedItem = courseListTable.getSelectionModel()
          .getSelectedItem();
      boolean remove = confirmationRemove();
      if (remove)
      {
        Course course = model.getCourseByNameAndTeacher(selectedItem.getCourseNameProperty().get(), model.getTeacherById(selectedItem.getTeacherNameProperty().get()));
        model.removeStudentFromCourse(holder.getStudent(),course);
        model.writeToTextFile("Students.txt","studentFile");
        viewModel.update(holder.getStudent());
        courseListTable.getSelectionModel().clearSelection();
      }
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText("Item not found: " + e.getMessage());
    }
  }

  private boolean confirmationAssign()
  {

    if (courseField.getText().equals("") || semesterList.getValue() == null)
    {
      return false;
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Assigning new course to this student");
    Optional<ButtonType> result = alert.showAndWait();

    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

  private boolean confirmationRemove()
  {
    int index = courseListTable.getSelectionModel().getSelectedIndex();
    CourseStudentViewModel selectedItem = courseListTable.getItems().get(index);
    if(index < 0 || index > courseListTable.getItems().size())
    {
      return false;
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Removing student from course{" + selectedItem.getCourseNameProperty().get() +  ", semester: " + selectedItem.getSemesterProperty().get() + "}");
    Optional<ButtonType> result = alert.showAndWait();

    return (result.isPresent()) && (result.get() == ButtonType.OK);


  }
}
