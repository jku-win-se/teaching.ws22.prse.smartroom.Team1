package com.example.smartroom_javafx.Loggings;

import com.example.smartroom_javafx.Objects.Fan;

import java.sql.Timestamp;

public class FanLogging {

    private int fanID;
    private boolean fanSetting;
    private Timestamp timestamp;

    public FanLogging(Timestamp timestamp, boolean fanSetting, int fanID) {
        this.fanID = fanID;
        this.fanSetting = fanSetting;
        this.timestamp = timestamp;
    }

    public int getFanID() {
        return fanID;
    }

    public boolean getFanSetting() {
        return fanSetting;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setFanID(int fanID) {
        this.fanID = fanID;
    }

    public void setFanSetting(boolean fanSetting) {
        this.fanSetting = fanSetting;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
