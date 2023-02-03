package com.example.smartroom_javafx;

import java.sql.*;
import java.util.Random;

public class DatabaseConnectionRandomValueInsert {
    String username = "postgres";
    String password = "user123";
    String URL = "jdbc:postgresql://localhost/postgres";
    static Connection connection;

    public DatabaseConnectionRandomValueInsert() throws SQLException {
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

    public void insertTemperature(Room room){
        //Random Value für Temperatur
        Random RandomTemperature = new Random();
        int upperbound = 100;//upperbound für random value bis 100 wegen Feuer Möglichkeit
        int temperature = RandomTemperature.nextInt(upperbound);
        //Timestamp jetzt gerade
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());


        try {
            String query = "INSERT INTO public.\"ROOM_TEMPERATURE_LOGGING\"(\n" +
                    "\t\"roomID\", \"temperature\", \"timeStamp\")\n" +
                    "\tVALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setTimestamp(3, timestamp);
            statement.setInt(2, temperature);
            statement.setInt(1, room.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
