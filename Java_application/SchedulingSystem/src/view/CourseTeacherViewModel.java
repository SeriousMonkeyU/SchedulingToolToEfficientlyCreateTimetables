package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Course;
import model.Teacher;

/**
 * A class responsible for managing content of one row in the table in <code>TeacherToCourseView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class CourseTeacherViewModel
{
  private IntegerProperty semesterProperty;
  private StringProperty courseNameProperty;
  private StringProperty classLettersProperty;
  private IntegerProperty ectsProperty;

  /**
   * Constructor initializing properties based on the course passed as parameter
   * @param course course which is used to initialize properties
   */

  public CourseTeacherViewModel(Course course)
  {
    semesterProperty = new SimpleIntegerProperty(course.getSemester());
    courseNameProperty = new SimpleStringProperty(course.getName());
    classLettersProperty = new SimpleStringProperty(course.getClassLetters());
    ectsProperty = new SimpleIntegerProperty(course.getEcts());
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
   * Getter for course name property
   * @return course name property
   */

  public StringProperty getCourseNameProperty()
  {
    return courseNameProperty;
  }

  /**
   * Getter for class letter property
   * @return class letter property
   */

  public StringProperty getClassLettersProperty()
  {
    return classLettersProperty;
  }

  /**
   * Getter for ects  property
   * @return ects property
   */

  public IntegerProperty getEctsProperty()
  {
    return ectsProperty;
  }
}
