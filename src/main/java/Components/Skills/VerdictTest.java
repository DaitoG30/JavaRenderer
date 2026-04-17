package Components.Skills;

import Components.Resource;
import Components.ResourceCost;
import Components.Skill;

public class VerdictTest extends Skill {

    public VerdictTest(){
        setName("Verdict");
        setSkillRange(Range.SINGLE);
        addCost(new ResourceCost(Resource.Type.MANA,10));
        addCost(new ResourceCost(Resource.Type.STAMINA,20));
    }

}
