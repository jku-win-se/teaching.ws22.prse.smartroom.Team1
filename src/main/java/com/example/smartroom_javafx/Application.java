package com.example.smartroom_javafx;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.io.*;
import java.sql.SQLException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("DigitalTwinSceneBuilder.fxml"));
        System.out.println("Test");

        Parent root = fxmlLoader.load();

        //Scene scene = new Scene(fxmlLoader.load(), 700, 450);

        Controller controller = fxmlLoader.getController();
        controller.loadRooms();

        stage.setTitle("Smart Room - Digital Twin");
        stage.setScene(new Scene(root));
        stage.show();

        stage.setOnCloseRequest(new EventHandler<>() {
            public void handle(WindowEvent windowEvent) {
                DatabaseConnectionInsert.databaseConnectionClose();
                DatabaseConnectionDelete.databaseConnectionClose();
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}