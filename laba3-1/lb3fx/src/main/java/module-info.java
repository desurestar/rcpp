module ru.kafpin.lb3fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.kafpin.lb3fx to javafx.fxml;
    exports ru.kafpin.lb3fx;
}