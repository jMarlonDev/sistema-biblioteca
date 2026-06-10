package com.biblioteca;

import java.sql.Connection;

import javax.swing.SwingUtilities;

import com.biblioteca.config.ConnectionDB;
import com.biblioteca.controller.LoginController;
import com.biblioteca.controller.RegisterController;
import com.biblioteca.model.LoginModel;
import com.biblioteca.model.RegisterModel;
import com.biblioteca.repository.AdministratorRepositoryImpl;
import com.biblioteca.repository.LibrarianRepositoryImpl;
import com.biblioteca.repository.UserRepositoryImpl;
import com.biblioteca.view.MainWindow;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            Connection connection = ConnectionDB.getConnection();

            AdministratorRepositoryImpl adminRepo = new AdministratorRepositoryImpl(connection);
            LibrarianRepositoryImpl librarianRepo = new LibrarianRepositoryImpl(connection);
            UserRepositoryImpl userRepo = new UserRepositoryImpl(connection);

            LoginModel loginModel = new LoginModel(adminRepo, librarianRepo, userRepo);
            RegisterModel registerModel = new RegisterModel(userRepo);

            MainWindow mainWindow = new MainWindow();

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
