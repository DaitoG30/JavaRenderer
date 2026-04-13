import GUI.Button;
import TurnEngine.*;
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
        InputManager Input = new InputManager(window);


        Loader loader = new Loader();

        Camera camera = new Camera(window);
        Grid grid = new Grid(10,10, new TurnManager(40));

        Light light = new Light(new Vector3f(1,1,1),new Vector3f(1,5140,1));


        List<GuiTexture> guis = new ArrayList<GuiTexture>();
        GuiTexture guiTexture = new GuiTexture(loader.loadTexture("grey"),new Vector2f(0.2f),new Vector2f(0.2f) );
        guis.add(guiTexture);
        guiTexture.setVisible(true);

        GuiRenderer guiRenderer = new GuiRenderer(loader);

        Maths.setLastTime(GLFW.glfwGetTime());

        grid.initializeTiles();
        for(Tile tile: grid.tiles){
            System.out.println(tile.coordinates);
        }

        RawModel model = OBJLoader.loadOBJModel("Cylinder",loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("Grey"));
        TexturedModel cylinder = new TexturedModel(texture,model);
        grid.placeUnit(new Vector2f(2,9),cylinder,false);
        grid.placeUnit(new Vector2f(5,5),cylinder,true);

        for (Unit unit: grid.units){
            unit.unitController = new unitPlayerController(Input,unit);
        }

        MasterRenderer masterRenderer = new MasterRenderer(window);

        Button button = new Button(loader.loadTexture("grey"),new Vector2f().zero(),new Vector2f(0.5f));


        while (!window.windowShouldClose()){
            Maths.setCurrentTime(GLFW.glfwGetTime());
            Maths.calcDeltaTime();
            Maths.setLastTime(GLFW.glfwGetTime());

            for (Unit unit: grid.units){
                unit.unitController.update();
            }

            guiTexture.setVisible(grid.turnManager.currentUnit.unitController.isActive());

            camera.move();
            button.render(guiRenderer);
            grid.render(masterRenderer);
            masterRenderer.render(light,camera);
            guiRenderer.render(guis);
            window.updateDisplay();

            if (Input.is_action_just_pressed(GLFW.GLFW_KEY_SPACE)){
                grid.turnManager.turnChange();
            }


        }

        grid.cleanUp();
        guiRenderer.cleanUp();
        masterRenderer.cleanUp();
        loader.cleanUp();
        window.closeDisplay();



    }


}
