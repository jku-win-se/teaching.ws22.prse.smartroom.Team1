package com.example.smartroom_javafx.Loggings;

import java.sql.Timestamp;

public class NumberOfPeopleLogging {
    private int roomID;
    private int numberOfPeople;
    private Timestamp timestamp;

    public NumberOfPeopleLogging(Timestamp timestamp, int numberOfPeople,int roomID ) {
        this.roomID = roomID;
        this.numberOfPeople = numberOfPeople;
        this.timestamp = timestamp;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
