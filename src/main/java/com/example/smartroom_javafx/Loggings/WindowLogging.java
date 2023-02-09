package com.example.smartroom_javafx.Loggings;

import java.sql.Timestamp;

public class WindowLogging {

    private int roomID;
    private boolean windowSetting;
    private Timestamp timestamp;

    public WindowLogging(Timestamp timestamp, boolean windowSetting, int roomID) {
        this.roomID = roomID;
        this.windowSetting = windowSetting;
        this.timestamp = timestamp;
    }

    public int getRoomID() {
        return roomID;
    }

    public boolean getWindowSetting() {
        return windowSetting;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setWindowSetting(boolean windowSetting) {
        this.windowSetting = windowSetting;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
