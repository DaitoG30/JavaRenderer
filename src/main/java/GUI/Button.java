package GUI;

import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class Button {


    public List<GuiTexture> buttonTextures = new ArrayList<>();
    private GuiTexture baseTexture;


    public Button(int buttonTexture, Vector2f position, Vector2f scale) {
        baseTexture =  new GuiTexture(buttonTexture,position,scale);
        buttonTextures.add(baseTexture);
    }


    public void render(GuiRenderer renderer) {
        renderer.render(buttonTextures);
    }


    public GuiTexture getBaseTexture() {
        return baseTexture;
    }

    public void setBaseTexture(GuiTexture baseTexture) {
        this.baseTexture = baseTexture;
    }


}
