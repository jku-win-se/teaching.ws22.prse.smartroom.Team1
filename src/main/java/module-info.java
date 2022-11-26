module com.example.smartroom_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.smartroom_javafx to javafx.fxml;
    exports com.example.smartroom_javafx;
}