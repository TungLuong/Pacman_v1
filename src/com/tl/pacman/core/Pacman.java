package com.tl.pacman.core;

import javax.swing.*;
import java.awt.*;
import static com.tl.pacman.core.Constants.*;

public class Pacman extends ObjMoving{
    private int life = LIFE;
    private int score = 0;
    // số đếm con ma đã bị ăn
    private int numberGhostBeEaten = 0;
    // thời gian mà score đã hiện
    private int[] timeDisplayScore = new int[4] ;
    // số điểm được thêm vào
    private int[] scoreAdd  = new int[4];
    // toạ độ của những con ma bị chết
    private int[] xGhostDie = new int[4] ;
    private int[] yGhostDie = new int[4];

    private ManagerGhost managerGhost;
    
    private AudioManager[] audioManager = new AudioManager[]{
            new AudioManager("/audios/pacman_beginning.wav"),
            new AudioManager("/audios/pacman_chomp.wav"),
            new AudioManager("/audios/pacman_death.wav"),
            new AudioManager("/audios/pacman_eatfruit.wav"),
            new AudioManager("/audios/pacman_eatghost.wav"),

    };
    
    private Image[] imgLeft = {
            new ImageIcon(getClass().getResource("/imgs/left1.png")).getImage(),
            new ImageIcon(getClass().getResource("/imgs/left2.png")).getImage()
    } ;

    private Image[] imgRight = {
            new ImageIcon(getClass().getResource("/imgs/right1.png")).getImage(),
            new ImageIcon(getClass().getResource("/imgs/right2.png")).getImage()
    } ;

    private Image[] imgUp = {
            new ImageIcon(getClass().getResource("/imgs/up1.png")).getImage(),
            new ImageIcon(getClass().getResource("/imgs/up2.png")).getImage()
    } ;

    private Image[] imgDown = {
            new ImageIcon(getClass().getResource("/imgs/down1.png")).getImage(),
            new ImageIcon(getClass().getResource("/imgs/down2.png")).getImage()
    };

    private Image imgRound = new ImageIcon(getClass().getResource("/imgs/round.png")).getImage();


    public Pacman(int x, int y ,ManagerItem managerItem,ManagerGhost managerGhost) {
        super( x, y,SIZE_PACMAN,null ,managerItem);
        this.x = x;
        this.y = y;
        this.managerGhost = managerGhost;
        this.image = imgLeft[0];
        audioManager[0].unit();
//        audioManager[1].unit();
//        audioManager[2].unit();
//        audioManager[3].unit();
//        audioManager[4].unit();
        audioManager[0].start();
    }

    /**
     * thay doi anh
     */
    public void changeImgMove() {
        time++;
        switch (direction) {
            case MOVE_RIGHT:
                setImgMove(imgRight);
                break;
            case MOVE_LEFT:
                setImgMove(imgLeft);
                break;
            case MOVE_UP:
                setImgMove(imgUp);
                break;
            case MOVE_DOWN:
                setImgMove(imgDown);
                break;
        }
    }

    /**
     *  xét ảnh hiển thị
     * @param imageMove
     */
    public void setImgMove(Image[] imageMove){
        if(time == TIME_CHANGE_IMG)image = imageMove[0];
        else if (time  == TIME_CHANGE_IMG *2 ) image = imageMove[1];
        else if (time  == TIME_CHANGE_IMG * 3) {
            image = imgRound;
            time = 0;
        }

    }

    /**
     * thay đổi anh khi thay đổi hướng
     */
    public void changeImgDir(){
        switch (direction) {
            case MOVE_RIGHT:
                image = imgRight[0];
                time =TIME_CHANGE_IMG +1;
                break;
            case MOVE_LEFT:
                image = imgLeft[0];
                time =TIME_CHANGE_IMG +1;
                break;
            case MOVE_UP:
                image = imgUp[0];
                time =TIME_CHANGE_IMG +1;
                break;
            case MOVE_DOWN:
                image = imgDown[0];
                time =TIME_CHANGE_IMG +1;
                break;
        }
    }

    /**
     * pacman ăn các dot
     */
    public void eatDot() {
        //chỉ xét khi pacman đi hết block
        if (x % Constants.SIZE_BLOCK == 0 &&  y % Constants.SIZE_BLOCK == 0 ) {

            // không xét toàn bộ item mà chỉ xét item liên quan đến pacman
            int colunmPac = x / Constants.SIZE_BLOCK;
            int rowPac = y / Constants.SIZE_BLOCK ;
            if((managerItem.getItems())[rowPac][colunmPac] != null){
                if((managerItem.getItems())[rowPac][colunmPac].getType() == BIG_DOT){
                    audioManager[AUDIO_BEGIN].stop();
                    audioManager[AUDIO_DEATH].stop();
                    audioManager[AUDIO_EAT_BIG_DOT].unit();
                    audioManager[AUDIO_EAT_BIG_DOT].start();
                    numberGhostBeEaten =0;
                    scoreAdd[0] = 0;
                    scoreAdd[1] = 0;
                    scoreAdd[2] = 0;
                    scoreAdd[3] = 0;

                    managerGhost.hollow();
                    managerItem.getItems()[rowPac][colunmPac].setType(NOTHING); ;
                }
                else if((managerItem.getItems())[rowPac][colunmPac].getType() == DOT){
                    audioManager[AUDIO_EAT_DOT].stop();
                    audioManager[AUDIO_BEGIN].stop();
                    audioManager[AUDIO_DEATH].stop();
                    audioManager[AUDIO_EAT_DOT].unit();
                    audioManager[AUDIO_EAT_DOT].start();
                    score = score+10;
                    managerItem.getItems()[rowPac][colunmPac].setType(NOTHING);
                    managerItem.setAmountDot(managerItem.getAmountDot() -1 );
                }
                else if((managerItem.getItems())[rowPac][colunmPac].getType() == NOTHING){
                    audioManager[AUDIO_EAT_DOT].stop();
                }
            }
        }
    }

