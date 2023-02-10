package com.example.smartroom_javafx.Loggings;

import java.sql.Timestamp;

public class DoorLogging {

    private int doorID;
    private boolean doorSetting;
    private Timestamp timestamp;

    public DoorLogging(Timestamp timestamp, boolean doorSetting, int doorID) {
        this.doorID = doorID;
        this.doorSetting = doorSetting;
        this.timestamp = timestamp;
    }

    public int getDoorID() {
        return doorID;
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

    public void setDoorID(int doorID) {
        this.doorID = doorID;
    }

    public void setDoorSetting(boolean doorSetting) {
        this.doorSetting = doorSetting;
    }
}
