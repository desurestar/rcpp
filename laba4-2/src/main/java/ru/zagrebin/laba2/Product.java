package ru.zagrebin.laba2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.SubScene;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
    private IntegerProperty id;
    private StringProperty name;
    private IntegerProperty price;
    private IntegerProperty stock;
    private StringProperty description;

    public Product(int id, String name, int price, int stock, String description) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
        this.description = new SimpleStringProperty(description);
    }

    public Product() {
        this(0, "", 0, 0, "");
    }

    public int getId(){
        return id.get();
    }

    public void setId(int id){
        this.id.set(id);
    }

    public IntegerProperty idProperty(){
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public static boolean addProduct(String name, int price, int stock, String description) {
        String query = "insert into products (name, price, stock, description) values (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setInt(2, price);
            stmt.setInt(3, stock);
            stmt.setString(4, description);

            int rows = stmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении товара: " + e.getMessage());
            e.getStackTrace();
            return false;
        }
    }

    public static boolean deleteProduct(int id) {
        String query = "delete from products where id = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении товара " + e.getMessage());
            e.getStackTrace();
            return false;
        }
    }

    public static ObservableList<Product> searchProduct(String subString) {
        String query = "select * from products where name ilike ?";
        ObservableList<Product> selectedProducts = FXCollections.observableArrayList();

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(query)) {
            stmt.setString(1, "%" + subString + "%");

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                int price = res.getInt("price");
                int stock = res.getInt("stock");
                String description = res.getString("description");

                selectedProducts.add(new Product(id, name, price, stock, description));
            }

            return selectedProducts;
        } catch (SQLException e) {
            System.err.println("Ошибка при поиске " + e.getMessage());
            return selectedProducts;
        }
    }

    public static boolean editProduct(int id, String name, int price, int stock, String description) {
        String query = """
                update products
                set name = ?, price = ?, stock = ?, description = ?
                where id = ?;
                """;

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, price);
            stmt.setInt(3, stock);
            stmt.setString(4, description);
            stmt.setInt(5, id);

            int rows = stmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Ошибка при редактировании товара " + e.getMessage());
            return false;
        }
    }
}
