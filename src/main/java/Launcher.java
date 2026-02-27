import Entities.Entity;
import Models.TexturedModel;
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
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        float[] vertices = {
                -0.35f, 0.5f, 0f,
                -0.35f, -0.5f, 0f,
                0.35f, -0.5f, 0f,
                0.35f, 0.5f, 0f,
        };

        int[] indices = {
                0,1,3,
                3,1,2
        };
        float[] textureCoords = {
                0,0,
                0,1,
                1,1,
                1,0
        };


        RawModel model = loader.loadToVAO(vertices,textureCoords,indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("tooth"));
        TexturedModel texturedModel = new TexturedModel(texture,model);
        Entity entity = new Entity(texturedModel,new Vector3f(-1,0,0), 0,0,0,1);



        while (!window.windowShouldClose()){
            entity.increasePosition(0.00002f,0,0);
            entity.increaseRotation(0,0.001f,0);
            renderer.prepareDisplay();
            shader.start();
            renderer.render(entity,shader);
            shader.stop();
            window.updateDisplay();

        }

        shader.cleanUp();
        loader.cleanUp();
        window.closeDisplay();



    }


}
