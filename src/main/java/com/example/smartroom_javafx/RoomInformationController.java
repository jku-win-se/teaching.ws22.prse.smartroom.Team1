package com.example.smartroom_javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RoomInformationController implements Initializable {

    Room room;
    DatabaseConnectionInsert insert = new DatabaseConnectionInsert();
    DatabaseConnectionDelete delete = new DatabaseConnectionDelete();

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

    @FXML
    private Button on_offButton;

    @FXML
    private Button getHomeScreen;

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
        switch (choosenThing) {
            case "Window" -> {
                Window window = new Window("Window");
                window.setWindowId(insert.windowDataID());
                room.addThing(window);
                insert.insertWindow(room);
                thingListView.getItems().add(window);
            }
            case "Door" -> {
                Door door = new Door("Door");
                door.setDoorId(insert.doorDataID());
                insert.insertDoor(room);
                room.addThing(door);
                thingListView.getItems().add(door);
            }
            case "Lights" -> {
                Light light = new Light("Light");
                light.setLightId(insert.lightDataID());
                insert.insertLight(room);
                room.addThing(light);
                thingListView.getItems().add(light);
            }
            case "Fan" -> {
                Fan fan = new Fan("Fan");
                fan.setFanId(insert.fanDataID());
                room.addThing(fan);
                insert.insertFan(room);
                thingListView.getItems().add(fan);
            }
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
        if (thingListView.getItems().get(choosenThing).getSetting()) {
            thingListView.getItems().get(choosenThing).setSetting(false);
            if(thing instanceof  Window){
                insert.insertWindow(room);
            }
            else if(thing instanceof Light){
                insert.insertLight(room);
            }
            else if(thing instanceof Door){
                insert.insertDoor(room);
            }
            else if(thing instanceof Fan){
                insert.insertFan(room);
            }
            //Boolen in Database fehlt noch für den else zweig
        } else {
            if(thing instanceof  Window){
                insert.insertWindow(room);
            }
            else if(thing instanceof Light){
                insert.insertLight(room);
            }
            else if(thing instanceof Door){
                insert.insertDoor(room);
            }
            else if(thing instanceof Fan) {
                insert.insertFan(room);
            }

            thingListView.getItems().get(choosenThing).setSetting(true);
        }
        thingListView.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thingsChoiceBox.getItems().addAll(thingsChoosen);
    }

}
