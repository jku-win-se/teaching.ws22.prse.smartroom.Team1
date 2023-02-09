package com.example.smartroom_javafx.Loggings;

import java.sql.Timestamp;

public class LightLogging {

    private int roomID;
    private boolean lightSetting;
    private Timestamp timestamp;

    public LightLogging(Timestamp timestamp, boolean lightSetting, int roomID) {
        this.roomID = roomID;
        this.lightSetting = lightSetting;
        this.timestamp = timestamp;
    }

    public int getRoomID() {
        return roomID;
    }

    public boolean getLightSetting() {
        return lightSetting;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setLightSetting(boolean lightSetting) {
        this.lightSetting = lightSetting;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
