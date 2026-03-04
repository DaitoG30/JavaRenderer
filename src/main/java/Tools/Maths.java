package Tools;

import RenderEngine.Camera;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class Maths {

    public static double lastTime;
    public static double currentTime;
    public static float deltaTime = (float)(currentTime - lastTime );

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale){
        Matrix4f matrix = new Matrix4f();
        matrix.translate(translation);
        matrix.rotateXYZ(rx, ry, rz);
        matrix.scale(scale);
        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera){
        Matrix4f matrix = new Matrix4f();
        matrix.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1,0,0), matrix);
        matrix.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0,1,0), matrix);
        Vector3f cameraPosition = camera.getPosition();
        Vector3f negativeCameraPosition = new Vector3f(-cameraPosition.x, -cameraPosition.y, -cameraPosition.z);
        matrix.translate(negativeCameraPosition);
        return matrix;
    }



    public static void setLastTime(double time) {
        lastTime = time;
    }

    public static void setCurrentTime(double time) {
        currentTime = time;
    }

    public static void calcDeltaTime(){
        deltaTime = (float)(currentTime - lastTime );
    }
}
