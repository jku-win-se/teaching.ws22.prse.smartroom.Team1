package com.example.smartroom_javafx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {

    @Test
    void TestDoor() {
        Door door = new Door("door");
        Assertions.assertNotNull(door);
        Assertions.assertEquals(0, door.getId());
        Assertions.assertEquals("door", door.getName());

        Assertions.assertFalse(door.getSetting());
        Assertions.assertEquals("door 0 locked", door.toString());

        door.setSetting(true);
        Assertions.assertTrue(door.getSetting());
        Assertions.assertEquals("door 0 unlocked", door.toString());

        door.setDoorId(3);
        Assertions.assertEquals(3, door.getDoorId());

        door.setName("Kitchen Door");
        Assertions.assertEquals("Kitchen Door", door.getName());

        door.setSetting(false);
        Assertions.assertFalse(door.getSetting());
        Assertions.assertEquals("Kitchen Door 3 locked", door.toString());

        door.setSetting(true);
        Assertions.assertTrue(door.getSetting());
        Assertions.assertEquals("Kitchen Door 3 unlocked", door.toString());

    }
}