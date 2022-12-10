package com.example.smartroom_javafx;

import java.sql.SQLException;
import java.util.LinkedList;

public class Room {

    private LinkedList<Thing> thingList = new LinkedList<>();
    private int id;
    private String name;
    private int size;
    private int people;
    private int temperature;
    private int co2;

    DatabaseConnectionInsert insert;

    {
        try {
            insert = new DatabaseConnectionInsert();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Room(String name, int size, int id) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public Room(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public Room(String name, int size, LinkedList<Thing> things, int id) {
        this.id = id;
        this.name = name;
        this.size = size;
        thingList.addAll(things);
    }


    public String getName(){
        return name;
    }

    public int getSize(){return this.size;}

    public void setName(String name){
        this.name = name;
    }

    public int getTemperature(){
        return temperature;
    }

    public int getCo2(){
        return co2;
    }

    public int getPeople(){return people;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public void addThing(Thing thing){
        thingList.add(thing);
    }

    public void removeThing(Thing thing){
        thingList.remove(thing);
    }

    public void updateRoomSize(int size){
        this.size = size;
    }

    public LinkedList<Thing> getAllThings(){
        LinkedList<Thing> things = new LinkedList<>();
        for (Thing thing : thingList) {
            things.add(thing);
        }
        return things;
    }

    public LinkedList<Door> getAllDoors() {
        LinkedList<Door> doors = new LinkedList<>();
        for (Thing thing : thingList) {
            if (thing instanceof Door) {
                doors.add((Door) thing);
            }
        }
        return doors;
    }

    public LinkedList<Fan> getAllFans() {
        LinkedList<Fan> fan = new LinkedList<>();
        for (Thing thing : thingList) {
            if (thing instanceof Fan) {
                fan.add((Fan) thing);
            }
        }
        return fan;
    }

    public LinkedList<Light> getAllLights() {
        LinkedList<Light> lights = new LinkedList<>();
        for (Thing thing : thingList) {
            if (thing instanceof Light) {
                lights.add((Light) thing);
            }
        }
        return lights;
    }

    public LinkedList<Window> getAllWindows() {
        LinkedList<Window> windows = new LinkedList<>();
        for (Thing thing : thingList) {
            if (thing instanceof Window) {
                windows.add((Window) thing);
            }
        }
        return windows;
    }

    public void activateThing(Thing thing){
        thing.setSetting(true);
    }

    public void deactivateThing(Thing thing){
        thing.setSetting(false);
    }

    public void activateEverything(){
        for(Thing thing : thingList){
            thing.setSetting(true);
        }
    }

    public void deactivateEverything(){
        for(Thing thing:thingList){
            thing.setSetting(false);
        }
    }

    public void activateAllLights(){
        for(Thing thing:thingList){
            if(thing instanceof Light) {
                thing.setSetting(true);
            }
        }
    }

    public void activateAllFans(){
        for(Thing thing:thingList){
            if(thing instanceof Fan) {
                thing.setSetting(true);
            }
        }
    }

    public void activateAllDoors(){
        for(Thing thing:thingList){
            if(thing instanceof Door) {
                thing.setSetting(true);
            }
        }
    }

    public void activateAllWindows(){
        for(Thing thing:thingList){
            if(thing instanceof Window) {
                thing.setSetting(true);
            }
        }
    }

    public void deactivateAllLights(){
        for(Thing thing:thingList){
            if(thing instanceof Light) {
                thing.setSetting(false);
            }
        }
    }

    public void deactivateAllFans(){
        for(Thing thing:thingList){
            if(thing instanceof Fan) {
                thing.setSetting(false);
            }
        }
    }

    public void deactivateDoors(){
        for(Thing thing:thingList){
            if(thing instanceof Door) {
                thing.setSetting(false);
            }
        }
    }

    public void deactivateWindow(){
        for(Thing thing:thingList){
            if(thing instanceof Window) {
                thing.setSetting(false);
            }
        }
    }

/*
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

 */

}
