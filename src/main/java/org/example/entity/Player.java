package org.example.entity;

import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Player Class
 * Author: hlion0626
 */
public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = Direction.DOWN;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/up.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/down.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/right.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/right2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        if (keyHandler.upPressed) {
            direction = Direction.UP;
            y -= speed;
        } else if (keyHandler.downPressed) {
            direction = Direction.DOWN;
            y += speed;
        } else if (keyHandler.leftPressed) {
            direction = Direction.LEFT;
            x -= speed;
        } else if (keyHandler.rightPressed) {
            direction = Direction.RIGHT;
            x += speed;
        }

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics g2) {
        BufferedImage image = null;

        if (direction == Direction.UP) {
            image = getImageBySpriteNum(up1, up2);
        } else if (direction == Direction.DOWN) {
            image = getImageBySpriteNum(down1, down2);
        } else if (direction == Direction.RIGHT) {
            image = getImageBySpriteNum(right1, right2);
        } else if (direction == Direction.LEFT) {
            image = getImageBySpriteNum(left1, left2);
        }

        g2.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }

    private BufferedImage getImageBySpriteNum(BufferedImage image1, BufferedImage image2) {
        if (spriteNum == 1) {
            return image1;
        } else if (spriteNum == 2) {
            return image2;
        } else {
            return null;
        }
    }
}