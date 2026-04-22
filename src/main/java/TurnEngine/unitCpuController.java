package TurnEngine;

import Components.Skill;

import java.util.List;

public class unitCpuController extends UnitController{

    Skill activeSkill;
    List<Unit> targets;

    public unitCpuController(Unit unit) {
        this.unit = unit;
    }

    @Override
    public void update() {
        System.out.println("turn performed");
        activeSkill = unit.basicSkill;
        activeSkill.performSkill(unit,targets);
        unit.turnEnd();
    }



}
