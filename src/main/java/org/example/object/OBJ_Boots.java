package org.example.object;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Boots extends SuperObject {
    public OBJ_Boots(GamePanel gp) {
        name = "boosts";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/objects/boosts.png")));
            utilityTool.scaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
