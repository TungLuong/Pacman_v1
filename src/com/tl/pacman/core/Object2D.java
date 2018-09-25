package com.tl.pacman.core;

import java.awt.*;

public class Object2D {
    protected int x;
    protected int y;
    protected int size;
    protected Image image;

    public Object2D(int x, int y, int size, Image image) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(image,x,y,size,size,null);
    }
}
