module ru.zagrebin.laba1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.zagrebin.laba1 to javafx.fxml;
    exports ru.zagrebin.laba1;
}