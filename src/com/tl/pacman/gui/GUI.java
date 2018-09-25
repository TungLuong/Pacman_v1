package com.tl.pacman.gui;

import com.tl.pacman.core.Constants;
import com.tl.pacman.core.IStartGame;

import javax.swing.*;

public class GUI extends JFrame {
    private PanelMenu panelMenu;
    public GUI(){
        setTitle("Pacman");
        setSize(Constants.WIDTH_FRAME,Constants.HEIGHT_FRAME);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        IStartGame IStartGame = new IStartGame(){
            @Override
            public void playGame() {
                panelMenu.setFocusable(false);
                panelMenu.removeKeyListener(panelMenu);
                remove(panelMenu);
                PanelGame panelGame = new PanelGame();
                add(panelGame);

                panelGame.getPanelPlay().setFocusable(true);
                panelGame.getPanelPlay().requestFocus(true);
                panelGame.getPanelPlay().requestFocus();

                repaint();
            }

            @Override
            public void exitGame() {
                System.exit(EXIT_ON_CLOSE);
            }
        };

        panelMenu = new PanelMenu(IStartGame);
        add(panelMenu);



    }
}
