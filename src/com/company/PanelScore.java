package com.company;

import javax.swing.*;
import javax.swing.text.Position;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelScore extends JPanel {

    public static JTextField inputName;
    public static JList showScore;

    public PanelScore(){

        setLayout(null);

        JLabel yourName = new JLabel("Twoje imie: ");
        yourName.setBounds(50,40,100,20);
        add(yourName);

        inputName = new JTextField();
        inputName.setBounds(140,200,100,20);
        add(inputName);

        JButton save = new JButton("Zapisz");
        save.addActionListener(new saveClick());
        save.setBounds(260,40,150,20);
        add(save);

        showScore = new JList();
        showScore.setBounds(50,80,300,100);
        add(showScore);

        JLabel labelScore = new JLabel( "Twój wynik to : " + (Board.score));
        labelScore.setBounds(50,10,300,30);
        add(labelScore);

        JLabel back3 = new JLabel("");
        Image background = new ImageIcon(("Background.jpg")).getImage();
        back3.setIcon(new ImageIcon(background));
        back3.setBounds(0, 0, 400, 300);
        add(back3);

    }
    public Dimension getPreferredSize() {

        return new Dimension(400, 300);

    }


    static class saveClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            new User(Board.score.toString(),PanelScore.inputName.getText());

            Board.newGame();

        }
    }
}
