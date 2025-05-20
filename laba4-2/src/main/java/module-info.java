module ru.zagrebin.laba2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires java.desktop;


    opens ru.zagrebin.laba2 to javafx.fxml;
    exports ru.zagrebin.laba2;
}