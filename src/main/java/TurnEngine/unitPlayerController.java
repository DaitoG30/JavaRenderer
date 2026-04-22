package TurnEngine;

import Components.Skill;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class unitPlayerController extends UnitController{

    InputManager Input;
    Skill activeSkill;
    List<Unit> initialTargets;
    List<Unit> validTargets;
    List<Unit> finalTargets = new ArrayList<>();
    Unit currentTarget;
    int currentTargetIndex;


    public unitPlayerController(InputManager Input, Unit unit){
        this.Input=Input;
        this.unit = unit;
    }

    @Override
    public void update(){
        initialTargets = unit.targets;
        if (isActive()){
            //input checks
            handleSkillInput(GLFW.GLFW_KEY_X,unit.ultimate);
            handleSkillInput(GLFW.GLFW_KEY_1,unit.basicSkill);
            handleSkillInput(GLFW.GLFW_KEY_2,unit.skill1);
            aimSkill();
        }
    }

    public void handleSkillInput(int key, Skill skill){
        if (Input.is_action_just_pressed(key)) {
            if ((activeSkill == null && validTargets == null) || (activeSkill != skill)){
                activeSkill = skill;
                validTargets = activeSkill.validateTargets(initialTargets);
                int len =  validTargets.size();
                currentTargetIndex = (int) Math.floor(len/2);
                System.out.println(activeSkill.getName() + " prepped");
                System.out.println(validTargets.toString());
            }
            else if (!validTargets.isEmpty() && activeSkill.canCast(unit)) {
                //placeholder function tbh
                activeSkill.performSkill(unit, finalTargets);
                activeSkill = null;
                finalTargets.clear();
                validTargets = null;
                unit.turnEnd();
            }
            else {
                System.out.println("no valid initialTargets found or not enough resources");
            }
        }
    }

    public void aimSkill(){

        if (activeSkill != null && !validTargets.isEmpty()) {
            switch (activeSkill.getSkillRange()) {
                case SINGLE:
                    //Lock onto central target
                    if (finalTargets.isEmpty()) {
                        currentTarget = validTargets.get(currentTargetIndex);
                        finalTargets.add(currentTarget);
                        System.out.println("Locked onto central unit");
                     }
                    //input check
                    if (Input.is_action_just_pressed(GLFW.GLFW_KEY_A)) {
                        if (currentTargetIndex <= 0){
                            finalTargets.clear();
                            currentTargetIndex = 0;
                            currentTarget = validTargets.get(currentTargetIndex);
                            finalTargets.add(currentTarget);
                            System.out.println("No more targets on the left");
                        }
                        else {
                            finalTargets.clear();
                            currentTargetIndex--;
                            currentTarget = validTargets.get(currentTargetIndex);
                            finalTargets.add(currentTarget);
                        }
                    }
                    if (Input.is_action_just_pressed(GLFW.GLFW_KEY_D)) {
                        if (currentTargetIndex >= validTargets.size()-1) {
                            finalTargets.clear();
                            currentTargetIndex = validTargets.size()-1;
                            currentTarget = validTargets.get(currentTargetIndex);
                            finalTargets.add(currentTarget);
                            System.out.println("No more targets on the right");
                        } else {
                            finalTargets.clear();
                            currentTargetIndex++;
                            currentTarget = validTargets.get(currentTargetIndex);
                            finalTargets.add(currentTarget);
                        }
                    }
                    break;
                case BURST:
                    //System.out.println("Burst Skill");
                    break;
                case AREA_OF_EFFECT:

                    if (finalTargets.isEmpty()) {
                        finalTargets = validTargets;
                        System.out.println("All Marked");
                    }

                    //System.out.println("Area of effect Skill. It affects everyone, no need to aim it.");
                    break;
            }
        }


    }


}
