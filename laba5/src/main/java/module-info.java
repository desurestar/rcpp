module ru.zagrebin.laba5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;


    opens ru.zagrebin.laba5 to javafx.fxml;
    exports ru.zagrebin.laba5;
    exports ru.zagrebin.laba5.student;
    opens ru.zagrebin.laba5.student to javafx.fxml;
    exports ru.zagrebin.laba5.interfaces;
    opens ru.zagrebin.laba5.interfaces to javafx.fxml;
}