package com.example.smartroom_javafx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.Timestamp;

import com.example.smartroom_javafx.Loggings.LightLogging;
import org.junit.jupiter.api.Test;

public class LightLoggingTest {

    @Test
    public void testLightLogging() {

        //arrange + act
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int lightID = 1;
        boolean lightSetting = true;
        LightLogging lightLogging = new LightLogging(timestamp, lightSetting, lightID);

        //assert
        assertEquals(lightID, lightLogging.getlightID());
        assertEquals(lightSetting, lightLogging.getLightSetting());
        assertEquals(timestamp, lightLogging.getTimestamp());
    }

    @Test
    public void testSetters() {

        //arrange
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int lightID = 1;
        boolean lightSetting = true;
        LightLogging lightLogging = new LightLogging(timestamp, lightSetting, lightID);
        lightID = 2;
        lightSetting = false;
        timestamp = new Timestamp(System.currentTimeMillis());

        //act
        lightLogging.setLightID(lightID);
        lightLogging.setLightSetting(lightSetting);
        lightLogging.setTimestamp(timestamp);

        //assert
        assertEquals(lightID, lightLogging.getlightID());
        assertEquals(lightSetting, lightLogging.getLightSetting());
        assertEquals(timestamp, lightLogging.getTimestamp());
    }
}
