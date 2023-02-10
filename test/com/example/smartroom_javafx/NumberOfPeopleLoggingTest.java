package com.example.smartroom_javafx;

import com.example.smartroom_javafx.Loggings.NumberOfPeopleLogging;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class NumberOfPeopleLoggingTest {

    @Test
    public void testGetRoomID() {

        //arrange + act
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        NumberOfPeopleLogging numberOfPeopleLogging = new NumberOfPeopleLogging(timestamp, 5, 1);

        //assert
        assertEquals(1, numberOfPeopleLogging.getRoomID());
    }

    @Test
    public void testGetNumberOfPeople() {

        //arrange + act
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        NumberOfPeopleLogging numberOfPeopleLogging = new NumberOfPeopleLogging(timestamp, 5, 1);

        //assert
        assertEquals(5, numberOfPeopleLogging.getNumberOfPeople());
    }

    @Test
    public void testGetTimestamp() {

        //arrange + act
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        NumberOfPeopleLogging numberOfPeopleLogging = new NumberOfPeopleLogging(timestamp, 5, 1);

        //assert
        assertEquals(timestamp, numberOfPeopleLogging.getTimestamp());
    }

    @Test
    public void testSetRoomID() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        NumberOfPeopleLogging numberOfPeopleLogging = new NumberOfPeopleLogging(timestamp, 5, 1);

        //act
        numberOfPeopleLogging.setRoomID(2);

        //assert
        assertEquals(2, numberOfPeopleLogging.getRoomID());
    }

    @Test
    public void testSetNumberOfPeople() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        NumberOfPeopleLogging numberOfPeopleLogging = new NumberOfPeopleLogging(timestamp, 5, 1);

        //act
        numberOfPeopleLogging.setNumberOfPeople(6);

        //assert
        assertEquals(6, numberOfPeopleLogging.getNumberOfPeople());
    }

    @Test
    public void testSetTimestamp() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        NumberOfPeopleLogging numberOfPeopleLogging = new NumberOfPeopleLogging(timestamp, 5, 1);
        Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());

        //act
        numberOfPeopleLogging.setTimestamp(newTimestamp);

        //assert
        assertEquals(newTimestamp, numberOfPeopleLogging.getTimestamp());
    }
}
