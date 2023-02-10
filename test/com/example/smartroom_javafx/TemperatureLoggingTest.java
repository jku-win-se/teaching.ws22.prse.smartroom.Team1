package com.example.smartroom_javafx;

import com.example.smartroom_javafx.Loggings.TemperatureLogging;
import org.junit.Test;
import java.sql.Timestamp;

import static org.junit.Assert.*;

public class TemperatureLoggingTest {

    @Test
    public void testGetRoomID() {

        //arrange + act
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        TemperatureLogging temperatureLogging = new TemperatureLogging(timestamp, 25, 1);

        //assert
        assertEquals(1, temperatureLogging.getRoomID());
    }

    @Test
    public void testSetRoomID() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        TemperatureLogging temperatureLogging = new TemperatureLogging(timestamp, 22, 1);

        //act
        temperatureLogging.setRoomID(2);

        //assert
        assertEquals(2, temperatureLogging.getRoomID());
    }

    @Test
    public void testGetTemperature() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        //act
        TemperatureLogging temperatureLogging = new TemperatureLogging(timestamp, 18, 1);

        //assert
        assertEquals(18, temperatureLogging.getTemperature());
    }

    @Test
    public void testSetTemperature() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        TemperatureLogging temperatureLogging = new TemperatureLogging(timestamp, 21, 1);

        //act
        temperatureLogging.setTemperature(25);

        //assert
        assertEquals(25, temperatureLogging.getTemperature());
    }

    @Test
    public void testGetTimestamp() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        //act
        TemperatureLogging temperatureLogging = new TemperatureLogging(timestamp, 19, 1);

        //assert
        assertEquals(timestamp, temperatureLogging.getTimestamp());
    }

    @Test
    public void testSetTimestamp() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
        TemperatureLogging temperatureLogging = new TemperatureLogging(timestamp, 26, 1);

        //act
        temperatureLogging.setTimestamp(newTimestamp);

        //assert
        assertEquals(newTimestamp, temperatureLogging.getTimestamp());
    }
}
