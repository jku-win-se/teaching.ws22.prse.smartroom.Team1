package Objekte;

public class Light extends Thing {

    private int lightId;

    public Light(String name) {
        super(name);
    }

    public Light(String name, int id) {
        super(name);
        this.lightId = id;
    }

    public void setLightId(int lightId) {
        this.lightId = lightId;
    }

    public int getLightId() {
        return lightId;
    }

    @Override
    public String toString(){
        if (getSetting()){
            return this.getName() + " " + getLightId() + " on";
        } else {
            return this.getName() + " " + getLightId() + " off";
        }
    }
}
