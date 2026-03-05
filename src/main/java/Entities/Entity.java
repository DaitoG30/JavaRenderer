package Entities;


import Components.Component;
import Components.Position;

import java.util.List;

public class Entity {

    private List<Component> components;

    public Component getComponent(int id){
        return components.get(id);
    }

    public void addComponent(Component component) {
        component.setId(components.size());
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }


}
