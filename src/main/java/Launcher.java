import Components.GameComponents.Stat;
import Components.Resource;
import Components.Skills.FeastOfAbsolutionTest;
import Components.Skills.LeachBiteTest;
import Components.Skills.VerdictTest;
import Entities.Light;
import GUI.Button;
import GUI.GuiRenderer;
import GUI.GuiTexture;
import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.*;
import Textures.ModelTexture;
import Tools.Maths;
import TurnEngine.*;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;


public class Launcher {


    public static void main(String[] args){
        //Create Window along with GL capabilities
        DisplayManger window = new DisplayManger("TEST",1280,720,false);
        window.createDisplay();
        InputManager Input = new InputManager(window);


        Loader loader = new Loader();

        Camera camera = new Camera(window);
        Grid grid = new Grid(10,10, new TurnManager(40));

        Light light = new Light(new Vector3f(1,1,1),new Vector3f(1,5140,1));


        List<GuiTexture> guis = new ArrayList<>();
        GuiTexture guiTexture = new GuiTexture(loader.loadTexture("grey"),new Vector2f(0.2f),new Vector2f(0.2f) );
        guis.add(guiTexture);
        guiTexture.setVisible(true);

        GuiRenderer guiRenderer = new GuiRenderer(loader);

        Maths.setLastTime(GLFW.glfwGetTime());

        grid.initializeTiles();
        for(Tile tile: grid.tiles){
            System.out.println(tile.coordinates);
        }

        RawModel model = OBJLoader.loadOBJModel("Cylinder",loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("Grey"));
        TexturedModel cylinder = new TexturedModel(texture,model);
        grid.placeUnit(new Vector2f(2,9),cylinder);
        grid.placeUnit(new Vector2f(5,5),cylinder);
        grid.placeUnit(new Vector2f(5,6),cylinder);
        grid.placeUnit(new Vector2f(4,6),cylinder);

        for (Unit unit: grid.units){
            unit.unitController = new unitPlayerController(Input,unit);
            unit.setManaRegenRate(new Stat(0.2f));
            unit.setStaminaRegenRate(new Stat(0.15f));
            Resource Mana = new Resource(100);
            Mana.setType(Resource.Type.MANA);
            Mana.setName("Mana");
            unit.setMana(Mana);
            Resource Health = new Resource(100);
            Health.setType(Resource.Type.HEALTH);
            Health.setName("Health");
            unit.setHealth(Health);
            Resource Stamina = new Resource(100);
            Stamina.setType(Resource.Type.STAMINA);
            Stamina.setName("Stamina");
            Stamina.setAmount(50);
            unit.setStamina(Stamina);
            unit.setStackable(new Resource(5));
            unit.setBasicSkill(new FeastOfAbsolutionTest());
            unit.setUltimate(new VerdictTest());
            unit.setSkill1(new LeachBiteTest());
        }
        grid.units.get(0).team = BattleManager.Team.TEAM_A;
        grid.units.get(1).team = BattleManager.Team.TEAM_B;
        grid.units.get(2).team = BattleManager.Team.TEAM_B;
        grid.units.get(3).team = BattleManager.Team.TEAM_B;

        grid.turnManager.battleStart();

        MasterRenderer masterRenderer = new MasterRenderer(window);

        Button button = new Button(loader.loadTexture("grey"),new Vector2f().zero(),new Vector2f(0.5f));


        while (!window.windowShouldClose()){
            Maths.setCurrentTime(GLFW.glfwGetTime());
            Maths.calcDeltaTime();
            Maths.setLastTime(GLFW.glfwGetTime());

            for (Unit unit: grid.units){
                unit.unitController.update();
            }

            guiTexture.setVisible(grid.turnManager.currentUnit.unitController.isActive());

            camera.move();
            button.render(guiRenderer);
            grid.render(masterRenderer);
            masterRenderer.render(light,camera);
            guiRenderer.render(guis);
            window.updateDisplay();

            if (Input.is_action_just_pressed(GLFW.GLFW_KEY_SPACE)){
                grid.turnManager.turnChange();
            }


        }

        grid.cleanUp();
        guiRenderer.cleanUp();
        masterRenderer.cleanUp();
        loader.cleanUp();
        window.closeDisplay();



    }


}
