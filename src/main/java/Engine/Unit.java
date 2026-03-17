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
    private Stat health = new Stat(100f);
    private Stat speed =  new Stat(100f);


    public Unit(Vector2f coordinates, TexturedModel texturedModel) {

        setModelEntity(new ModelEntity(texturedModel, new Vector3f(coordinates.x ,2f ,coordinates.y), 0,0,0,0.4f));

    }

    public float calcActionValue(){
        actionValue = (speed.getMaxValue()/1000f);
        return actionValue;

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
