package com.tl.pacman.core;

import java.util.ArrayList;
import java.util.List;

import static com.tl.pacman.core.AStar.*;

/**
 * class điểm
 */
public class Node {
    // các điểm kề
    private List<Node> nodesNext = new ArrayList<>();
    private int x;
    private int y;
    //điểm nó được nối tới
    private Node cameFrom;
    private double  g;
    private double  h;
    private double  f;

    public Node(int x,int y){
        this.x = x;
        this.y = y;
    }


    public double distance(Node node){
        return (double) Math.sqrt(((this.x - node.x)*(this.x - node.x)) + ((this.y - node.y)*(this.y - node.y)));
    }
    public List<Node> findNodeNext(Node[][] arrNodes){
        nodesNext = new ArrayList<>();
        int i = y/Constants.SIZE_BLOCK;
        int j = x/ Constants.SIZE_BLOCK;
        if (i<Constants.ROW -1) {
            if (arrNodes[i + 1][j] != null) nodesNext.add(arrNodes[i + 1][j]);
        }
        if (i > 0){
            if(arrNodes[i-1][j] != null) nodesNext.add(arrNodes[i-1][j]);
        }
        if (j < Constants.COLUNM -1) {
            if (arrNodes[i][j + 1] != null) nodesNext.add(arrNodes[i][j + 1]);
        }
        if(j>0) {
            if (arrNodes[i][j - 1] != null) nodesNext.add(arrNodes[i][j - 1]);
        }
        return nodesNext;
    }

    public List<Node> getNodesNext() {
        return nodesNext;
    }

    public void setNodesNext(List<Node> nodesNext) {
        this.nodesNext = nodesNext;
    }

    public Node getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(Node cameFrom) {
        this.cameFrom = cameFrom;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
