package RenderEngine;

import Tools.Maths;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera {

    private Vector3f position = new Vector3f(0, 0, 0);
    private float yaw = 0f;
    private float pitch = 0f;
    private float roll = 0f;

    private double lastX = 0, lastY = 0;
    private boolean firstMouse = true;
    private static final float SENSITIVITY = 0.1f;
    private static final float MAX_PITCH = 89f;

    private DisplayManger Display;

    public Camera(DisplayManger displayManger) {
        Display = displayManger;
        // lock and hide cursor
        GLFW.glfwSetInputMode(Display.getWindow(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
    }

    public void move() {
        handleMouse();
        handleKeyboard();
    }

    private void handleMouse() {
        double[] xPos = new double[1];
        double[] yPos = new double[1];
        GLFW.glfwGetCursorPos(Display.getWindow(), xPos, yPos);

        if (firstMouse) {
            lastX = xPos[0];
            lastY = yPos[0];
            firstMouse = false;
        }

        double deltaX = xPos[0] - lastX;
        double deltaY = yPos[0] - lastY; // inverted, y goes bottom to top
        lastX = xPos[0];
        lastY = yPos[0];

        yaw += (float) deltaX * SENSITIVITY;
        pitch += (float) deltaY * SENSITIVITY;
        pitch = Math.max(-MAX_PITCH, Math.min(MAX_PITCH, pitch));
        // clamp pitch
    }

    private void handleKeyboard() {
        // direction based on yaw so movement is relative to where you're looking
        float yawRad = (float) Math.toRadians(yaw);

        float forwardX = (float) Math.sin(yawRad);
        float forwardZ = (float) -Math.cos(yawRad);

        float speed = Maths.deltaTime * 20;

        if (GLFW.glfwGetKey(Display.getWindow(), GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) {
            position.x += forwardX * speed;
            position.z += forwardZ * speed;
        }
        if (GLFW.glfwGetKey(Display.getWindow(), GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) {
            position.x -= forwardX * speed;
            position.z -= forwardZ * speed;
        }
        if (GLFW.glfwGetKey(Display.getWindow(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
            position.x -= (float) Math.cos(yawRad) * speed;
            position.z -= (float) Math.sin(yawRad) * speed;
        }
        if (GLFW.glfwGetKey(Display.getWindow(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
            position.x += (float) Math.cos(yawRad) * speed;
            position.z += (float) Math.sin(yawRad) * speed;
        }
        if (GLFW.glfwGetKey(Display.getWindow(), GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
            position.y += speed;
        }
        if (GLFW.glfwGetKey(Display.getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS) {
            position.y -= speed;
        }
    }

    public Vector3f getPosition() { return position; }
    public float getYaw() { return yaw; }
    public float getPitch() { return pitch; }
    public float getRoll() { return roll; }
}