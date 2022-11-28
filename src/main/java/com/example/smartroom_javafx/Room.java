package com.example.smartroom_javafx;

public class Room {

    private String roomName;
    private int door;
    private int fan;
    private int light;
    private int window;

    public Room(String roomName, int door, int fan, int light, int window) {
        this.roomName = roomName;
        this.door = door;
        this.fan = fan;
        this.light = light;
        this.window = window;
    }

    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }

    public int getDoor() { return door; }
    public void setDoor(int door) { this.door = door; }

    public int getFan() { return fan; }
    public void setFan(int fan) { this.fan = fan; }

    public int getLight() { return light; }
    public void setLight(int light) { this.light = light; }

    public int getWindow() { return window; }
    public void setWindow(int window) { this.window = window; }

}
