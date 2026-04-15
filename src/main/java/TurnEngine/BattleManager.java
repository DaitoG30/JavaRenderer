package TurnEngine;

import java.util.ArrayList;
import java.util.List;

public class BattleManager {

    TurnManager turnManager;

    public enum Team {
        TEAM_A,
        TEAM_B,
    }


    public List<Unit> getTargets(Unit currentUnit) {
        List<Unit> possibleTargets = new ArrayList<>();
        for (Unit unit :turnManager.units){
            if (unit.team != currentUnit.team){
                possibleTargets.add(unit);
            }
        }
        return possibleTargets;
    }





}
