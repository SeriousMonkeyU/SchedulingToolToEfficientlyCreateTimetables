package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.*;

/**
 * A class responsible for managing content of one row in the table in <code>StudentDataView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class StudentViewModel
{
  private StringProperty nameProperty;
  private StringProperty idProperty;
  private IntegerProperty semesterProperty;
  private StringProperty classLetterProperty;

  /**
   * Constructor initializing properties based on the student passed as parameter
   * @param student student which is used to initialize properties
   */

  public StudentViewModel(Student student)
  {
    nameProperty = new SimpleStringProperty(student.getName());
    idProperty = new SimpleStringProperty(student.getId());
    semesterProperty = new SimpleIntegerProperty(student.getSemester());
    classLetterProperty = new SimpleStringProperty(student.getClassLetters());
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
   * Getter for id property
   * @return id property
   */

  public StringProperty getIdProperty()
  {
    return idProperty;
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
   * Getter for class letter property
   * @return class letter property
   */

  public StringProperty getClassLetterProperty()
  {
    return classLetterProperty;
  }


}
