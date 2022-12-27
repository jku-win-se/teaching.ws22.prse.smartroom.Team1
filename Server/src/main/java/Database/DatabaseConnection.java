package Database;

import Objekte.Room;

import java.io.File;
import java.sql.*;
import java.util.LinkedList;


public class DatabaseConnection {

    static String username = "postgres";

    static String password = "user123";

    static String URL = "jdbc:postgresql://localhost/postgres";


    static Connection connection;



    public static void AddRoomToDatabase(Room room) throws SQLException {



        try {

            String query = "INSERT INTO public.\"ROOM\"(\n" +
                    "\t\"roomID\", \"roomSize\", \"roomName\")\n" +
                    "\tVALUES (?, ?, ?);";


            long id = 0;


            connection  = DriverManager.getConnection(URL, username, password);


            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, room.getId());
            stmt.setInt(2, room.getSize());
            stmt.setString(3, room.getName());

            int affectedRows = stmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Import finished");
    }

    public static void DeleteRoomFromDatabase(Room room) throws SQLException {
        String SQL_DELETE = "DELETE FROM public.\"ROOM\" WHERE \"roomID\"=?";

        try (Connection conn = DriverManager.getConnection(URL, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE)) {

            preparedStatement.setInt(1, room.getId());

            int row = preparedStatement.executeUpdate();

            // rows affected
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static LinkedList<Room> SelectAllRooms(LinkedList<Room> rooms) throws SQLException {
        Statement stmtRooms = connection.createStatement();

        String queryAllRooms = "SELECT " +
                "\t\"roomID\", \"roomSize\", \"roomName\")\n" +
                "\tFROM public.\"ROOM\"";

        ResultSet rsRooms = stmtRooms.executeQuery(queryAllRooms);

        while (rsRooms.next()) {
            Room room = new Room(rsRooms.getString("roomName"), rsRooms.getInt("roomSize"),rsRooms.getInt("roomID"));
            rooms.add(room);

        }
        return rooms;
    }

    public void ImportFile(File excelFile) throws SQLException{

    }









}
