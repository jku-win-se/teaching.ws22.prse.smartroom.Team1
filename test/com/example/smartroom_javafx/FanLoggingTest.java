package com.example.smartroom_javafx;

import com.example.smartroom_javafx.Loggings.FanLogging;
import org.junit.Test;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class FanLoggingTest {

    @Test
    public void testGetFanID() {

        //arrange + act
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        FanLogging fanLogging = new FanLogging(timestamp, true, 1);
        int expected = 1;
        int result = fanLogging.getFanID();

        //assert
        assertEquals(expected, result);
    }

    @Test
    public void testGetFanSetting() {

        //arrange + act
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        FanLogging fanLogging = new FanLogging(timestamp, true, 1);
        boolean expected = true;
        boolean result = fanLogging.getFanSetting();

        //assert
        assertEquals(expected, result);
    }

    @Test
    public void testGetTimestamp() {

        //arrange + act
        Timestamp expected = new Timestamp(System.currentTimeMillis());
        FanLogging fanLogging = new FanLogging(expected, true, 1);
        Timestamp result = fanLogging.getTimestamp();

        //assert
        assertEquals(expected, result);
    }

    @Test
    public void testSetFanID() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        FanLogging fanLogging = new FanLogging(timestamp, true, 1);
        int expected = 2;

        //act
        fanLogging.setFanID(expected);
        int result = fanLogging.getFanID();

        //assert
        assertEquals(expected, result);
    }

    @Test
    public void testSetFanSetting() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        FanLogging fanLogging = new FanLogging(timestamp, true, 1);
        boolean expected = false;

        //act
        fanLogging.setFanSetting(expected);
        boolean result = fanLogging.getFanSetting();

        //assert
        assertEquals(expected, result);
    }

    @Test
    public void testSetTimestamp() {

        //arrange
        Timestamp expected = new Timestamp(System.currentTimeMillis());
        FanLogging fanLogging = new FanLogging(new Timestamp(0), true, 1);

        //act
        fanLogging.setTimestamp(expected);
        Timestamp result = fanLogging.getTimestamp();

        //assert
        assertEquals(expected, result);
    }
}
