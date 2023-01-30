package com.example.smartroom_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

        public static LinkedList<Room> rooms = new LinkedList<>();

        DatabaseConnectionInsert insert = new DatabaseConnectionInsert();
        DatabaseConnectionDelete delete = new DatabaseConnectionDelete();

        @FXML
        private TextField NumOfFans;

        @FXML
        private TextField NumOfLights;

        @FXML
        private TextField NumOfWindows;

        @FXML
        private TextField NumOfDoors;

        @FXML
        private ListView<Room> listRoom;

        @FXML
        private Button roomInfoButton;

        @FXML
        private Button exportRoomButton;

        @FXML
        private TableColumn<Room, Integer> RoomIDColumn;

        @FXML
        private TableColumn<Room, String> RoomNameColumn;

        @FXML
        private TableColumn<Room, Integer> RoomSizeColumn;

        @FXML
        private TextField RoomIDInput;

        @FXML
        private TextField RoomNameInput;

        @FXML
        private TextField RoomSizeInput;

        @FXML
        private Button chartInfoButton;


    public Controller() throws SQLException {
    }

    public void initRoomsOverview() throws SQLException {
        LinkedList<Room> backupList = new LinkedList<>();
        backupList = (LinkedList)rooms.clone();
        rooms.clear();
        rooms = insert.createRooms(rooms);

        backupList.clear();

        for (Room room : rooms) {
            listRoom.getItems().add(room);
        }
    }


    @FXML
    void btnAddRoom(ActionEvent event) throws Exception {

        LinkedList<Thing> things = new LinkedList<>();

        try{
            int numOfD = Integer.parseInt(NumOfDoors.getText());
            if (numOfD < 0) {
                throw new InputMismatchException();
            }else{
                int doorId = insert.doorDataID();
                for (int i = 0; i < numOfD; i++) {
                    Door door = new Door("Door");
                    door.setDoorId(doorId);
                    things.add(door);
                    doorId++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Only Integers!");
            return;
        }

        try{
            int numOfF = Integer.parseInt(NumOfFans.getText());
            if (numOfF < 0) {
                throw new Exception();
            }else{
                int fanId = insert.fanDataID();
                for (int i = 0; i < numOfF; i++) {
                    Fan fan = new Fan("Fan");
                    fan.setFanId(fanId);
                    things.add(fan);
                    fanId++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Only Integers!");
            return;
        }

        try{
            int numOfL = Integer.parseInt(NumOfLights.getText());
            if(numOfL < 0){
                throw new Exception();
            }else{
                int lightId = insert.lightDataID();
                for (int i = 0; i < numOfL; i++) {
                    Light light = new Light("Light");
                    light.setLightId(lightId);
                    things.add(light);
                    lightId++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Only Integers!");
            return;
        }

        try{
            int numOfW = Integer.parseInt(NumOfWindows.getText());
            if(numOfW < 0){
                throw new Exception();
            }else{
                int windowId = insert.windowDataID();
                for (int i = 0; i < numOfW; i++) {
                    Window window = new Window("Window");
                    window.setWindowId(windowId);
                    things.add(window);
                    windowId++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Only Integers!");
            return;
        }

        try{
            if(Integer.parseInt(RoomSizeInput.getText())<0){
                throw new Exception();
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Only Integers!");
            return;
        }

        boolean x = true;
        for(Room room: listRoom.getItems()){
            if (room.getId() ==  Integer.parseInt(RoomIDInput.getText())){
                x = false;
                break;
            }
        }

        if(x){
            Room room = new Room(RoomNameInput.getText(), Integer.parseInt(RoomSizeInput.getText()), things, Integer.parseInt(RoomIDInput.getText()));
            insert.insertRoom(room);
            listRoom.getItems().add(room);
            rooms.add(room);

        }else{
            System.out.println("Room ID already exists");
        }
    }

    @FXML
    void btnDeleteRoom(ActionEvent event) throws SQLException{
        Room deletedRoom = (listRoom.getSelectionModel().getSelectedItem());
        int chosenRoom = listRoom.getSelectionModel().getSelectedIndex();
        listRoom.getItems().remove(chosenRoom);
        delete.databaseRoomDelete(deletedRoom);
        rooms.remove(chosenRoom);
    }

    @FXML
    void btnImportData(ActionEvent event) throws SQLException {
        FileChooser chooser = new FileChooser();
        File excelFile = chooser.showOpenDialog(new Stage());
        DatabaseConnectionImport.ImportData readData = new DatabaseConnectionImport.ImportData(excelFile);
        loadRooms();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void exportRooms(ActionEvent event) throws SQLException, IOException {
        DatabaseConnectionExport export = new DatabaseConnectionExport();
    }

    public void loadRooms() throws SQLException {
        rooms = insert.createRooms(rooms);
        for(Room room: rooms){
            listRoom.getItems().add(room);
        }
    }

    public void loadRooms2() throws SQLException {
        for(Room room: rooms){
            listRoom.getItems().add(room);
        }
    }



    @FXML
    public void getRoomInformation(MouseEvent event) throws Exception {
        Parent root;
        Stage stage;
        int chosenRoom = listRoom.getSelectionModel().getSelectedIndex();
        Room room = (listRoom.getItems().get(chosenRoom));

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("RoomInformation.fxml"));

        root = fxmlLoader.load();


        RoomInformationController roomInformation = fxmlLoader.getController();
        roomInformation.initializeRoomInfo(room);
        roomInformation.initRoomControl(room);

        stage = (Stage) roomInfoButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.getRoot());
        stage.setScene(scene);

    }

    @FXML
    void getRoomChart(MouseEvent event) throws IOException {

        Parent root;
        Stage stage;
        int chosenRoom = listRoom.getSelectionModel().getSelectedIndex();
        Room room = (listRoom.getItems().get(chosenRoom));

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Charts.fxml"));

        root = fxmlLoader.load();


        Charts chartInformation = fxmlLoader.getController();


        stage = (Stage) chartInfoButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.getRoot());
        stage.setScene(scene);

    }
}