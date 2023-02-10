package com.example.smartroom_javafx.Database;

import com.example.smartroom_javafx.Loggings.*;
import com.example.smartroom_javafx.Objects.Room;
import com.example.smartroom_javafx.Objects.Thing;

import java.sql.*;
import java.util.LinkedList;

public class DatabaseConnectionGetLogging {

    String username = "postgres";
    String password = "user123";
    String URL = "jdbc:postgresql://localhost/postgres";
    static Connection connection;

    public DatabaseConnectionGetLogging() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void databaseConnectionClose() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static LinkedList<TemperatureLogging> SelectAllTemperatureLoggings(Room room) throws SQLException {

        LinkedList<TemperatureLogging> roomTemperatureLoggings = new LinkedList<>();
        String queryAllLoggings = "Select \"timeStamp\", \"temperature\", \"roomID\" From \"ROOM_TEMPERATURE_LOGGING\" WHERE \"roomID\" = ?";
        PreparedStatement stmtTemperatureLoggings = connection.prepareStatement(queryAllLoggings);
        stmtTemperatureLoggings.setInt(1, room.getId());

        ResultSet rsRoomTemperatureLogging = stmtTemperatureLoggings.executeQuery();



        while (rsRoomTemperatureLogging.next()) {
            TemperatureLogging logging = new TemperatureLogging(rsRoomTemperatureLogging.getTimestamp("timestamp"), rsRoomTemperatureLogging.getInt("temperature"),rsRoomTemperatureLogging.getInt("roomID"));
            roomTemperatureLoggings.add(logging);
            System.out.println("Temperature Logging -> roomID: " + logging.getRoomID() + " temperature:" + logging.getTemperature() + " timestamp:" + logging.getTimestamp());

        }
        return roomTemperatureLoggings;
    }

    public static LinkedList<NumberOfPeopleLogging> SelectAllNumberOfPeopleLogging(Room room) throws SQLException {

        LinkedList<NumberOfPeopleLogging> roomNumberOfPeopleLoggings = new LinkedList<>();
        String queryAllLoggings = "Select \"timeStamp\", \"numberOfPeople\", \"roomID\" From \"ROOM_PEOPLE_LOGGING\" WHERE \"roomID\" = ?";
        PreparedStatement stmtNumberOfPeopleLoggings = connection.prepareStatement(queryAllLoggings);
        stmtNumberOfPeopleLoggings.setInt(1, room.getId());

        ResultSet rsNumberOfPeopleLogging = stmtNumberOfPeopleLoggings.executeQuery();



        while (rsNumberOfPeopleLogging.next()) {
            NumberOfPeopleLogging logging = new NumberOfPeopleLogging(rsNumberOfPeopleLogging.getTimestamp("timestamp"), rsNumberOfPeopleLogging.getInt("numberOfPeople"),rsNumberOfPeopleLogging.getInt("roomID"));
            roomNumberOfPeopleLoggings.add(logging);
            System.out.println("Number of People Logging -> roomID: " + logging.getRoomID() + " number of people:" + logging.getNumberOfPeople() + " timestamp:" + logging.getTimestamp());

        }
        return roomNumberOfPeopleLoggings;
    }

    public static LinkedList<CO2Logging> SelectCO2Logging(Room room) throws SQLException {

        LinkedList<CO2Logging> CO2ValueLoggings = new LinkedList<>();
        String queryAllLoggings = "Select \"timeStamp\", \"co2\", \"roomID\" From \"ROOM_CO2_LOGGING\" WHERE \"roomID\" = ?";
        PreparedStatement stmtCO2ValueLoggings = connection.prepareStatement(queryAllLoggings);
        stmtCO2ValueLoggings.setInt(1, room.getId());

        ResultSet rsCO2ValueLogging = stmtCO2ValueLoggings.executeQuery();



        while (rsCO2ValueLogging.next()) {
            CO2Logging logging = new CO2Logging(rsCO2ValueLogging.getTimestamp("timestamp"), rsCO2ValueLogging.getInt("co2"),rsCO2ValueLogging.getInt("roomID"));
            CO2ValueLoggings.add(logging);
            System.out.println("CO2 Value Logging -> roomID: " + logging.getRoomID() + " co2:" + logging.getCo2Value() + " timestamp:" + logging.getTimestamp());

        }

        return CO2ValueLoggings;

    }



