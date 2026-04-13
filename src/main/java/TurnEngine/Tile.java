package TurnEngine;

import Entities.ModelEntity;
import Models.TexturedModel;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Tile {


    public Vector2f coordinates = new Vector2f();
    private boolean occupied;
    private boolean impassable;
    private Unit unit;


    ModelEntity modelEntity;

    public Tile(int x, int y, TexturedModel texturedModel){
        this.coordinates = new Vector2f(x,y);

        modelEntity = new ModelEntity(texturedModel,new Vector3f( coordinates.x,0, coordinates.y), 0,0,0,0.4f);
    }

    public ModelEntity getModelEntity() {
        return modelEntity;
    }

    public void setModelEntity(ModelEntity modelEntity) {
        this.modelEntity = modelEntity;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isImpassable() {
        return impassable;
    }

    public void setImpassable(boolean impassable) {
        this.impassable = impassable;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
