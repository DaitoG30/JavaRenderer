package Engine;

import Entities.ModelEntity;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {

    List<ModelEntity> children = new ArrayList<ModelEntity>();

    public ModelEntity getChild(int index){
        return children.get(index);
    }

    public List<ModelEntity> getChildren(){
        return children;
    }

    public void deleteChild(ModelEntity modelEntity){
        children.remove(modelEntity);
    }

    public void addChild(ModelEntity modelEntity){
        System.out.println(children.size());
        modelEntity.setId(children.size());
        children.add(modelEntity);

    }

    public void removeChild(ModelEntity modelEntity){
        children.remove(modelEntity);
    }

    public SceneManager(){

    }



}
