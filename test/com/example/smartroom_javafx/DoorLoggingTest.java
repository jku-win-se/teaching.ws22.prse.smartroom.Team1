package com.example.smartroom_javafx;

import static org.junit.Assert.*;
import java.sql.Timestamp;

import com.example.smartroom_javafx.Loggings.DoorLogging;
import org.junit.Test;

public class DoorLoggingTest {

    @Test
    public void testConstructor() {

        //arrange + act
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int doorID = 1;
        boolean doorSetting = true;
        DoorLogging logging = new DoorLogging(timestamp, doorSetting, doorID);

        //assert
        assertEquals(doorID, logging.getDoorID());
        assertEquals(doorSetting, logging.getDoorSetting());
        assertEquals(timestamp, logging.getTimestamp());
    }

    @Test
    public void testSetDoorID() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int doorID = 1;
        boolean doorSetting = true;
        DoorLogging logging = new DoorLogging(timestamp, doorSetting, doorID);
        int newDoorID = 2;

        //act
        logging.setDoorID(newDoorID);

        //assert
        assertEquals(newDoorID, logging.getDoorID());
    }

    @Test
    public void testSetDoorSetting() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int doorID = 1;
        boolean doorSetting = true;
        DoorLogging logging = new DoorLogging(timestamp, doorSetting, doorID);
        boolean newDoorSetting = false;

        //act
        logging.setDoorSetting(newDoorSetting);

        //assert
        assertEquals(newDoorSetting, logging.getDoorSetting());
    }

    @Test
    public void testSetTimestamp() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int doorID = 1;
        boolean doorSetting = true;
        DoorLogging logging = new DoorLogging(timestamp, doorSetting, doorID);
        Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());

        //act
        logging.setTimestamp(newTimestamp);

        //assert
        assertEquals(newTimestamp, logging.getTimestamp());
    }
}
