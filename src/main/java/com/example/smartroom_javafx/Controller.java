package com.example.smartroom_javafx;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    LinkedList<Room> rooms = new LinkedList<>();



                @FXML
                private TableView<Room> RoomTableView;

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
                void btnAddRoom(ActionEvent event) throws SQLException {
                         String RoomName = RoomNameInput.getText();
                         int RoomSize = Integer.parseInt(RoomSizeInput.getText());
                         int RoomID = Integer.parseInt(RoomIDInput.getText());

                         createRoom(RoomName, RoomSize, RoomID);


                }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }

        public void createRoom(String RoomName, int RoomSize, int RoomID) throws SQLException {
                Room room = new Room(RoomName, RoomSize, RoomSize);
                rooms.add(room);
                DatabaseConnection.AddRoomToDatabase(room);





        }


}