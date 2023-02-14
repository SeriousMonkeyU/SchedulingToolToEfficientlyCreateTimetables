package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import mediator.SchedulingManagerModel;
import mediator.StudentHolder;
import model.Course;
import model.Student;

import java.util.Optional;

/**
 * A class that is controller for <code>AddStudentView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class AddStudentViewController
{

  @FXML TextField nameField;
  @FXML TextField idField;
  @FXML ChoiceBox<String> classLetter;
  @FXML ChoiceBox<Integer> semesterList;
  @FXML Label errorLabel;


  private Region root;
  private SchedulingManagerModel model;
  private ViewHandler viewHandler;

  /**
   * Empty constructor, its purpose is taken over by <code>init</code> method
   */
  public AddStudentViewController()
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

    classLetter.getItems().addAll("X","Y", "Z","DK");
    semesterList.getItems().addAll(1,2,3,4,5,6,7);
    reset();

  }

  /**
   * Method resetting window in GUI to its default state each time the window is opened again (e.g. resetting text in labels, text fields, refreshing table)
   */


  public void reset()
  {
    nameField.setText("");
    errorLabel.setText("");
    idField.setText("");
    classLetter.valueProperty().setValue(null);
    semesterList.valueProperty().setValue(null);
  }

  /**
   * Getter for root
   * @return root of the scene
   */

  public Region getRoot()
  {
    return root;
  }


  @FXML private void confirmButtonPressed()
  {
    try
    {
      int semester;
      if (semesterList.getValue() == null)
      {
        semester = -1;
      }
      else {
        semester= semesterList.getValue();
      }

      String name = nameField.getText();
      String id = idField.getText();
      String classLetters = classLetter.getValue();

      Student student = new Student(name,id,semester,classLetters);
      boolean add = confirmation();
      if (add)
      {
        model.addStudent(student);
        for(int i = 0; i < model.getAllCourses().getNumberOfCourses(); i++)
        {
          if (model.getStudent(student).getSemester() == model.getCourse(i).getSemester()
              && model.getStudent(student).getClassLetters().equals(model.getCourse(i).getClassLetters()))
          {
            model.getStudent(student).assignToCourse(model.getCourse(i));
          }
        }
        errorLabel.setText("");
        model.writeToTextFile("Students.txt","studentFile");
        viewHandler.openView("studentData");
      }

    }
    catch (Exception e)
    {
      errorLabel.setText("Invalid student: " + e.getMessage());
      nameField.setText("");
      idField.setText("");
      classLetter.valueProperty().setValue(null);
      semesterList.valueProperty().setValue(null);
    }
  }

  @FXML private void backButtonPressed()
  {
    viewHandler.openView("studentData");
  }

  private boolean confirmation()
  {

    if (nameField.getText().equals("") || idField.getText().equals("") || semesterList.getValue() == null || classLetter.getValue() == null)
    {
      model.addStudent(new Student(nameField.getText(), idField.getText(), semesterList.getValue(),classLetter.getValue()));
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Adding new student");
    Optional<ButtonType> result = alert.showAndWait();

    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }
}
