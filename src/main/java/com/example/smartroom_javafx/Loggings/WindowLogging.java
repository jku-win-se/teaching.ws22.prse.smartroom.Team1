package com.example.smartroom_javafx.Loggings;

import java.sql.Timestamp;

public class WindowLogging {

    private int windowID;
    private boolean windowSetting;
    private Timestamp timestamp;

    public WindowLogging(Timestamp timestamp, boolean windowSetting, int windowID) {
        this.windowID = windowID;
        this.windowSetting = windowSetting;
        this.timestamp = timestamp;
    }

    public int getWindowID() {
        return windowID;
    }

    public boolean getWindowSetting() {
        return windowSetting;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setWindowID(int windowID) {
        this.windowID = windowID;
    }

    public void setWindowSetting(boolean windowSetting) {
        this.windowSetting = windowSetting;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
