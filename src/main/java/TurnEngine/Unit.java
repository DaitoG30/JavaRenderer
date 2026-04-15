package TurnEngine;

import Components.GameComponents.Affliction;
import Components.GameComponents.Boon;
import Components.GameComponents.Stat;
import Components.Skill;
import Entities.Entity;
import Entities.ModelEntity;
import Models.TexturedModel;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.List;

public class Unit extends Entity {


    private Vector2f coordinates = new Vector2f();
    public TurnManager turnManager;
    public BattleManager.Team team;

    ModelEntity modelEntity;

    Skill basicSkill;
    Skill skill1;
    Skill skill2;
    Skill ultimate;
    List<Unit> targets;
    public UnitController unitController;

    List<Affliction> afflictions;
    List<Boon> boons;




    public float getActionValue() {
        return actionValue;
    }

    private float actionValue;


    private Stat health = new Stat(100f);
    private Stat speed =  new Stat((float) (50 * Math.random()));


    /**
     * Initialising the unit class
     * @param coordinates These are the X and Y Values as a Vector-2f and determine the placement of the unit and which tile they will be placed on.
     * @param texturedModel The Model that the unit will be using to represent itself.
     */

    public Unit(Vector2f coordinates, TexturedModel texturedModel, boolean playerControlled) {

        setModelEntity(new ModelEntity(texturedModel, new Vector3f(coordinates.x ,1 ,coordinates.y), 0,0,0,0.3f));

    }

    public float calcActionValue(){
        actionValue = (1000f/speed.getMaxValue());
        return actionValue;
    }

    public void turnEnd(){
        unitController.setActive(false);
        turnManager.turnChange();
    }

    public void turnStart(){
        targets = turnManager.battleManager.getTargets(this);

        unitController.setActive(true);
    }

    public ModelEntity getModelEntity() {
        return modelEntity;
    }

    public void setModelEntity(ModelEntity modelEntity) {
        this.modelEntity = modelEntity;
    }

    public float consumeActionValue(float value){
        return actionValue -= value;
    }

    public Vector2f getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Vector2f coordinates) {
        this.coordinates = coordinates;
    }


    public Skill getBasicSkill() {
        return basicSkill;
    }

    public void setBasicSkill(Skill basicSkill) {
        this.basicSkill = basicSkill;
    }

    public Skill getSkill1() {
        return skill1;
    }

    public void setSkill1(Skill skill1) {
        this.skill1 = skill1;
    }

    public Skill getSkill2() {
        return skill2;
    }

    public void setSkill2(Skill skill2) {
        this.skill2 = skill2;
    }

    public Skill getUltimate() {
        return ultimate;
    }

    public void setUltimate(Skill ultimate) {
        this.ultimate = ultimate;
    }
}
