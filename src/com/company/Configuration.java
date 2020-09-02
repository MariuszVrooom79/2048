package com.company;

/*
private final static int WIDTH = 500;
    private final static int HEIGHT = 500;
    private final static int COLUMN_COUNT = 4;
    private final static int ROW_COUNT = 4;
    private final static int TILE_WIDTH = WIDTH / COLUMN_COUNT;
    private final static int TILE_HEIGHT = HEIGHT / ROW_COUNT;
 */

import java.awt.*;

public interface Configuration {
    int getWindowWidth();
    int getWindowHeight();
    int getRenderXOffset();
    int getRenderYOffset();
    int getBoardWidth();
    int getBoardHeight();
    int getColumnCount();
    int getRowCount();
    String getFontName();
    int getFontSize();
    Color getTileColor(int tileValue);
    String getWinMessage();
    String getLoseMessage();

    default int getTileWidth() {
        return getBoardWidth() / getColumnCount();
    }
    default int getTileHeight() {
        return getBoardHeight() / getRowCount();
    }
}
