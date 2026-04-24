package Components.Skills;

import Components.Resource;
import Components.ResourceCost;
import Components.Skill;

public class FeastOfAbsolutionTest extends Skill {

    public FeastOfAbsolutionTest() {
        setName("Feast Of Absolution");
        addCost(new ResourceCost(Resource.Type.MANA,10));
        setSkillRange(Range.AREA_OF_EFFECT);
        setBaseValue(-5);
    }


}
