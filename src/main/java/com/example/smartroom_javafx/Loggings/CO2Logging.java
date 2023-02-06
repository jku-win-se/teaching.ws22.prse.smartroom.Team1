package com.example.smartroom_javafx.Loggings;

import java.sql.Timestamp;

public class CO2Logging {

    private int roomID;
    private int co2Value;
    private Timestamp timestamp;


    public CO2Logging(Timestamp timestamp, int co2Value, int roomID) {
        this.roomID = roomID;
        this.co2Value = co2Value;
        this.timestamp = timestamp;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getCo2Value() {
        return co2Value;
    }

    public void setCo2Value(int co2Value) {
        this.co2Value = co2Value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
