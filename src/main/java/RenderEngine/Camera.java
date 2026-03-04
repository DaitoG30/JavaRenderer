package RenderEngine;

import Tools.Maths;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import java.nio.DoubleBuffer;

public class Camera {

    private Vector3f position = new  Vector3f(0,0,0);
    private float yaw = 0f;
    private float pitch = 0f;
    private float roll = 0f;


    private DisplayManger Display;
    public Camera(DisplayManger DisplayManger) {
        Display = DisplayManger;
    }

    public void move(){

        if (GLFW.glfwGetKey(Display.getWindow(), GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS){
            position.z -= Maths.deltaTime * 50;
        }
        if (GLFW.glfwGetKey(Display.getWindow(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS){
            position.x -= Maths.deltaTime * 1;
        }
        if (GLFW.glfwGetKey(Display.getWindow(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS){
            position.x += Maths.deltaTime * 1;
        }
        if (GLFW.glfwGetKey(Display.getWindow(), GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS){
            position.z += Maths.deltaTime * 50;
        }
    }


    public Vector3f getPosition() {
        return position;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public float getRoll() {
        return roll;
    }
}
