package com.example.smartroom_javafx.Database;

import com.example.smartroom_javafx.Objects.*;

import java.sql.*;
import java.util.LinkedList;

public class DatabaseConnectionInsert {

    String username = "postgres";
    String password = "user123";
    String URL = "jdbc:postgresql://localhost/postgres";
    static Connection connection;

    public DatabaseConnectionInsert() throws SQLException {
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


    public LinkedList<Room> createRooms(LinkedList<Room> roomList) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.
                executeQuery("Select \"roomName\", \"roomSize\", \"roomID\" From \"ROOM\"");
        while (result.next()) {
            Room room = new Room(result.getString("roomName"), result.getInt("roomSize"));
            room.setId(result.getInt("roomID"));
            roomList.add(room);
            createDataDoor(room);
            createDataFan(room);
            createDataLight(room);
            createDataWindow(room);
        }
        return roomList;
    }

    public void insertRoom(Room room){
        try {
            String query = "INSERT INTO public.\"ROOM\"(\n" +
                    "\t\"roomID\", \"roomSize\", \"roomName\")\n" +
                    "\tVALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(3, room.getName());
            statement.setInt(2, room.getSize());
            statement.setInt(1, room.getId());
            statement.executeUpdate();
            insertFan(room);
            insertDoor(room);
            insertWindow(room);
            insertLight(room);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insertFan(Room room) throws SQLException {
        try {
            for (Fan fan : room.getAllFans()) {
                String query = "INSERT INTO public.\"FAN\"(\n" +
                        "\t\"fanID\", \"roomID\")\n" +
                        "\tVALUES (?, ?);";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, fanDataID());
                statement.setInt(2, room.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertDoor(Room room) throws SQLException {
        try {
            for (Door door : room.getAllDoors()) {
                String query = "INSERT INTO public.\"DOOR\"(\n" +
                        "\t\"doorID\", \"roomID\")\n" +
                        "\tVALUES (?, ?);";

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, doorDataID());
                statement.setInt(2, room.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertWindow(Room room) throws SQLException {
        try {
            for (Window window : room.getAllWindows()) {
                String query = "INSERT INTO public.\"GLASSWINDOW\"(\n" +
                        "\t\"windowID\", \"roomID\")\n" +
                        "\tVALUES (?, ?);";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, windowDataID());
                statement.setInt(2, room.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertLight(Room room) throws SQLException {
        try {
            for (Light light : room.getAllLights()) {
                String query = "INSERT INTO public.\"LIGHT\"(\n" +
                        "\t\"lightID\", \"roomID\")\n" +
                        "\tVALUES (?, ?);";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, lightDataID());
                statement.setInt(2, room.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createDataLight(Room room) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("Select * From public.\"LIGHT\"");
        while (result.next()) {
            if (result.getInt("roomID") == room.getId()) {
                Light light = new Light("Light", result.getInt("lightID"));
                room.addThing(light);
            }
        }
    }

    public void createDataDoor(Room room) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("Select * From public.\"DOOR\"");
        while (result.next()) {
            if (result.getInt("roomID") == room.getId()) {
                Door door = new Door("Door", result.getInt("doorID"));
                room.addThing(door);
            }
        }
    }

    public void createDataFan(Room room) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("Select * From public.\"FAN\"");
        while (result.next()) {
            if (result.getInt("roomID") == room.getId()) {
                Fan fan = new Fan("Fan", result.getInt("fanID"));
                room.addThing(fan);
            }
        }
    }

    public void createDataWindow(Room room) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("Select * From public.\"GLASSWINDOW\"");
        while (result.next()) {
            if (result.getInt("roomID") == room.getId()) {
                Window window = new Window("Window", result.getInt("windowID"));
                room.addThing(window);
            }
        }
    }

    public int roomDataID() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            ResultSet result = statement.
                    executeQuery("SELECT \"roomName\", \"roomSize\", \"roomID\" FROM \"ROOM\" Order By \"roomID\" DESC LIMIT 1");
            while (result.next()) {
                int id = result.getInt("roomID");
                return id + 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int fanDataID() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            ResultSet result = statement.
                    executeQuery("Select \"fanID\", \"roomID\" From \"FAN\" Order By \"fanID\" DESC LIMIT 1");
            while (result.next()) {
                int id = result.getInt("fanID");
                return id + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int windowDataID() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            ResultSet result = statement.
                    executeQuery("Select \"windowID\" From \"GLASSWINDOW\" Order By \"windowID\" DESC LIMIT 1");
            while (result.next()) {
                int id = result.getInt("windowID");
                return id + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int doorDataID() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            ResultSet result = statement.
                    executeQuery("Select \"doorID\" From \"DOOR\" Order By \"doorID\" DESC LIMIT 1");
            while (result.next()) {
                int id = result.getInt("doorID");
                return id + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int lightDataID() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            ResultSet result = statement.
                    executeQuery("Select \"lightID\" From \"LIGHT\" Order By \"lightID\" DESC LIMIT 1");
            while (result.next()) {
                int id = result.getInt("lightID");
                return id + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return 0;
    }
}