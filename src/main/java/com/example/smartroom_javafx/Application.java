package com.example.smartroom_javafx;

import com.example.smartroom_javafx.Controller.Controller;
import com.example.smartroom_javafx.Database.DatabaseConnectionDelete;
import com.example.smartroom_javafx.Database.DatabaseConnectionInsert;
import com.example.smartroom_javafx.Database.DatabaseConnectionRandomValueInsert;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Diese Klasse ist zuständig für das Starten der Applikation
 */

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("DigitalTwinSceneBuilder.fxml"));

        Parent root = fxmlLoader.load();
        System.out.println("Smart Room Application started.");

        Controller controller = fxmlLoader.getController();
        controller.loadRooms();

        stage.setTitle("Smart Room - Digital Twin");
        stage.setScene(new Scene(root));
        stage.show();

        stage.setOnCloseRequest(new EventHandler<>() {
            public void handle(WindowEvent windowEvent) {
                DatabaseConnectionInsert.databaseConnectionClose();
                DatabaseConnectionDelete.databaseConnectionClose();
                DatabaseConnectionRandomValueInsert.databaseConnectionClose();
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}