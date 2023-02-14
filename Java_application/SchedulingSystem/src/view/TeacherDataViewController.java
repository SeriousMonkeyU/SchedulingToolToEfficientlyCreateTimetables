package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import mediator.SchedulingManagerModel;
import mediator.TeacherHolder;
import model.Teacher;
import model.TeacherList;

import java.util.Optional;

/**
 * A class that is controller for <code>TeacherDataView</code> window in GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

    public class TeacherDataViewController
    {
        @FXML private TableView<TeacherViewModel> teacherListTable;
        @FXML private TableColumn<TeacherViewModel, String> emailColumn;
        @FXML private TableColumn<TeacherViewModel, String> courseColumn;
        @FXML private TableColumn<TeacherViewModel, String> idColumn;
        @FXML private Label errorLabel;
        @FXML private TextField idField;
        @FXML private ChoiceBox<Integer> semesterList;

        private Region root;
        private SchedulingManagerModel model;
        private ViewHandler viewHandler;
        private TeacherListViewModel viewModel;

        /**
         * Empty constructor, its purpose is taken over by <code>init</code> method
         */

        public TeacherDataViewController()
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
            this.viewModel = new TeacherListViewModel(model);

                idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
                emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
                courseColumn.setCellValueFactory(cellData -> cellData.getValue().getCourseProperty());


            teacherListTable.setItems(viewModel.getList());

            semesterList.getItems().addAll(1,2,3,4,5,6,7);

            errorLabel.setText("");
        }

        /**
         * Method resetting window in GUI to its default state each time the window is opened again (e.g. resetting text in labels, text fields, refreshing table)
         */


        public void reset()
        {
            errorLabel.setText("");
            idField.setText("");
            semesterList.valueProperty().setValue(null);
            viewModel.update();
            teacherListTable.getSelectionModel().clearSelection();
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
            viewHandler.openView("addTeacher");
        }

        @FXML private void removeButtonPressed()
        {
            try
            {
                TeacherViewModel selectedItem = teacherListTable.getSelectionModel().getSelectedItem();
                boolean remove = confirmation();
                if (remove)
                {
                    Teacher teacher = model.getTeacherById(selectedItem.getIdProperty().get());
                    for (int i = 0; i < teacher.getNumberOfCourses(); i++)
                    {
                        if (model.getCourses(teacher.getCourse(i).getName(),teacher.getCourse(i).getSemester()).getNumberOfCourses() > 1)
                        {
                            model.removeCourse(model.getCourseByNameAndTeacher(teacher.getCourse(i).getName(),teacher));
                        }
                        else
                        {
                            teacher.getCourse(i).setTeacher(null);
                        }
                    }
                    model.writeToTextFile("Courses.txt","courseFile");
                    model.removeTeacher(teacher);
                    viewModel.remove(teacher);
                    teacherListTable.getSelectionModel().clearSelection();
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
                TeacherViewModel selectedItem = teacherListTable.getSelectionModel().getSelectedItem();
                Teacher teacher = model.getTeacherById(selectedItem.getIdProperty().get());
                TeacherHolder holder = TeacherHolder.getInstance();
                holder.setTeacher(teacher);
                viewHandler.openView("teacherDetails");

            }
            catch (Exception e)
            {
                errorLabel.setText("Please select teacher to go to the details page");
            }

        }

        @FXML private void filterButtonPressed()
        {
            try
            {
                int semester = -1;
                if (semesterList.getValue() != null)
                {
                    semester = semesterList.getValue();
                }
                String id = idField.getText();
                if (id.equals(""))
                {
                    id = null;
                }
                TeacherList list = model.getTeachers(id,semester);
                viewModel.update(list);
                idField.setText("");
                semesterList.valueProperty().setValue(null);
                errorLabel.setText("");
            }
            catch (Exception e)
            {
                errorLabel.setText("Fill in at least one filter to filter content of the table and provide correct data");
                idField.setText("");
                semesterList.valueProperty().setValue(null);
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
            int index = teacherListTable.getSelectionModel().getSelectedIndex();
            TeacherViewModel selectedItem = teacherListTable.getItems().get(index);
            if(index < 0 || index > teacherListTable.getItems().size())
            {
                return false;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Removing Teacher {id: " + selectedItem.getIdProperty().get() + ", " + selectedItem.getEmailProperty().get() + "}");
            Optional<ButtonType> result = alert.showAndWait();

            return (result.isPresent()) && (result.get() == ButtonType.OK);


        }

    }


