module com.aes.program {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.aes.program.Model to javafx.controls;
    opens com.aes.program to javafx.fxml;
    opens com.aes.program.Controller to javafx.fxml;
    opens com.aes.program.Images to javafx.fxml;
    exports com.aes.program;
}