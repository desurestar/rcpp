package ru.zagrebin.laba6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class EmployeesController {

    @FXML
    private TableView<?> dataGridView;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnEditEmployee;

    @FXML
    private Button btnDeleteEmployee;

    // Обязательные методы (должны точно соответствовать FXML)
    @FXML
    private void handleAddEmployee() {
        // Ваш код добавления
    }

    @FXML
    private void handleEditEmployee() {
        // Ваш код редактирования
    }

    @FXML
    private void handleDeleteEmployee() {
        // Ваш код удаления
    }
}
