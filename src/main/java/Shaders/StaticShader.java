package Shaders;

import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/main/java/Shaders/VertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/main/java/Shaders/FragmentShader.glsl";

    private int location_transformationMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bind(){
        super.bind(0,"position");
        super.bind(1,"textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        System.out.println("Uniform location: " + location_transformationMatrix);
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }

}
