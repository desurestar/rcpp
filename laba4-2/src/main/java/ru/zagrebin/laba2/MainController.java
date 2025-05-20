package ru.zagrebin.laba2;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.plaf.metal.OceanTheme;
import java.io.IOException;
import java.sql.*;

public class MainController {
    @FXML
    private TableColumn<Product, String> descriptionColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Integer> priceColumn;

    @FXML
    private TableColumn<Product, Integer> stockColumn;

    @FXML
    private TextField tfTextForSearch;

    @FXML
    private TableView<Product> tbProducts;
    private final ObservableList<Product> products = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        String query = "select id, name, price, stock, description from products";

        try(Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query)) {

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                int price = res.getInt("price");
                int stock = res.getInt("stock");
                String description = res.getString("description");

                products.add(new Product(id, name, price, stock, description));
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }

        nameColumn.setCellValueFactory(param -> param.getValue().nameProperty());
        priceColumn.setCellValueFactory(param -> param.getValue().priceProperty().asObject());
        stockColumn.setCellValueFactory(param -> param.getValue().stockProperty().asObject());
        descriptionColumn.setCellValueFactory(param -> param.getValue().descriptionProperty());

        tbProducts.setItems(products);
    }

    @FXML
    void onAddCkick(ActionEvent event) throws IOException {
        Product product = new Product();
        openWindow(product, "Добавить продукт");
        if (!product.getName().isEmpty()) {
            products.add(product);
        }
    }

    @FXML
    void onDeleteClick(ActionEvent event) {

        Product product = tbProducts.getSelectionModel().getSelectedItem();

        if (product == null) {
            Alert info = new Alert(Alert.AlertType.ERROR);
            info.setTitle("Ошибка при удалении");
            info.setHeaderText("Не выбран элемент для удаления");
            info.setContentText("Для удаления выберете строку в таблице");
            info.showAndWait();
            return;
        }

        products.remove(product);
        boolean res = Product.deleteProduct(product.getId());
        Alert info;
        if (res) {
            info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Успех");
            info.setHeaderText("Удаление прошло успешно");
        } else {
            info = new Alert(Alert.AlertType.ERROR);
            info.setTitle("Ошибка при удалении");
            info.setHeaderText("Ошибка на стороне сервера");
        }
        info.showAndWait();
    }

    @FXML
    void onEditClick(ActionEvent event) {
        Product product = tbProducts.getSelectionModel().getSelectedItem();

        if (product != null) {
            try {
                openWindow(product, "Редактирование товара");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @FXML
    void onExitClick(ActionEvent event) {
        DatabaseConnection.closeConnection();
        Platform.exit();
    }

    void openWindow(Product product, String title) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("addNewStudent-view.fxml"));
        Parent pane = loader.load(); //Todo обработать исключение
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(MainApp.getStage());

        NewStudentController newStudentController = loader.getController();
        newStudentController.setStage(stage);
        newStudentController.setStudent(product);
        stage.showAndWait();
    }

    public void onSearchClick(ActionEvent actionEvent) {
        String subString = tfTextForSearch.getText();

        var res = Product.searchProduct(subString);
        products.clear();
        products.addAll(res);
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Результат");
        info.setHeaderText("Найдено " + products.size() + " строк");
        info.showAndWait();
    }
}
