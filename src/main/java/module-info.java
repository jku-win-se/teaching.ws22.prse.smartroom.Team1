module com.example.smartroom_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.web;
    requires poi.ooxml;
    requires poi;


    opens com.example.smartroom_javafx to javafx.fxml;
    exports com.example.smartroom_javafx;
    exports com.example.smartroom_javafx.Loggings;
    opens com.example.smartroom_javafx.Loggings to javafx.fxml;
    exports com.example.smartroom_javafx.Database;
    opens com.example.smartroom_javafx.Database to javafx.fxml;
    exports com.example.smartroom_javafx.Controller;
    opens com.example.smartroom_javafx.Controller to javafx.fxml;
    exports com.example.smartroom_javafx.Objects;
    opens com.example.smartroom_javafx.Objects to javafx.fxml;
}