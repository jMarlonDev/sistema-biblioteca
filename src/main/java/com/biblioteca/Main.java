package com.biblioteca;

import java.sql.Connection;

import javax.swing.SwingUtilities;

import com.biblioteca.config.ConnectionDB;
import com.biblioteca.controller.LibrarianController;
import com.biblioteca.controller.LoginController;
import com.biblioteca.controller.RegisterController; // <- Añadido
import com.biblioteca.model.LibrarianModel;
import com.biblioteca.model.LoginModel;
import com.biblioteca.model.RegisterModel; // <- Añadido
import com.biblioteca.repository.AdministratorRepositoryImpl;
import com.biblioteca.repository.LibrarianRepositoryImpl;
import com.biblioteca.repository.UserRepositoryImpl;
import com.biblioteca.view.LibrarianManagementView;
import com.biblioteca.view.MainWindow; // <- Añadido

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            Connection connection = ConnectionDB.getConnection();

            AdministratorRepositoryImpl adminRepo = new AdministratorRepositoryImpl(connection);
            LibrarianRepositoryImpl librarianRepo = new LibrarianRepositoryImpl(connection);
            UserRepositoryImpl userRepo = new UserRepositoryImpl(connection);

            LoginModel loginModel = new LoginModel(adminRepo, librarianRepo, userRepo);
            RegisterModel registerModel = new RegisterModel(userRepo);

            LibrarianModel librarianModel = new LibrarianModel(librarianRepo);

            MainWindow mainWindow = new MainWindow();

            LibrarianManagementView librarianView = new LibrarianManagementView();

            new LibrarianController(librarianView, librarianModel);

            mainWindow.getMenuAdministrator().addContentPanel(librarianView, "Manage Librarians");

            new LoginController(
                    mainWindow.getFormLogin(),
                    loginModel,
                    mainWindow
            );

            new RegisterController(
                    mainWindow.getFormRegister(),
                    registerModel,
                    mainWindow
            );

            mainWindow.showView(MainWindow.VISTA_LOGIN);
        });
    }
}
