package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NavBar extends JPanel {

    private JLabel welcomeLabel;

    public NavBar(String username) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.decode("#51A4CE"));
        this.setPreferredSize(new Dimension(0, 60));

        JLabel titleNav = new JLabel("Library");
        titleNav.setForeground(Color.white);
        titleNav.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel divTitleNav = new JPanel();
        divTitleNav.setOpaque(false);
        divTitleNav.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        divTitleNav.add(titleNav);

        welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel divWelcome = new JPanel();
        divWelcome.setOpaque(false);
        divWelcome.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        divWelcome.add(welcomeLabel);

        JButton navBtn = new JButton("Logout");
        navBtn.setFocusPainted(false);
        navBtn.setBorderPainted(false);
        navBtn.setPreferredSize(new Dimension(100, 30));

        JPanel divNavBtn = new JPanel();
        divNavBtn.setOpaque(false);
        divNavBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        divNavBtn.add(navBtn);

        this.add(divTitleNav, BorderLayout.WEST);
        this.add(divWelcome, BorderLayout.CENTER);
        this.add(divNavBtn, BorderLayout.EAST);
    }

    public void setUsername(String username) {
        welcomeLabel.setText("Welcome, " + username);
    }
}
