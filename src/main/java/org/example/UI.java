package org.example;

import org.example.object.OBJ_key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font FONT_ARIAL_40_PLAIN, FONT_ARIAL_80_BOLD;
    BufferedImage keyImage;
    public String message = "";
    int messageCounter = 0;
    public boolean MESSAGE_ON = false;
    public boolean GAME_FINISHED = false;
    public double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        FONT_ARIAL_40_PLAIN = new Font("Arial", Font.PLAIN, 40);
        FONT_ARIAL_80_BOLD = new Font("Arial", Font.BOLD, 80);
        keyImage = new OBJ_key(gp).image;
    }

    public void showMessage(String text) {
        message = text;
        MESSAGE_ON = true;
    }

    public void draw(Graphics2D g2) {

        if (GAME_FINISHED) {
            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            g2.setFont(FONT_ARIAL_40_PLAIN);
            g2.setColor(Color.white);
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.SCREEN_WIDTH / 2 - textLength / 2;
            y = gp.SCREEN_HEIGHT / 2 - (gp.TILE_SIZE * 3);
            g2.drawString(text, x, y);

            text = "Your Time is :" + decimalFormat.format(playTime) + "!";
            g2.setFont(FONT_ARIAL_80_BOLD);
            g2.setColor(Color.white);
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.SCREEN_WIDTH / 2 - textLength / 2;
            y = gp.SCREEN_HEIGHT / 2 + (gp.TILE_SIZE * 4);
            g2.drawString(text, x, y);

            text = "Congratulations";
            g2.setFont(FONT_ARIAL_80_BOLD);
            g2.setColor(Color.orange);
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.SCREEN_WIDTH / 2 - textLength / 2;
            y = gp.SCREEN_HEIGHT / 2 + (gp.TILE_SIZE * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        } else {
            g2.setFont(FONT_ARIAL_40_PLAIN);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.TILE_SIZE / 2, gp.TILE_SIZE / 2, gp.TILE_SIZE, gp.TILE_SIZE, null);
            g2.drawString("x " + gp.player.hasKey, 74, 50);

            // TIME
            playTime +=(double) 1 / 60;
            g2.drawString("Time: " + decimalFormat.format(playTime), gp.TILE_SIZE * 11, 65);

            // MESSAGE
            if (MESSAGE_ON) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.TILE_SIZE / 2, gp.TILE_SIZE * 5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    MESSAGE_ON = false;
                }
            }
        }
    }
}
