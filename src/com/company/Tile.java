package com.company;

import java.awt.*;
import java.util.*;

public class Tile implements Renderable, Cloneable {
    private Configuration configuration;
    private int value;
    private int xIndex;
    private int yIndex;
    private int width;
    private int height;

    public Tile(Configuration configuration, int value, int xIndex, int yIndex) {
        this.configuration = configuration;
        this.value = value;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.width = configuration.getTileWidth();
        this.height = configuration.getTileHeight();
    }

    @Override
    public void render(Graphics g) {
        Color color = configuration.getTileColor(value);
        g.setColor(color);
        g.fillRect(getX(), getY(), width, height);
        g.setColor(Color.LIGHT_GRAY);
        drawValue(g);
        g.drawRect(getX(), getY(), width, height);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean canBeJoined(Tile other) {
        return this != other && this.value == other.value;
    }

    public boolean join(Tile other) {
        if (canBeJoined(other)) {
            value += other.value;
            xIndex = other.xIndex;
            yIndex = other.yIndex;
            return true;
        }
        return false;
    }

    public int getxIndex() {
        return xIndex;
    }

    public void setxIndex(int xIndex) {
        this.xIndex = xIndex;
    }

    public int getyIndex() {
        return yIndex;
    }

    public void setyIndex(int yIndex) {
        this.yIndex = yIndex;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Tile tile = (Tile) other;
        return value == tile.value &&
                xIndex == tile.xIndex &&
                yIndex == tile.yIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, xIndex, yIndex);
    }

    @Override
    public String toString() {
        return value + "";
    }

    private void drawValue(Graphics g) {
        g.setColor(Color.GRAY);
        g.setFont(new Font(configuration.getFontName(), Font.PLAIN, configuration.getFontSize()));
        String text = value + "";
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textHeight = g.getFontMetrics().getHeight();
        int textX = getX() + width / 2 - textWidth / 2;
        int textY = getY() + height / 2 + textHeight / 2;
        g.drawString(text, textX, textY);
    }

    private int getX() {
        return xIndex * width;
    }
    private int getY() {
        return yIndex * height;
    }
}
