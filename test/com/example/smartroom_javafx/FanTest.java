package com.example.smartroom_javafx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FanTest {

    //arrange
    Fan fan = new Fan("fan");

    @Test
    void TestFanFirst(){
        //act + assert
        Assertions.assertNotNull(fan);
        Assertions.assertEquals(0, fan.getId());
        Assertions.assertEquals("fan", fan.getName());
    }

    @Test
    void TestFanSecond(){
        //act + assert
        Assertions.assertFalse(fan.getSetting());
        Assertions.assertEquals("fan 0 off", fan.toString());
    }

    @Test
    void TestFanThird(){
        //act
        fan.setSetting(true);

        //assert
        Assertions.assertTrue(fan.getSetting());
        Assertions.assertEquals("fan 0 on", fan.toString());
    }

    @Test
    void TestFanFourth(){
        //act
        fan.setFanId(5);
        fan.setName("Mican Fan");

        //assert
        Assertions.assertEquals("Mican Fan", fan.getName());
        Assertions.assertEquals(5, fan.getFanId());
    }

    @Test
    void TestFanFifth(){
        //act
        fan.setFanId(5);
        fan.setName("Mican Fan");
        fan.setSetting(false);

        //assert
        Assertions.assertFalse(fan.getSetting());
        Assertions.assertEquals("Mican Fan 5 off", fan.toString());
    }

    @Test
    void TestFanSixth(){
        //act
        fan.setFanId(5);
        fan.setName("Mican Fan");
        fan.setSetting(false);
        fan.setSetting(true);

        //assert
        Assertions.assertTrue(fan.getSetting());
        Assertions.assertEquals("Mican Fan 5 on", fan.toString());
    }

}