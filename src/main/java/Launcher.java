import RenderEngine.DisplayManger;
import RenderEngine.Loader;
import RenderEngine.RawModel;
import RenderEngine.Renderer;
import Shaders.StaticShader;

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

        RawModel model = loader.loadToVAO(vertices,indices);



        while (!window.windowShouldClose()){
            renderer.prepareDisplay();
            shader.start();
            renderer.render(model);
            shader.stop();
            window.updateDisplay();

        }

        shader.cleanUp();
        loader.cleanUp();
        window.closeDisplay();



    }


}