    public static LinkedList<FanLogging> SelectFanLogging(Thing thing) throws SQLException {

        LinkedList<FanLogging> FanLoggings = new LinkedList<>();
        String queryAllLoggings = "Select \"timeStamp\", \"switch\", \"fanID\" From \"FAN_LOGGING\" WHERE \"fanID\" = ?";
        PreparedStatement stmtFanLoggings = connection.prepareStatement(queryAllLoggings);
        stmtFanLoggings.setInt(1, thing.getId());

        ResultSet rsFanLogging = stmtFanLoggings.executeQuery();



        while (rsFanLogging.next()) {
            FanLogging logging = new FanLogging(rsFanLogging.getTimestamp("timestamp"), rsFanLogging.getBoolean("fanSetting"),rsFanLogging.getInt("roomID"));
            FanLoggings.add(logging);
            System.out.println("Fan Logging -> fanID: " + logging.getFanID() + " fan on:" + logging.getFanSetting() + " timestamp:" + logging.getTimestamp());

        }

        return FanLoggings;

    }




    public static LinkedList<LightLogging> SelectLightLogging(Thing thing) throws SQLException {

        LinkedList<LightLogging> LightLoggings = new LinkedList<>();
        String queryAllLoggings = "Select \"timeStamp\", \"switch\", \"lightID\" From \"LIGHT_LOGGING\" WHERE \"lightID\" = ?";
        PreparedStatement stmtLightLoggings = connection.prepareStatement(queryAllLoggings);
        stmtLightLoggings.setInt(1, thing.getId());

        ResultSet rsLightLogging = stmtLightLoggings.executeQuery();



        while (rsLightLogging.next()) {
            LightLogging logging = new LightLogging(rsLightLogging.getTimestamp("timestamp"), rsLightLogging.getBoolean("fanSetting"),rsLightLogging.getInt("roomID"));
            LightLoggings.add(logging);
            System.out.println("Light Logging -> lightID: " + logging.getlightID() + " light on:" + logging.getLightSetting() + " timestamp:" + logging.getTimestamp());

        }

        return LightLoggings;

    }


    public static LinkedList<WindowLogging> SelectWindowLogging(Thing thing) throws SQLException {

        LinkedList<WindowLogging> WindowLoggings = new LinkedList<>();
        String queryAllLoggings = "Select \"timeStamp\", \"switch\", \"windowID\" From \"GLASSWINDOW_LOGGING\" WHERE \"windowID\" = ?";
        PreparedStatement stmtWindowLoggings = connection.prepareStatement(queryAllLoggings);
        stmtWindowLoggings.setInt(1, thing.getId());

        ResultSet rsWindowLogging = stmtWindowLoggings.executeQuery();

        while (rsWindowLogging.next()) {
            WindowLogging logging = new WindowLogging(rsWindowLogging.getTimestamp("timestamp"), rsWindowLogging.getBoolean("windowSetting"),rsWindowLogging.getInt("roomID"));
            WindowLoggings.add(logging);
            System.out.println("Window Logging -> windowID: " + logging.getWindowID() + " window open:" + logging.getWindowSetting() + " timestamp:" + logging.getTimestamp());

        }

        return WindowLoggings;

    }



    public static LinkedList<DoorLogging> SelectDoorLogging(Thing thing) throws SQLException {

        LinkedList<DoorLogging> DoorLoggings = new LinkedList<>();
        String queryAllLoggings = "Select \"timeStamp\", \"switch\", \"doorID\" From \"DOOR_LOGGING\" WHERE \"doorID\" = ?";
        PreparedStatement stmtDoorLoggings = connection.prepareStatement(queryAllLoggings);
        stmtDoorLoggings.setInt(1, thing.getId());

        ResultSet rsDoorLogging = stmtDoorLoggings.executeQuery();



        while (rsDoorLogging.next()) {
            DoorLogging logging = new DoorLogging(rsDoorLogging.getTimestamp("timestamp"), rsDoorLogging.getBoolean("fanSetting"),rsDoorLogging.getInt("roomID"));
            DoorLoggings.add(logging);
            System.out.println("Door Logging -> doorID: " + logging.getDoorID() + " door open:" + logging.getDoorSetting() + " timestamp:" + logging.getTimestamp());

        }

        return DoorLoggings;

    }



}
