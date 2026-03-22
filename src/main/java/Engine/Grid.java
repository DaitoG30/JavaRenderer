package Engine;

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

    public int width;
    public int height;
    public float scale;

    public List<Tile> tiles =  new ArrayList<>();
    public List<Unit> units =  new ArrayList<>();


    public Grid(int width, int height, float tileScale) {
        this.width = width;
        this.height = height;
        this.scale = tileScale;
    }

    public void render(MasterRenderer renderer){
        for(Tile tile: tiles){
            renderer.processEntities(tile.getModelEntity());
        }

        for(Unit unit: units){
            renderer.processEntities(unit.getModelEntity());
        }

    }

    public void initializeTiles() {
        Loader loader = new Loader();
        RawModel model = OBJLoader.loadOBJModel("cube",loader);
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


    private void placeUnit(Vector2f coordinates, TexturedModel texturedModel ){

        Unit unit = new Unit(coordinates,texturedModel);
        units.add(unit);

    }


    private void traverseGridHeight(int amount,int[] currentPosition, Unit unit){

    }

    private void traverseGridWidth(int amount,int[] currentPosition, Unit unit){

    }

}
