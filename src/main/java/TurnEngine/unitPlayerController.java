package TurnEngine;

import Components.Skill;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class unitPlayerController extends UnitController{

    InputManager Input;
    Skill activeSkill;
    List<Unit> targets;
    List<Unit> validTargets;


    public unitPlayerController(InputManager Input, Unit unit){
        this.Input=Input;
        this.unit = unit;
    }

    @Override
    public void update(){
        targets = unit.targets;
        if (isActive()){
            //input checks
            if (Input.is_action_just_pressed(GLFW.GLFW_KEY_1)) {
                if ((activeSkill == null && validTargets == null) || (activeSkill != unit.basicSkill)){
                    activeSkill = unit.basicSkill;
                    validTargets = activeSkill.validateTargets(targets);
                    System.out.println(activeSkill.getName() + " prepped");
                    System.out.println(validTargets.toString());
                }
                else if (!validTargets.isEmpty()) {
                    //placeholder function tbh
                    activeSkill.performSkill();
                    activeSkill = null;
                    validTargets = null;
                }
                else {
                    System.out.println("no valid targets found");
                }
            }
            if (Input.is_action_just_pressed(GLFW.GLFW_KEY_2)) {
                if ((activeSkill == null && validTargets == null) || (activeSkill != unit.skill1)){
                    activeSkill = unit.skill1;
                    validTargets = activeSkill.validateTargets(targets);
                    System.out.println(activeSkill.getName() + " prepped");
                    System.out.println(validTargets.toString());
                } else if (!validTargets.isEmpty()) {
                    //placeholder function tbh
                    activeSkill.performSkill();
                    activeSkill = null;
                    validTargets = null;
                }
                else {
                    System.out.println("no valid targets found");
                }
            }
            if (Input.is_action_just_pressed(GLFW.GLFW_KEY_X)) {
                if ((activeSkill == null && validTargets == null) || (activeSkill != unit.ultimate)){
                    activeSkill = unit.ultimate;
                    validTargets = activeSkill.validateTargets(targets);
                    System.out.println(activeSkill.getName() + " prepped");
                    System.out.println(validTargets.toString());
                }
                else if (!validTargets.isEmpty()) {
                    //placeholder function tbh
                    activeSkill.performSkill();
                    activeSkill = null;
                    validTargets = null;
                }
                else {
                    System.out.println("no valid targets found");
                }
            }

        }


    }


    


}
