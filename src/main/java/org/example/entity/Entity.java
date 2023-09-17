package org.example.entity;

import java.awt.image.BufferedImage;

/**
 * this is Super class for Player, NPC, Monster
 * author: hlion0626
 */
public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public Direction direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
