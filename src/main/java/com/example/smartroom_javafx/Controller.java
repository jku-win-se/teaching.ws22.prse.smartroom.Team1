package com.example.smartroom_javafx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


        //Table
        @FXML
        private TableView<Room> tableView;


        //Columns
        @FXML
        private TableColumn<Room, String> roomNameColumn;

        @FXML
        private TableColumn<Room, Integer> doorColumn;

        @FXML
        private TableColumn<Room, Integer> fanColumn;

        @FXML
        private TableColumn<Room, Integer> lightColumn;

        @FXML
        private TableColumn<Room, Integer> windowColumn;


        //Text input
        @FXML
        private TextField doorInput;

        @FXML
        private TextField fanInput;

        @FXML
        private TextField lightInput;

        @FXML
        private TextField roomNameInput;

        @FXML
        private TextField windowInput;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                roomNameColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("roomName"));
                doorColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("door"));
                fanColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("fan"));
                lightColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("light"));
                windowColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("window"));
        }


        //Button
        @FXML
        void btnAddRoom(ActionEvent event) {
                Room room = new Room(roomNameInput.getText(),
                        Integer.parseInt(doorInput.getText()),
                        Integer.parseInt(fanInput.getText()),
                        Integer.parseInt(lightInput.getText()),
                        Integer.parseInt(windowInput.getText()));
                ObservableList<Room> rooms = tableView.getItems();
                rooms.add(room);
                tableView.setItems(rooms);
                }

        @FXML
        void btnRemoveRoom(ActionEvent event) {
                int selectedID = tableView.getSelectionModel().getSelectedIndex();
                tableView.getItems().remove(selectedID);
        }
    }