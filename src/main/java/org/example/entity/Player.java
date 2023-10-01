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

    public final int screenX;
    public final int screenY;

    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.SCREEN_WIDTH / 2 - (gp.TILE_SIZE / 2);
        screenY = gp.SCREEN_HEIGHT / 2 - (gp.TILE_SIZE / 2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.TILE_SIZE * 23;
        worldY = gp.TILE_SIZE * 20;
        speed = 4;
        direction = Direction.UP;
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
        if (keyHandler.upPressed) direction = Direction.UP;
        else if (keyHandler.downPressed) direction = Direction.DOWN;
        else if (keyHandler.leftPressed) direction = Direction.LEFT;
        else if (keyHandler.rightPressed) direction = Direction.RIGHT;

        // CHECK TILE COLLISION
        collisionOn = false;
        gp.collisionChecker.checkTile(this);

        // CHECK OBJECT COLLISION
        int objectIndex = gp.collisionChecker.checkObject(this, true);
        pickUpObject(objectIndex);

        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if (!collisionOn) {
            if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed) {
                switch (direction) {
                    case UP: worldY -= speed; break;
                    case DOWN: worldY += speed; break;
                    case LEFT: worldX -= speed; break;
                    case RIGHT: worldX += speed; break;
                }
//                System.out.printf("Screen(%d, %d), World(%d, %d) %n", screenX, screenY, worldX, worldY);
            }
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

    public void pickUpObject(int objectIndex) {
        if (objectIndex == 999) return;
        String objectName = gp.obj[objectIndex].name;
        switch (objectName) {
            case "key":
                gp.obj[objectIndex] = null;
                hasKey++;
                System.out.println("Key: " + hasKey);
                break;
            case "chest":
                if (hasKey > 0) {
                    gp.obj[objectIndex] = null;
                    hasKey--;
                    System.out.println("Key: " + hasKey);
                }
                break;
            case "boosts":
                gp.obj[objectIndex] = null;
                speed+=5;
                break;
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

        g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
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
