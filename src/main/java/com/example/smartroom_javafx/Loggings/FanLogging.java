package com.example.smartroom_javafx.Loggings;

import com.example.smartroom_javafx.Objects.Fan;

import java.sql.Timestamp;

public class FanLogging {

    private int roomID;
    private boolean fanSetting;
    private Timestamp timestamp;

    public FanLogging(Timestamp timestamp, boolean fanSetting, int roomID) {
        this.roomID = roomID;
        this.fanSetting = fanSetting;
        this.timestamp = timestamp;
    }

    public int getRoomID() {
        return roomID;
    }

    public boolean getFanSetting() {
        return fanSetting;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setFanSetting(boolean fanSetting) {
        this.fanSetting = fanSetting;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
