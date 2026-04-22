package TurnEngine;

import java.util.ArrayList;
import java.util.List;

public class BattleManager {

    TurnManager turnManager;

    public enum Team {
        TEAM_A,
        TEAM_B,
    }



    public void checkBattle(){
        //Check for which team has units alive
        int teamACount = 0;
        int teamBCount = 0;

        for (Unit unit: turnManager.units){
            if (unit.isAlive){
                if (unit.team == Team.TEAM_A){
                    teamACount++;
                }
                else{
                    teamBCount++;
                }
            }
        }

        if (teamBCount == 0 || teamACount == 0){
            turnManager.endGame();
        }


    }



    public List<Unit> getEnemyTargets(Unit currentUnit) {
        List<Unit> possibleTargets = new ArrayList<>();
        for (Unit unit :turnManager.units){
            if (unit.team != currentUnit.team && unit.isAlive){
                possibleTargets.add(unit);
            }
        }
        return possibleTargets;
    }

    public List<Unit> getAlliedTargets(Unit currentUnit) {
        List<Unit> possibleTargets = new ArrayList<>();
        for (Unit unit :turnManager.units){
            if (unit.team == currentUnit.team && unit.isAlive){
                possibleTargets.add(unit);
            }
        }
        return possibleTargets;
    }



}
