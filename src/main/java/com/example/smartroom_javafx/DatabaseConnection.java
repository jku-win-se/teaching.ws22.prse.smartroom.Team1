package com.example.smartroom_javafx;

import javax.xml.transform.Result;
import java.sql.*;

public class DatabaseConnection {

    static String username = "postgres";

    static String password = "user123";

    static String URL = "jdbc:postgresql://localhost/postgres";


    static Connection connection;



    public static void AddRoomToDatabase(Room room) throws SQLException {



        try {

            String query = "INSERT INTO public.\"ROOM\"(\n" +
                    "\t\"roomID\", \"RoomSize\", \"roomName\")\n" +
                    "\tVALUES (?, ?, ?);";


            long id = 0;


            connection  = DriverManager.getConnection(URL, username, password);


            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, room.getSize());
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




    }
}
