package com.tl.pacman.gui;

import com.tl.pacman.core.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.tl.pacman.core.Constants.*;

public class PanelPlay extends JPanel implements Runnable{
    private ManagerItem managerItem;
    private Pacman pacman;
    private ManagerGhost managerGhost;
    private boolean stop;
    private IMenu menu;
    private Image imgLost = new ImageIcon(getClass().getResource("/imgs/game_over.png")).getImage();
    private Image imgWin = new ImageIcon(getClass().getResource("/imgs/win.png")).getImage();
    private boolean isPacManDie = false;
    private boolean isPacManEatAllDot = false;
    private AudioManager audioPause ;

    public PanelPlay(IMenu menu){

        this.menu = menu;
        setSize(WIDTH_FRAME,HEIGHT_PLAY);
        setLocation(0,HEIGHT_MENU_PLAY);
        setBackground(Color.BLACK);
        audioPause = new AudioManager("/audios/pacman_pause.wav");
        managerItem = new ManagerItem("/maze/maze1.txt");
        managerGhost = new ManagerGhost(managerItem);
        pacman = new Pacman(LOCATION_DEFAUL_PAC_X,LOCATION_DEFAUL_PAC_Y,managerItem,managerGhost);
        unitEvent();
        Thread th = new Thread(this);
        th.start();

    }

    private void unitEvent(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // an phim xuong
                int code = e.getKeyCode();
                switch (code) {
                    case KeyEvent.VK_LEFT:
                        if(!stop)
                        pacman.setNewDirection(MOVE_LEFT);
                        repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(!stop)
                        pacman.setNewDirection(MOVE_RIGHT);
                        repaint();
                        break;
                    case KeyEvent.VK_UP:
                        if(!stop)
                        pacman.setNewDirection(MOVE_UP);
                        repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        if(!stop)
                        pacman.setNewDirection(MOVE_DOWN);
                        repaint();
                        break;
                    case KeyEvent.VK_SPACE:
                        audioPause.unit();
                        audioPause.start();
                        stop = !stop;
                        repaint();
                        break;

                    case KeyEvent.VK_ESCAPE:
                        System.exit(JFrame.EXIT_ON_CLOSE);
                        break;
                    case KeyEvent.VK_ENTER:
//                        if (isPacManDie || isPacManEatAllDot){
//                            managerGhost.setDefaulGhost(GHOST_CYAN);
//                            managerGhost.setDefaulGhost(GHOST_ORANGE);
//                            managerGhost.setDefaulGhost(GHOST_PINK);
//                            managerGhost.setDefaulGhost(GHOST_RED);
//                            managerItem.setDefaul();
//                            pacman.setDefaulPacMan();
//                            pacman.setDefaulScoreAndLife();
//                            repaint();
//                        }
//                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        System.out.println(pacman.getLife());
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        pacman.draw(g2d);
        managerItem.drawAllItem(g2d);
        managerGhost.drawAllGhost(g2d);
        pacman.drawScoreAdd(g2d);
        if(isPacManDie){
             g2d.drawImage(imgLost,(COLUNM*SIZE_BLOCK - WIDTH_IMG_LOST)/2,(HEIGHT_PLAY - HEIGHT_IMG_LOST-30)/2,WIDTH_IMG_LOST,HEIGHT_IMG_LOST,null);
        }
        else if(isPacManEatAllDot){
            g2d.drawImage(imgWin,(COLUNM*SIZE_BLOCK - WIDTH_IMG_WIN)/2,(HEIGHT_PLAY - HEIGHT_IMG_WIN -30)/2,WIDTH_IMG_WIN,HEIGHT_IMG_WIN,null);

        }

    }

    @Override
    public void run() {
        while (true) {
            System.out.println("LIFE"+pacman.getLife());
            isPacManDie = pacman.dead();
            isPacManEatAllDot = pacman.eatAllDot();
            if (!isPacManDie && !isPacManEatAllDot) {
                if (!stop) {
                    pacman.move();
                    pacman.eatDot();
                    managerGhost.moveAll(pacman.getX(), pacman.getY());
                    menu.updateScore(pacman.getScore());
                    menu.updateLife(pacman.getLife());
                }
            }
            repaint();
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
