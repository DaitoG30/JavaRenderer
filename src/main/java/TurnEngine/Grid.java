package TurnEngine;

import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.Loader;
import RenderEngine.MasterRenderer;
import RenderEngine.OBJLoader;
import Textures.ModelTexture;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private final int width;
    private final int height;

    Loader loader = new Loader();

    public List<Tile> tiles =  new ArrayList<>();
    public List<Unit> units =  new ArrayList<>();
    public TurnManager turnManager;


    public Grid(int width, int height, TurnManager turnManager) {
        this.turnManager = turnManager;
        this.width = width;
        this.height = height;
    }

    public void render(MasterRenderer renderer){
        for(Tile tile: tiles){
            renderer.processEntities(tile.getModelEntity());
        }

        for(Unit unit: units){
            renderer.processEntities(unit.getModelEntity());
        }

    }

    public void cleanUp(){
        loader.cleanUp();

    }

    public void initializeTiles() {
        RawModel model = OBJLoader.loadOBJModel("Plane",loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("Dirt"));
        TexturedModel texturedModel = new TexturedModel(texture,model);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                createTile(x, y, texturedModel);
            }
        }
    }

    private void createTile(int x, int y, TexturedModel texturedModel){
        Tile tile = new Tile(x,y,texturedModel);
        tiles.add(tile);
    }

    /**
     *Placing the Unit onto the grid and marking in its position in world space.
     *
     * @param coordinates These are the X and Y Values as a Vector-2f and determine the placement of the unit and which tile they will be placed on.
     * @param texturedModel The Model that the unit will be using to represent itself.
     *
     *
     * @see Unit
     */

    public void placeUnit(Vector2f coordinates, TexturedModel texturedModel ) {

        Tile tile = getTileAt(coordinates);

        if(tile != null ){
            if (!tile.isOccupied()){
                tile.setOccupied(true);
                tile.setImpassable(true);
                Unit unit = new Unit(coordinates, texturedModel);
                units.add(unit);
                tile.setUnit(unit);
            }
            else {
                System.err.println("Tile already occupied");
            }
        }
        else{
            System.err.println("No tile at " + coordinates);
        }

        turnManager.units = units;
    }

    public Tile getTileAt(Vector2f coordinates){
        for(Tile tile: tiles){
            if (coordinates.equals(tile.coordinates)) return tile;
        }
        return null;
    }


    private void traverseGridHeight(float amount,Unit unit){

    }

    private void traverseGridWidth(float amount, Unit unit){

    }

}
