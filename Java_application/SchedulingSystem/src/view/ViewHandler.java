package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import mediator.SchedulingManagerModel;

/**
 * A class responsible for coordinating all the scenes and controllers in GUI, allows switching the windows in GUI, loads fxml files
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class ViewHandler
{
  private Scene currentScene;
  private Stage primaryStage;
  private SchedulingManagerModel model;
  private StudentDataViewController studentDataViewController;
  private StudentToCourseViewController studentToCourseViewController;
  private AddStudentViewController addStudentViewController;
  private NavigationViewController navigationViewController;
  private ImportDataViewController importDataViewController;
  private CourseDataViewController courseDataViewController;
  private TeacherDataViewController teacherDataViewController;
  private SessionDataViewController sessionDataViewController;
  private RemoveSessionViewController removeSessionViewController;
  private AddSessionStep1ViewController addSessionStep1ViewController;
  private AddSessionStep2ViewController addSessionStep2ViewController;
  private AddSessionStep3ViewController addSessionStep3ViewController;
  private AddTeacherViewController addTeacherViewController;
  private TeacherToCourseViewController teacherToCourseViewController;

  /**
   * Constructor initializing the scene and model variables
   * @param model <code>SchedulingManagerModel</code> object
   */
  public ViewHandler(SchedulingManagerModel model)
  {
    this.currentScene = new Scene(new Region());
    this.model = model;
  }

  /**
   * Method initializing the stage and opening the first scene in GUI
   * @param primaryStage stage
   */

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("navigation");
  }

  /**
   * Method loading fxml files and opening views (this method allows switching the scenes in GUI)
   * @param id id representing one of the scenes in GUI
   */
  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "studentData":
        root = loadStudentDataView("StudentDataView.fxml");
        break;
      case "studentDetails":
        root = loadStudentToCourseView("StudentToCourseView.fxml");
        break;
      case "addStudent":
        root = loadAddStudentView("AddStudentView.fxml");
        break;
      case "navigation":
        root = loadNavigationView("NavigationView.fxml");
        break;
      case "importData":
        root = loadImportDataView("ImportDataView.fxml");
        break;
      case "courseData":
        root = loadCourseDataView("CourseDataView.fxml");
        break;
      case "teacherData":
        root = loadTeacherDataView("TeacherDataView.fxml");
        break;
      case "addTeacher":
        root = loadAddTeacherView("AddTeacherView.fxml");
        break;
      case "teacherDetails":
        root = loadTeacherToCourseView("TeacherToCourseView.fxml");
        break;
      case "sessionData":
        root = loadSessionDataView("SessionDataView.fxml");
        break;
      case "step1":
        root = loadAddSessionStep1View("AddSessionStep1View.fxml");
        break;
      case "step2":
        root = loadAddSessionStep2View("AddSessionStep2View.fxml");
        break;
      case "step3":
        root = loadAddSessionStep3View("AddSessionStep3View.fxml");
        break;
      case "removeSession":
        root = loadRemoveSessionView("RemoveSessionView.fxml");
        break;
    }

    currentScene.setRoot(root);

    String title = "";

    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }

    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  /**
   * Method closing the stage and effectively closing the whole GUI
   */

  public void closeView()
  {
    primaryStage.close();
  }

  private Region loadStudentDataView(String fxmlFile)
  {
    Region root = null;
    if (studentDataViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        studentDataViewController = loader.getController();
        studentDataViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      studentDataViewController.reset();
    }
    return studentDataViewController.getRoot();
  }


  private Region loadStudentToCourseView(String fxmlFile)
  {
    Region root = null;
    if (studentToCourseViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        studentToCourseViewController = loader.getController();
        studentToCourseViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      studentToCourseViewController.reset();
    }
    return studentToCourseViewController.getRoot();
  }


  private Region loadAddStudentView(String fxmlFile)
  {
    Region root = null;
    if (addStudentViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        addStudentViewController = loader.getController();
        addStudentViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      addStudentViewController.reset();
    }
    return addStudentViewController.getRoot();
  }

  private Region loadNavigationView(String fxmlFile)
  {
    Region root = null;
    if (navigationViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        navigationViewController = loader.getController();
        navigationViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      navigationViewController.reset();
    }
    return navigationViewController.getRoot();
  }

  private Region loadImportDataView(String fxmlFile)
  {
    Region root = null;
    if (importDataViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        importDataViewController = loader.getController();
        importDataViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      importDataViewController.reset();
    }
    return importDataViewController.getRoot();
  }

  private Region loadCourseDataView(String fxmlFile)
  {
    Region root = null;
    if (courseDataViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        courseDataViewController = loader.getController();
        courseDataViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      courseDataViewController.reset();
    }
    return courseDataViewController.getRoot();
  }

  private Region loadSessionDataView(String fxmlFile)
  {
    Region root = null;
    if (sessionDataViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        sessionDataViewController = loader.getController();
        sessionDataViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      sessionDataViewController.reset();
    }
    return sessionDataViewController.getRoot();
  }

  private Region loadTeacherDataView(String fxmlFile)
  {
    Region root = null;
    if (teacherDataViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        teacherDataViewController = loader.getController();
        teacherDataViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      teacherDataViewController.reset();
    }
    return teacherDataViewController.getRoot();
  }


  private Region loadRemoveSessionView(String fxmlFile)
  {
    Region root = null;
    if (removeSessionViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        removeSessionViewController = loader.getController();
        removeSessionViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      removeSessionViewController.reset();
    }
    return removeSessionViewController.getRoot();
  }


  private Region loadAddSessionStep1View(String fxmlFile)
  {
    Region root = null;
    if (addSessionStep1ViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        addSessionStep1ViewController = loader.getController();
        addSessionStep1ViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      addSessionStep1ViewController.reset();
    }
    return addSessionStep1ViewController.getRoot();
  }

  private Region loadAddSessionStep2View(String fxmlFile)
  {
    Region root = null;
    if (addSessionStep2ViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        addSessionStep2ViewController = loader.getController();
        addSessionStep2ViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      addSessionStep2ViewController.reset();
    }
    return addSessionStep2ViewController.getRoot();
  }

  private Region loadAddSessionStep3View(String fxmlFile)
  {
    Region root = null;
    if (addSessionStep3ViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        addSessionStep3ViewController = loader.getController();
        addSessionStep3ViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      addSessionStep3ViewController.reset();
    }
    return addSessionStep3ViewController.getRoot();
  }

  private Region loadAddTeacherView(String fxmlFile)
  {
    Region root = null;
    if (addTeacherViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        addTeacherViewController = loader.getController();
        addTeacherViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      addTeacherViewController.reset();
    }
    return addTeacherViewController.getRoot();
  }

  private Region loadTeacherToCourseView(String fxmlFile)
  {
    Region root = null;
    if (teacherToCourseViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        teacherToCourseViewController = loader.getController();
        teacherToCourseViewController.init(this, model,root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      teacherToCourseViewController.reset();
    }
    return teacherToCourseViewController.getRoot();
  }
}
