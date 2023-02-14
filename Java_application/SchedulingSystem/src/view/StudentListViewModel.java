package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.SchedulingManagerModel;
import model.Student;
import model.StudentList;

/**
 * A class responsible for managing content of the table in <code>StudentDataView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class StudentListViewModel
{
  private ObservableList<StudentViewModel> list;
  private SchedulingManagerModel model;

  /**
   * Constructor initializing new list which will contain all the students in the system
   * @param model <code>SchedulingManagerModel</code> object
   */

  public StudentListViewModel(SchedulingManagerModel model)
  {
    this.model = model;
    list = FXCollections.observableArrayList();
    update();
  }

  /**
   * Getter for list
   * @return list of rows in table
   */

  public ObservableList<StudentViewModel> getList()
  {
    return list;
  }

  /**
   * Method updating content of the table with all the students in the system
   */


  public void update()
  {
    list.clear();
    for (int i = 0; i < model.getAllStudents().getNumberOfStudents(); i++)
    {
      list.add(new StudentViewModel(model.getStudent(i)));
    }
  }

  /**
   * Method updating content of the table with students that are included in the list passed as parameter
   * @param studentList list of students that will be displayed in the table
   */

  public void update(StudentList studentList)
  {
    list.clear();
    for (int i = 0; i < studentList.getNumberOfStudents(); i++)
    {
      list.add(new StudentViewModel(studentList.getStudent(i)));
    }
  }

  /**
   * Method adding student to the table
   * @param student student that will be added
   */

  public void add(Student student)
  {
    list.add(new StudentViewModel(student));
  }

  /**
   * Method removing student from the table
   * @param student student that will be removed
   */

  public void remove(Student student)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getNameProperty().get().equals(student.getName()) && list.get(i).getIdProperty().get().equals(student.getId())
      && list.get(i).getSemesterProperty().get() == student.getSemester() && list.get(i).getClassLetterProperty().get().equals(student.getClassLetters()))
      {
        list.remove(i);
        break;
      }
    }
  }
}
