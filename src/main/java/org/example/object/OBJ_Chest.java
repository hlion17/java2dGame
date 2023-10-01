package org.example.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Chest extends SuperObject {
    public OBJ_Chest() {
        name = "chect";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/objects/Chest.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
