package Tools;

import RenderEngine.DisplayManger;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

public class DebugUI {
    private static ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private static ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();
    private DisplayManger window;

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


    public void imGuiRenderDebug() {
        imGuiGlfw.newFrame();
        ImGui.newFrame();
        ImGui.begin("Debug");
        ImGui.text("FPS: " );
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
