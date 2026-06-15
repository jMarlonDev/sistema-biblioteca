package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

    public static final String VISTA_LOGIN = "login";
    public static final String VISTA_REGISTER = "register";
    public static final String VISTA_ADMIN = "admin";
    public static final String VISTA_LIBRARIAN = "librarian";
    public static final String VISTA_USER = "user";

    private final CardLayout viewDesign;
    private final JPanel mainContainer;

    private final FormLogin formLogin;
    private final FormRegister formRegister;
    private final MenuAdministrator menuAdministrator;

    public MainWindow() {
        this.setTitle("Library");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1200, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        viewDesign = new CardLayout();
        mainContainer = new JPanel(viewDesign);

        formLogin = new FormLogin();
        formRegister = new FormRegister();
        menuAdministrator = new MenuAdministrator();

        mainContainer.add(formLogin, VISTA_LOGIN);
        mainContainer.add(formRegister, VISTA_REGISTER);
        mainContainer.add(menuAdministrator, VISTA_ADMIN);
        mainContainer.add(new MenuLibrarian(), VISTA_LIBRARIAN);
        mainContainer.add(new UserMenu(), VISTA_USER);

        this.add(mainContainer, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void showView(String nameView) {
        viewDesign.show(mainContainer, nameView);
    }

    public FormLogin getFormLogin() {
        return formLogin;
    }

    public FormRegister getFormRegister() {
        return formRegister;
    }

    public MenuAdministrator getMenuAdministrator() {
        return menuAdministrator;
    }
}
