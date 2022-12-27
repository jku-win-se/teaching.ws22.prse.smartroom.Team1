package com.example.smartroom_javafx;


import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class Controller implements Initializable {



        @FXML
        private TextField NumOfFans;

        @FXML
        private TextField NumOfLights;

        @FXML
        private TextField NumOfWindows;

        @FXML
        private TextField NumOfDoors;


        @FXML
        private Button roomInfoButton;

        @FXML
        private Button exportRoomButton;


        @FXML
        private TextField RoomIDInput;

        @FXML
        private TextField RoomNameInput;

        @FXML
        private TextField RoomSizeInput;

    public Controller() throws SQLException {
    }




    @FXML
    void btnAddRoom(ActionEvent event) throws Exception {

        String roomName = RoomNameInput.getText();

        System.out.println(roomName);

        HttpClient client = HttpClient.newHttpClient();

        String requestBody = "{\"name\":\"" + RoomNameInput + "\",\"size\":" + RoomSizeInput + ",\"id\":" + RoomIDInput
                + ",\"windows\":" + NumOfWindows + ",\"fans\":" + NumOfFans + ",\"doors\":" + NumOfDoors
                + ",\"lights\":" + NumOfLights + "}";

       /* HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/rooms"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .setHeader("Content-Type", "application/json")
                .build();


        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
        */

        HttpRequest request = HttpRequest.newBuilder()
                .uri((URI.create("http://localhost:8080/rooms/" + requestBody)))
                .POST(HttpRequest.BodyPublishers.noBody()).build();
        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println("http IOException" + e);
        } catch (InterruptedException e) {






        }
        /*

        HttpClient client = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder()
                .uri((URI.create("http://localhost:8080/rooms" + "?name=" + "\"" + RoomNameInput + "\"" + "&size=" + "\"" + RoomSizeInput + "\"" + "&id=" + "\"" + RoomIDInput
                        + "\"" + "&windows=" + "\"" + NumOfWindows + "\"" + "&fans=" + "\"" + NumOfFans + "\"" + "&doors=" + "\"" + NumOfDoors + "\"" + "&lights=" + "\"" + NumOfLights
                )))
                .POST(HttpRequest.BodyPublishers.noBody()).build();
        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {


        }

         */



        /*
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
            //DatabaseConnection.AddRoomToDatabase(room);

        }else{
            System.out.println("Room ID already exists");
        }
    }

        LinkedList<Thing> things = new LinkedList<>();

        // Setze die Werte für die Dinge im Raum basierend auf den Eingaben des Benutzers
        // ...

        // Erstelle das Room-Objekt
        Room room = new Room(RoomNameInput.getText(), Integer.parseInt(RoomSizeInput.getText()), things, Integer.parseInt(RoomIDInput.getText()));

        // Sende eine HTTP-POST-Anfrage an den Spring Boot-Server, um den Raum zu speichern
        URI uri = new URI("http://localhost:8080/rooms");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(room)))
                .setHeader("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Überprüfe die Antwort des Servers
        if (response.statusCode() == 201) {
            // Der Raum wurde erfolgreich gespeichert. Füge ihn der ListView hinzu.
            listRoom.getItems().add(room);
        } else {
            // Es gab ein Problem beim Speichern des Raums. Zeige eine Fehlermeldung an.
            System.out.println("Error saving room: " + response.body());
        }

         */
    }



    @FXML
    void btnDeleteRoom(ActionEvent event) throws SQLException{
    /*
        Room deletedRoom = (listRoom.getSelectionModel().getSelectedItem());
        int chosenRoom = listRoom.getSelectionModel().getSelectedIndex();
        listRoom.getItems().remove(chosenRoom);
        delete.databaseRoomDelete(deletedRoom);
        rooms.remove(chosenRoom);

     */
    }

    @FXML
    void btnImportData(ActionEvent event) {
                FileChooser chooser = new FileChooser();
                File excelFile = chooser.showOpenDialog(new Stage());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void exportRooms(ActionEvent event) throws SQLException, IOException {
    /*
        DatabaseConnectionExport export = new DatabaseConnectionExport();

     */
    }

    public void loadRooms() throws SQLException {

        /*
        rooms = insert.createRooms(rooms);
        for(Room room: rooms){
            listRoom.getItems().add(room);
        }

         */

        /*

        JSONObject rooms = new JSONObject(response.body().toString());
        long id = rooms.getLong("id");
        String name = rooms.getString("name");
        int size = rooms.getInt("size");
        JSONArray objects = rooms.getJSONArray("lightSources");

         */

    }

    @FXML
    public void getRoomInformation(MouseEvent event) throws Exception {
    /*
        Parent root;
        Stage stage;
        int chosenRoom = listRoom.getSelectionModel().getSelectedIndex();
        Room room = (listRoom.getItems().get(chosenRoom));

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("RoomInformation.fxml"));

        root = loader.load();


        RoomInformationController roomInformation = loader.getController();
        roomInformation.initializeRoomInfo(room);

        stage = (Stage) roomInfoButton.getScene().getWindow();
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);

     */


        /*
        int chosenRoom = listRoom.getSelectionModel().getSelectedIndex();
        Room room = (listRoom.getItems().get(chosenRoom));
        FXMLLoader loader = new FXMLLoader(
                Application.class.getResource("/RoomInformation.fxml"));

        RoomInformationController roomInformation = loader.getController();
        roomInformation.initializeRoomInfo(room);

        Stage stage = (Stage) roomInfoButton.getScene().getWindow();
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);

         */
    }
}