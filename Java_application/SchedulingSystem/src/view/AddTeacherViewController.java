package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import mediator.SchedulingManagerModel;
import model.Teacher;

import java.util.Optional;

/**
 * A class that is controller for <code>AddTeacherView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class AddTeacherViewController
{

  @FXML TextField idField;
  @FXML Label errorLabel;

  private Region root;
  private SchedulingManagerModel model;
  private ViewHandler viewHandler;

  /**
   * Empty constructor, its purpose is taken over by <code>init</code> method
   */

  public AddTeacherViewController()
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

    reset();

  }

  /**
   * Method resetting window in GUI to its default state each time the window is opened again (e.g. resetting text in labels, text fields, refreshing table)
   */


  public void reset()
  {
    errorLabel.setText("");
    idField.setText("");
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
      String id = idField.getText();

      Teacher teacher = new Teacher(id);
      boolean add = confirmation();
      if (add)
      {
        model.addTeacher(teacher);
        viewHandler.openView("teacherData");
      }

    }
    catch (Exception e)
    {
      errorLabel.setText("Invalid teacher: " + e.getMessage());
      idField.setText("");
    }
  }

  @FXML private void backButtonPressed()
  {
    viewHandler.openView("teacherData");
  }

  private boolean confirmation()
  {

    if (idField.getText().equals(""))
    {
      model.addTeacher(new Teacher(idField.getText()));
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Adding new teacher");
    Optional<ButtonType> result = alert.showAndWait();

    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }
}
