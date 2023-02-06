package com.example.smartroom_javafx.Database;

import com.example.smartroom_javafx.Objects.Room;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnectionDelete {

    static String username = "postgres";
    static String password = "user123";
    static String URL = "jdbc:postgresql://localhost/postgres";
    static Connection connection;

    public DatabaseConnectionDelete() {
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


    public void databaseRoomDelete (Room room) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Delete From \"ROOM\" where \"roomID\" = ?");
            preparedStatement.setInt(1, room.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void databaseDoorDelete (int doorID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Delete From \"DOOR\" where \"doorID\" = ?");
            preparedStatement.setInt(1, doorID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void databaseLightDelete (int lightID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Delete From \"LIGHT\" where \"lightID\" = ?");
            preparedStatement.setInt(1, lightID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void databaseFanDelete (int fanID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Delete From \"FAN\" where \"fanID\" = ?");
            preparedStatement.setInt(1, fanID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void databaseWindowDelete (int windowID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Delete From \"GLASSWINDOW\" where \"windowID\" = ?");
            preparedStatement.setInt(1, windowID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

