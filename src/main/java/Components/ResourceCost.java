package Components;

import TurnEngine.Unit;

public class ResourceCost implements Cost {
    private Resource.Type resourceType;
    private int costAmount;
    private Resource resource;

    public ResourceCost(Resource.Type resourceType, int costAmount) {
        this.resourceType = resourceType;
        this.costAmount = costAmount;
    }

    @Override
    public boolean canCast(Unit unit) {
        resource = unit.getResource(resourceType);
        return resource.getAmount() >= costAmount;
    }

    @Override
    public void cast(Unit unit) {
        resource = unit.getResource(resourceType);
        resource.setAmount(resource.getAmount() - costAmount);
        System.out.println(resource.getAmount());
    }

    public Resource getResource() {
        return resource;
    }

}
