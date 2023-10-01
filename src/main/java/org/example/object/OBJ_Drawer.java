package org.example.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Drawer extends SuperObject {
    public OBJ_Drawer() {
        name = "drawer";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/objects/drawer.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;
    }
}
