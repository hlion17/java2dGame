package org.example.tile;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tiles = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/tiles/rock-1.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/tiles/grass-1.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/tiles/sky-1.png")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(tiles[0].image, 0, 0, gp.TILE_SIZE, gp.TILE_SIZE, null);
        g2.drawImage(tiles[1].image, 48, 0, gp.TILE_SIZE, gp.TILE_SIZE, null);
        g2.drawImage(tiles[2].image, 96, 0, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
}
