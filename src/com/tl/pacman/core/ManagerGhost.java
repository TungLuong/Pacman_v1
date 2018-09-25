package com.tl.pacman.core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.tl.pacman.core.Constants.*;

public class ManagerGhost {

    private  Image[] imgGhostCyan = {new ImageIcon(ManagerGhost.class.getResource("/imgs/ghostcyan1.png")).getImage(),
                                            new ImageIcon(ManagerGhost.class.getResource("/imgs/ghostcyan2.png")).getImage()};
    private  Image[] imgGhostOrange = {new ImageIcon(ManagerGhost.class.getResource("/imgs/ghostorange1.png")).getImage(),
                                            new ImageIcon(ManagerGhost.class.getResource("/imgs/ghostorange2.png")).getImage()};

    private  Image[] imgGhostPink = {new ImageIcon(ManagerGhost.class.getResource("/imgs/ghostpink1.png")).getImage(),
                                            new ImageIcon(ManagerGhost.class.getResource("/imgs/ghostpink2.png")).getImage()};

    private  Image[] imgGhostRed = {new ImageIcon(ManagerGhost.class.getResource("/imgs/ghostred1.png")).getImage(),
                                            new ImageIcon(ManagerGhost.class.getResource("/imgs/ghostred2.png")).getImage()};

    private  Image[] imgGhostHollow = {new ImageIcon(ManagerGhost.class.getResource("/imgs/ghosthollow1.png")).getImage(),
                                            new ImageIcon(ManagerGhost.class.getResource("/imgs/ghosthollow2.png")).getImage(),
                                            new ImageIcon(ManagerGhost.class.getResource("/imgs/ghosthollow3.png")).getImage()};
    private  Image[] imgGhostHollowBegin = {imgGhostHollow[1],imgGhostHollow[2]};
    private  Image[] imgGhostHollowAfter = {imgGhostHollow[0],imgGhostHollow[2]};

    // dùng để xác định thời gian các con ma bị hollow
    private int timeManagerGhost;

    private List<Ghost> ghosts =  new ArrayList<>();

    public ManagerGhost(ManagerItem managerItem){

        ghosts.add( new Ghost(LOCATION_DEFAUL_GHOST_CYAN_X, LOCATION_DEFAUL_GHOST_CYAN_Y ,imgGhostCyan,managerItem));
        ghosts.add(new Ghost(LOCATION_DEFAUL_GHOST_ORANGE_X, LOCATION_DEFAUL_GHOST_ORANGE_Y,imgGhostOrange,managerItem));
        ghosts.add(new Ghost(LOCATION_DEFAUL_GHOST_PINK_X, LOCATION_DEFAUL_GHOST_PINK_Y,imgGhostPink,managerItem));
        ghosts.add(new Ghost(LOCATION_DEFAUL_GHOST_RED_X, LOCATION_DEFAUL_GHOST_RED_Y,imgGhostRed,managerItem));

        ghosts.get(GHOST_CYAN).setFriend(new Ghost[]{ghosts.get(GHOST_ORANGE), ghosts.get(GHOST_PINK), ghosts.get(GHOST_RED)});
        ghosts.get(GHOST_ORANGE).setFriend(new Ghost[]{ghosts.get(GHOST_RED), ghosts.get(GHOST_PINK), ghosts.get(GHOST_CYAN)});
        ghosts.get(GHOST_PINK).setFriend(new Ghost[]{ghosts.get(GHOST_ORANGE), ghosts.get(GHOST_RED), ghosts.get(GHOST_CYAN)});
        ghosts.get(GHOST_RED).setFriend(new Ghost[]{ghosts.get(GHOST_ORANGE), ghosts.get(GHOST_PINK), ghosts.get(GHOST_CYAN)});

        ghosts.get(GHOST_CYAN).setType(GHOST_CYAN);
        ghosts.get(GHOST_ORANGE).setType(GHOST_ORANGE);
        ghosts.get(GHOST_PINK).setType(GHOST_PINK);
        ghosts.get(GHOST_RED).setType(GHOST_RED);
    }

