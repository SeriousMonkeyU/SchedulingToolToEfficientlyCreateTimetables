package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.SchedulingManagerModel;
import model.Course;
import model.CourseList;

/**
 * A class responsible for managing content of the table in <code>CourseDataView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class CourseListViewModel
{
  private ObservableList<CourseViewModel> list;
  private SchedulingManagerModel model;

  /**
   * Constructor initializing new list which will contain all the courses in the system
   * @param model <code>SchedulingManagerModel</code> object
   */

  public CourseListViewModel(SchedulingManagerModel model)
  {
    this.model = model;
    list = FXCollections.observableArrayList();
    update();
  }

  /**
   * Getter for list
   * @return list of rows in table
   */

  public ObservableList<CourseViewModel> getList()
  {
    return list;
  }

  /**
   * Method updating content of the table with all the courses in the system
   */

  public void update()
  {
    list.clear();
    for (int i = 0; i < model.getAllCourses().getNumberOfCourses(); i++)
    {
      list.add(new CourseViewModel(model.getCourse(i)));
    }
  }

  /**
   * Method updating content of the table with courses that are included in the list passed as parameter
   * @param courseList list of courses that will be displayed in the table
   */


  public void update(CourseList courseList)
  {
    list.clear();

    for (int i = 0; i < courseList.getNumberOfCourses(); i++)
    {
      list.add(new CourseViewModel(courseList.getCourse(i)));
    }
  }


  /**
   * Method adding course to the table
   * @param course course that will be added
   */


  public void add(Course course)
  {
    list.add(new CourseViewModel(course));
  }

  /**
   * Method removing course from the table
   * @param course course that will be removed
   */

  public void remove(Course course)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getNameProperty().get().equals(course.getName())
          && list.get(i).getSemesterProperty().get() == course.getSemester()
          && list.get(i).getEctsProperty().get() == course.getEcts()
          && list.get(i).getTeacherProperty()
          .get().equals(course.getTeacher().getId()))
      {
        list.remove(i);
        break;
      }
    }
  }
}
