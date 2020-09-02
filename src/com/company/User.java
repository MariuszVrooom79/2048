package com.company;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class User implements Serializable{

    private String yourScore;
    private String name;

    public static TreeMap<String, String> lista = new TreeMap<>();
    public static TreeMap<String, String> listScore = new TreeMap<>();
    public static DefaultListModel <Set> defaulScore = new DefaultListModel<>();


    public User(String yourScore, String name) {
        this.yourScore = yourScore;
        this.name = name;
        saveToFile();
    }

    @Override
    public String toString() {
        return "" +
                "Wynik='" + yourScore + '\'' +
                ", Nazwa ='" + name + '\'' +
                '}';
    }

    public static void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("Score.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            listScore.put( Board.score.toString(),PanelScore.inputName.getText());
            oos.writeObject(listScore);

            oos.close();
            fos.close();

        } catch (Exception ex) {
        }
    }

    public static void odczytPlik() {

        try {
            FileInputStream fis = new FileInputStream("Score.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (TreeMap<String, String>) ois.readObject();

            listScore = lista;

            defaulScore.addElement(lista.entrySet());

            fis.close();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
