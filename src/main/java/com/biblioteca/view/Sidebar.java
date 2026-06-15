package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Sidebar extends JPanel {

    public Sidebar(String[] options, Consumer<String> onOptionClick) {

        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        this.setMinimumSize(new Dimension(180, 0));

        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(220, 220, 220)));

        JPanel divButtons = new JPanel();
        divButtons.setLayout(new GridLayout(options.length, 1, 0, 10));
        divButtons.setBackground(Color.white);
        divButtons.setBorder(BorderFactory.createEmptyBorder(50, 15, 0, 20));

        for (String option : options) {
            JButton btn = new JButton(option);

            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setHorizontalAlignment(JButton.LEFT);

            btn.addActionListener((e) -> {
                if (onOptionClick != null) {
                    onOptionClick.accept(option);
                }
            });

            divButtons.add(btn);
        }

        this.add(divButtons, BorderLayout.NORTH);
    }
}
