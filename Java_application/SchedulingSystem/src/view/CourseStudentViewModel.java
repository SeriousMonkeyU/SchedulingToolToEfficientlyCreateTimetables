package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Course;

/**
 * A class responsible for managing content of one row in the table in <code>StudentToCourseView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class CourseStudentViewModel
{
  private IntegerProperty semesterProperty;
  private StringProperty courseNameProperty;
  private StringProperty teacherNameProperty;

  /**
   * Constructor initializing properties based on the course passed as parameter
   * @param course course which is used to initialize properties
   */

  public CourseStudentViewModel(Course course)
  {
    semesterProperty = new SimpleIntegerProperty(course.getSemester());
    courseNameProperty = new SimpleStringProperty(course.getName());
    teacherNameProperty = new SimpleStringProperty(course.getTeacher().getId());
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
   * Getter for teacher name property
   * @return teacher name property
   */

  public StringProperty getTeacherNameProperty()
  {
    return teacherNameProperty;
  }
}

