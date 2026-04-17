package Components;

public class Resource {

    private String name;
    private int amount;
    private int maxAmount;
    private Type type;


    public enum Type{
        STANDARD,
        HEALTH,
        MANA,
        STAMINA,
        STACK
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
