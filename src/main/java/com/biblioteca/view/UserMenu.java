package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class UserMenu extends JPanel {

    public static final String OPT_AVAILABILITY = "Check Book Availability";
    public static final String OPT_BORROW = "Request Borrow Book";
    public static final String OPT_RETURN = "Return Books";

    private final String[] userOptions = {
        OPT_AVAILABILITY,
        OPT_BORROW,
        OPT_RETURN
    };

    private final CardLayout cardLayout;
    private final JPanel contentContainer;

    public UserMenu() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200, 600));

        NavBar navbar = new NavBar("User");

        cardLayout = new CardLayout();
        contentContainer = new JPanel(cardLayout);

        Sidebar sidebar = new Sidebar(userOptions, this::showContent);

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
