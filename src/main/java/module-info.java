module com.hara.bmicalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hara.bmicalculator to javafx.fxml;
    exports com.hara.bmicalculator;
}