package org.example.tile;

import org.example.GamePanel;
import org.example.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tiles;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tiles = new Tile[10];
        this.mapTileNum = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];
        getTileImage();
        loadMap("/assets/maps/world01.txt");
    }

    public void getTileImage() {
        setUp(0, "ground-1", false);
        setUp(1, "tumble-2", true);
        setUp(2, "water-1", true);
        setUp(3, "grass-1", false);
        setUp(4, "ground-2", false);
    }

    public void setUp(int index, String imageName, boolean collision) {
        UtilityTool utilityTool = new UtilityTool();
        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/tiles/" + imageName + ".png")));
            tiles[index].image = utilityTool.scaleImage(tiles[index].image, gp.TILE_SIZE, gp.TILE_SIZE);
            tiles[index].collision = collision;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream map01 = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(map01)));

            int col = 0;
            int row = 0;

            while (col < gp.MAX_WORLD_COL && row < gp.MAX_WORLD_ROW) {
                String line = br.readLine();
                String[] split = line.split(" ");

                while (col < gp.MAX_WORLD_COL) {
                    mapTileNum[col][row] = Integer.parseInt(split[col]);
                    col++;
                }

                if (col == gp.MAX_WORLD_COL) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.MAX_WORLD_COL && worldRow < gp.MAX_WORLD_ROW) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int playerScreenXOffsetStart = (gp.player.worldX - gp.player.screenX);
            int playerScreenXOffsetEnd = (gp.player.worldX + gp.player.screenX);
            int playerScreenYOffsetStart = (gp.player.worldY - gp.player.screenY);
            int playerScreenYOffsetEnd = (gp.player.worldY + gp.player.screenY);

            int worldX = worldCol * gp.TILE_SIZE;
            int worldY = worldRow * gp.TILE_SIZE;
            int screenX = worldX - playerScreenXOffsetStart;
            int screenY = worldY - playerScreenYOffsetStart;

            if (worldX + gp.TILE_SIZE > playerScreenXOffsetStart
                    && worldX - gp.TILE_SIZE < playerScreenXOffsetEnd
                    && worldY + gp.TILE_SIZE > playerScreenYOffsetStart
                    && worldY - gp.TILE_SIZE < playerScreenYOffsetEnd) {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
