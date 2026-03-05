package Tools;

import Engine.SceneManager;
import Entities.ModelEntity;
import RenderEngine.DisplayManger;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

import java.util.List;

public class DebugUI {
    private static final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private static final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();
    private final DisplayManger window;

    float angle = 0.0f;

    public DebugUI(DisplayManger display) {
        window = display;
    }

    public void imGuiInit() {
        ImGui.createContext();
        ImGuiIO io = ImGui.getIO();
        io.setIniFilename(null); // disable .ini file

        ImGui.styleColorsDark();

        imGuiGlfw.init(window.getWindow(), true);
        imGuiGl3.init("#version 400 core");
    }


    public void imGuiRenderDebug(SceneManager sceneManager) {
        imGuiGlfw.newFrame();
        ImGui.newFrame();
        ImGui.begin("Debug");
        List<ModelEntity> child = sceneManager.getChildren();
        for(ModelEntity modelEntity : child){
            ImGui.text("ModelEntity-"+ modelEntity.getId());
        }
        ImGui.end();
        ImGui.render();
        imGuiGl3.renderDrawData(ImGui.getDrawData());
    }

    public void cleanUp(){
        imGuiGl3.dispose();
        imGuiGlfw.dispose();
        ImGui.destroyContext();
    }


}
