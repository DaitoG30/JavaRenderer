import Entities.Entity;
import Entities.Light;
import Models.TexturedModel;
import RenderEngine.*;
import Models.RawModel;
import Shaders.StaticShader;
import Textures.ModelTexture;
import Tools.Maths;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.joml.Math;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Launcher {

    private static ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private static ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

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
        Entity entity = new Entity(texturedModel,new Vector3f(0,0,-200), 0.5f,0.5f,0,0.3f);
        Light light = new Light(new Vector3f(1,1,1),new Vector3f(1,140,1));


        Maths.setLastTime(GLFW.glfwGetTime());


        ImGui.createContext();
        ImGuiIO io = ImGui.getIO();
        io.setIniFilename(null); // disable .ini file

        ImGui.styleColorsDark();

        imGuiGlfw.init(window.getWindow(), true);
        imGuiGl3.init("#version 400 core");

        while (!window.windowShouldClose()){
            Maths.setCurrentTime(GLFW.glfwGetTime());
            Maths.calcDeltaTime();
            Maths.setLastTime(GLFW.glfwGetTime());
            //entity.increasePosition(0,(float) Math.sin(GLFW.glfwGetTime() * 3) * 0.45f ,0);
            entity.increaseRotation(0,1,0);
            renderer.prepareDisplay();
            camera.move();
            shader.start();
            shader.loadLight(light);
            shader.loadViewMatrix(camera);
            renderer.render(entity,shader);
            shader.stop();
            // Start frame
            imGuiGlfw.newFrame();
            ImGui.newFrame();

// Your UI here
            ImGui.begin("Debug");
            ImGui.text("FPS: " );
            ImGui.end();

// Render
            ImGui.render();
            imGuiGl3.renderDrawData(ImGui.getDrawData());
            window.updateDisplay();

        }

        imGuiGl3.dispose();
        imGuiGlfw.dispose();
        ImGui.destroyContext();
        shader.cleanUp();
        loader.cleanUp();
        window.closeDisplay();



    }


}
