package RenderEngine;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

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
            position.z -= 0.2f;
        }
        if (GLFW.glfwGetKey(Display.getWindow(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS){
            position.x -= 0.002f;
        }
        if (GLFW.glfwGetKey(Display.getWindow(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS){
            position.x += 0.002f;
        }
        if (GLFW.glfwGetKey(Display.getWindow(), GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS){
            position.z += 0.2f;
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
