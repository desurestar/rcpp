package ru.zagrebin.laba2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Data;
import lombok.Setter;

import java.sql.*;

@Setter
public class NewStudentController {

    @FXML
    private Label labelInfo;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPrice;

    @FXML
    private TextField tfStock;

    private Product product;
    private Stage stage;

    public void setStudent(Product product){
        this.product = product;
        tfName.setText(product.getName());
        tfPrice.setText(String.format("%d", product.getPrice()));
        tfStock.setText(String.format("%d", product.getStock()));
        tfDescription.setText(product.getDescription());

    }
    @FXML
    void onCancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void onOK(ActionEvent event) {
        if (this.stage.getTitle().equals("Добавить продукт")) {
            if (!(tfName.getText().isEmpty() && tfDescription.getText().isEmpty()
                    && Integer.parseInt(tfPrice.getText()) < 0
                    && Integer.parseInt(tfStock.getText()) < 0)) {
                product.setName(tfName.getText());
                product.setPrice(Integer.parseInt(tfPrice.getText()));
                product.setStock(Integer.parseInt(tfStock.getText()));
                product.setDescription(tfDescription.getText());

                boolean res = Product.addProduct(product.getName(), product.getPrice(), product.getStock(), product.getDescription());

                if (res) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успех");
                    alert.setHeaderText("Добавлен новый товар");
                    alert.setContentText("Название: " + product.getName() + "\nКоличество: " + product.getStock());
                    alert.showAndWait();
                    System.out.println("Товар успешно добавлен");
                } else {
                    System.out.println("Что-то пошло не так");
                }

                stage.close();
            } else {
                labelInfo.setText("Заполните все поля корректными данными!!!");
            }
        } else {
            if (!(tfName.getText().isEmpty() && tfDescription.getText().isEmpty()
                    && Integer.parseInt(tfPrice.getText()) < 0
                    && Integer.parseInt(tfStock.getText()) < 0)) {
                product.setName(tfName.getText());
                product.setPrice(Integer.parseInt(tfPrice.getText()));
                product.setStock(Integer.parseInt(tfStock.getText()));
                product.setDescription(tfDescription.getText());

                boolean res = Product.editProduct(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getDescription());

                if (res) {
                    System.out.println("Товар успешно изменен");
                } else {
                    System.out.println("Что-то пошло не так");
                }

                stage.close();
            } else {
                labelInfo.setText("Заполните все поля корректными данными!!!");
            }
        }
    }

}
