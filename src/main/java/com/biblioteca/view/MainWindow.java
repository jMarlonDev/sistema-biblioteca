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

    private CardLayout viewDesign;
    private JPanel mainContainer;

    public MainWindow() {
        this.setTitle("Library");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1200, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        viewDesign = new CardLayout();
        mainContainer = new JPanel(viewDesign);

        //mainContainer.add(new FormLogin(), VISTA_LOGIN);
        //mainContainer.add(new MenuAdministrator(), VISTA_ADMIN);
        mainContainer.add(new MenuLibrarian(), VISTA_LIBRARIAN);
        //mainContainer.add(new FormRegister(), VISTA_REGISTER);

        this.add(mainContainer, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public void showView(String nameView) {
        viewDesign.show(mainContainer, nameView);
    }
}
