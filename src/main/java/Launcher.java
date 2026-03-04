import Entities.Entity;
import Models.TexturedModel;
import RenderEngine.*;
import Models.RawModel;
import Shaders.StaticShader;
import Textures.ModelTexture;
import Tools.Maths;
import org.joml.Math;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Launcher {

    public static void main(String[] args){
        //Create Window along with GL capabilities
        DisplayManger window = new DisplayManger("TEST",1280,720,false);
        window.createDisplay();

        Loader loader = new Loader();

        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader,window);
        Camera camera = new Camera(window);


        RawModel model = OBJLoader.loadOBJModel("dragon",loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("Dirt"));
        TexturedModel texturedModel = new TexturedModel(texture,model);
        Entity entity = new Entity(texturedModel,new Vector3f(0,0,-200), 0.5f,0.5f,0,1);


        Maths.setLastTime(GLFW.glfwGetTime());

        while (!window.windowShouldClose()){
            Maths.setCurrentTime(GLFW.glfwGetTime());
            Maths.calcDeltaTime();
            Maths.setLastTime(GLFW.glfwGetTime());
            entity.increasePosition(0,(float) Math.sin(GLFW.glfwGetTime() * 3) * 0.45f ,0);
            entity.increaseRotation(0,1,0);
            renderer.prepareDisplay();
            camera.move();
            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(entity,shader);
            shader.stop();
            window.updateDisplay();

        }

        shader.cleanUp();
        loader.cleanUp();
        window.closeDisplay();



    }


}
