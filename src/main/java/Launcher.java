import Engine.Grid;
import Engine.SceneManager;
import Engine.Tile;
import Entities.ModelEntity;
import Entities.Light;
import Models.TexturedModel;
import RenderEngine.*;
import Models.RawModel;
import Shaders.StaticShader;
import Textures.ModelTexture;
import Tools.DebugUI;
import Tools.Maths;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;
import java.util.List;

public class Launcher {


    public static void main(String[] args){
        //Create Window along with GL capabilities
        DisplayManger window = new DisplayManger("TEST",1280,720,false);
        window.createDisplay();

        Loader loader = new Loader();

        Camera camera = new Camera(window);
        Grid grid = new Grid(10,10);

        Light light = new Light(new Vector3f(1,1,1),new Vector3f(1,140,1));
        DebugUI debugUI = new DebugUI(window);

        Maths.setLastTime(GLFW.glfwGetTime());

        debugUI.imGuiInit();

        grid.initializeTiles();
        for(Tile tile: grid.tiles){
            System.out.println(tile.coordinates);
        }

        MasterRenderer masterRenderer = new MasterRenderer(window);
        while (!window.windowShouldClose()){
            Maths.setCurrentTime(GLFW.glfwGetTime());
            Maths.calcDeltaTime();
            Maths.setLastTime(GLFW.glfwGetTime());
            camera.move();
            for(Tile tile: grid.tiles){

               masterRenderer.processEntities(tile.getModelEntity());

            }
            masterRenderer.render(light,camera);
            window.updateDisplay();

        }
        masterRenderer.cleanUp();
        debugUI.cleanUp();
        loader.cleanUp();
        window.closeDisplay();



    }


}
