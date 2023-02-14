package view;

import javafx.beans.property.*;
import model.*;

/**
 * A class responsible for managing content of one row in the table in <code>CourseDataView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class CourseViewModel
{
  private StringProperty nameProperty;
  private IntegerProperty semesterProperty;
  private IntegerProperty ectsProperty;
  private StringProperty teacherProperty;

  /**
   * Constructor initializing properties based on the course passed as parameter
   * @param course course which is used to initialize properties
   */

  public CourseViewModel (Course course)
  {
    nameProperty = new SimpleStringProperty(course.getName());
    semesterProperty = new SimpleIntegerProperty(course.getSemester());
    ectsProperty = new SimpleIntegerProperty(course.getEcts());
    teacherProperty = new SimpleStringProperty(course.getTeacher().getId());
  }

  /**
   * Getter for name property
   * @return name property
   */

  public StringProperty getNameProperty()
  {
    return nameProperty;
  }

  /**
   * Getter for semester property
   * @return semester property
   */

  public IntegerProperty getSemesterProperty()
  {
    return semesterProperty;
  }

  /**
   * Getter for ects property
   * @return ects property
   */

  public IntegerProperty getEctsProperty()
  {
    return ectsProperty;
  }

  /**
   * Getter for teacher property
   * @return teacher property
   */

  public StringProperty getTeacherProperty()
  {
    return teacherProperty;
  }
}
