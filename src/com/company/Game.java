package com.company;

import com.company.Board;

import javax.swing.*;
import java.awt.*;

public class Game implements Renderable {
    private Board board;
    private Configuration configuration;

    public Game(JFrame parent, Configuration configuration) {
        board = new Board(parent, configuration);
        this.configuration = configuration;
    }

    @Override
    public void render(Graphics g) {
        board.render(g);
    }

    public void reset() {
        board.reset();
    }

    public Board getBoard() {
        return board;
    }
}
