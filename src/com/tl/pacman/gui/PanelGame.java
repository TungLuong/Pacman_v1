package com.tl.pacman.gui;

import com.tl.pacman.core.Constants;

import javax.swing.*;

public class PanelGame extends JPanel {
    private PanelMenuPlay panelMenuPlay;
    private PanelPlay panelPlay;

    public PanelGame() {
        setLayout(null);
        setSize(Constants.WIDTH_FRAME, Constants.HEIGHT_FRAME);
        panelMenuPlay = new PanelMenuPlay();
        panelPlay = new PanelPlay(panelMenuPlay);
        add(panelMenuPlay);
        add(panelPlay);

    }

    public PanelPlay getPanelPlay() {
        return panelPlay;
    }
}
