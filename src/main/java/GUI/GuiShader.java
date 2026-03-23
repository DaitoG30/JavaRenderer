package GUI;

import Shaders.ShaderProgram;
import org.joml.Matrix4f;

public class GuiShader extends ShaderProgram {
    
    private static final String VERTEX_FIlE = "/GUI/Vertex.glsl";
    private static final String FRAGMENT_FILE = "/GUI/Fragment.glsl";


    private int location_transformationMatrix;

    public GuiShader(){
        super(VERTEX_FIlE, FRAGMENT_FILE);
    }

    public void loadTransformMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
    }

    @Override
    protected void bind(){
        super.bind(0,"position");
    }

}
