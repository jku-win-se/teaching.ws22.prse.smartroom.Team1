package com.example.smartroom_javafx.Database;

import com.example.smartroom_javafx.Loggings.CO2Logging;
import com.example.smartroom_javafx.Loggings.NumberOfPeopleLogging;
import com.example.smartroom_javafx.Loggings.TemperatureLogging;
import com.example.smartroom_javafx.Objects.Room;

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
            System.out.println("Temperature Logging -> roomID: " + logging.getRoomID() + " number of people:" + logging.getNumberOfPeople() + " timestamp:" + logging.getTimestamp());

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
            System.out.println("Temperature Logging -> roomID: " + logging.getRoomID() + " co2:" + logging.getCo2Value() + " timestamp:" + logging.getTimestamp());

        }

        return CO2ValueLoggings;

    }







}
