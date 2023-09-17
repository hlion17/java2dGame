package org.example.tile;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tiles = new Tile[10];
        this.mapTileNum = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW];
        getTileImage();
        loadMap("/assets/maps/map01.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/tiles/ground-1.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/tiles/tumble-1.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/tiles/water-1.png")));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/tiles/grass-1.png")));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/tiles/ground-2.png")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream map01 = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(map01)));

            int col = 0;
            int row = 0;

            while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
                String line = br.readLine();
                String[] split = line.split(" ");

                while (col < gp.MAX_SCREEN_COL) {
                    mapTileNum[col][row] = Integer.parseInt(split[col]);
                    col++;
                }

                if (col == gp.MAX_SCREEN_COL) {
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
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tiles[tileNum].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
            x += gp.TILE_SIZE;
            col++;

            if (col == gp.MAX_SCREEN_COL) {
                col = 0;
                x = 0;
                row++;
                y += gp.TILE_SIZE;
            }
        }
    }
}
