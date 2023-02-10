package com.example.smartroom_javafx.Objects;

import com.example.smartroom_javafx.Database.DatabaseConnectionInsert;

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

    public int getSize(){
        return this.size;}

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


    @Override
    public String toString(){
        return this.name;
    }

}
