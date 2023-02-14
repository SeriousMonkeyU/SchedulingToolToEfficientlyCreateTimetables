package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import mediator.SchedulingManagerModel;
import model.Student;

import java.util.Optional;

/**
 * A class that is controller for <code>NavigationView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class NavigationViewController
{

  private Region root;
  private SchedulingManagerModel model;
  private ViewHandler viewHandler;

  /**
   * Empty constructor, its purpose is taken over by <code>init</code> method
   */

  public NavigationViewController()
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

  }

  /**
   * Getter for root
   * @return root of the scene
   */

  public Region getRoot()
  {
    return root;
  }

 @FXML private void studentButtonPressed()
 {
   viewHandler.openView("studentData");
 }

  @FXML private void teacherButtonPressed()
  {
    viewHandler.openView("teacherData");
  }

  @FXML private void courseButtonPressed()
  {
    viewHandler.openView("courseData");
  }

  @FXML private void sessionButtonPressed()
  {
    viewHandler.openView("sessionData");
  }

  @FXML private void importButtonPressed()
  {
    viewHandler.openView("importData");
  }

}
