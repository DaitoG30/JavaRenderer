package Engine;

import Components.GameComponents.Stat;
import Entities.Entity;

public class Unit extends Entity {

    private int[] coordinates = new int[2];

    private float actionValue;
    private Stat health = new Stat(100f);
    private Stat speed =  new Stat(100f);
    

    public float calcActionValue(){

        actionValue = (speed.getMaxValue()/1000f);
        return actionValue;

    }

    public float consumeActionValue(float value){
        return actionValue -= value;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int x,int y) {
        this.coordinates = new int[]{x,y};
    }

}
