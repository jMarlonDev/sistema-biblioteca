package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class UserMenu extends JPanel {

    private String[] userOptions = {
        "Check Book Availability",
        "Request Borrow Book",
        "Return Books"
    };

    public UserMenu() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200, 600));

        NavBar navbar = new NavBar("User");
        Sidebar sidebar = new Sidebar(userOptions);

        this.add(navbar, BorderLayout.NORTH);
        this.add(sidebar, BorderLayout.WEST);
    }

}
