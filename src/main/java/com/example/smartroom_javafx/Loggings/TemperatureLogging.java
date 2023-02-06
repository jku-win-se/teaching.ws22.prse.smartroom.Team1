package com.example.smartroom_javafx.Loggings;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;

public class TemperatureLogging {

    private int roomID;
    private int temperature;
    private Timestamp timestamp;


    public TemperatureLogging(Timestamp timestamp, int temperature, int roomID) {
        this.roomID = roomID;
        this.temperature = temperature;
        this.timestamp = timestamp;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
