package com.example.smartroom_javafx.Objects;

import java.util.Date;

public abstract class Thing {
    private int id;
    private String name;
    private Boolean setting;
    private Date time;

    public Thing(String name){
        this.name = name;
        this.setting = false;
    }

    public Thing(int id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Boolean getSetting() {
        return setting;
    }

    public int getId() {
        return id;
    }

    public void setSetting(Boolean setting) {
        this.setting = setting;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString(){
        return this.name + ": " + this.setting;
    }
}
