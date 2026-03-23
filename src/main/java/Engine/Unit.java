package Engine;

import Components.GameComponents.Stat;
import Entities.Entity;
import Entities.ModelEntity;
import Models.TexturedModel;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Unit extends Entity {

    private Vector2f coordinates = new Vector2f();

    ModelEntity modelEntity;
    
    private boolean knocked;
    private boolean dead;
    private float actionValue;
    private boolean playerControlled;
    private Stat health = new Stat(100f);
    private Stat speed =  new Stat(100f);


    /**
     * Initialising the unit class
     * @param coordinates These are the X and Y Values as a Vector-2f and determine the placement of the unit and which tile they will be placed on.
     * @param texturedModel The Model that the unit will be using to represent itself.
     */

    public Unit(Vector2f coordinates, TexturedModel texturedModel) {

        setModelEntity(new ModelEntity(texturedModel, new Vector3f(coordinates.x ,1 ,coordinates.y), 0,0,0,0.3f));

    }

    public float calcActionValue(){
        actionValue = (speed.getMaxValue()/1000f);
        return actionValue;

    }


    /**
     *
     * Turn End logic can and should be overridden for unit specific custom logic.
     * Can be used to trigger boon and affliction effects
     *
     */

    public void turnEnd(){

    }

    /**
     *
     * Turn Start logic can and should be overridden for unit specific custom logic.
     * Can be used to trigger boon and affliction effects
     *
     */

    public void turnStart(){

    }
    
    public ModelEntity getModelEntity() {
        return modelEntity;
    }

    public void setModelEntity(ModelEntity modelEntity) {
        this.modelEntity = modelEntity;
    }

    public float consumeActionValue(float value){
        return actionValue -= value;
    }

    public Vector2f getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Vector2f coordinates) {
        this.coordinates = coordinates;
    }
}
