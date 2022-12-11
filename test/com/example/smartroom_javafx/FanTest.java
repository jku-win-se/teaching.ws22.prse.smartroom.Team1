package com.example.smartroom_javafx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FanTest {

    @Test
    void TestFan(){
        Fan fan = new Fan("fan");
        Assertions.assertNotNull(fan);
        Assertions.assertEquals(0, fan.getId());
        Assertions.assertEquals("fan", fan.getName());

        Assertions.assertFalse(fan.getSetting());
        Assertions.assertEquals("fan 0 off", fan.toString());

        fan.setSetting(true);
        Assertions.assertTrue(fan.getSetting());
        Assertions.assertEquals("fan 0 on", fan.toString());

        fan.setFanId(5);
        Assertions.assertEquals(5, fan.getFanId());

        fan.setName("Mican Fan");
        Assertions.assertEquals("Mican Fan", fan.getName());

        fan.setSetting(false);
        Assertions.assertFalse(fan.getSetting());
        Assertions.assertEquals("Mican Fan 5 off", fan.toString());

        fan.setSetting(true);
        Assertions.assertTrue(fan.getSetting());
        Assertions.assertEquals("Mican Fan 5 on", fan.toString());
    }

}