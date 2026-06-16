package com.biblioteca;

import java.sql.Connection;

import javax.swing.SwingUtilities;

import com.biblioteca.config.ConnectionDB;
import com.biblioteca.controller.LibrarianController;
import com.biblioteca.controller.LoanController;
import com.biblioteca.controller.LoginController;
import com.biblioteca.controller.RegisterController;
import com.biblioteca.controller.UserController;
import com.biblioteca.model.LibrarianModel;
import com.biblioteca.model.LoanModel;
import com.biblioteca.model.LoginModel;
import com.biblioteca.model.RegisterModel;
import com.biblioteca.model.UserModel;
import com.biblioteca.repository.AdministratorRepositoryImpl;
import com.biblioteca.repository.BookRepositoryImpl;
import com.biblioteca.repository.LibrarianRepositoryImpl;
import com.biblioteca.repository.LoanRepositoryImpl;
import com.biblioteca.repository.UserRepositoryImpl;
import com.biblioteca.view.LibrarianManagementView;
import com.biblioteca.view.LoanManagementView;
import com.biblioteca.view.MainWindow;
import com.biblioteca.view.ReturnManagementView;
import com.biblioteca.view.UserManagementView;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            Connection connection = ConnectionDB.getConnection();

            AdministratorRepositoryImpl adminRepo = new AdministratorRepositoryImpl(connection);
            LibrarianRepositoryImpl librarianRepo = new LibrarianRepositoryImpl(connection);
            UserRepositoryImpl userRepo = new UserRepositoryImpl(connection);
            BookRepositoryImpl bookRepo = new BookRepositoryImpl(connection);
            LoanRepositoryImpl loanRepo = new LoanRepositoryImpl(connection);

            LoginModel loginModel = new LoginModel(adminRepo, librarianRepo, userRepo);
            RegisterModel registerModel = new RegisterModel(userRepo);

            LibrarianModel librarianModel = new LibrarianModel(librarianRepo);
            UserModel userModel = new UserModel(userRepo);
            LoanModel loanModel = new LoanModel(loanRepo, bookRepo, userRepo);

            MainWindow mainWindow = new MainWindow();

            LibrarianManagementView librarianView = new LibrarianManagementView();
            new LibrarianController(librarianView, librarianModel);
            mainWindow.getMenuAdministrator().addContentPanel(librarianView, "Manage Librarians");

            UserManagementView userView = new UserManagementView();
            new UserController(userView, userModel);
            mainWindow.getMenuAdministrator().addContentPanel(userView, "Manage Users");

            LoanManagementView loanView = new LoanManagementView();
            ReturnManagementView returnView = new ReturnManagementView();
            new LoanController(loanView, returnView, loanModel);
            mainWindow.getMenuAdministrator().addContentPanel(loanView, "Manage Loans");
            mainWindow.getMenuAdministrator().addContentPanel(returnView, "Manage Returns");

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
