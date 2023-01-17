package com.example.smartroom_javafx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoomTest {

    //arrange
    Room room = new Room("Wohnzimmer", 120, 3);

    @Test
    void TestRoomFirst() {
        //act + assert
        Assertions.assertNotNull(room);
        Assertions.assertEquals(3, room.getId());
        Assertions.assertEquals("Wohnzimmer", room.getName());
    }

    @Test
    void TestRoomSecond() {
        //act
        room.setId(284);

        //assert
        Assertions.assertEquals(284, room.getId());
    }

    @Test
    void TestRoomThird() {
        //act
        room.setName("Kitchen");

        //assert
        Assertions.assertEquals("Kitchen", room.getName());
    }
}