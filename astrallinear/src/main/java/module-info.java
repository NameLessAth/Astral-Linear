module com.astrallinear.astrallinear {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    opens com.astrallinear.astrallinear to javafx.fxml;
    exports com.astrallinear.astrallinear;
}