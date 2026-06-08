package com.biblioteca.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormRegister extends JPanel {

    public FormRegister() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(400, 400));

        JLabel title = new JLabel("Register");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 50));

        JLabel name = new JLabel("Your name");
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField inputName = new JTextField();

        inputName.setMaximumSize(new Dimension(200, 30));
        inputName.setPreferredSize(new Dimension(200, 30));

        JLabel email = new JLabel("Your e-mail");
        email.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField inputEmail = new JTextField();
        inputEmail.setMaximumSize(new Dimension(200, 30));
        inputEmail.setPreferredSize(new Dimension(200, 30));

        JLabel password = new JLabel("Your password");
        password.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField inputPassword = new JTextField();
        inputPassword.setMaximumSize(new Dimension(200, 30));
        inputPassword.setPreferredSize(new Dimension(200, 30));

        JLabel createdAccount = new JLabel();
        createdAccount.setText("Already have one? Back to Login");
        createdAccount.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btn = new JButton("Submit");

        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFocusPainted(false);
        btn.setMaximumSize(new Dimension(150, 30));
        btn.setPreferredSize(new Dimension(150, 30));

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(name);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(inputName);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(email);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(inputEmail);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(password);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(inputPassword);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(createdAccount);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(btn);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(Box.createVerticalGlue());
    }

}
