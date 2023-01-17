package com.example.smartroom_javafx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {

    //arrange
    Door door = new Door("door");

    @Test
    void TestDoorFirst() {
        //act + assert
        Assertions.assertNotNull(door);
        Assertions.assertEquals(0, door.getId());
        Assertions.assertEquals("door", door.getName());
    }

    @Test
    void TestDoorSecond() {
        //act + assert
        Assertions.assertFalse(door.getSetting());
        Assertions.assertEquals("door 0 locked", door.toString());
    }

    @Test
    void TestDoorThird() {
        //act
        door.setSetting(true);

        //assert
        Assertions.assertTrue(door.getSetting());
        Assertions.assertEquals("door 0 unlocked", door.toString());
    }

    @Test
    void TestDoorFourth() {
        //act
        door.setDoorId(3);
        door.setName("Kitchen Door");

        //assert
        Assertions.assertEquals(3, door.getDoorId());
        Assertions.assertEquals("Kitchen Door", door.getName());
    }


    @Test
    void TestDoorFifth() {
        //act
        door.setDoorId(3);
        door.setName("Kitchen Door");
        door.setSetting(false);

        //assert
        Assertions.assertFalse(door.getSetting());
        Assertions.assertEquals("Kitchen Door 3 locked", door.toString());
    }

    @Test
    void TestDoorSixth() {
        //act
        door.setDoorId(3);
        door.setName("Kitchen Door");
        door.setSetting(false);
        door.setSetting(true);

        //assert
        Assertions.assertTrue(door.getSetting());
        Assertions.assertEquals("Kitchen Door 3 unlocked", door.toString());
    }
}