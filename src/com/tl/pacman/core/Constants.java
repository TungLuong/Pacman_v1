package com.tl.pacman.core;

public interface Constants {

    int COLUNM = 29;
    int ROW = 25;
    int WIDTH_FRAME = 760;
    int HEIGHT_FRAME = 735;
    int HEIGHT_PLAY = 675;
    int HEIGHT_MENU_PLAY = HEIGHT_FRAME - HEIGHT_PLAY;

    int WIDTH_IMG_WIN = 300;
    int WIDTH_IMG_LOST = 300;
    int HEIGHT_IMG_WIN = 150;
    int HEIGHT_IMG_LOST = 150;


    int AUDIO_BEGIN = 0;
    int AUDIO_EAT_DOT = 1;
    int AUDIO_DEATH = 2;
    int AUDIO_EAT_BIG_DOT = 3;
    int AUDIO_EAT_GHOST = 4;

    int TIME_CHANGE_IMG = 10;
    int TIME_DISPLAY_SCORE = 100;

    int LIFE = 3;

    int SIZE_GHOST = 26;
    int SIZE_PACMAN = SIZE_GHOST;
    int SIZE_SCORE_ADD = 10;
    int SIZE_BLOCK = 26;
    int SIZE_WALL = 18;
    int SIZE_DOT = 4;
    int SIZE_BIG_DOT_MAX = 16;
    int SIZE_BIG_DOT_MIN = 10;
    int DELTA_SIZE = 20;

    int DELTA_LOCATION_WALL = ((SIZE_BLOCK - SIZE_WALL) / 2);
    int DELTA_LOCATION_DOT = (SIZE_BLOCK - SIZE_DOT) / 2;
    int DELTA_LOCATION_BIG_DOT_MAX = (SIZE_BLOCK - SIZE_BIG_DOT_MAX) / 2;
    int DELTA_LOCATION_BIG_DOT_MIN = (SIZE_BLOCK - SIZE_BIG_DOT_MIN) / 2;

    int WALL = 0;
    int DOT = 1;
    int BIG_DOT = 4;
    int SPECIAL_WAY = 2;
    int NOTHING = 3;

    int LOCATION_DEFAUL_GHOST_CYAN_X = 9 * Constants.SIZE_BLOCK;
    int LOCATION_DEFAUL_GHOST_CYAN_Y = 11 * Constants.SIZE_BLOCK;
    int LOCATION_DEFAUL_GHOST_ORANGE_X = 9 * Constants.SIZE_BLOCK;
    int LOCATION_DEFAUL_GHOST_ORANGE_Y = 13 * Constants.SIZE_BLOCK;
    int LOCATION_DEFAUL_GHOST_PINK_X = 19 * Constants.SIZE_BLOCK;
    int LOCATION_DEFAUL_GHOST_PINK_Y = 11 * Constants.SIZE_BLOCK;
    int LOCATION_DEFAUL_GHOST_RED_X = 19 * Constants.SIZE_BLOCK;
    int LOCATION_DEFAUL_GHOST_RED_Y = 13 * Constants.SIZE_BLOCK;

    int LOCATION_DEFAUL_PAC_X = 10 * Constants.SIZE_BLOCK;
    int LOCATION_DEFAUL_PAC_Y = 5 * Constants.SIZE_BLOCK;

    int GHOST_CYAN = 0;
    int GHOST_ORANGE = 1;
    int GHOST_PINK = 2;
    int GHOST_RED = 3;
    int SPEED_DELAY_NORMAL = 1;
    int SPEED_DELAY_MAX = 2;
    int TIME_CHANGE_HOLLOW = 1000;

    int MOVE_LEFT = 0;
    int MOVE_UP = 1;
    int MOVE_RIGHT = 2;
    int MOVE_DOWN = 3;
    int MOVE_DEFAUl = 10;
}
