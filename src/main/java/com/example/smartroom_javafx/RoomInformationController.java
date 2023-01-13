package com.example.smartroom_javafx;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RoomInformationController {

    Room room;
    DatabaseConnectionInsert insert = new DatabaseConnectionInsert();
    DatabaseConnectionDelete delete = new DatabaseConnectionDelete();

    @FXML
    private Button homepageButton;

    @FXML
    private ListView<Light> lights;

    @FXML
    private BorderPane listRooms;

    @FXML
    private ListView<Door> doors;

    @FXML
    private ListView<Fan> fans;

    @FXML
    private ListView<Window> windows;

    @FXML
    private Label roomName;

    @FXML
    private Label roomSize;

    public RoomInformationController() throws SQLException {
    }


    @FXML
    public void initializeRoomInfo(Room room){

        this.room = room;
        this.roomName.setText(room.getName());

        for (Door door : room.getAllDoors()) {
            this.doors.getItems().add(door);
        }
        for (Light light : room.getAllLights()) {
            this.lights.getItems().add(light);
        }
        for (Fan fan: room.getAllFans()) {
            this.fans.getItems().add(fan);
        }
        for (Window window : room.getAllWindows()) {
            this.windows.getItems().add(window);
        }
    }

    @FXML
    void goToHomepage(ActionEvent event) {
        Parent root;
        Stage stage;

        FXMLLoader fxmlloader = new FXMLLoader(Application.class.getResource("DigitalTwinSceneBuilder.fxml"));
        try {
            root = fxmlloader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Controller controller = fxmlloader.getController();
        controller.initializeRoomInfo(room);

        stage = (Stage) homepageButton.getScene().getWindow();
        Scene scene = new Scene(fxmlloader.getRoot());
        stage.setScene(scene);
        //controller.loadRooms2();
    }


}
