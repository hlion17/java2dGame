package org.example;

import java.awt.*;

public class UI {
    GamePanel gp;
    Font FONT_ARIAL_40_PLAIN, FONT_ARIAL_80_BOLD;
    public String message = "";
    int messageCounter = 0;
    public boolean MESSAGE_ON = false;
    public boolean GAME_FINISHED = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        FONT_ARIAL_40_PLAIN = new Font("Arial", Font.PLAIN, 40);
        FONT_ARIAL_80_BOLD = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text) {
        message = text;
        MESSAGE_ON = true;
    }

    public void draw(Graphics2D g2) {
    }
}
