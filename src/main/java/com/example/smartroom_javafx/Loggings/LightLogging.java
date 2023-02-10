package com.example.smartroom_javafx.Loggings;

import java.sql.Timestamp;

public class LightLogging {

    private int lightID;
    private boolean lightSetting;
    private Timestamp timestamp;

    public LightLogging(Timestamp timestamp, boolean lightSetting, int lightID) {
        this.lightID = lightID;
        this.lightSetting = lightSetting;
        this.timestamp = timestamp;
    }

    public int getlightID() {
        return lightID;
    }

    public boolean getLightSetting() {
        return lightSetting;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setLightID(int lightID) {
        this.lightID = lightID;
    }

    public void setLightSetting(boolean lightSetting) {
        this.lightSetting = lightSetting;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
