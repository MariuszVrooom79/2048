package com.company;

import com.company.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainForm extends JFrame implements ActionListener {
    private Game game;
    private Configuration configuration;

    public MainForm(Configuration configuration) {
        super("2048");
        this.configuration = configuration;
        setSize(configuration.getWindowWidth(), configuration.getWindowHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        game = new Game(this, configuration);
        addKeyListener(game.getBoard());
        createMainMenu();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(configuration.getRenderXOffset(), configuration.getRenderYOffset());
        game.render(g);
    }

    private void createMainMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("Menu");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem back = new JMenuItem("Back");
        back.addActionListener(new back());
        resetItem.addActionListener(this);

        menuFile.add(resetItem);
        menuFile.add(back);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.reset();
        repaint();
    }
    static class back implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame window = new JFrame("Gra 2048");
            window.add(new Panel2048());
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            window.pack();
        }
    }
}
