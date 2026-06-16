package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MenuLibrarian extends JPanel {

    public static final String OPT_BOOKS = "Manage Books";
    public static final String OPT_USERS = "Manage Users";
    public static final String OPT_LOANS = "Lend Book";
    public static final String OPT_RETURNS = "Book Return";
    public static final String OPT_REPORTS = "Loan Reports"; // ← nuevo

    private final String[] librarianOptions = {
        OPT_BOOKS,
        OPT_USERS,
        OPT_LOANS,
        OPT_RETURNS,
        OPT_REPORTS // ← nuevo
    };

    private final CardLayout cardLayout;
    private final JPanel contentContainer;

    public MenuLibrarian() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200, 600));

        NavBar navbar = new NavBar("Librarian");

        cardLayout = new CardLayout();
        contentContainer = new JPanel(cardLayout);

        Sidebar sidebar = new Sidebar(librarianOptions, this::showContent);

        this.add(navbar, BorderLayout.NORTH);
        this.add(sidebar, BorderLayout.WEST);
        this.add(contentContainer, BorderLayout.CENTER);
    }

    public void addContentPanel(JPanel panel, String name) {
        contentContainer.add(panel, name);
    }

    public void showContent(String name) {
        cardLayout.show(contentContainer, name);
    }
}
