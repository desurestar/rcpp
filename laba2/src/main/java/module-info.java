module ru.zagrebin.laba2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ru.zagrebin.laba2 to javafx.fxml;
    exports ru.zagrebin.laba2;
}