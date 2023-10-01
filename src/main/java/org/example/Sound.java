package org.example;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundUrl = new URL[30];

    public Sound() {
        soundUrl[0] = getClass().getResource("/assets/sound/background.wav");
        soundUrl[1] = getClass().getResource("/assets/sound/coin.wav");
        soundUrl[2] = getClass().getResource("/assets/sound/powerup.wav");
        soundUrl[3] = getClass().getResource("/assets/sound/unlock.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
