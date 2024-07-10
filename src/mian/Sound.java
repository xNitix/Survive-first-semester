package mian;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    private Clip clip;
    private URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/music/default.wav");
        soundURL[1] = getClass().getResource("/sounds/itemPickUp.wav");
        soundURL[2] = getClass().getResource("/sounds/keyPickUp.wav");
    }

    public void setFile(int index) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        this.clip.start();
    }

    public void loop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        this.clip.stop();
    }

}
