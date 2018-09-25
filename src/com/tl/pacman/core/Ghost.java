package com.tl.pacman.core;

import java.awt.*;
import java.util.List;
import java.util.Random;


import static com.tl.pacman.core.Constants.*;
//import static com.tl.pacman.core.ManagerItem.arrNodes;


public class Ghost extends ObjMoving {
    private static final int TIME_FIND_PAC_MAN = 300;
    private static final int TIME_CHANGE_IMG = 10;

    private Image[] imgGhost;
    private boolean isHollow = false;
    // bạn của ghost
    private Ghost[] friend;
    //
    private int timeGhost;
    private int type;
    private AStar aStar;


    public Ghost(int x, int y, Image[] imgGhost, ManagerItem managerItem) {
        super(x, y, SIZE_GHOST, null, managerItem);
        this.x = x;
        this.y = y;
        this.imgGhost = imgGhost;
        aStar = new AStar(managerItem.getArrNodes());
        setTimeGhost();
    }

    /**
     * tạo thời gian mặc định ngẫu nhiên cho ghost
     */
    public void setTimeGhost() {
        Random random = new Random();
        timeGhost = random.nextInt((int) (TIME_FIND_PAC_MAN * 1.5));
    }

    /**
     * thay đổi hướng của ghost theo toạ độ pacman
     * @param xPac
     * @param yPac
     */
    public void changDir(int xPac, int yPac) {
        // nếu nhỏ hơn thời gian tìm kiếm pacman hoặc đang ở trạng thái hollow sẽ có hướng di chuyển ngẫu nhiên
        if (timeGhost < TIME_FIND_PAC_MAN || isHollow) {
            changDirRandom();
            //
        } else if (timeGhost >= TIME_FIND_PAC_MAN && timeGhost < TIME_FIND_PAC_MAN * 2) {
            List<Node> nodes = null;
            nodes = findPac(xPac, yPac);
            int i = nodes.size() - 1;
            if (i>1) {
                int deltaX;
                int deltaY;
                deltaX = nodes.get(i - 1).getX() - nodes.get(i).getX();
                deltaY = nodes.get(i - 1).getY() - nodes.get(i).getY();
                if (deltaX == Constants.SIZE_BLOCK) direction = MOVE_RIGHT;
                else if (deltaX == -Constants.SIZE_BLOCK) direction = MOVE_LEFT;
                else if (deltaY == Constants.SIZE_BLOCK) direction = MOVE_DOWN;
                else if (deltaY == -Constants.SIZE_BLOCK) direction = MOVE_UP;
            }
        } else timeGhost = 0;
        timeGhost++;
    }

    /**
     * thay đổi hướng ngẫu nhiên
     */
    public void changDirRandom() {
        Random random = new Random();
        direction = random.nextInt(4);
    }

    /**
     * tìm vị trí của pacman để đuổi theo,
     * nếu con ma khác đã đuổi sẽ tìm đường khác để chặn đầu
     * @param xPac
     * @param yPac
     * @return
     */
    public List<Node> findPac(int xPac, int yPac) {
        int jPac = (xPac / SIZE_BLOCK);
        int iPac = (yPac / SIZE_BLOCK);
        int jGhost = (x / SIZE_BLOCK);
        int iGhost = (y / SIZE_BLOCK);
        int[] jFriend = new int[friend.length];
        for (int i = 0; i < jFriend.length; i++) {
            jFriend[i] = (friend[i].x / SIZE_BLOCK);
        }
        int[] iFriend = new int[friend.length];
        for (int i = 0; i < jFriend.length; i++) {
            iFriend[i] = (friend[i].y / SIZE_BLOCK);
        }

        aStar.setNewWay(iPac, jPac, iGhost, jGhost, iFriend, jFriend);
        aStar.Find();
        return aStar.reconstructPath();

    }

    /**
     * thay đổi ảnh
     */
    public void changeImgMove() {
        time++;
        if (time < TIME_CHANGE_IMG) image = imgGhost[0];
        else if (time < TIME_CHANGE_IMG * 2) image = imgGhost[1];
        else time = 0;
    }

    public Image[] getImgGhost() {
        return imgGhost;
    }

    public void setImgGhost(Image[] imgGhost) {
        this.imgGhost = imgGhost;
    }

    public boolean isHollow() {
        return isHollow;
    }

    public void setHollow(boolean hollow) {
        isHollow = hollow;
    }

    public Ghost[] getFriend() {
        return friend;
    }

    public void setFriend(Ghost[] friend) {
        this.friend = friend;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
