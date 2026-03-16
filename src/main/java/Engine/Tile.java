package Engine;

import Entities.ModelEntity;
import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.Loader;
import RenderEngine.OBJLoader;
import Textures.ModelTexture;
import org.joml.Vector3f;

public class Tile {


    public int[] coordinates = new int[2];
    private boolean occupied;
    private boolean impassable;
    private Unit unit;


    ModelEntity modelEntity;

    public Tile(int x, int y, TexturedModel texturedModel){
        this.coordinates = new int[]{x,y};

        modelEntity = new ModelEntity(texturedModel,new Vector3f((float) x,0,(float)y), 0,0,0,0.4f);
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

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }
}
