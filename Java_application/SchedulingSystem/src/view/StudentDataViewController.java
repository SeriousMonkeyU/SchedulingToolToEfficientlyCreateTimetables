package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import mediator.SchedulingManagerModel;
import mediator.StudentHolder;
import model.Student;
import model.StudentList;

import java.util.Optional;

/**
 * A class that is controller for <code>StudentDataView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class StudentDataViewController
{
  @FXML private TableView<StudentViewModel> studentListTable;
  @FXML private TableColumn<StudentViewModel, String> nameColumn;
  @FXML private TableColumn<StudentViewModel, String> idColumn;
  @FXML private TableColumn<StudentViewModel, Number> semesterColumn;
  @FXML private TableColumn<StudentViewModel, String> classLetterColumn;
  @FXML private Label errorLabel;
  @FXML private ChoiceBox<Integer> semesterList;
  @FXML private ChoiceBox<String> classLetterList;
  @FXML private TextField nameField;

  private Region root;
  private SchedulingManagerModel model;
  private ViewHandler viewHandler;
  private StudentListViewModel viewModel;

  /**
   * Empty constructor, its purpose is taken over by <code>init</code> method
   */

  public StudentDataViewController()
  {

  }
  /**
   * Method initializing all settings of the window the first time it is loaded, serves also a kind of constructor for class
   * Initializing values in the table located in window
   *
   * @param viewHandler view handler coordinating all windows and controllers
   * @param model <code>SchedulingManagerModel</code> object
   * @param root root of the scene
   */

  public void init(ViewHandler viewHandler, SchedulingManagerModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    this.viewModel = new StudentListViewModel(model);

    nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    idColumn.setCellValueFactory(cellData -> cellData.getValue()
        .getIdProperty());
    semesterColumn.setCellValueFactory(cellData -> cellData.getValue()
        .getSemesterProperty());
    classLetterColumn.setCellValueFactory(cellData -> cellData.getValue()
        .getClassLetterProperty());

    studentListTable.setItems(viewModel.getList());


    semesterList.getItems().addAll(1,2,3,4,5,6,7);
    classLetterList.getItems().addAll("X","Y","Z","DK");
    errorLabel.setText("");
  }

  /**
   * Method resetting window in GUI to its default state each time the window is opened again (e.g. resetting text in labels, text fields, refreshing table)
   */


  public void reset()
  {
    errorLabel.setText("");
    nameField.setText("");
    semesterList.valueProperty().set(null);
    classLetterList.valueProperty().setValue(null);
    viewModel.update();
    studentListTable.getSelectionModel().clearSelection();
  }
  /**
   * Getter for root
   * @return root of the scene
   */


  public Region getRoot()
  {
    return root;
  }


  @FXML private void addButtonPressed()
  {
    viewHandler.openView("addStudent");
  }

  @FXML private void removeButtonPressed()
  {
    try
    {
      StudentViewModel selectedItem = studentListTable.getSelectionModel()
          .getSelectedItem();
      boolean remove = confirmation();
      if (remove)
      {
        Student student = new Student(selectedItem.getNameProperty().get(), selectedItem.getIdProperty()
            .get(),selectedItem.getSemesterProperty().get(),selectedItem.getClassLetterProperty().get());
        model.removeStudent(student.getId());
        viewModel.remove(student);
        studentListTable.getSelectionModel().clearSelection();
        model.writeToTextFile("Students.txt","studentFile");
      }
    }
    catch (Exception e)
    {
      errorLabel.setText("Item not found: " + e.getMessage());
    }
  }



  @FXML private void detailsButtonPressed()
  {
    try
    {
      StudentViewModel selectedItem = studentListTable.getSelectionModel()
          .getSelectedItem();
        Student student = model.getStudent(new Student(selectedItem.getNameProperty().get(), selectedItem.getIdProperty()
                        .get(),selectedItem.getSemesterProperty().get(),selectedItem.getClassLetterProperty().get()));
        StudentHolder holder = StudentHolder.getInstance();
        holder.setStudent(student);
        viewHandler.openView("studentDetails");

    }
    catch (Exception e)
    {
      errorLabel.setText("Please select student to go to the details page");
    }

  }

  @FXML private void filterButtonPressed()
  {
    try
    {
      String nameFilter = nameField.getText();
      if (nameFilter.equals(""))
      {
        nameFilter = null;
      }
      int semesterFilter;
      if (semesterList.getValue() != null)
      {
        semesterFilter = semesterList.getValue();
      }
      else
      {
        semesterFilter = -1;
      }
      String classLetterFilter = classLetterList.getValue();

      StudentList list = model.getStudents(nameFilter,semesterFilter,classLetterFilter);
      viewModel.update(list);
    }
    catch (Exception e)
    {
      errorLabel.setText("Fill in at least one filter to filter content of the table");
      viewModel.update();
    }
  }

  @FXML private void backButtonPressed()
  {
    viewHandler.openView("navigation");
  }
  @FXML private void resetFilterButton()
  {
    reset();
  }

  private boolean confirmation()
  {
    int index = studentListTable.getSelectionModel().getSelectedIndex();
    StudentViewModel selectedItem = studentListTable.getItems().get(index);
    if(index < 0 || index > studentListTable.getItems().size())
    {
      return false;
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Removing student {" + selectedItem.getNameProperty().get() + ", id: " + selectedItem.getIdProperty().get() + ", semester: " + selectedItem.getSemesterProperty().get() + ", class: " + selectedItem.getClassLetterProperty().get() + "}");
    Optional<ButtonType> result = alert.showAndWait();

    return (result.isPresent()) && (result.get() == ButtonType.OK);


  }

}
