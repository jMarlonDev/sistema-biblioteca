package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MenuLibrarian extends JPanel {

    private String[] librarianOptions = {
        "Check Book Availability",
        "Lend Book",
        "Book Return"
    };

    public MenuLibrarian() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200, 600));

        NavBar navbar = new NavBar("Librarian");
        // Sidebar sidebar = new Sidebar(librarianOptions);
        this.add(navbar, BorderLayout.NORTH);
        // this.add(sidebar, BorderLayout.WEST);
    }

}
