package com.example.smartroom_javafx.Controller;

import com.example.smartroom_javafx.*;
import com.example.smartroom_javafx.Database.DatabaseConnectionDelete;
import com.example.smartroom_javafx.Database.DatabaseConnectionInsert;
import com.example.smartroom_javafx.Objects.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RoomInformationController implements Initializable {

    Room room;
    DatabaseConnectionInsert insert = new DatabaseConnectionInsert();
    DatabaseConnectionDelete delete = new DatabaseConnectionDelete();

    @FXML
    private Button homepageButton;

    @FXML
    private ChoiceBox<String> thingsChoiceBox;

    @FXML
    private final String[] thingsChoosen = {"Door", "Window", "Fan", "Lights"};

    @FXML
    private Button infoButton;

    @FXML
    private ListView<Thing> thingListView;

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

    void initRoomControl(Room room) {
        this.room = room;
        this.roomName.setText(room.getName());
        for (Thing thing : room.getAllThings()) {
            thingListView.getItems().add(thing);
        }
    }

    @FXML
    void addThing(MouseEvent event) throws SQLException {
        String choosenThing = thingsChoiceBox.getValue();
        if ("Window".equals(choosenThing)) {
            Window window = new Window("Window");
            window.setWindowId(insert.windowDataID());
            room.addThing(window);
            insert.insertWindow(room);
            thingListView.getItems().add(window);
        } else if ("Door".equals(choosenThing)) {
            Door door = new Door("Door");
            door.setDoorId(insert.doorDataID());
            insert.insertDoor(room);
            room.addThing(door);
            thingListView.getItems().add(door);
        } else if ("Lights".equals(choosenThing)) {
            Light light = new Light("Light");
            light.setLightId(insert.lightDataID());
            insert.insertLight(room);
            room.addThing(light);
            thingListView.getItems().add(light);
        } else if ("Fan".equals(choosenThing)) {
            Fan fan = new Fan("Fan");
            fan.setFanId(insert.fanDataID());
            room.addThing(fan);
            insert.insertFan(room);
            thingListView.getItems().add(fan);
        }
    }

    @FXML
    void deleteThing(MouseEvent event) {
        int choosenThing = thingListView.getSelectionModel().getSelectedIndex();

        for (Thing thing : room.getAllThings()){
            if (thingListView.getItems().get(choosenThing).equals(thing)) {
                room.removeThing(thing);
                if (thing instanceof Window){
                    delete.databaseWindowDelete(((Window) thing).getWindowId());
                }
                else if (thing instanceof Door){
                    delete.databaseDoorDelete(((Door) thing).getDoorId());
                }
                else if (thing instanceof Light){
                    delete.databaseLightDelete(((Light) thing).getLightId());
                }
                else if (thing instanceof Fan){
                    delete.databaseFanDelete(((Fan) thing).getFanId());
                }
            }
        }
        thingListView.getItems().remove(choosenThing);
        thingListView.refresh();
    }


    @FXML
    void changeSettingOfThings(MouseEvent event) throws SQLException {
        int choosenThing = thingListView.getSelectionModel().getSelectedIndex();
        Thing thing = thingListView.getItems().get(choosenThing);
        boolean isSetting = thing.getSetting();
        thing.setSetting(!isSetting);
        if (isSetting) {
            if (thing instanceof Window) {
                insert.insertWindow(room);
            } else if (thing instanceof Light) {
                insert.insertLight(room);
            } else if (thing instanceof Door) {
                insert.insertDoor(room);
            } else if (thing instanceof Fan) {
                insert.insertFan(room);
            }
        } else {
            if (thing instanceof Window) {
                insert.insertWindow(room);
            } else if (thing instanceof Light) {
                insert.insertLight(room);
            } else if (thing instanceof Door) {
                insert.insertDoor(room);
            } else if (thing instanceof Fan) {
                insert.insertFan(room);
            }
        }
        thingListView.refresh();
    }


    @FXML
    void goToHomepage(ActionEvent event) {
        Parent root;
        Stage stage;

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("DigitalTwinSceneBuilder.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Controller controller = fxmlLoader.getController();
        try {
            controller.loadRooms2();
            //mit loadRooms wurden die Räume in der Listview doppelt angezeigt, weil createRooms-Methode in der DBConnectionInsert
            //dann immer wieder räume erstellt und wieder in die liste haut
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stage = (Stage) homepageButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.getRoot());
        stage.setScene(scene);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thingsChoiceBox.getItems().addAll(thingsChoosen);
    }

}
