module ru.zagrebin.laba6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;
    requires java.sql;


    opens ru.zagrebin.laba6 to javafx.fxml;

//    opens ru.zagrebin.laba6.app to javafx.fxml;
    exports ru.zagrebin.laba6.controller;
    opens ru.zagrebin.laba6.controller to javafx.fxml;
    exports ru.zagrebin.laba6;
}