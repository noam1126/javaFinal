module com.example.clientapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clientapp to javafx.fxml;
    exports com.example.clientapp;
}