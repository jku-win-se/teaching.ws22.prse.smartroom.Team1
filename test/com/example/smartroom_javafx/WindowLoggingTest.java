package com.example.smartroom_javafx;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import com.example.smartroom_javafx.Loggings.WindowLogging;
import org.junit.Test;

public class WindowLoggingTest {

    @Test
    public void testConstructorAndGetters() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int windowID = 1;
        boolean windowSetting = true;

        //act
        WindowLogging windowLogging = new WindowLogging(timestamp, windowSetting, windowID);

        //assert
        assertEquals(windowID, windowLogging.getWindowID());
        assertEquals(windowSetting, windowLogging.getWindowSetting());
        assertEquals(timestamp, windowLogging.getTimestamp());
    }

    @Test
    public void testSetters() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int windowID = 1;
        boolean windowSetting = true;
        WindowLogging windowLogging = new WindowLogging(timestamp, windowSetting, windowID);
        int newWindowID = 2;
        boolean newWindowSetting = false;
        Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());

        //act
        windowLogging.setWindowID(newWindowID);
        windowLogging.setWindowSetting(newWindowSetting);
        windowLogging.setTimestamp(newTimestamp);

        //assert
        assertEquals(newWindowID, windowLogging.getWindowID());
        assertEquals(newWindowSetting, windowLogging.getWindowSetting());
        assertEquals(newTimestamp, windowLogging.getTimestamp());
    }
}
