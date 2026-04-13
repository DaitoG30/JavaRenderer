package TurnEngine;

public class unitCpuController extends UnitController{

    @Override
    public void update() {
        System.out.println("turn performed");
        unit.turnEnd();
    }



}
