package com.tl.pacman.core;

import java.awt.*;
import static com.tl.pacman.core.Constants.*;

public class ObjMoving extends Object2D {

    protected int speedDelay = SPEED_DELAY_NORMAL;
    // thời gian tồn tại
    protected long time = 0;
    //thời gian mục đích để giảm tốc độ
    protected long timeSpeed =0;

    // hướng đối tượng di chuyển hiện tại
    protected int direction = MOVE_DEFAUl;
    // hướng đối tượng di chuyển tương lai < được dự đoán >
    protected int newDirection = MOVE_DEFAUl;

    protected ManagerItem managerItem;

    public ObjMoving(int x, int y, int size, Image image,ManagerItem managerItem) {
        super(x, y, size, image);
        this.managerItem = managerItem;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getNewDirection() {
        return newDirection;
    }

    public void setNewDirection(int newDirection) {
        this.newDirection = newDirection;
    }

    /**
     * phuong thuc di chuyen
     */
    public void move() {

        if (timeSpeed %  (speedDelay) == 0){
            // thay doi anh
            changeImgMove();
            switch (direction) {
                case MOVE_RIGHT:
                    // chi xet khi di chuyen qua khoi block
                    if (x % SIZE_BLOCK == 0 && y % SIZE_BLOCK == 0) {
                        int i = y / SIZE_BLOCK;
                        int j = x / SIZE_BLOCK;
                        // khi co the thuc hien di chuyen theo huong moi
                        if (moveWithNewDir(managerItem)) {
                            direction = newDirection;
                            newDirection = MOVE_DEFAUl;
                            changeImgDir();
                        }
                        //Neu la duong dac biet
                        else if ((managerItem.getItems())[i][j + 1].getType() == SPECIAL_WAY) {
                            x = x - ((COLUNM - 3) * SIZE_BLOCK);
                        }
                        //Neu khong phai la tuong
                        else if ((managerItem.getItems())[i][j + 1].getType() != WALL) {
                            x = x + 1;
                        }
                    } else
                        x = x + 1;
                    break;
                case MOVE_LEFT:
                    if (x % SIZE_BLOCK == 0 && y % SIZE_BLOCK == 0) {
                        int i = y / SIZE_BLOCK;
                        int j = x / SIZE_BLOCK;
                        if (moveWithNewDir(managerItem)) {
                            direction = newDirection;
                            newDirection = MOVE_DEFAUl;
                            changeImgDir();
                        } else if ((managerItem.getItems())[i][j - 1].getType() == SPECIAL_WAY) {
                            x = x + ((COLUNM - 3) * SIZE_BLOCK);
                        } else if ((managerItem.getItems())[i][j - 1].getType() != WALL) {
                            x = x - 1;
                        }
                    } else
                        x = x - 1;
                    break;
                case MOVE_UP:
                    if (x % SIZE_BLOCK == 0 && y % SIZE_BLOCK == 0) {
                        int i = y / SIZE_BLOCK;
                        int j = x / SIZE_BLOCK;
                        if (moveWithNewDir(managerItem)) {
                            direction = newDirection;
                            newDirection = MOVE_DEFAUl;
                            changeImgDir();
                        } else if ((managerItem.getItems())[i - 1][j].getType() == SPECIAL_WAY) {
                            x = x + ((ROW - 3) * SIZE_BLOCK);
                        } else if ((managerItem.getItems())[i - 1][j].getType() != WALL) {
                            y = y - 1;
                        }
                    } else
                        y = y - 1;
                    break;
                case MOVE_DOWN:
                    if (x % SIZE_BLOCK == 0 && y % SIZE_BLOCK == 0) {
                        int i = y / SIZE_BLOCK;
                        int j = x / SIZE_BLOCK;
                        if (moveWithNewDir(managerItem)) {
                            direction = newDirection;
                            newDirection = MOVE_DEFAUl;
                            changeImgDir();
                        } else if ((managerItem.getItems())[i + 1][j].getType() == SPECIAL_WAY) {
                            x = x - ((ROW - 3) * SIZE_BLOCK);
                        } else if ((managerItem.getItems())[i + 1][j].getType() != WALL) {
                            y = y + 1;
                        }
                    } else
                        y = y + 1;
                    break;
                case MOVE_DEFAUl:
                    direction = newDirection;
            }
        }
        timeSpeed++;
    }

    protected void changeImgMove() {
    }

    protected void changeImgDir() {
    }

    /**
     * neu co the di chuyen theo huong moi
     * @return true nếu hướng mới không có tường, có thể rẽ sang được
     */
    protected boolean moveWithNewDir(ManagerItem managerItem) {
        if (timeSpeed %  (speedDelay) == 0) {
            int i = y / SIZE_BLOCK;
            int j = x / SIZE_BLOCK;
            switch (newDirection) {
                case MOVE_RIGHT:
                    if (j < COLUNM) {
                        if (managerItem.getItems()[i][j + 1].getType() == WALL) {
                            return false;
                        } else return true;
                    } else return false;
                case MOVE_LEFT:
                    if (j > 0) {
                        if (managerItem.getItems()[i][j - 1].getType() == WALL) {
                            return false;
                        } else return true;
                    } else return false;
                case MOVE_UP:
                    if (i > 0) {
                        if ((managerItem.getItems())[i - 1][j].getType() == WALL) {
                            return false;
                        } else return true;
                    } else return false;
                case MOVE_DOWN:
                    if (i < ROW) {
                        if ((managerItem.getItems())[i + 1][j].getType() == WALL) {
                            return false;
                        } else return true;
                    } else return false;
                case MOVE_DEFAUl:
                    return false;
            }
        }
        return false;
    }



    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getSpeedDelay() {
        return speedDelay;
    }

    public void setSpeedDelay(int speedDelay) {
        this.speedDelay = speedDelay;
    }

}
