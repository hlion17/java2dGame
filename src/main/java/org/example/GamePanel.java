package org.example;

import org.example.entity.Player;
import org.example.object.SuperObject;
import org.example.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int ORIGINAL_TITLE_SIZE = 16;  // 16x16 tile
    final int SCALE = 3;
    public final int TILE_SIZE = ORIGINAL_TITLE_SIZE * SCALE;  // 48x48 tile
    public final int MAX_SCREEN_COL = 16;
    public final int MAX_SCREEN_ROW = 12;
    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;  // 768 pixels
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;  // 576 pixels

    // WORLD SETTINGS
    public final int MAX_WORLD_COL = 50;
    public final int MAX_WORLD_ROW = 50;

    // FPS
    final int FPS = 60;

    // SYSTEM
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler(this);
    TileManager tileManager = new TileManager(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    // ENTITY and OBJECT
    public Player player = new Player(this, keyHandler);
    public SuperObject[] obj = new SuperObject[10];

    // GAME STATE
    public GameState gameState;

    public void setupGame() {
        assetSetter.setObject();
//        playMusic(0);
        gameState = GameState.PLAY;
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // DEBUG
        long drawStart = 0;
        if (keyHandler.checkDrawTime) drawStart = System.nanoTime();

        // TILE
        tileManager.draw(g2);

        // OBJECT
        for (SuperObject o : obj) {
            if (o != null) o.draw(g2, this);
        }

        // PLAYER
        player.draw(g2);

        // UI
        ui.draw(g2);

        // DEBUG
        if (keyHandler.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }

    public void update() {
        if (gameState == GameState.PLAY) {
            player.update();
        } else if (gameState == GameState.PAUSE) {
            // nothing
        }
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
