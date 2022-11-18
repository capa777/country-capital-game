package com.capa;

import com.capa.App;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Popup extends JFrame implements ActionListener {
    boolean isVisible =false;
    public final JMenu menu1 = new JMenu("Wyjdz");
    JMenuBar menuBar = new JMenuBar();
    public Popup () {
        super("Samouczek");
        try {
            menu1.add( new JMenuItem("Wyjdz"));
            menu1.getItem(0).addActionListener(this);
            setVisible(isVisible);
            menuBar.add(menu1);
            setJMenuBar(menuBar);


        } catch (Exception e) {
            e.printStackTrace();
        }
        setSize(400, 350);
        setVisible(isVisible);
    }

    public void paint(Graphics gDC) {
        gDC.clearRect(0, 0, getSize().width, getSize().height);
        gDC.drawString (App.contentpopup, 50, getHeight()/2);
    }
    void refresh(){
        setVisible(isVisible);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
