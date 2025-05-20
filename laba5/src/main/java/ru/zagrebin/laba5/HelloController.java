package ru.zagrebin.laba5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Data;
import ru.zagrebin.laba5.interfaces.Dao;
import ru.zagrebin.laba5.student.Student;
import ru.zagrebin.laba5.student.StudentDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class HelloController {

    @FXML
    private TableView<Student> tbStudents;

    @FXML
    private TableColumn<Student, String> firstNameColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Label lblText;

    @FXML
    private TableColumn<Student, String> cityColumn;

    @FXML
    private TableColumn<Student, String> middleNameColumn;

    @FXML
    private TableColumn<Student, String> departmentColumn;

    @FXML
    private TableColumn<Student, String> lastNameColumn;

    @FXML
    private TableColumn<Student, String> birthDateColumn;

    @FXML
    private TableColumn<Student, String> groupColumn;

    private final Dao<Student, Long> dao = new StudentDao();
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        tbStudents.setItems(studentList);

        loadAllStudents();
    }

    private void loadAllStudents() {
        studentList.setAll(dao.findAll());
    }

    @FXML
    void onAddClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("add-new-student-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Добавить нового студента");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            loadAllStudents();
        } catch (IOException e) {
            e.printStackTrace();
            lblText.setText("Ошибка при открытии формы добавления");
        }
    }

    @FXML
    void onEditClick(ActionEvent event) {
        Student selectedStudent = tbStudents.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            lblText.setText("Выберете студента");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("add-new-student-view.fxml"));
            Parent root = loader.load();

            AddNewStudentController controller = loader.getController();
            controller.setStudent(selectedStudent);

            Stage stage = new Stage();
            stage.setTitle("Редактировать студента");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            loadAllStudents();
        } catch (IOException e) {
            e.printStackTrace();
            lblText.setText("Ошибка при открытии формы редактирования");
        }
    }

    @FXML
    void onDeleteClick(ActionEvent event) {
        Student student = tbStudents.getSelectionModel().getSelectedItem();
        if (student != null) {
            dao.delete(student);
            loadAllStudents();
            lblText.setText("Удален студет: " + student.getLastName() + " " + student.getFirstName());
        } else {
            lblText.setText("Выберете студента для удаления");
        }
    }

    @FXML
    void onSearch(ActionEvent event) {
        String query = searchField.getText().trim();

        if (query.isEmpty()) {
            loadAllStudents();
            lblText.setText("Показаны все записи");
            return;
        }

        try {
            long id = Long.parseLong(query);
            dao.findOptionalById(id).
                    ifPresentOrElse(student -> studentList.setAll(student),
                                    () -> lblText.setText("Студент с таким ID не найден"));
        } catch (NumberFormatException e) {
            var found = new ArrayList<Student>(dao.findByLastName(query));
            found.addAll(dao.findByGroup(query));
            studentList.setAll(found);
            lblText.setText("Результаты поиска по фамилии/группе");
        }
    }

    @FXML
    void onExitClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void onDeleteById(ActionEvent actionEvent) {
        try {
            long id = Long.parseLong(searchField.getText().trim());
            dao.deleteById(id);
            loadAllStudents();
            lblText.setText("Удалён студент c ID: " + id);
        } catch (NumberFormatException e) {
            lblText.setText("Введите корректный ID");
        }
    }
}
