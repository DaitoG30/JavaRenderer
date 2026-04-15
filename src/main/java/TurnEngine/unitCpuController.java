package TurnEngine;

import Components.Skill;

public class unitCpuController extends UnitController{

    Skill activeSkill;

    public unitCpuController(Unit unit) {
        this.unit = unit;
    }

    @Override
    public void update() {
        System.out.println("turn performed");
        activeSkill = unit.basicSkill;
        activeSkill.performSkill();
        unit.turnEnd();
    }



}
