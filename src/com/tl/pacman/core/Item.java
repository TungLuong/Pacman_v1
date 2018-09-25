package com.tl.pacman.core;

import java.awt.*;

import static com.tl.pacman.core.Constants.*;

public class Item extends Object2D {
    public static int CHANGE_TIME = 40;

    private int type ;
    private int time;
    public Item(int x, int y, int size, Image image,int type) {
        super(x, y, size, null);
        this.type = type;
    }

    @Override
    public void draw(Graphics2D g2d) {
        switch (type){
            case WALL:
                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(6.0f));
                g2d.drawRect(x,y,size ,size );
                break;
            case DOT :
                g2d.setColor(Color.YELLOW);
                g2d.fillOval(x,y ,size,size);
                break;
            case NOTHING:
                break;
            case BIG_DOT:
                time++;
                if (time < CHANGE_TIME) {
                    g2d.setColor(Color.ORANGE);
                    g2d.fillOval(x + DELTA_LOCATION_BIG_DOT_MAX, y + DELTA_LOCATION_BIG_DOT_MAX, SIZE_BIG_DOT_MAX, SIZE_BIG_DOT_MAX);
                } else if (time < 2 * CHANGE_TIME) {
                    g2d.setColor(Color.ORANGE);
                    g2d.fillOval(x + DELTA_LOCATION_BIG_DOT_MIN, y + DELTA_LOCATION_BIG_DOT_MIN, SIZE_BIG_DOT_MIN, SIZE_BIG_DOT_MIN);
                } else time = 0;
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
