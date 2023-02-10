package com.example.smartroom_javafx.Controller;


import com.example.smartroom_javafx.Application;
import com.example.smartroom_javafx.Database.DatabaseConnectionGetLogging;
import com.example.smartroom_javafx.Database.DatabaseConnectionInsert;
import com.example.smartroom_javafx.Loggings.*;
import com.example.smartroom_javafx.Objects.*;
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
    DatabaseConnectionInsert insert = new DatabaseConnectionInsert();

    Room room;

    @FXML
    private Label alarm;

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
        /*
        LinkedList<WindowLogging> WindowLoggings = DatabaseConnectionGetLogging.SelectWindowLogging(room);
        LinkedList<LightLogging> LightLoggings = DatabaseConnectionGetLogging.SelectLightLogging(room);
        LinkedList<DoorLogging> DoorLoggings = DatabaseConnectionGetLogging.SelectDoorLogging(room);
        LinkedList<FanLogging> FanLoggings = DatabaseConnectionGetLogging.SelectFanLogging(room);

         */


        //Befüllung Room Temperature Chart

        XYChart.Series temperatureSeries = new XYChart.Series();
        temperatureSeries.setName("Temperature");
        long firstTimestamp = roomTemperatureLoggings.get(0).getTimestamp().getTime();
        long lastTimestamp = roomTemperatureLoggings.get(roomTemperatureLoggings.size() - 1).getTimestamp().getTime();

        //Alarm
        int tempAlarm = roomTemperatureLoggings.get(roomTemperatureLoggings.size() - 1).getTemperature();

        if(tempAlarm > 70){
            alarm.setText("WARNUNG: TEMPERATUR ÜBER 70 GRAD!");
            alarm.setStyle("-fx-text-fill: red;");
            //Open all doors -> true für unlocked
            LinkedList<Door> doorList = room.getAllDoors();
            for (int i = 0; i < doorList.size(); i++) {
                Door door = doorList.get(i);
                door.setSetting(true);
            }
            insert.insertDoor(room);

        }else{
            alarm.setText("Keine Temperaturgefahr.");
        }

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

        //Befüllung Number of People Chart

        XYChart.Series numberOfPeopleSeries = new XYChart.Series();
        numberOfPeopleSeries.setName("Number of People");
        long firstTimestampTwo = roomNumberOfPeopleLoggings.get(0).getTimestamp().getTime();
        long lastTimestampTwo = roomNumberOfPeopleLoggings.get(roomNumberOfPeopleLoggings.size() - 1).getTimestamp().getTime();

        //Energy Saving
        int peopleSensor = roomNumberOfPeopleLoggings.get(roomNumberOfPeopleLoggings.size() - 1).getNumberOfPeople();

        if(peopleSensor > 0){
            //Turn all lights ON wenn Leute im Raum sind -> true für on
            LinkedList<Light> lightList = room.getAllLights();
            for (int i = 0; i < lightList.size(); i++) {
                Light light = lightList.get(i);
                light.setSetting(true);
            }
            insert.insertLight(room);

            System.out.println("Lights are are on because someone is in the room!");

        }else if (peopleSensor == 0){
            //Turn all lights OFF wenn keine Leute im Raum sind -> false für off
            LinkedList<Light> lightList = room.getAllLights();
            for (int i = 0; i < lightList.size(); i++) {
                Light light = lightList.get(i);
                light.setSetting(false);
            }
            insert.insertLight(room);

            System.out.println("All Lights are off because the room is empty!");

            //Turn off all running devices, also alle Fans -> false für off
            LinkedList<Fan> fanList = room.getAllFans();
            for (int i = 0; i < fanList.size(); i++) {
                Fan fan = fanList.get(i);
                fan.setSetting(false);
            }
            insert.insertFan(room);

            System.out.println("All Devices are off because the room is empty!");

        }


        for (int i = 0; i < roomNumberOfPeopleLoggings.size(); i++) {
            NumberOfPeopleLogging log = roomNumberOfPeopleLoggings.get(i);
            long timestamp = log.getTimestamp().getTime(); // get the timestamp in milliseconds
            numberOfPeopleSeries.getData().add(new XYChart.Data(timestamp, log.getNumberOfPeople()));
        }

        NumberAxis xAxisTwo = (NumberAxis) numberOfPeopleChart.getXAxis();
        xAxisTwo.setLowerBound(firstTimestampTwo);
        xAxisTwo.setUpperBound(lastTimestampTwo);
        xAxisTwo.setAutoRanging(false);
        xAxisTwo.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(object.longValue()));
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        numberOfPeopleChart.getData().add(numberOfPeopleSeries);

        //Befüllung CO2 Value Chart

        XYChart.Series co2ValueSeries = new XYChart.Series();
        co2ValueSeries.setName("CO2 Value");
        long firstTimestampThree = CO2ValueLoggings.get(0).getTimestamp().getTime();
        long lastTimestampThree = CO2ValueLoggings.get(CO2ValueLoggings.size() - 1).getTimestamp().getTime();

        //Air Quality
        int co2Alarm = CO2ValueLoggings.get(CO2ValueLoggings.size() - 1).getCo2Value();

        if(co2Alarm > 1000) {

            //Open all windows und turn on fans -> true für open und on
            LinkedList<Window> windowList = room.getAllWindows();
            for (int i = 0; i < windowList.size(); i++) {
                Window window = windowList.get(i);
                window.setSetting(true);
            }
            insert.insertWindow(room);

            LinkedList<Fan> fanList = room.getAllFans();
            for (int i = 0; i < fanList.size(); i++) {
                Fan fan = fanList.get(i);
                fan.setSetting(true);
            }
            insert.insertFan(room);

            roomName.setStyle("-fx-text-fill: red;");

            System.out.println("All windows are open and fans are on because the CO2 Value is over 1000! Fresh air is needed.");


        }else if(co2Alarm > 800 && co2Alarm < 1000){
            roomName.setStyle("-fx-text-fill: yellow;");
        } else if (co2Alarm < 800) {
            roomName.setStyle("-fx-text-fill: green;");

        }

        for (int i = 0; i < CO2ValueLoggings.size(); i++) {
            CO2Logging log = CO2ValueLoggings.get(i);
            long timestamp = log.getTimestamp().getTime(); // get the timestamp in milliseconds
            co2ValueSeries.getData().add(new XYChart.Data(timestamp, log.getCo2Value()));
        }

        NumberAxis xAxisThree = (NumberAxis) co2Chart.getXAxis();
        xAxisThree.setLowerBound(firstTimestampThree);
        xAxisThree.setUpperBound(lastTimestampThree);
        xAxisThree.setAutoRanging(false);
        xAxisThree.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(object.longValue()));
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        co2Chart.getData().add(co2ValueSeries);

        /*
        //---Vorbereitete COde für das Befüllen von Object-Charts - konnte nicht rechtzeitig umgesetzt werden

        //Befüllung Light Chart
        XYChart.Series lightSeries = new XYChart.Series();
        lightSeries.setName("Light Setting");
        long firstTimestampFour = LightLoggings.get(0).getTimestamp().getTime();
        long lastTimestampFour = LightLoggings.get(LightLoggings.size() - 1).getTimestamp().getTime();


        for (int i = 0; i < LightLoggings.size(); i++) {
            LightLogging log = LightLoggings.get(i);
            long timestamp = log.getTimestamp().getTime(); // get the timestamp in milliseconds
            lightSeries.getData().add(new XYChart.Data(timestamp, log.getLightSetting()));
        }

        NumberAxis xAxisFour = (NumberAxis) lightChart.getXAxis();
        xAxisFour.setLowerBound(firstTimestampThree);
        xAxisFour.setUpperBound(lastTimestampThree);
        xAxisFour.setAutoRanging(false);
        xAxisFour.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(object.longValue()));
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        lightChart.getData().add(lightSeries);

        //Befüllung Window Chart

        XYChart.Series windowSeries = new XYChart.Series();
        windowSeries.setName("Window Setting");
        long firstTimestampfive = WindowLoggings.get(0).getTimestamp().getTime();
        long lastTimestampfive = WindowLoggings.get(WindowLoggings.size() - 1).getTimestamp().getTime();


        for (int i = 0; i < WindowLoggings.size(); i++) {
            WindowLogging log = WindowLoggings.get(i);
            long timestamp = log.getTimestamp().getTime(); // get the timestamp in milliseconds
            windowSeries.getData().add(new XYChart.Data(timestamp, log.getWindowSetting()));
        }

        NumberAxis xAxisfive = (NumberAxis) windowChart.getXAxis();
        xAxisfive.setLowerBound(firstTimestampfive);
        xAxisfive.setUpperBound(lastTimestampfive);
        xAxisfive.setAutoRanging(false);
        xAxisfive.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(object.longValue()));
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        windowChart.getData().add(windowSeries);

        //Befüllung Fan Chart

        XYChart.Series fanSeries = new XYChart.Series();
        fanSeries.setName("Fan Setting");
        long firstTimestampsix = FanLoggings.get(0).getTimestamp().getTime();
        long lastTimestampsix = FanLoggings.get(FanLoggings.size() - 1).getTimestamp().getTime();

        for (int i = 0; i < FanLoggings.size(); i++) {
            FanLogging log = FanLoggings.get(i);
            long timestamp = log.getTimestamp().getTime(); // get the timestamp in milliseconds
            fanSeries.getData().add(new XYChart.Data(timestamp, log.getFanSetting()));
        }

        NumberAxis xAxissix = (NumberAxis) windowChart.getXAxis();
        xAxissix.setLowerBound(firstTimestampsix);
        xAxissix.setUpperBound(lastTimestampsix);
        xAxissix.setAutoRanging(false);
        xAxissix.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(object.longValue()));
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        fanChart.getData().add(fanSeries);

        //Befüllung Door Chart

        XYChart.Series doorSeries = new XYChart.Series();
        doorSeries.setName("Door Setting");
        long firstTimestampseven = DoorLoggings.get(0).getTimestamp().getTime();
        long lastTimestampseven = DoorLoggings.get(DoorLoggings.size() - 1).getTimestamp().getTime();


        for (int i = 0; i < DoorLoggings.size(); i++) {
            DoorLogging log = DoorLoggings.get(i);
            long timestamp = log.getTimestamp().getTime(); // get the timestamp in milliseconds
            doorSeries.getData().add(new XYChart.Data(timestamp, log.getDoorSetting()));
        }

        NumberAxis xAxisseven = (NumberAxis) doorChart.getXAxis();
        xAxisseven.setLowerBound(firstTimestampseven);
        xAxisseven.setUpperBound(lastTimestampseven);
        xAxisseven.setAutoRanging(false);
        xAxisseven.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(object.longValue()));
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        doorChart.getData().add(doorSeries);
         */

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stage = (Stage) homepageButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.getRoot());
        stage.setScene(scene);
    }
}
