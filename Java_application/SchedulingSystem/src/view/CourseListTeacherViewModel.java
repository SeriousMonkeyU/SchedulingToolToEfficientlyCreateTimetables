package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.SchedulingManagerModel;
import model.Course;
import model.Teacher;

/**
 * A class responsible for managing content of the table in <code>TeacherToCourse</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class CourseListTeacherViewModel
{
  private ObservableList<CourseTeacherViewModel> list;
  private SchedulingManagerModel model;

  /**
   * Constructor initializing new list which will contain courses that teacher is teaching
   * @param model <code>SchedulingManagerModel</code> object
   */

  public CourseListTeacherViewModel(SchedulingManagerModel model)
  {
    list = FXCollections.observableArrayList();
    this.model = model;
  }

  /**
   * Getter for list
   * @return list of rows in table
   */

  public ObservableList<CourseTeacherViewModel> getList()
  {
    return list;
  }

  /**
   * Method updating content of the table with courses that passed as parameter teacher is teaching
   * @param teacher teacher whose courses will be displayed in the table
   */

  public void update(Teacher teacher)
  {
    list.clear();
    for (int i = 0; i < teacher.getNumberOfCourses(); i++)
    {
      list.add(new CourseTeacherViewModel(teacher.getCourse(i)));
    }
  }

  /**
   * Method adding course to the table
   * @param course course that will be added
   */

  public void add(Course course)
  {
    list.add(new CourseTeacherViewModel(course));
  }

  /**
   * Method removing course from the table
   * @param course course that will be removed
   */

  public void remove(Course course)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getCourseNameProperty().get().equals(course.getName())
          && list.get(i).getSemesterProperty().get() == course.getSemester()
          && list.get(i).getEctsProperty().get() == course.getEcts() && list.get(
          i).getClassLettersProperty().get().equals(course.getClassLetters()))
      {
        list.remove(i);
        break;
      }
    }
  }
}
