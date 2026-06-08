package com.biblioteca.view;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

    public MainWindow() {
        this.setTitle("Library");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

        FormLogin formLogin = new FormLogin();

        this.add(formLogin);
        this.setSize(1200, 600);
        this.setVisible(true);
    }

}