    /**
     *
     * @return true nếu pacman hết mạng
     */
    public boolean dead(){
        int length = managerGhost.getGhosts().size();

        for(int i=0;i<length;i++){
            // pacman gặp ghost
            if ((managerGhost.getGhosts()).get(i).getX() - DELTA_SIZE < x && x <(managerGhost.getGhosts()).get(i).getX() + DELTA_SIZE
                    && (managerGhost.getGhosts()).get(i).getY() - DELTA_SIZE < y && y <(managerGhost.getGhosts()).get(i).getY() + DELTA_SIZE){
                // nếu ghost đang trong trạng thái hollow thì ăn ghost sẽ đc điểm
                if ((managerGhost.getGhosts()).get(i).isHollow()){
                    audioManager[AUDIO_BEGIN].stop();
                    audioManager[AUDIO_EAT_GHOST].stop();
                    audioManager[AUDIO_EAT_GHOST].unit();
                    audioManager[AUDIO_EAT_GHOST].start();
                    timeDisplayScore[i] = 0;
                    xGhostDie[i] = managerGhost.getGhosts().get(i).getX();
                    yGhostDie[i] = managerGhost.getGhosts().get(i).getY();
                    switch (numberGhostBeEaten){
                        case 0:
                            scoreAdd[i] = 200;
                            score = score+scoreAdd[i];
                            break;
                        case 1:
                            scoreAdd[i] = 400;
                            score = score+scoreAdd[i];
                            break;
                        case 2:
                            scoreAdd[i] = 800;
                            score = score+scoreAdd[i];
                            break;
                        case 3:
                            scoreAdd[i] = 1600;
                            score = score+scoreAdd[i];
                            break;
                    }
                    numberGhostBeEaten++;
                    managerGhost.setDefaulGhost(i);
                    return false;
                }
                //nếu không thì sẽ bị mất mạng
                else {
                    audioManager[AUDIO_BEGIN].stop();
                    audioManager[AUDIO_DEATH].unit();
                    audioManager[AUDIO_DEATH].start();
                    if (life == 0){
                        stopAudio();
                        return true;
                    }else {
                        for (int j =0; j< length;j++){
                            managerGhost.setDefaulGhost(j);
                        }
                        life = life - 1;
                        setDefaulPacMan();
                        return false;
                 }
                }
            }

        }
        return false;
    }

    /**
     * xét pacman trở lại mặc định
     */
    public void setDefaulPacMan() {
        setDirection(MOVE_DEFAUl);
        setNewDirection(MOVE_DEFAUl);
        setX(LOCATION_DEFAUL_PAC_X);
        setY(LOCATION_DEFAUL_PAC_Y);
        setImage(imgLeft[0]);
    }

    /**
     * xét score và life về mặc định
     */
    public void setDefaulScoreAndLife() {
        life = LIFE;
        score = 0;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * vẽ số điểm kho pacman ăn đc ghost ở trạng thái hollow
     * @param g2d
     */
    public void drawScoreAdd(Graphics2D g2d){
        for (int i=0;i<managerGhost.getGhosts().size();i++){
            if (timeDisplayScore[i] < TIME_DISPLAY_SCORE && scoreAdd[i] >= 200  ){
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Tahoma",Font.ITALIC,SIZE_SCORE_ADD));
                g2d.drawString("+"+String.valueOf(scoreAdd[i]),xGhostDie[i],yGhostDie[i] +SIZE_BLOCK -SIZE_SCORE_ADD);
            }
            timeDisplayScore[i]++;
        }
    }

    /**
     *
     * @return true nếu số lượng dot đã bằng 0
     */
    public boolean eatAllDot() {
        if(managerItem.getAmountDot() < 1){
            stopAudio();
            return true;
        }
        return false;
    }

    public void stopAudio(){
        audioManager[AUDIO_BEGIN].stop();
        audioManager[AUDIO_EAT_DOT].stop();
        audioManager[AUDIO_DEATH].stop();
        audioManager[AUDIO_EAT_BIG_DOT].stop();
        audioManager[AUDIO_EAT_GHOST].stop();
    }
}
