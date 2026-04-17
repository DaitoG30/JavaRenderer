package Components;

import TurnEngine.Unit;

public interface Cost {

    boolean canCast(Unit unit);
    void cast(Unit unit);

}


