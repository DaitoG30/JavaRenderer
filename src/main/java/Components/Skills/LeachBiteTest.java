package Components.Skills;

import Components.Resource;
import Components.ResourceCost;
import Components.Skill;

public class LeachBiteTest extends Skill {

    public LeachBiteTest() {
        setName("Leach Bite");
        addCost(new ResourceCost(Resource.Type.MANA,10));
        setSkillRange(Range.SINGLE);
        setBaseValue(-20);
    }

}
