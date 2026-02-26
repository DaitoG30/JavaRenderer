package Shaders;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/main/java/Shaders/VertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/main/java/Shaders/FragmentShader.glsl";

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bind(){
        super.bind(0,"position");
    }

}
