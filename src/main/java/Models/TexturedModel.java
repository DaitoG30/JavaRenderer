package Models;

import Textures.ModelTexture;

public class TexturedModel {

    private ModelTexture texture;
    private RawModel rawModel;

    public TexturedModel(ModelTexture texture, RawModel rawModel) {
        this.texture = texture;
        this.rawModel = rawModel;
    }

    public ModelTexture getTexture() {
        return texture;
    }

    public RawModel getRawModel() {
        return rawModel;
    }
}
