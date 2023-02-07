package com.example.smartroom_javafx.Controller;


import com.example.smartroom_javafx.Application;
import com.example.smartroom_javafx.Database.DatabaseConnectionGetLogging;
import com.example.smartroom_javafx.Loggings.CO2Logging;
import com.example.smartroom_javafx.Loggings.NumberOfPeopleLogging;
import com.example.smartroom_javafx.Loggings.TemperatureLogging;
import com.example.smartroom_javafx.Objects.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

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

        LinkedList<TemperatureLogging> roomTemperatureLoggings = DatabaseConnectionGetLogging.SelectAllTemperatureLoggings(room);
        LinkedList<NumberOfPeopleLogging> roomNumberOfPeopleLoggings = DatabaseConnectionGetLogging.SelectAllNumberOfPeopleLogging(room);
        LinkedList<CO2Logging> CO2ValueLoggings = DatabaseConnectionGetLogging.SelectCO2Logging(room);



        XYChart.Series temperatureSeries = new XYChart.Series();
        temperatureSeries.setName("temperature");
        long firstTimestamp = roomTemperatureLoggings.get(0).getTimestamp().getTime();
        long lastTimestamp = roomTemperatureLoggings.get(roomTemperatureLoggings.size() - 1).getTimestamp().getTime();

        for (int i = 0; i < roomTemperatureLoggings.size(); i++) {
            TemperatureLogging log = roomTemperatureLoggings.get(i);
            long timestamp = log.getTimestamp().getTime(); // get the timestamp in milliseconds
            temperatureSeries.getData().add(new XYChart.Data(timestamp, log.getTemperature()));
        }

        NumberAxis xAxis = (NumberAxis) temperatureChart.getXAxis();
        xAxis.setLowerBound(firstTimestamp);
        xAxis.setUpperBound(lastTimestamp);
        xAxis.setAutoRanging(false);
        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(object.longValue()));
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        temperatureChart.getData().add(temperatureSeries);






























        for (int i = 0; i < roomNumberOfPeopleLoggings.size(); i++) {
            //numberOfPeopleChart
            //series.getData().add(new XYChart.Data(entry.getKey().toString(), entry.getValue()));
        }

        for (int i = 0; i < CO2ValueLoggings.size(); i++) {
            //co2Chart
            //series.getData().add(new XYChart.Data(entry.getKey().toString(), entry.getValue()));
        }


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
