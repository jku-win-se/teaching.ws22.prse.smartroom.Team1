package com.example.smartroom_javafx;

public class Window extends Thing {

    private int windowId;
    public Window(String name) {super(name); }

    public Window(String name, int id) {
        super(name);
        this.windowId = id;
    }

    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    public int getWindowId() {
        return windowId;
    }

    @Override
    public String toString(){
        if (getSetting()) {
            return this.getName() + " " + getWindowId() + " open";
        } else {
            return this.getName() +  " " + getWindowId() + " closed";
        }
    }
}
