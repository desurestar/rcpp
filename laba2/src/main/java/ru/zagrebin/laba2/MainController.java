package ru.zagrebin.laba2;

import javafx.application.Platform;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private TableColumn<Student, Integer> ageColumn;

    @FXML
    private TableColumn<Student, String> lastnameColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> patronymicColumn;

    @FXML
    private TableColumn<Student, String> groupColumn;

    @FXML
    private TableColumn<Student, String> cityColumn;

    @FXML
    private TableView<Student> tbStudents;

    @FXML
    private Label lblText;
    private final ObservableList<Student> students = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        students.add(new Student("Загребин", "Дмитрий", "Алексеевич", 20, "Муром", "pin-122"));
        students.add(new Student("Аляпин", "Артем", "Алексеевич", 20, "Муром", "pin-122"));
        students.add(new Student("Гарин", "Ярослав", "Алексеевич", 20, "Муром", "pin-122"));

        lastnameColumn.setCellValueFactory(param -> param.getValue().lastnameProperty());
        nameColumn.setCellValueFactory(param -> param.getValue().nameProperty());
        ageColumn.setCellValueFactory(param -> param.getValue().ageProperty().asObject());
        patronymicColumn.setCellValueFactory(param -> param.getValue().patronymicProperty());
        groupColumn.setCellValueFactory(param -> param.getValue().groupProperty());
        cityColumn.setCellValueFactory(param -> param.getValue().cityProperty());

        tbStudents.setItems(students);

        tbStudents.getSelectionModel().selectedItemProperty().addListener((observable, lastStudent, current) -> {
            if (current != null) {
                lblText.setText("Информация:\n" + current.toString());

            }
        });
    }

    @FXML
    void onAddCkick(ActionEvent event) throws IOException {
        Student student = new Student();
        openWindow(student);
        if (!(student.getName().isEmpty() || student.getLastname().isEmpty() || student.getPatronymic().isEmpty() || student.getCity().isEmpty() || student.getGroup().isEmpty() || student.getAge() <= 0)) {
            students.add(student);
        }
    }

    @FXML
    void onDeleteClick(ActionEvent event) {
        Student student = tbStudents.getSelectionModel().getSelectedItem();

        if (student == null) {
            lblText.setText("Информация:\n" + "Не выбран студент для удаления.");
            return;
        }

        students.remove(student);
        lblText.setText("Информация:\n" + "Удаленный студент\n" + student.toString());
    }

    @FXML
    void onEditClick(ActionEvent event) {
        Student student = tbStudents.getSelectionModel().getSelectedItem();
        if (student != null) {
            try {
                openWindow(student);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void onExitClick(ActionEvent event) {
        Platform.exit();
    }

    void openWindow(Student student) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("addNewStudent-view.fxml"));
        Parent pane = loader.load(); //Todo обработать исключение
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setTitle("Добавить студента");
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(MainApp.getStage());

        NewStudentController newStudentController = loader.getController();
        newStudentController.setStage(stage);
        newStudentController.setStudent(student);
        stage.showAndWait();
    }

}
