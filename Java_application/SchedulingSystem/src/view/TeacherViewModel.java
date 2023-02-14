package view;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Teacher;

/**
 * A class responsible for managing content of one row in the table in <code>TeacherDataView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class TeacherViewModel
{
  private StringProperty idProperty;
  private StringProperty emailProperty;
  private StringProperty courseProperty;

  /**
   * Constructor initializing properties based on the teacher passed as parameter
   * @param teacher teacher which is used to initialize properties
   */

  public TeacherViewModel(Teacher teacher)
  {
    idProperty = new SimpleStringProperty(teacher.getId());
    emailProperty = new SimpleStringProperty(teacher.getEmail());
    String courses = "";
    for (int i = 0; i < teacher.getNumberOfCourses();i++)
    {
      courses += teacher.getCourse(i).getName() + ", ";
    }
    courseProperty = new SimpleStringProperty(courses);
  }

  /**
   * Getter for id property
   * @return id property
   */

  public StringProperty getIdProperty()
  {
    return idProperty;
  }

  /**
   * Getter for email property
   * @return email property
   */

  public StringProperty getEmailProperty()
  {
    return emailProperty;
  }

  /**
   * Getter for course property
   * @return course property
   */

  public StringProperty getCourseProperty()
  {
    return courseProperty;
  }


}
