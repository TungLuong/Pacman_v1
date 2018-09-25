package com.tl.pacman.core;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class AudioManager {
    private String url;
    private Clip clip;
    private AudioInputStream in;

    public AudioManager(String url) {
        this.url = url;
    }

    public void unit() {
        try {
            clip = AudioSystem.getClip();
            in = AudioSystem.getAudioInputStream(getClass().getResource(url));
            clip.open(in);

        } catch (IllegalStateException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loop() {
        if (clip != null)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            if (in == null) {
                return;
            }
            try {
                in.close();
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        if (clip != null)
            clip.start();
    }
}
