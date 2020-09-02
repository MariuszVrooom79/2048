package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

public class Board implements Renderable, KeyListener {
    private JFrame parent;
    private Tile[][] tiles;
    public static Integer score;
    private Configuration configuration;

    public Board(JFrame parent, Configuration configuration) {
        this.parent = parent;
        this.configuration = configuration;
        reset();
    }

    public void reset() {
        tiles = new Tile[configuration.getRowCount()][configuration.getColumnCount()];
        addRandomTile();
        addRandomTile();
        score = 0;
    }
    public static void newGame(){
        JFrame window = new JFrame("Gra 2048");
        window.add(new Panel2048());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.pack();
    }
    public void yourScore(){
        JFrame loseWin = new JFrame("Twój wynik.");
        loseWin.add(new PanelScore());
        loseWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loseWin.setVisible(true);
        loseWin.pack();

    }

    @Override
    public void render(Graphics g) {
        renderBoard(g);
        for (int y = 0; y < tiles.length; y++) { // wiersze
            for (int x = 0; x < tiles[y].length; x++) { // kolumny
                Tile tile = tiles[y][x];
                if (tile != null) {
                    tile.render(g);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Transition transition = keyCodeToTransition(e.getKeyCode());
        if (tryGoToNextState(transition)) {
            addRandomTile();
            parent.repaint();
            // System.out.println(Arrays.deepToString(tiles));

            GameResult result = checkGameResult();
            parent.setTitle(result.toString());


            if (result == GameResult.Win) {
                JOptionPane.showMessageDialog(parent, configuration.getWinMessage());
                reset();
            } else if (result == GameResult.Lose) {
                yourScore();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    private boolean tryGoToNextState(Transition transition) {
        Tile[][] nextState = goToNextState(transition, false);

        if (nextState != null && !areTheSame(tiles, nextState)) {
            tiles = nextState;
            return true;
        }
        return false;
    }

    private Tile[][] goToNextState(Transition transition, boolean isVirtual) {
        if (transition == Transition.Left) return goToNextState(-1, 0, isVirtual);
        if (transition == Transition.Right) return goToNextState(1, 0, isVirtual);
        if (transition == Transition.Up) return goToNextState(0, -1, isVirtual);
        if (transition == Transition.Down) return goToNextState(0, 1, isVirtual);
        return null;
    }

    private Tile[][] goToNextState(int dx, int dy, boolean isVirtual) {
        Tile[][] nextState = cloneTiles();

        translate(nextState, dx, dy);
        join(nextState, dx, dy, isVirtual);

        return nextState;
    }

    private void translate(Tile[][] state, int dx, int dy) {
        boolean isChanging = true;

        while (isChanging) {
            isChanging = false;
            for (int y = 0; y < state.length; y++) { // wiersze
                for (int x = 0; x < state[y].length; x++) { // kolumny
                    Tile tile = state[y][x];
                    if (tile != null) {
                        int nextX = x + dx;
                        int nextY = y + dy;
                        if (isEmptyAt(state, nextX, nextY)) {
                            state[y][x] = null;
                            state[nextY][nextX] = tile;
                            tile.setxIndex(nextX);
                            tile.setyIndex(nextY);
                            isChanging = true;
                        }
                    }
                }
            }
        }
    }

    private void join(Tile[][] state, int dx, int dy, boolean isVirtual) {
        for (int y = 0; y < state.length; y++) { // wiersze
            for (int x = 0; x < state[y].length; x++) { // kolumny
                Tile tile = state[y][x];
                if (tile != null) {
                    int nextX = x + dx;
                    int nextY = y + dy;
                    if (isJoinableAt(state, nextX, nextY, tile)) {
                        if (tile.join(state[nextY][nextX])) {
                            if (!isVirtual) {
                                score += tile.getValue();
                            }
                            state[nextY][nextX] = tile;
                            state[y][x] = null;
                        }
                    }
                }
            }
        }
    }

    private GameResult checkGameResult() {
        for (int y = 0; y < tiles.length; y++) { // wiersze
            for (int x = 0; x < tiles[y].length; x++) { // kolumny
                if (tiles[y][x] != null && tiles[y][x].getValue() == 2048) {
                    return GameResult.Win;
                }
            }
        }
        boolean isLeftStateSame = areTheSame(tiles, goToNextState(Transition.Left, true));
        boolean isRightStateSame = areTheSame(tiles, goToNextState(Transition.Right, true));
        boolean isUpStateSame = areTheSame(tiles, goToNextState(Transition.Up, true));
        boolean isDownStateSame = areTheSame(tiles, goToNextState(Transition.Down, true));

        if (isFullOfTiles() && isLeftStateSame && isRightStateSame && isUpStateSame && isDownStateSame) {
            return GameResult.Lose;
        }
        return GameResult.IsRunning;
    }

    private boolean isInBounds(Tile[][] state, int x, int y) {
        return y >= 0 && y < state.length && x >= 0 && x < state[y].length;
    }

    private boolean isEmptyAt(Tile[][] state, int x, int y) {
        if (isInBounds(state, x, y)) {
            return state[y][x] == null;
        }
        return false;
    }

    private boolean isJoinableAt(Tile[][] state, int x, int y, Tile tile) {
        if (isInBounds(state, x, y) && !isEmptyAt(state, x, y)) {
            return tile.canBeJoined(state[y][x]);
        }
        return false;
    }

    private boolean areTheSame(Tile[][] state1, Tile[][] state2) {
        for (int y = 0; y < state1.length; y++) {
            for (int x = 0; x < state1[y].length; x++) {
                Tile currentTile = state1[y][x];
                if (currentTile != null && !currentTile.equals(state2[y][x])) {
                    return false;
                }
            }
        }
        return true;
    }

    private Tile[][] cloneTiles() {
        Tile[][] copy = new Tile[configuration.getRowCount()][configuration.getColumnCount()];

        for (int y = 0; y < tiles.length; y++) { // wiersze
            for (int x = 0; x < tiles[y].length; x++) { // kolumny
                if (tiles[y][x] != null) {
                    copy[y][x] = (Tile) tiles[y][x].clone();
                }
            }
        }
        return copy;
    }

    private void renderBoard(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0,
                configuration.getBoardWidth(),
                configuration.getBoardHeight());
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial",Font.BOLD,16));
        g.drawString("Twoje punkty = " + score, 10, configuration.getBoardHeight() + 25);

    }

    private void addTile(int value, int x, int y) {
        tiles[y][x] = new Tile(configuration, value, x, y);
    }

    private void addRandomTile() {
        if (!isFullOfTiles()) {
            while (true) {
                int y = ThreadLocalRandom.current().nextInt(0, tiles.length);
                int x = ThreadLocalRandom.current().nextInt(0, tiles[y].length);

                if (tiles[y][x] == null) {
                    addTile(2, x, y);
                    break;
                }
            }
        }
    }

    private boolean isFullOfTiles() {
        for (int y = 0; y < tiles.length; y++) { // wiersze
            for (int x = 0; x < tiles[y].length; x++) { // kolumny
                if (tiles[y][x] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private Transition keyCodeToTransition(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) return Transition.Left;
        if (keyCode == KeyEvent.VK_RIGHT) return Transition.Right;
        if (keyCode == KeyEvent.VK_UP) return Transition.Up;
        if (keyCode == KeyEvent.VK_DOWN) return Transition.Down;
        return Transition.Unknown;
    }

    private void closeParent() {
        parent.dispatchEvent(new WindowEvent(parent, WindowEvent.WINDOW_CLOSING));
    }
}
