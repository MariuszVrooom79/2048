package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiScore extends JPanel {

    public static JList wynik = new JList();

    public GuiScore() {

        setLayout(null);

        wynik = new JList();
        wynik.setBounds(10, 50, 400, 200);
        add(wynik);

        JLabel naa = new JLabel("Najlepsze wyniki : ");
        naa.setBounds(10, 10, 300, 20);
        add(naa);

        wynik.setModel(User.defaulScore);

        JButton back = new JButton("Wstecz");
        back.addActionListener(new backA());
        back.setBounds(200,260,50,20);
        add(back);

        JLabel back3 = new JLabel("");
        Image background = new ImageIcon(("Background.jpg")).getImage();
        back3.setIcon(new ImageIcon(background));
        back3.setBounds(0, 0, 450, 300);
        add(back3);

    }

    static class backA implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame window = new JFrame("Gra 2048");
            window.add(new Panel2048());
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            window.pack();
        }
    }
    public Dimension getPreferredSize(){
        return new Dimension(450,300);
    }
}

