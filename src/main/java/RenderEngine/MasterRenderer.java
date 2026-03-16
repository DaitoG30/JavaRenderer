package RenderEngine;

import Entities.Light;
import Entities.ModelEntity;
import Models.TexturedModel;
import Shaders.StaticShader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {

    private StaticShader shader;
    private DisplayManger display;
    private Renderer renderer;

    private Map<TexturedModel, List<ModelEntity>> entities = new HashMap<TexturedModel,List<ModelEntity>>();

    public MasterRenderer(DisplayManger Display) {
        this.display = Display;
        this.shader = new StaticShader();
        renderer = new Renderer(shader,display);
    }

    public void render(Light sun,Camera camera) {
        renderer.prepareDisplay();
        shader.start();
        shader.loadLight(sun);
        shader.loadViewMatrix(camera);
        renderer.render(entities);
        shader.stop();
        entities.clear();
    }

    public void processEntities(ModelEntity entity) {
        TexturedModel entityModel = entity.getModel();
        List<ModelEntity> batch = entities.get(entityModel);
        if (batch != null) {
            batch.add(entity);
        }
        else {
            List<ModelEntity> newBatch = new ArrayList<ModelEntity>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);
        }

    }

    public void cleanUp() {
        shader.cleanUp();
    }

}
