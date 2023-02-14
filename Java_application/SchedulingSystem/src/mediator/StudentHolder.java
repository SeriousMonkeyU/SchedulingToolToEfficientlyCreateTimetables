package mediator;

import model.Student;

/**
 * A class which only purpose is to keep data of one <code>Student</code> object between windows in GUI. Class has private constructor and final static instance
 * variable that calls the constructor to ensure that only one instance of this class exists in the system (singleton class).
 *
 * @author Group 5
 * @version v1 December 2021
 */

public final class StudentHolder {

  private Student student;
  private final static StudentHolder INSTANCE = new StudentHolder();

  private StudentHolder() {}

  /**
   * Static method returning the only existing instance of this class
   * @return instance of <code>StudentHolder</code>
   */

  public static StudentHolder getInstance() {
    return INSTANCE;
  }
  /**
   * Method setting data of which object will the <code>StudentHolder</code> class keep
   * @param student <code>Student</code> object which data we want to keep in this holder
   */

  public void setStudent(Student student) {
    this.student = student;
  }

  /**
   * Method returning the object which data we kept in this class
   * @return <code>Student</code> object that the class was "holding"
   */

  public Student getStudent() {
    return this.student;
  }
}
