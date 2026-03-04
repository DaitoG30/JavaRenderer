package RenderEngine;

import Entities.Entity;
import Models.RawModel;
import Models.TexturedModel;
import Shaders.StaticShader;
import Tools.Maths;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Renderer {

    public static final float FOV = (float) Math.toRadians(90);
    public static final float Z_NEAR = 0.1f;
    public static final float Z_FAR = 1000f;
    public static DisplayManger Display;

    private Matrix4f projectionMatrix;

    public void prepareDisplay() {

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LESS);
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.5f,0.5f,0.5f,1);
    }

    public  Renderer(StaticShader shader,DisplayManger display) {
        Display = display;
        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }


    public void render(Entity entity, StaticShader staticShader) {
        TexturedModel texturedModel = entity.getModel();
        RawModel model = texturedModel.getRawModel();
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        Matrix4f matrix4f = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(),entity.getRotZ(), entity.getScale());
        staticShader.loadTransformationMatrix(matrix4f);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getTextureID());
        GL11.glDrawElements(GL11.GL_TRIANGLES,model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }


    private void createProjectionMatrix() {
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) (1f / Math.tan(Math.toRadians(FOV/2f)) );
        float x_scale = y_scale / aspectRatio;
        float frustum_length = Z_FAR - Z_NEAR;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00(x_scale);
        projectionMatrix.m11(y_scale);
        projectionMatrix.m22(-((Z_FAR +  Z_NEAR) / frustum_length));
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-((2 * Z_NEAR * Z_FAR) / frustum_length));
        projectionMatrix.m33(0);

    }
}
