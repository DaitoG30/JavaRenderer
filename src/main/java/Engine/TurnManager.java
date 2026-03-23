package Engine;

import java.util.ArrayList;
import java.util.List;



public class TurnManager {

    private List<Unit> units = new ArrayList<>();
    private List<Unit> turnOrder = new ArrayList<>();
    private Unit currentUnit;
    private int currentTurn;
    private int totalTurns;
    int turnLimit;

    public TurnManager(int maxTurns){
        turnLimit = maxTurns;
        calculateOrder();
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

    public void calculateOrder(){}

    public void turnChange(){
        currentUnit.turnEnd();

    }


}
