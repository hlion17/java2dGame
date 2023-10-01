package org.example.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_key extends SuperObject {
    public OBJ_key() {
        name = "key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/objects/key.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
