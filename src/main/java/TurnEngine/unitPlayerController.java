package TurnEngine;

import Components.Skills.Skill;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public class unitPlayerController extends UnitController{

    InputManager Input;
    Grid grid;
    Skill ActiveSkill;


    public unitPlayerController(InputManager Input, Unit unit){
        this.Input=Input;
        this.unit = unit;
    }

    @Override
    public void update(){
        if (isActive()){
            //input checks
            if (Input.is_action_just_pressed(GLFW.GLFW_KEY_1)) {
                System.out.println("skill 1");

            }
            if (Input.is_action_just_pressed(GLFW.GLFW_KEY_2)) {
                System.out.println("skill 2");
            }
            if (Input.is_action_just_pressed(GLFW.GLFW_KEY_X)) {
                System.out.println("Ultimate skill");
            }

        }


    }


    


}
