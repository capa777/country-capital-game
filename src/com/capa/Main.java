package com.capa;

import com.capa.App;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new App();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}