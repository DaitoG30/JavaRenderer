package Components.GameComponents;


import TurnEngine.Unit;

public class Affliction {

    private String afflictionName;
    private Stat targetStat;


    public void takeEffect(Unit unit){

        unit.getStat(Stat.Type.SPEED);

    }

    public String getAfflictionName() {
        return afflictionName;
    }

    public void setAfflictionName(String afflictionName) {
        this.afflictionName = afflictionName;
    }

    public Stat getTargetStat() {
        return targetStat;
    }

    public void setTargetStat(Stat targetStat) {
        this.targetStat = targetStat;
    }
}
