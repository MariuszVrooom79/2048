package com.company;

import javax.swing.*;
import java.awt.*;

public class PanelHowtoPlay extends JPanel {

    public PanelHowtoPlay(){
        setLayout(null);

        JButton back = new JButton("Wstecz");
        back.addActionListener(new GuiScore.backA());
        back.setBounds(400,260,120,40);
        add(back);

        JLabel label = new JLabel("");
        Image mainPhoto = new ImageIcon(("2048.jpg")).getImage();
        label.setIcon(new ImageIcon(mainPhoto));
        label.setBounds(0, 0, 600, 400);
        add(label);

        JLabel back2 = new JLabel("");
        Image background = new ImageIcon(("Background.jpg")).getImage();
        back2.setIcon(new ImageIcon(background));
        back2.setBounds(0, 0, 400, 400);
        add(back2);

    }
    public Dimension getPreferredSize(){
        return new Dimension(600,400);
    }
}
