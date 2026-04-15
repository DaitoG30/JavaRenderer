package TurnEngine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



public class TurnManager {

    BattleManager battleManager = new BattleManager();
    public List<Unit> units = new ArrayList<>();
    public Unit currentUnit;
    private int currentTurn;
    private int unitIndex;
    private int totalTurns;
    int turnLimit;

    public TurnManager(int maxTurns){
        turnLimit = maxTurns;
    }

    public void battleStart(){
        for (Unit unit : units){
            unit.turnManager = this;
        }
        calculateOrder();
        battleManager.turnManager = this;
        unitIndex = 0;
        System.out.println("Turn: " + currentTurn);
    }

    public void  addUnit(Unit unit){
        units.add(unit);
    }

    public void  removeUnit(Unit unit){
        units.remove(unit);
    }

    public void  removeAllUnits(){
        units.clear();
    }


    /*TODO
     * Rewrite function description along with adjusting it for when a character's speed changes during the same cycle
     */

    /**
     *
     * This function will reset the turn order recalculating the action-value for each unit in the turn list then setting the current unit to the top unit after the sort.
     * Can be called at the end of the turn cycle when the last unit is about to finish their turn.
     *
     */

    public void calculateOrder(){

        units.sort(Comparator.comparing(Unit::calcActionValue));
        currentUnit = units.getFirst();

    }

    public void turnChange(){
        if (unitIndex < (units.toArray().length - 1)) {
            unitIndex++;
            currentUnit = units.get(unitIndex);
            currentUnit.turnStart();
        }
        else {
            unitIndex = 0;
            currentUnit = units.getFirst();
            currentUnit.turnStart();
        }

        currentTurn ++;

        System.out.println("Turn: " + currentTurn);
    }

    public void endGame(){
        unitIndex = 0;
        System.out.println("Game Over");
    }

    public void update(){
        if (currentTurn >= turnLimit){
            endGame();
        }
    }

}
