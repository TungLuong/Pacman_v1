package com.tl.pacman.core;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.tl.pacman.core.Constants.*;

public class ManagerItem {
    // item hiện tại
    private Item[][] items = new Item[ROW][COLUNM];
    // các toạ độ điểm hiện tại
    private Node[][] arrNodes = new Node[ROW][COLUNM];

    private Item[][] itemsCopy = new Item[ROW][COLUNM];
    private Node[][] arrNodesCopy = new Node[ROW][COLUNM];

    private int amountDotCopy;
    // số lượng chấm Dot
    private int amountDot;
    public ManagerItem(String filePath) {
        try {
            InputStream in = getClass().getResource(filePath).openStream();
            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(inR);
            String line = reader.readLine();
            int numberRead = 0;
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    int type = line.charAt(i) - '0';
                    int x = SIZE_BLOCK * i;
                    int y = numberRead *SIZE_BLOCK;
                    switch (type){
                        case WALL:
                            items[numberRead][i] = new Item(x + DELTA_LOCATION_WALL, y +DELTA_LOCATION_WALL,SIZE_WALL,null,WALL);
                            itemsCopy[numberRead][i] = new Item(x + DELTA_LOCATION_WALL, y +DELTA_LOCATION_WALL,SIZE_WALL,null,WALL);
                            break;
                        case DOT:
                            items[numberRead][i] = new Item(x +DELTA_LOCATION_DOT, y + DELTA_LOCATION_DOT ,SIZE_DOT,null,DOT);
                            arrNodes[numberRead][i] = new Node(i * Constants.SIZE_BLOCK, numberRead * Constants.SIZE_BLOCK);

                            itemsCopy[numberRead][i] = new Item(x +DELTA_LOCATION_DOT, y + DELTA_LOCATION_DOT ,SIZE_DOT,null,DOT);
                            arrNodesCopy[numberRead][i] = new Node(i * Constants.SIZE_BLOCK, numberRead * Constants.SIZE_BLOCK);

                            amountDot++;
                            amountDotCopy++;
                            break;
                        case BIG_DOT:
                            items[numberRead][i] = new Item(x , y  ,SIZE_BIG_DOT_MAX,null,BIG_DOT);
                            arrNodes[numberRead][i] = new Node(i * Constants.SIZE_BLOCK, numberRead * Constants.SIZE_BLOCK);

                            itemsCopy[numberRead][i] = new Item(x , y  ,SIZE_BIG_DOT_MAX,null,BIG_DOT);
                            arrNodesCopy[numberRead][i] = new Node(i * Constants.SIZE_BLOCK, numberRead * Constants.SIZE_BLOCK);

                            break;
                        case NOTHING:
                            items[numberRead][i] = new Item(x,y,SIZE_BLOCK,null,NOTHING);
                            arrNodes[numberRead][i] = new Node(i * Constants.SIZE_BLOCK, numberRead * Constants.SIZE_BLOCK);

                            itemsCopy[numberRead][i] = new Item(x,y,SIZE_BLOCK,null,NOTHING);
                            arrNodesCopy[numberRead][i] = new Node(i * Constants.SIZE_BLOCK, numberRead * Constants.SIZE_BLOCK);
                            break;
                        case SPECIAL_WAY:
                            items[numberRead][i] = new Item(x,y,SIZE_BLOCK,null,SPECIAL_WAY);
                            arrNodes[numberRead][i] = new Node(i * Constants.SIZE_BLOCK, numberRead * Constants.SIZE_BLOCK);

                            itemsCopy[numberRead][i] = new Item(x,y,SIZE_BLOCK,null,SPECIAL_WAY);
                            arrNodesCopy[numberRead][i] = new Node(i * Constants.SIZE_BLOCK, numberRead * Constants.SIZE_BLOCK);
                            break;

                    }
                }
                line = reader.readLine();
                numberRead++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void drawAllItem(Graphics2D g2d) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUNM; j++) {
                if (items[i][j] != null){
                    items[i][j].draw(g2d);
                }
            }
        }
    }
    public Item[][] getItems() {
        return items;
    }

    public Node[][] getArrNodes() {
        return arrNodes;
    }

    public void setAmountDot(int amountDot) {
        this.amountDot = amountDot;
    }

    public int getAmountDot(){return  amountDot;}

    /**
     * tạo lại Item và Node mặc định
     */
    public void setDefaul(){
        for (int i =0;i < ROW ; i++){
            for (int j =0; j < COLUNM;j++){
                items[i][j] = itemsCopy[i][j];
                arrNodes[i][j] = arrNodesCopy[i][j];
            }
        }
        amountDot = amountDotCopy;
    }


}
