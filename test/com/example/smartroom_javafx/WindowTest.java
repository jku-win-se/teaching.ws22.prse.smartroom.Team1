package com.example.smartroom_javafx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WindowTest {

    //arrange
    Window window = new Window("window");

    @Test
    void TestWindowFirst() {
        //act + assert
        Assertions.assertNotNull(window);
        Assertions.assertEquals(0, window.getId());
        Assertions.assertEquals("window", window.getName());
        Assertions.assertFalse(window.getSetting());
        Assertions.assertEquals("window 0 closed", window.toString());

    }

    @Test
    void TestWindowSecond() {
        //act
        window.setSetting(true);

        //assert
        Assertions.assertTrue(window.getSetting());
        Assertions.assertEquals("window 0 open", window.toString());
    }

    @Test
    void TestWindowThird() {
        //act
        window.setWindowId(12);
        window.setName("East Window");

        //assert
        Assertions.assertEquals(12, window.getWindowId());
        Assertions.assertEquals("East Window", window.getName());

    }

    @Test
    void TestWindowFourth() {
        //act
        window.setWindowId(12);
        window.setName("East Window");
        window.setSetting(false);

        //assert
        Assertions.assertFalse(window.getSetting());
        Assertions.assertEquals("East Window 12 closed", window.toString());

    }

    @Test
    void TestWindowFifth() {
        //act
        window.setWindowId(12);
        window.setName("East Window");
        window.setSetting(false);
        window.setSetting(true);

        //assert
        Assertions.assertTrue(window.getSetting());
        Assertions.assertEquals("East Window 12 open", window.toString());
    }
}