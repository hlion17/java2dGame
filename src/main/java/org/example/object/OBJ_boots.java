package org.example.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_boots extends SuperObject {
    public OBJ_boots() {
        name = "boosts";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/objects/boosts.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
