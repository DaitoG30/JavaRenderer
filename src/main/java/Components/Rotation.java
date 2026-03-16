package Components;

import org.joml.Vector3f;

public class Rotation extends Component {

    public Vector3f rotation;

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
}
