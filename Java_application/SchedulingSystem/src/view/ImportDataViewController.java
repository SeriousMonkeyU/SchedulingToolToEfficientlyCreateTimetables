package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import mediator.SchedulingManagerModel;
import model.Student;

import java.util.Optional;

/**
 * A class that is controller for <code>ImportDataView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class ImportDataViewController
{

  @FXML private Label errorLabel;
  @FXML private TextField fileNameField;
  @FXML private ChoiceBox<String> typeList;

  private Region root;
  private SchedulingManagerModel model;
  private ViewHandler viewHandler;

  /**
   * Empty constructor, its purpose is taken over by <code>init</code> method
   */

  public ImportDataViewController()
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

    typeList.getItems().addAll("Student file", "Course file", "Room file");

    reset();
  }

  /**
   * Method resetting window in GUI to its default state each time the window is opened again (e.g. resetting text in labels, text fields, refreshing table)
   */


  public void reset()
  {
    typeList.valueProperty().setValue(null);
    errorLabel.setText("");
    fileNameField.setText("");
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
    viewHandler.openView("navigation");
  }

  @FXML private void confirmButtonPressed()
  {
    try
    {
      String fileName = fileNameField.getText();
      String typeOfData = null;
      switch (typeList.getValue())
      {
        case "Student file":
          typeOfData = "studentFile";
          break;
        case "Course file":
          typeOfData = "courseFile";
          break;
        case "Room file":
          typeOfData = "roomFile";
          break;
      }

      boolean importData = confirmation();

      typeList.valueProperty().setValue(null);
      fileNameField.setText("");

      if (importData)
      {
        model.importTextFile(fileName,typeOfData);
        switch (typeOfData)
        {
          case "studentFile":
            model.writeToTextFile("Students.txt",typeOfData);
            break;
          case "courseFile":
            model.writeToTextFile("Courses.txt",typeOfData);
            break;
          case "roomFile":
            model.writeToTextFile("Rooms.txt",typeOfData);
            break;
        }

      }

    }
    catch (Exception e)
    {
      reset();
      errorLabel.setText("Remember that both fields need to be filled and enter correct file name");
    }
  }

  private boolean confirmation()
  {
    if (fileNameField.getText().equals("") || typeList.getValue() == null)
    {
      throw new IllegalArgumentException();
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Importing new data. Please be aware that this process will irreversibly overwrite current data stored in " + typeList.getValue().toLowerCase());
    Optional<ButtonType> result = alert.showAndWait();

    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }
}
