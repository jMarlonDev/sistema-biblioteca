package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MenuAdministrator extends JPanel {

    private String[] adminOptions = {
        "Manage Librarians",
        "Manage Users",
        "Manage Loans",
        "Manage Returns",
        "Loan Reports",
        "Overdue Reports"
    };

    public MenuAdministrator() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200, 600));

        NavBar navbar = new NavBar("Administrator");
        Sidebar sidebar = new Sidebar(adminOptions);
        this.add(navbar, BorderLayout.NORTH);
        this.add(sidebar, BorderLayout.WEST);
    }

}
