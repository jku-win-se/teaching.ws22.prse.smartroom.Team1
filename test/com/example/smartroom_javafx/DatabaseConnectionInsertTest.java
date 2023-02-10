package com.example.smartroom_javafx;

import com.example.smartroom_javafx.Database.DatabaseConnectionInsert;
import com.example.smartroom_javafx.Objects.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionInsertTest {

    private DatabaseConnectionInsert connectionInsert;

    @BeforeEach
    void setUp() throws SQLException {
        //arrange
        connectionInsert = new DatabaseConnectionInsert();
    }

    @AfterEach
    void tearDown() {
        DatabaseConnectionInsert.databaseConnectionClose();
    }

    @Test
    void createRoomsTest() throws SQLException {
        //arrange
        LinkedList<Room> rooms = new LinkedList<>();

        //act
        rooms = connectionInsert.createRooms(rooms);

        //assert
        assertNotNull(rooms);
    }

    @Test
    void insertRoomTest() throws SQLException {

        //arrange
        Room room = new Room("Test Room", 100);
        room.setId(1);
        connectionInsert.insertRoom(room);
        LinkedList<Room> rooms = new LinkedList<>();
        rooms = connectionInsert.createRooms(rooms);
        boolean inserted = false;

        //act
        for (Room r : rooms) {
            if (r.getName().equals(room.getName()) && r.getSize() == room.getSize()) {
                inserted = true;
                break;
            }
        }

        //assert
        assertTrue(inserted);
    }

    @Test
    void insertFanTest() throws SQLException {

        //arrange
        Room room = new Room("Test Room", 100);
        room.setId(1);
        Fan fan = new Fan("dyson");
        room.addThing(fan);
        connectionInsert.insertOneFan(room, fan);
        LinkedList<Room> rooms = new LinkedList<>();
        rooms = connectionInsert.createRooms(rooms);
        boolean inserted = false;

        //act
        for (Room r : rooms) {
            if (r.getName().equals(room.getName()) && r.getSize() == room.getSize()) {
                inserted = true;
                break;
            }
        }

        //assert
        assertTrue(inserted);
    }
}

