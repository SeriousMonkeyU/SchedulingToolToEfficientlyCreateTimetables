package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.SchedulingManagerModel;
import model.Teacher;
import model.TeacherList;

/**
 * A class responsible for managing content of the table in <code>TeacherDataView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class TeacherListViewModel
{
  private ObservableList<TeacherViewModel> list;
  private SchedulingManagerModel model;

  /**
   * Constructor initializing new list which will contain all the teachers in the system
   * @param model <code>SchedulingManagerModel</code> object
   */

  public TeacherListViewModel(SchedulingManagerModel model)
  {
    this.model = model;
    list = FXCollections.observableArrayList();
    update();
  }

  /**
   * Getter for list
   * @return list of rows in table
   */

  public ObservableList<TeacherViewModel> getList()
  {
    return list;
  }

  /**
   * Method updating content of the table with all the teachers in the system
   */

  public void update()
  {
    list.clear();
    for (int i = 0; i < model.getAllTeachers().getNumberOfTeachers(); i++)
    {
      list.add(new TeacherViewModel(model.getTeacher(i)));
    }
  }

  /**
   * Method updating content of the table with teachers that are included in the list passed as parameter
   * @param teacherList list of teachers that will be displayed in the table
   */

  public void update(TeacherList teacherList)
  {
    list.clear();
    for(int i=0; i< teacherList.getNumberOfTeachers(); i++){
      list.add(new TeacherViewModel(teacherList.getTeacher(i)));
    }
  }

  /**
   * Method adding teacher to the table
   * @param teacher teacher that will be added
   */

  public void add(Teacher teacher)
  {
    list.add(new TeacherViewModel(teacher));
  }

  /**
   * Method removing teacher from the table
   * @param teacher teacher that will be removed
   */

  public void remove(Teacher teacher)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getEmailProperty().get().equals(teacher.getEmail()) && list.get(i).getIdProperty().get().equals(teacher.getId()))
      {
        list.remove(i);
        break;
      }
    }
  }
}
