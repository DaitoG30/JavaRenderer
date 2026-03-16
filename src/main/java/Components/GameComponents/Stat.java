package Components.GameComponents;

import Components.Component;

public class Stat extends Component {

    public float currentValue;
    public float maxValue;

    public Stat(float maxValue) {
        this.maxValue = maxValue;
        this.currentValue = maxValue;
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
