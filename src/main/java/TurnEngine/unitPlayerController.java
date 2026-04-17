package TurnEngine;

import Components.Skill;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class unitPlayerController extends UnitController{

    InputManager Input;
    Skill activeSkill;
    List<Unit> targets;
    List<Unit> validTargets;
    Unit currentTarget;


    public unitPlayerController(InputManager Input, Unit unit){
        this.Input=Input;
        this.unit = unit;
    }

    @Override
    public void update(){
        targets = unit.targets;
        if (isActive()){
            //input checks
            handleSkillInput(GLFW.GLFW_KEY_X,unit.ultimate);
            handleSkillInput(GLFW.GLFW_KEY_1,unit.basicSkill);
            handleSkillInput(GLFW.GLFW_KEY_2,unit.skill1);
        }


    }

    public void handleSkillInput(int key, Skill skill){
        if (Input.is_action_just_pressed(key)) {
            if ((activeSkill == null && validTargets == null) || (activeSkill != skill)){
                activeSkill = skill;
                validTargets = activeSkill.validateTargets(targets);
                System.out.println(activeSkill.getName() + " prepped");
                System.out.println(validTargets.toString());
            }
            else if (!validTargets.isEmpty() && activeSkill.canCast(unit)) {
                //placeholder function tbh
                activeSkill.performSkill(unit);
                activeSkill = null;
                validTargets = null;
                unit.turnEnd();
            }
            else {
                System.out.println("no valid targets found or not enough resources");
            }
        }
    }

    public void aimSkill(){

        if (activeSkill != null && !validTargets.isEmpty()) {
            int currentTargetIndex = Math.round((float) validTargets.toArray().length/2) - 1;
            switch (activeSkill.getSkillRange()) {
                case SINGLE:
                    //input check
                    System.out.println("Single Target Skill");
                    break;
                case BURST:
                    System.out.println("Burst Skill");
                    break;
                case AREA_OF_EFFECT:
                    System.out.println("Area of effect Skill. It affects everyone, no need to aim it.");
                    break;
            }
        }


    }


}
