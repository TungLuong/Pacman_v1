package com.tl.pacman.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.tl.pacman.core.Constants.COLUNM;
import static com.tl.pacman.core.Constants.ROW;
import static com.tl.pacman.core.Constants.WALL;

public class AStar {

    private Node[][] arrNodes = new Node[ROW][COLUNM];
    private Node[][] newArrNodes = new Node[ROW][COLUNM];
    private Node start ;
    private Node target;

    public AStar(Node[][] arrNodes) {
       this.arrNodes = arrNodes;
    }

    private   List<Node> open = new ArrayList<>();
    private List<Node> close = new ArrayList<>();

    public  List<Node> Find()
    {
        open.clear();
        close.clear();
        if (target == null) return null;
        //calculate h,g,f for start

        if (start == null){
            return null;
        }
        start.setG(0); ;//distance between current and start
        start.setH(start.distance(target));
        start.setF(start.getG()+start.getH());
        start.setCameFrom(null);
        //add start to open list
        open.add(start);
        while (open.size() != 0)
        {
            //Find node in open list have min f
            Node current = open.get(0);
            for (Node i_node : open)
            {
                if (i_node.getF() < current.getF())
                    current = i_node;
            }
            //Remove current from open list
            open.remove(current);
            //Add curent to close list
            close.add(current);
            if (current == target)//If current is target then return path
            {
                //Return path
                return reconstructPath();
            }
            else
            {
                List<Node> nodes = current.findNodeNext(newArrNodes);
                for (Node i_node : nodes )
                {

                    {  if (close.contains(i_node))
                           continue;//if node in close will do nothing

                       double tmp_current_g = current.getG() + current.distance(i_node);
                       if (!open.contains(i_node) || tmp_current_g < i_node.getG())
                       {
                           i_node.setCameFrom(current);
                           i_node.setG(tmp_current_g);
                           i_node.setH(i_node.distance(target));
                           i_node.setF(i_node.getH()+i_node.getG());
                           if (!open.contains(i_node))
                               open.add(i_node);
                       }
                   }
                }
            }
        }
        return null;
    }
    public  List<Node> reconstructPath()
    {
        List<Node> path = new Stack<>();
        path.clear();
        Node tmp = target;
        while (tmp != null)
        {
            path.add(tmp);
            tmp = tmp.getCameFrom();
        }
        return path;
    }

    public void setNewWay(int iPac, int jPac, int iGhost, int jGhost, int[] iGhostFried, int[] jGhostFriend) {

        for (int i =0;i<ROW;i++){
            for (int j =0;j<COLUNM;j++){
                newArrNodes[i][j] = arrNodes[i][j];
            }
        }
        //newArrNodes = arrNodes.clone();
        this.start = arrNodes[iGhost][jGhost];
        this.target = arrNodes[iPac][jPac];
        //System.out.println("arrNodes "+arrNodes[iGhostFried[2]][jGhostFriend[2]]);
        for (int i=0;i< iGhostFried.length;i++){
            this.newArrNodes[iGhostFried[i]][jGhostFriend[i]] = null;
        }
    }
}
