package Components;


import Components.GameComponents.Affliction;
import Components.GameComponents.Boon;
import TurnEngine.Unit;

import java.util.ArrayList;
import java.util.List;

public class Skill {


    private String name;
    private String description;
    private int manaCost;
    private int staminaCost;
    private List<Unit> validTargets;
    private List<Affliction> afflictions;
    private List<Boon> boons;
    private final List<ResourceCost> cost = new ArrayList<>();



    private Range skillRange;


    public enum Range{
        SINGLE,
        BURST,
        AREA_OF_EFFECT
    }


    /**
     * This function is for skills that require special targets.
     * it will default to checking if the targets are not null
     * e.g. targets with specific boons or afflictions on them to be valid
     * it will return a unit as valid to the valid targets array
     * @param targets the list of allies or enemies that need to be checked for certain conditions
     * @return A list of valid targets that the skill can manipulate
     */
    public List<Unit> validateTargets(List<Unit> targets) {
        List<Unit> validTargets = new ArrayList<Unit>();
        for (Unit target : targets) {
            if (target != null){
                validTargets.add(target);
            }
        }
        return validTargets;
    }



    public boolean canCast(Unit unit) {
        for (ResourceCost cost : cost) {
            if (!cost.canCast(unit)){
                System.out.println("Can't cast " +name +", "+ cost.getResource().getName() + " is too low");
                return false;
            }
        }
        return true;
    }


    public void performSkill(Unit unit){
        for(ResourceCost cost : cost){
            cost.cast(unit);
        }
        System.out.println(getName() + " performed");
    }

    public List<ResourceCost> getCost(){
        return cost;
    }

    public void addCost(ResourceCost resourceCost){
        cost.add(resourceCost);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Range getSkillRange() {
        return skillRange;
    }

    public void setSkillRange(Range skillRange) {
        this.skillRange = skillRange;
    }

}
