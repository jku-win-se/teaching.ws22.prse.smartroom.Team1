package com.example.smartroom_javafx.Loggings;

import java.sql.Timestamp;

public class DoorLogging {

    private int roomID;
    private boolean doorSetting;
    private Timestamp timestamp;

    public DoorLogging(Timestamp timestamp, boolean doorSetting, int roomID) {
        this.roomID = roomID;
        this.doorSetting = doorSetting;
        this.timestamp = timestamp;
    }

    public int getRoomID() {
        return roomID;
    }

    public boolean getDoorSetting() {
        return doorSetting;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setDoorSetting(boolean doorSetting) {
        this.doorSetting = doorSetting;
    }
}
