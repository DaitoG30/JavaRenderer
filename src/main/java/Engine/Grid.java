package Engine;

import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.Loader;
import RenderEngine.OBJLoader;
import Textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    public int width;
    public int height;

    public List<Tile> tiles =  new ArrayList<>();


    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
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

    private void traverseGridHeight(int amount,int[] currentPosition, Unit unit){




    }

    private void traverseGridWidth(int amount,int[] currentPosition, Unit unit){


    }

}
