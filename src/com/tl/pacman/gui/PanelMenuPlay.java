package com.tl.pacman.gui;

import javax.swing.*;
import java.awt.*;

import static com.tl.pacman.core.Constants.*;

public class PanelMenuPlay extends JPanel implements IMenu {
    private int score;
    private int life ;
    private Image imgLife ;
    public PanelMenuPlay() {
        imgLife = new ImageIcon(getClass().getResource("/imgs/left1.png")).getImage();
        setSize(WIDTH_FRAME, HEIGHT_MENU_PLAY);
        setLocation(0, 0);
        setBackground(Color.BLACK);
        setLayout(null);
    }


    @Override
    public void updateScore(int score) {
        //update dap
        this.score =score;
        repaint();
    }

    @Override
    public void updateLife(int life) {
        this.life = life;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);//repaint cua con (play)
        Graphics2D graphics2D  = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("Tahoma",Font.ITALIC,40));
        graphics2D.drawString("Score :",440,45);
        graphics2D.drawString(String.valueOf(score),600,45);

        graphics2D.setFont(new Font("Tahoma",Font.ITALIC,40));
        graphics2D.drawString("Life:",30,45);
        for (int i =1; i <= life; i++ ){
            graphics2D.drawImage(imgLife,100+50*i,15,30,30,null);
        }
        graphics2D.setColor(Color.BLUE);
        graphics2D.setStroke(new BasicStroke(6.0f));
        graphics2D.drawRect(4,3,WIDTH_FRAME - 13,HEIGHT_MENU_PLAY - 3);

    }
}
