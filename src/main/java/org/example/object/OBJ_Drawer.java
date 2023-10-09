package org.example.object;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Drawer extends SuperObject {
    public OBJ_Drawer(GamePanel gp) {
        name = "drawer";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/objects/drawer.png")));
            utilityTool.scaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;
    }
}
