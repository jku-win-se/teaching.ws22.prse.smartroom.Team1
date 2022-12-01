package com.example.smartroom_javafx;

public class Fan extends Thing {

    private int fanId;

    public Fan(String name) {
        super(name);
    }

    public Fan(String name, int id) {
        super(name);
        this.fanId = id;
    }

    public void setFanId(int fanId) {
        this.fanId = fanId;
    }

    public int getFanId() {
        return fanId;
    }

    @Override
    public String toString(){
        if (getSetting()){
            return this.getName() + " " + getFanId() +  " on";
        } else {
            return this.getName() + " "  + getFanId() + " off";
        }
    }
}
