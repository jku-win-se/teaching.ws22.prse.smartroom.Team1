package com.example.smartroom_javafx.Controller;


import com.example.smartroom_javafx.Application;
import com.example.smartroom_javafx.Database.DatabaseConnectionGetLogging;
import com.example.smartroom_javafx.Objects.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Charts {

    DatabaseConnectionGetLogging randomValueInsert = new DatabaseConnectionGetLogging();

    Room room;

    @FXML
    private Button homepageButton;

    @FXML
    private LineChart<?, ?> co2Chart;

    @FXML
    private LineChart<?, ?> doorChart;

    @FXML
    private LineChart<?, ?> fanChart;

    @FXML
    private LineChart<?, ?> lightChart;

    @FXML
    private LineChart<?, ?> numberOfPeopleChart;

    @FXML
    private LineChart<?, ?> temperatureChart;

    @FXML
    private LineChart<?, ?> windowChart;

    @FXML
    private Label roomName;

    public Charts() throws SQLException {
    }

    public void initializeRoomInfo(Room room){

        this.room = room;
        this.roomName.setText(room.getName());


    }

    public void getRoomLogging(Room room) throws SQLException {

        this.room = room;

        System.out.println(room.getId());

        DatabaseConnectionGetLogging.SelectAllTemperatureLoggings(room);
        DatabaseConnectionGetLogging.SelectAllNumberOfPeopleLogging(room);
        DatabaseConnectionGetLogging.SelectCO2Logging(room);




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


}
