import Entities.Entity;
import Models.TexturedModel;
import RenderEngine.Camera;
import RenderEngine.DisplayManger;
import RenderEngine.Loader;
import Models.RawModel;
import RenderEngine.Renderer;
import Shaders.StaticShader;
import Textures.ModelTexture;
import org.joml.Vector3f;

public class Launcher {

    public static void main(String[] args){
        //Create Window along with GL capabilities
        DisplayManger window = new DisplayManger("TEST",1280,720,false);
        window.createDisplay();

        Loader loader = new Loader();

        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader,window);
        Camera camera = new Camera(window);



        float[] vertices = {
                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,0.5f,-0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,-0.5f,0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                0.5f,0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                -0.5f,-0.5f,0.5f,
                -0.5f,0.5f,0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,0.5f,-0.5f,
                0.5f,0.5f,-0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,-0.5f,0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f

        };

        int[] indices = {
                0,1,3,
                3,1,2,
                4,5,7,
                7,5,6,
                8,9,11,
                11,9,10,
                12,13,15,
                15,13,14,
                16,17,19,
                19,17,18,
                20,21,23,
                23,21,22

        };

        float[] textureCoords = {

                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0


        };



        RawModel model = loader.loadToVAO(vertices,textureCoords,indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("Dirt"));
        TexturedModel texturedModel = new TexturedModel(texture,model);
        Entity entity = new Entity(texturedModel,new Vector3f(0,0,-200), 0,0,1,1);



        while (!window.windowShouldClose()){
            entity.increaseRotation(0.0002f,0.0002f,0);
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
