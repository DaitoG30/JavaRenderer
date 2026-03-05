import Engine.SceneManager;
import Entities.Entity;
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

public class Launcher {

    private static ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private static ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

    private static SceneManager sceneManager = new SceneManager();

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
        ModelEntity modelEntity = new ModelEntity(texturedModel,new Vector3f(0,0,-200), 0.5f,0.5f,0,0.3f);
        Light light = new Light(new Vector3f(1,1,1),new Vector3f(1,140,1));
        DebugUI debugUI = new DebugUI(window);

        Maths.setLastTime(GLFW.glfwGetTime());

        debugUI.imGuiInit();

        sceneManager.addChild(new ModelEntity(texturedModel,new Vector3f(1,0,-200), 0.5f,0.5f,0,0.3f));
        sceneManager.addChild(new ModelEntity(texturedModel,new Vector3f(5,0,-200), 0.5f,0.5f,0,0.3f));
        sceneManager.addChild(new ModelEntity(texturedModel,new Vector3f(-5,0,-200), 0.5f,0.5f,0,0.3f));
        sceneManager.addChild(new ModelEntity(texturedModel,new Vector3f(-1,0,-200), 0.5f,0.5f,0,0.3f));

        while (!window.windowShouldClose()){
            Maths.setCurrentTime(GLFW.glfwGetTime());
            Maths.calcDeltaTime();
            Maths.setLastTime(GLFW.glfwGetTime());
            //modelEntity.increasePosition(0,(float) Math.sin(GLFW.glfwGetTime() * 3) * 0.45f ,0);
            modelEntity.increaseRotation(0,1,0);
            renderer.prepareDisplay();
            camera.move();
            shader.start();
            shader.loadLight(light);
            shader.loadViewMatrix(camera);
            for(ModelEntity entity: sceneManager.getChildren()){
                //System.out.println(entity);
                renderer.render(entity,shader);
            }

            //renderer.render(modelEntity,shader);
            shader.stop();
            debugUI.imGuiRenderDebug(sceneManager);
            window.updateDisplay();

        }

        debugUI.cleanUp();
        shader.cleanUp();
        loader.cleanUp();
        window.closeDisplay();



    }


}
