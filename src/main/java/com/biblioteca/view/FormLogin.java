package com.biblioteca.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FormLogin extends JPanel {

    private JTextField inputEmail;
    private JPasswordField inputPassword;
    private JButton btnSubmit;
    private JLabel errorMessage;
    private JLabel goToRegister;

    public FormLogin() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(400, 400));

        JLabel title = new JLabel("Login");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 50));

        JLabel lblEmail = new JLabel("Your e-mail");
        lblEmail.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputEmail = new JTextField();
        inputEmail.setMaximumSize(new Dimension(200, 30));
        inputEmail.setPreferredSize(new Dimension(200, 30));

        JLabel lblPassword = new JLabel("Your password");
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputPassword = new JPasswordField();
        inputPassword.setMaximumSize(new Dimension(200, 30));
        inputPassword.setPreferredSize(new Dimension(200, 30));

        errorMessage = new JLabel("");
        errorMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorMessage.setForeground(java.awt.Color.RED);
        errorMessage.setFont(new Font("Arial", Font.PLAIN, 11));

        goToRegister = new JLabel("Do not have an account? Create one");
        goToRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        goToRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnSubmit = new JButton("Login");
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setMaximumSize(new Dimension(150, 30));
        btnSubmit.setPreferredSize(new Dimension(150, 30));

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(lblEmail);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(inputEmail);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(lblPassword);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(inputPassword);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(errorMessage);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(goToRegister);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(btnSubmit);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(Box.createVerticalGlue());
    }

    public String getEmail() {
        return inputEmail.getText().trim();
    }

    public String getPassword() {
        return new String(inputPassword.getPassword()).trim();
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }

    public JLabel getGoToRegister() {
        return goToRegister;
    }

    public void showError(String message) {
        errorMessage.setText(message);
    }

    public void clearFields() {
        inputEmail.setText("");
        inputPassword.setText("");
        errorMessage.setText("");
    }
}