    /**
     * tất cả ghost rơi vào trạng thái hollow
     */
    void hollow(){
        for (Ghost ghost : ghosts) {
            ghost.setHollow(true);
            ghost.setImgGhost(imgGhostHollowBegin);
            timeManagerGhost = 0;
            ghost.setSpeedDelay(SPEED_DELAY_MAX);
        }
    }

    private void changeAllImgsHollow(){
        boolean bl = false;
        int size = ghosts.size();
        for (Ghost ghost : ghosts) {
            if (ghost.isHollow()) {
                bl = true;
            }
        }
        if (bl){
            if (timeManagerGhost == TIME_CHANGE_HOLLOW){
                for (Ghost ghost : ghosts) {
                    if (ghost.isHollow()) {
                        ghost.setImgGhost(imgGhostHollowAfter);
                    }
                }
            }
            else if (timeManagerGhost == TIME_CHANGE_HOLLOW * 1.5){
                for (int i=0;i<size;i++){
                    ghosts.get(i).setHollow(false);
                    timeManagerGhost =0;
                    switch (i) {
                        case GHOST_CYAN:
                            ghosts.get(i).setImgGhost(imgGhostCyan);
                            break;
                        case GHOST_ORANGE:
                            ghosts.get(i).setImgGhost(imgGhostOrange);
                            break;
                        case GHOST_PINK:
                            ghosts.get(i).setImgGhost(imgGhostPink);
                            break;
                        case GHOST_RED:
                            ghosts.get(i).setImgGhost(imgGhostRed);
                            break;
                    }
                    ghosts.get(i).setSpeedDelay(SPEED_DELAY_NORMAL);
                }
            }
        }
    }

    /**
     * di chuyển
     * @param xPac
     * @param yPac
     */
    public void moveAll(int xPac, int yPac){
        timeManagerGhost++;
        changeAllImgsHollow();
        for (Ghost ghost : ghosts) {
            if (ghost.getX() % (SIZE_BLOCK) == 0 && ghost.getY() % (SIZE_BLOCK) == 0) {
                ghost.changDir(xPac, yPac);
                //System.out.println("ManagerGhost/moveAll/direction:"+ghosts.get(i).direction);
            }
            ghost.move();
        }
    }
    public void drawAllGhost(Graphics2D g2d){
        //System.out.println(time);
        for (Ghost ghost : ghosts) {
            ghost.draw(g2d);
        }
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    /**
     * tạo lại con ma mặc định
     * @param indexGhost
     */
    public void setDefaulGhost(int indexGhost){

        ghosts.get(indexGhost).setHollow(false);
        ghosts.get(indexGhost).setSpeedDelay(SPEED_DELAY_NORMAL);
        ghosts.get(indexGhost).setTimeGhost();
        switch (indexGhost){
            case GHOST_CYAN:
                ghosts.get(indexGhost).setX(LOCATION_DEFAUL_GHOST_CYAN_X);
                ghosts.get(indexGhost).setY(LOCATION_DEFAUL_GHOST_CYAN_Y);
                ghosts.get(indexGhost).setImgGhost(imgGhostCyan);
                break;
            case GHOST_ORANGE:
                ghosts.get(indexGhost).setX(LOCATION_DEFAUL_GHOST_ORANGE_X);
                ghosts.get(indexGhost).setY(LOCATION_DEFAUL_GHOST_ORANGE_Y);
                ghosts.get(indexGhost).setImgGhost(imgGhostOrange);
                break;
            case GHOST_PINK:
                ghosts.get(indexGhost).setX(LOCATION_DEFAUL_GHOST_PINK_X);
                ghosts.get(indexGhost).setY(LOCATION_DEFAUL_GHOST_PINK_Y);
                ghosts.get(indexGhost).setImgGhost(imgGhostPink);
                break;
            case GHOST_RED:
                ghosts.get(indexGhost).setX(LOCATION_DEFAUL_GHOST_RED_X);
                ghosts.get(indexGhost).setY(LOCATION_DEFAUL_GHOST_RED_Y);
                ghosts.get(indexGhost).setImgGhost(imgGhostRed);
                break;
        }
    }

}
