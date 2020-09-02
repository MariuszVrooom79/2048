package com.company;

import java.awt.*;

import static com.sun.tools.doclint.Entity.ge;

public class DefaultConfiguration implements Configuration {
    @Override
    public int getWindowWidth() {
        return 600;
    }

    @Override
    public int getWindowHeight() {
        return 620;
    }

    @Override
    public int getRenderXOffset() {
        return 10;
    }

    @Override
    public int getRenderYOffset() {
        return 70;
    }

    @Override
    public int getBoardWidth() {
        return 500;
    }

    @Override
    public int getBoardHeight() {
        return 500;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return 4;
    }

    @Override
    public String getFontName() {
        return "Arial";
    }

    @Override
    public int getFontSize()
    {
        return 50;
    }

    public Color getTileColor(int tileValue) {

        if (tileValue == 2) return new Color(238, 228, 218);
        if (tileValue == 4) return new Color(234, 201, 163);
        if (tileValue == 8) return new Color(244, 178, 126);
        if (tileValue == 16) return new Color(246, 151, 107);
        if (tileValue == 32) return new Color(247, 126, 105);
        if (tileValue == 64) return new Color(247, 97, 72);
        if (tileValue == 128) return new Color(238, 236, 17);
        if (tileValue == 256) return new Color(101, 164, 244);
        if (tileValue == 512) return new Color(128, 255, 0);
        if (tileValue == 1024) return new Color(153, 153, 255);
        if (tileValue == 2048) return new Color(53, 157, 255);

        return null;
    }

    @Override
    public String getWinMessage() {
        return "Wygrana! Twój wynik to : ";
    }

    @Override
    public String getLoseMessage() {
        return "Przegrana! Twoj wynik to : ";
    }
}
