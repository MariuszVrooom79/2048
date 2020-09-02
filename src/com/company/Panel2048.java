package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Panel2048 extends JPanel {

    Music mus = new Music();
    PlayMusicButton pmb = new PlayMusicButton();
    String musicOnOff = "Off";
    static JButton muzyka;

    public Panel2048() {

        setLayout(null);

        JButton startGame = new JButton("");
        startGame.addActionListener(new StartGame());
        Image start = new ImageIcon(("start.png")).getImage();
        startGame.setIcon(new ImageIcon(start));
        startGame.setBounds(200, 420, 220, 60);
        add(startGame);


        muzyka = new JButton("Music on/of");
        muzyka.addActionListener(pmb);
        muzyka.setBounds(320, 490, 100, 40);
        add(muzyka);


        JLabel label = new JLabel("");
        Image mainPhoto = new ImageIcon(("pic.jpg")).getImage();
        label.setIcon(new ImageIcon(mainPhoto));
        label.setBounds(100, 10, 400, 400);
        add(label);

        JButton scoreBoard = new JButton("Score Board");
        scoreBoard.addActionListener(new scoreBoard());
        scoreBoard.setBounds(200,490,100,40);
        add(scoreBoard);

        JButton howToPlay = new JButton("Jak w to Grać");
        howToPlay.addActionListener(new howToPlay());
        howToPlay.setBounds(255,530,100,40);
        add(howToPlay);

        JLabel back = new JLabel("");
        Image background = new ImageIcon(("Background.jpg")).getImage();
        back.setIcon(new ImageIcon(background));
        back.setBounds(0, 0, 700, 700);
        add(back);

    }

    class StartGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Configuration configuration = new DefaultConfiguration();
            MainForm mainForm = new MainForm(configuration);
            mainForm.setVisible(true);

        }
    }
    static class scoreBoard implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame score = new JFrame("Wyniki");
            score.add(new GuiScore());
            score.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            score.setVisible(true);
            score.pack();
        }
    }
    static class howToPlay implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame howtoPlay = new JFrame("Jak w to Grać");
            howtoPlay.add(new PanelHowtoPlay());
            howtoPlay.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            howtoPlay.setVisible(true);
            howtoPlay.pack();
        }
    }

    class Music {

        Clip clip;

        public void setFile(String soundFile) {

            try {
                File file = new File(soundFile);
                AudioInputStream music1 = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(music1);
                System.out.println("Odczytane");
            } catch (Exception e) {
                System.out.println("Błąd odczytu");

            }
        }

        public void play() {
            clip.setFramePosition(0);
            clip.start();
        }


        public void stop() {
            clip.stop();
            clip.close();
        }
    }

    class PlayMusicButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(musicOnOff.equals("Off")) {
                mus.setFile("music.wav");
                mus.play();
                musicOnOff = "On";
                muzyka.setText("Music On");
            }else if(musicOnOff.equals("On")){
                mus.stop();
                musicOnOff = "Off";
                muzyka.setText("Music Off");

            }
        }
    }
    public Dimension getPreferredSize(){
        return new Dimension(600,600);
    }
}
