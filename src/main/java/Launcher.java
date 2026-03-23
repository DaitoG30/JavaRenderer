import Engine.Grid;
import Engine.Tile;
import Engine.TurnManager;
import Entities.Light;
import GUI.GuiRenderer;
import GUI.GuiTexture;
import Models.TexturedModel;
import RenderEngine.*;
import Models.RawModel;
import Textures.ModelTexture;
import Tools.DebugUI;
import Tools.Maths;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;


public class Launcher {


    public static void main(String[] args){
        //Create Window along with GL capabilities
        DisplayManger window = new DisplayManger("TEST",1280,720,false);
        window.createDisplay();

        Loader loader = new Loader();

        Camera camera = new Camera(window);
        Grid grid = new Grid(10,10, new TurnManager(40));

        Light light = new Light(new Vector3f(1,1,1),new Vector3f(1,140,1));
        DebugUI debugUI = new DebugUI(window);


        List<GuiTexture> guis = new ArrayList<GuiTexture>();
        GuiTexture guiTexture = new GuiTexture(loader.loadTexture("grey"),new Vector2f(0.2f),new Vector2f(0.2f) );
        guis.add(guiTexture);
        guiTexture.setVisible(true);

        GuiRenderer guiRenderer = new GuiRenderer(loader);

        Maths.setLastTime(GLFW.glfwGetTime());

        debugUI.imGuiInit();

        grid.initializeTiles();
        for(Tile tile: grid.tiles){
            System.out.println(tile.coordinates);
        }

        RawModel model = OBJLoader.loadOBJModel("Cylinder",loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("Grey"));
        TexturedModel cylinder = new TexturedModel(texture,model);
        grid.placeUnit(new Vector2f(2,9),cylinder);
        grid.placeUnit(new Vector2f(5,5),cylinder);

        MasterRenderer masterRenderer = new MasterRenderer(window);
        while (!window.windowShouldClose()){
            Maths.setCurrentTime(GLFW.glfwGetTime());
            Maths.calcDeltaTime();
            Maths.setLastTime(GLFW.glfwGetTime());
            camera.move();
            grid.render(masterRenderer);
            masterRenderer.render(light,camera);
            guiRenderer.render(guis);
            window.updateDisplay();

        }

        guiRenderer.cleanUp();
        masterRenderer.cleanUp();
        debugUI.cleanUp();
        loader.cleanUp();
        window.closeDisplay();



    }


}
