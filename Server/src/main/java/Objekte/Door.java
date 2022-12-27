package Objekte;

public class Door extends Thing {

    private int doorId;

    public Door(String name) {
        super(name);
    }

    public Door(String name, int id) {
        super(name);
        this.doorId = id;
    }


    public void setDoorId(int doorId) {
        this.doorId = doorId;
    }

    public int getDoorId() {
        return doorId;
    }

    @Override
    public String toString(){
        if (getSetting()) {
            return this.getName() + " " + getDoorId()+ " unlocked";
        } else {
            return this.getName() + " " + getDoorId() + " locked";
        }
    }
}
