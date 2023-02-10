package com.example.smartroom_javafx;

import static org.junit.Assert.*;
import java.sql.Timestamp;

import com.example.smartroom_javafx.Loggings.CO2Logging;
import org.junit.Test;

public class CO2LoggingTest {

    @Test
    public void testConstructor() {

        //arrange + act
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int roomID = 1;
        int co2Value = 600;
        CO2Logging logging = new CO2Logging(timestamp, co2Value, roomID);

        //assert
        assertEquals(roomID, logging.getRoomID());
        assertEquals(co2Value, logging.getCo2Value());
        assertEquals(timestamp, logging.getTimestamp());
    }

    @Test
    public void testSetRoomID() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int roomID = 1;
        int co2Value = 500;
        CO2Logging logging = new CO2Logging(timestamp, co2Value, roomID);
        int newRoomID = 2;

        //act
        logging.setRoomID(newRoomID);

        //assert
        assertEquals(newRoomID, logging.getRoomID());
    }

    @Test
    public void testSetCo2Value() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int roomID = 1;
        int co2Value = 600;
        CO2Logging logging = new CO2Logging(timestamp, co2Value, roomID);
        int newCo2Value = 700;

        //act
        logging.setCo2Value(newCo2Value);

        //assert
        assertEquals(newCo2Value, logging.getCo2Value());
    }

    @Test
    public void testSetTimestamp() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int roomID = 1;
        int co2Value = 500;
        CO2Logging logging = new CO2Logging(timestamp, co2Value, roomID);
        Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());

        //act
        logging.setTimestamp(newTimestamp);

        //assert
        assertEquals(newTimestamp, logging.getTimestamp());
    }
}
