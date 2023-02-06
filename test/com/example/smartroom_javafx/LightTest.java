package com.example.smartroom_javafx;

import com.example.smartroom_javafx.Objects.Light;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LightTest {

    //arrange
    Light light = new Light("light");

    @Test
    void TestLightfirst() {
        //act + assert
        Assertions.assertNotNull(light);
        Assertions.assertEquals(0, light.getId());
        Assertions.assertEquals("light", light.getName());
        Assertions.assertFalse(light.getSetting());
        Assertions.assertEquals("light 0 off", light.toString());

    }

    @Test
    void TestLightsecond() {
        //act
        light.setSetting(true);

        //assert
        Assertions.assertTrue(light.getSetting());
        Assertions.assertEquals("light 0 on", light.toString());
    }

    @Test
    void TestLightthird() {
        //act
        light.setLightId(2);
        light.setName("Ikea Light");

        //assert
        Assertions.assertEquals(2, light.getLightId());
        Assertions.assertEquals("Ikea Light", light.getName());
    }

    @Test
    void TestLightfourth() {
        //act
        light.setLightId(2);
        light.setName("Ikea Light");
        light.setSetting(false);

        //assert
        Assertions.assertFalse(light.getSetting());
        Assertions.assertEquals("Ikea Light 2 off", light.toString());
    }

    @Test
    void TestLightfifth() {
        //act
        light.setLightId(2);
        light.setName("Ikea Light");
        light.setSetting(false);
        light.setSetting(true);

        //assert
        Assertions.assertTrue(light.getSetting());
        Assertions.assertEquals("Ikea Light 2 on", light.toString());
    }
}