module com.anton.contactbook {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.anton.contactbook to javafx.fxml;
    exports com.anton.contactbook;
}