package Engine;

import java.util.ArrayList;
import java.util.List;

public class TurnManager {

    private List<Unit> units = new ArrayList<>();




    public void  addUnit(Unit unit){
        units.add(unit);
    }

    public void  removeUnit(Unit unit){
        units.remove(unit);
    }

    public void  removeAllUnits(){
        units.clear();
    }

    public void calculateOrder(){
        Unit prevUnit;


    }




}
