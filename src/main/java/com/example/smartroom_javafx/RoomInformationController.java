package com.example.smartroom_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.TreeMap;

public class RoomInformationController {

    Room room;
    DatabaseConnectionInsert insert = new DatabaseConnectionInsert();
    DatabaseConnectionDelete delete = new DatabaseConnectionDelete();

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
    private Button getHomeScreen;

    @FXML
    private Label roomSize;

    public RoomInformationController() throws SQLException {
    }

    @FXML
    public void initializeRoomInfo(Room room){

        this.room = room;
        this.roomName.setText(room.getName());
        roomSize.setText(String.valueOf(room.getSize())+ " m2");

        /*Map<Timestamp, Integer> tempFromDataBase = chartData.createTempData(room);
        TreeMap<Timestamp,Integer> map = new TreeMap<>();
        map = (TreeMap<Timestamp, Integer>) tempFromDataBase;
        this.temprature.setText(String.valueOf(map.lastEntry().getValue()) + " Celius");

        Map<Timestamp, Integer> peopleFromDataBase = chartData.createPeopleInRoomData(room);
        TreeMap<Timestamp,Integer> peopleMap = new TreeMap<>();
        peopleMap = (TreeMap<Timestamp, Integer>) peopleFromDataBase;
        this.personInRoom.setText(String.valueOf(peopleMap.lastEntry().getValue()) + " People/Room");

        Map<Timestamp, Integer> co2FromDataBase = chartData.createCo2Data(room);
        TreeMap<Timestamp,Integer> co2Map = new TreeMap<>();
        co2Map = (TreeMap<Timestamp, Integer>) co2FromDataBase;
        this.co2.setText(String.valueOf(co2Map.lastEntry().getValue()) + " CO2");*/

        for (Door door : room.getAllDoors()) {
            this.doors.getItems().add(door);
        }
        for (Light lights : room.getAllLights()) {
            this.lights.getItems().add(lights);
        }
        for (Fan fan: room.getAllFans()) {
            this.fans.getItems().add(fan);
        }
        for (Window window : room.getAllWindows()) {
            this.windows.getItems().add(window);
        }
    }

}
