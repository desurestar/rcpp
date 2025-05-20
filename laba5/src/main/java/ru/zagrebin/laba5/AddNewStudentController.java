package ru.zagrebin.laba5;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.zagrebin.laba5.interfaces.Dao;
import ru.zagrebin.laba5.student.Student;
import ru.zagrebin.laba5.student.StudentDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class AddNewStudentController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField middleNameField;
    @FXML private TextField groupField;
    @FXML private TextField departmentField;
    @FXML private TextField birthDateField;
    @FXML private TextField cityField;

    private final Dao<Student, Long> dao = new StudentDao();
    private Student student;


    public void setStudent(Student student) {
        this.student = student;

        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());
        middleNameField.setText(student.getMiddleName());
        groupField.setText(student.getGroup());
        departmentField.setText(student.getDepartment());
        birthDateField.setText(student.getBirthDate());
        cityField.setText(student.getCity());
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Отключаем нестрогий режим, чтобы проверка была строгой

        try {
            Date parsedDate = dateFormat.parse(date);
            // Если разбор прошел успешно, значит дата корректна
            return true;
        } catch (ParseException e) {
            // Если возникло исключение, значит дата некорректна
            return false;
        }
    }

    @FXML
    private void onSave() {
        if (student == null) {
            student = new Student();
        }

        // Получаем значения из полей и удаляем пробелы
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String middleName = middleNameField.getText().trim();
        String group = groupField.getText().trim();
        String department = departmentField.getText().trim();
        String birthDate = birthDateField.getText().trim();
        String city = cityField.getText().trim();

        // Проверка на пустые поля
        if (firstName.isEmpty() || lastName.isEmpty() || group.isEmpty() || department.isEmpty() || birthDate.isEmpty() || city.isEmpty()) {
            showAlert("Ошибка", "Все поля должны быть заполнены.");
            return;
        }

        // Проверка корректности даты
        if (!isValidDate(birthDate)) {
            showAlert("Ошибка", "Некорректная дата. Проверьте формат и значения даты.");
            return;
        }

        // Устанавливаем значения в объект Student
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setMiddleName(middleName);
        student.setGroup(group);
        student.setDepartment(department);
        student.setBirthDate(birthDate);
        student.setCity(city);

        if (student.getId() == 0) {
            dao.save(student);
        } else {
            dao.update(student);
        }

        closeWindow();
    }

    @FXML
    private void onCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
