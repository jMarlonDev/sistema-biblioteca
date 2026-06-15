package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MenuAdministrator extends JPanel {

    private String[] adminOptions = {
        "Manage Librarians", "Manage Users", "Manage Loans",
        "Manage Returns", "Loan Reports", "Overdue Reports"
    };

    private final CardLayout contentLayout;
    private final JPanel contentContainer;

    public MenuAdministrator() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200, 600));

        NavBar navbar = new NavBar("Administrator");

        contentLayout = new CardLayout();
        contentContainer = new JPanel(contentLayout);

        Sidebar sidebar = new Sidebar(adminOptions, this::showContent);

        this.add(navbar, BorderLayout.NORTH);
        this.add(sidebar, BorderLayout.WEST);
        this.add(contentContainer, BorderLayout.CENTER);
    }

    public void addContentPanel(JPanel panel, String name) {
        contentContainer.add(panel, name);
    }

    public void showContent(String name) {
        contentLayout.show(contentContainer, name);
    }
}
