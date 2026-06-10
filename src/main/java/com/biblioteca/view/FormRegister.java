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

public class FormRegister extends JPanel {

    private JTextField inputName;
    private JTextField inputEmail;
    private JPasswordField inputPassword;
    private JButton btnSubmit;
    private JLabel messageText;
    private JLabel goToLogin;

    public FormRegister() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(400, 400));

        JLabel title = new JLabel("Register");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 50));

        JLabel lblName = new JLabel("Your name");
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputName = new JTextField();
        inputName.setMaximumSize(new Dimension(200, 30));
        inputName.setPreferredSize(new Dimension(200, 30));

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

        messageText = new JLabel("");
        messageText.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageText.setFont(new Font("Arial", Font.PLAIN, 11));

        goToLogin = new JLabel("Already have one? Back to Login");
        goToLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        goToLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnSubmit = new JButton("Register");
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setMaximumSize(new Dimension(150, 30));
        btnSubmit.setPreferredSize(new Dimension(150, 30));

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(lblName);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(inputName);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(lblEmail);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(inputEmail);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(lblPassword);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(inputPassword);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(messageText);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(goToLogin);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(btnSubmit);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(Box.createVerticalGlue());
    }

    public String getName() {
        return inputName.getText().trim();
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

    public JLabel getGoToLogin() {
        return goToLogin;
    }

    public void showStatusMessage(String message, boolean isError) {
        messageText.setText(message);
        messageText.setForeground(isError ? java.awt.Color.RED : new java.awt.Color(0, 150, 0));
    }

    public void clearFields() {
        inputName.setText("");
        inputEmail.setText("");
        inputPassword.setText("");
        messageText.setText("");
    }
}
