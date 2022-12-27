module com.example.smartroom_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.web;
    requires poi.ooxml;
    requires poi;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;


    opens com.example.smartroom_javafx to javafx.fxml;
    exports com.example.smartroom_javafx;
}