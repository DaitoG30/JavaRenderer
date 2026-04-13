package Components.Skills;


import TurnEngine.Unit;

import java.util.List;

public class Skill {

    private String name;
    private String description;
    private int manaCost;
    private int staminaCost;
    private List<Unit> validTargets;



    /**
     * This function is for skills that require special targets. e.g. targets with specific boons or afflictions on them to be valid
     * it will return a unit as valid to the valid targets array
     */
    private void validateTargets(){}




}
