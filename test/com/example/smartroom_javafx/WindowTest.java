package com.example.smartroom_javafx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WindowTest {

    @Test
    void TestWindow() {
        Window window = new Window("window");
        Assertions.assertNotNull(window);
        Assertions.assertEquals(0, window.getId());
        Assertions.assertEquals("window", window.getName());

        Assertions.assertFalse(window.getSetting());
        Assertions.assertEquals("window 0 closed", window.toString());

        window.setSetting(true);
        Assertions.assertTrue(window.getSetting());
        Assertions.assertEquals("window 0 open", window.toString());

        window.setWindowId(12);
        Assertions.assertEquals(12, window.getWindowId());

        window.setName("East Window");
        Assertions.assertEquals("East Window", window.getName());

        window.setSetting(false);
        Assertions.assertFalse(window.getSetting());
        Assertions.assertEquals("East Window 12 closed", window.toString());

        window.setSetting(true);
        Assertions.assertTrue(window.getSetting());
        Assertions.assertEquals("East Window 12 open", window.toString());
    }
}