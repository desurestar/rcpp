package ru.zagrebin.laba2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
@Setter
public class NewStudentController {

    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfLastname;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfGroup;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfPatronymic;

    @FXML
    private Label labelInfo;

    private Student student;
    private Stage stage;

    public void setStudent(Student student){
        this.student = student;
        tfLastname.setText(student.getLastname());
        tfName.setText(student.getName());
        tfPatronymic.setText(student.getPatronymic());
        tfGroup.setText(student.getGroup());
        tfCity.setText(student.getCity());
        tfAge.setText(String.format("%d", student.getAge()));

    }
    @FXML
    void onCancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void onOK(ActionEvent event) {
        if (!(tfCity.getText().isEmpty() || tfGroup.getText().isEmpty() || tfPatronymic.getText().isEmpty() || tfName.getText().isEmpty() || tfLastname.getText().isEmpty() || Integer.parseInt(tfAge.getText()) <= 0)) {
            student.setName(tfName.getText());
            student.setLastname(tfLastname.getText());
            student.setPatronymic(tfPatronymic.getText());
            student.setAge(Integer.parseInt(tfAge.getText()));
            student.setCity(tfCity.getText());
            student.setGroup(tfGroup.getText());
            stage.close();
        } else {
            labelInfo.setText("Заполните все поля корректными данными!!!");
        }
    }

}
