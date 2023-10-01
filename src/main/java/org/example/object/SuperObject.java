package org.example.object;

import org.example.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp) {
        int playerScreenXOffsetStart = (gp.player.worldX - gp.player.screenX);
        int playerScreenXOffsetEnd = (gp.player.worldX + gp.player.screenX);
        int playerScreenYOffsetStart = (gp.player.worldY - gp.player.screenY);
        int playerScreenYOffsetEnd = (gp.player.worldY + gp.player.screenY);

        int screenX = worldX - playerScreenXOffsetStart;
        int screenY = worldY - playerScreenYOffsetStart;

        if (worldX + gp.TILE_SIZE > playerScreenXOffsetStart
                && worldX - gp.TILE_SIZE < playerScreenXOffsetEnd
                && worldY + gp.TILE_SIZE > playerScreenYOffsetStart
                && worldY - gp.TILE_SIZE < playerScreenYOffsetEnd) {
            g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
        }
    }
}
