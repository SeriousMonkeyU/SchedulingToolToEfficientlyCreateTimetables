package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.SchedulingManagerModel;
import model.Course;
import model.Student;

/**
 * A class responsible for managing content of the table in <code>StudentToCourseView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class CourseListStudentViewModel
{
  private ObservableList<CourseStudentViewModel> list;
  private SchedulingManagerModel model;

  /**
   * Constructor initializing new list which will contain courses that student is taking
   * @param model <code>SchedulingManagerModel</code> object
   * @param student student whose courses will be displayed in the table
   */

  public CourseListStudentViewModel(SchedulingManagerModel model, Student student)
  {
    this.model = model;
    list = FXCollections.observableArrayList();
    update(student);
  }

  /**
   * Getter for list
   * @return list of rows in table
   */
  public ObservableList<CourseStudentViewModel> getList()
  {
    return list;
  }

  /**
   * Method updating content of the table with courses that passed as parameter student is taking
   * @param student student whose courses will be displayed in the table
   */

  public void update(Student student)
  {
    list.clear();
    for (int i = 0; i < student.getNumberOfCourses(); i++)
    {
      list.add(new CourseStudentViewModel(student.getCourse(i)));
    }
  }

  /**
   * Method adding course to the table
   * @param course course that will be added
   */

  public void add(Course course)
  {
    list.add(new CourseStudentViewModel(course));
  }

  /**
   * Method removing course from the table
   * @param course course that will be removed
   */

  public void remove(Course course)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getCourseNameProperty().get().equals(course.getName()) && list.get(i).getTeacherNameProperty().get().equals(course.getTeacher().getId())
          && list.get(i).getSemesterProperty().get() == course.getSemester())
        list.remove(i);
        break;
      }
    }
  }



