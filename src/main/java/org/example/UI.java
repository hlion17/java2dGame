package org.example;

import java.awt.*;

public class UI {
    GamePanel gp;
    Graphics2D g2;
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

        this.g2 = g2;

        g2.setFont(FONT_ARIAL_40_PLAIN);
        g2.setColor(Color.white);

        if (gp.gameState == GameState.PLAY) {
            // Do playState stuff later
        }else if (gp.gameState == GameState.PAUSE) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.SCREEN_HEIGHT / 2;

        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.SCREEN_WIDTH / 2 - length / 2;
    }
}
