package TurnEngine;

import Components.GameComponents.Affliction;
import Components.GameComponents.Boon;
import Components.GameComponents.Stat;
import Components.Resource;
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
    boolean isAlive = true;

    ModelEntity modelEntity;

    //Skills
    Skill basicSkill;
    Skill skill1;
    Skill skill2;
    Skill ultimate;
    //Resources
    Resource Health;
    Resource Mana;
    Resource Stamina;
    Resource Stackable;
    //Stats
    private Stat Defense;
    private Stat speed =  new Stat((float) (50 * Math.random()));

    List<Unit> targets;
    public UnitController unitController;

    List<Affliction> afflictions;
    List<Boon> boons;




    public float getActionValue() {
        return actionValue;
    }

    private float actionValue;

    /**
     * Initialising the unit class
     * @param coordinates These are the X and Y Values as a Vector-2f and determine the placement of the unit and which tile they will be placed on.
     * @param texturedModel The Model that the unit will be using to represent itself.
     */

    public Unit(Vector2f coordinates, TexturedModel texturedModel) {

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

    // Used by the skill to call for the appropriate initialTargets, allies or enemies.
    public void requestTargets(boolean teamTargeted){
        BattleManager btManager = turnManager.battleManager;

        if (!teamTargeted){
            btManager.getEnemyTargets(this);
        }
        else {
            btManager.getAlliedTargets(this);
        }

    }

    public void turnStart(){
        targets = turnManager.battleManager.getEnemyTargets(this);

        unitController.setActive(true);
    }

    public void resourceCheck() {
        //check if dead
        if (Health.getAmount() <= 0) {
            isAlive = false;
            System.out.println("dead");
        }

        if (Mana.getAmount() <= 0) {
            //Stat reduction
            System.out.println("mana exhausted");
        }
        if (Stamina.getAmount() <= 0) {
            //Stat reduction
            System.out.println("Stamina exhausted");
        }

    }

    public Resource getResource(Resource.Type type) {
        return switch (type) {
            case HEALTH -> Health;
            case MANA -> Mana;
            case STAMINA -> Stamina;
            case STACK ->  Stackable;
            default -> null;
        };
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


    //Resource getters and setters

    public Resource getHealth() {
        return Health;
    }

    public void setHealth(Resource health) {
        Health = health;
    }

    public Resource getMana() {
        return Mana;
    }

    public void setMana(Resource mana) {
        Mana = mana;
    }

    public Resource getStamina() {
        return Stamina;
    }

    public void setStamina(Resource stamina) {
        Stamina = stamina;
    }

    public Resource getStackable() {
        return Stackable;
    }

    public void setStackable(Resource stackable) {
        Stackable = stackable;
    }

    //Skill getter and setters
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
