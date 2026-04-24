package Components.GameComponents;


public class Stat {

    public float currentValue;
    public float maxValue;

    public Stat(float maxValue) {
        this.maxValue = maxValue;
        currentValue = this.maxValue;
    }

    public enum Type{
        ATTACK,
        SPEED,
        DEFENSE,
        STAMINA_REGEN,
        MANA_REGEN,

    }


    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public float getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(float currentValue) {
        this.currentValue = currentValue;
    }

}
