module com.astrallinear.astrallinear {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.astrallinear.astrallinear to javafx.fxml;
    exports com.astrallinear.astrallinear;
}