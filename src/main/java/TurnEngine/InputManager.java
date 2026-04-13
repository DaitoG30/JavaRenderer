package TurnEngine;

import RenderEngine.DisplayManger;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class InputManager {

    public DisplayManger display;

    public InputManager(DisplayManger display){
        this.display = display;
    }

    private final Map<Integer, Boolean> previousKeyStates = new HashMap<>();

    public boolean is_action_just_pressed(int KEY_PRESSED) {
        boolean pressed = GLFW.glfwGetKey(display.getWindow(), KEY_PRESSED) == GLFW.GLFW_PRESS;
        boolean wasPressed = previousKeyStates.getOrDefault(KEY_PRESSED, false);

        previousKeyStates.put(KEY_PRESSED, pressed);

        return pressed && !wasPressed;
    }







}
