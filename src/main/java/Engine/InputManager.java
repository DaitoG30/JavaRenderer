package Engine;

import RenderEngine.DisplayManger;
import org.lwjgl.glfw.GLFW;

public class InputManager {

    public DisplayManger display;

    public InputManager(DisplayManger display){
        this.display = display;
    }

    public boolean is_action_just_pressed(int KEY_PRESSED){
        return GLFW.glfwGetKey(display.getWindow(), KEY_PRESSED) == GLFW.GLFW_PRESS;
    }


}
