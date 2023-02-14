package mediator;

import model.Teacher;

/**
 * A class which only purpose is to keep data of one <code>Teacher</code> object between windows in GUI. Class has private constructor and final static instance
 * variable that calls the constructor to ensure that only one instance of this class exists in the system (singleton class).
 *
 * @author Group 5
 * @version v1 December 2021
 */

public final class TeacherHolder {

  private Teacher teacher;
  private final static TeacherHolder INSTANCE = new TeacherHolder();

  private TeacherHolder() {}

  /**
   * Static method returning the only existing instance of this class
   * @return instance of <code>TeacherHolder</code>
   */

  public static TeacherHolder getInstance() {
    return INSTANCE;
  }

  /**
   * Method setting data of which object will the <code>TeacherHolder</code> class keep
   * @param teacher <code>Teacher</code> object which data we want to keep in this holder
   */

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  /**
   * Method returning the object which data we kept in this class
   * @return <code>Teacher</code> object that the class was "holding"
   */

  public Teacher getTeacher() {
    return this.teacher;
  }
}
