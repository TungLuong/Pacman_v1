package com.tl.pacman.gui;

import com.tl.pacman.core.AudioManager;
import com.tl.pacman.core.IStartGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.tl.pacman.core.Constants.*;

public class PanelMenu extends JPanel implements KeyListener {
    private IStartGame IStartGame;
    private Image bg;
    private AudioManager audioManager;
    public PanelMenu(IStartGame IStartGame){
        audioManager = new AudioManager("/audios/pacman_intermission.wav");
        bg = new ImageIcon(getClass().getResource("/imgs/images_menu.png")).getImage();
        this.IStartGame = IStartGame;
        setSize(WIDTH_FRAME,HEIGHT_FRAME);
        setLocation(0,0);
        setLayout(null);
        setFocusable(true);
        requestFocus();
        unitEvent();
        audioManager.unit();
        audioManager.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics2D  = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(bg,0,0,WIDTH_FRAME,HEIGHT_FRAME,null);

    }

    private void unitEvent(){
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // an phim xuong
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_ENTER:
                IStartGame.playGame();
                audioManager.stop();
                repaint();
                break;
            case KeyEvent.VK_ESCAPE:
                IStartGame.exitGame();
                audioManager.stop();
                repaint();
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
