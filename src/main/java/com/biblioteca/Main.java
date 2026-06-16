package com.biblioteca;

import java.sql.Connection;

import javax.swing.SwingUtilities;

import com.biblioteca.config.ConnectionDB;
import com.biblioteca.controller.BookController;
import com.biblioteca.controller.LibrarianController;
import com.biblioteca.controller.LoanController;
import com.biblioteca.controller.LoginController;
import com.biblioteca.controller.RegisterController;
import com.biblioteca.controller.ReportController;
import com.biblioteca.controller.UserController;
import com.biblioteca.model.BookModel;
import com.biblioteca.model.LibrarianModel;
import com.biblioteca.model.LoanModel;
import com.biblioteca.model.LoginModel;
import com.biblioteca.model.RegisterModel;
import com.biblioteca.model.ReportModel;
import com.biblioteca.model.UserModel;
import com.biblioteca.repository.AdministratorRepositoryImpl;
import com.biblioteca.repository.BookRepositoryImpl;
import com.biblioteca.repository.LibrarianRepositoryImpl;
import com.biblioteca.repository.LoanRepositoryImpl;
import com.biblioteca.repository.ReportRepositoryImpl;
import com.biblioteca.repository.UserRepositoryImpl;
import com.biblioteca.view.BookManagementView;
import com.biblioteca.view.LibrarianManagementView;
import com.biblioteca.view.LoanManagementView;
import com.biblioteca.view.MainWindow;
import com.biblioteca.view.MenuLibrarian;
import com.biblioteca.view.ReportManagementView;
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
            ReportRepositoryImpl reportRepo = new ReportRepositoryImpl(connection);

            LoginModel loginModel = new LoginModel(adminRepo, librarianRepo, userRepo);
            RegisterModel registerModel = new RegisterModel(userRepo);

            LibrarianModel librarianModel = new LibrarianModel(librarianRepo);
            UserModel userModel = new UserModel(userRepo);
            BookModel bookModel = new BookModel(bookRepo);
            LoanModel loanModel = new LoanModel(loanRepo, bookRepo, userRepo);
            ReportModel reportModel = new ReportModel(reportRepo);

            MainWindow mainWindow = new MainWindow();

            LibrarianManagementView librarianView = new LibrarianManagementView();
            new LibrarianController(librarianView, librarianModel);
            mainWindow.getMenuAdministrator().addContentPanel(librarianView, "Manage Librarians");

            UserManagementView adminUserView = new UserManagementView();
            new UserController(adminUserView, userModel);
            mainWindow.getMenuAdministrator().addContentPanel(adminUserView, "Manage Users");

            LoanManagementView adminLoanView = new LoanManagementView();
            ReturnManagementView adminReturnView = new ReturnManagementView();
            LoanController adminLoanController = new LoanController(adminLoanView, adminReturnView, loanModel);
            mainWindow.getMenuAdministrator().addContentPanel(adminLoanView, "Manage Loans");
            mainWindow.getMenuAdministrator().addContentPanel(adminReturnView, "Manage Returns");

            ReportManagementView adminReportView = new ReportManagementView();
            ReportController adminReportController = new ReportController(adminReportView, reportModel);
            mainWindow.getMenuAdministrator().addContentPanel(adminReportView, "System Reports");
            adminLoanController.setReportController(adminReportController);

            BookManagementView bookView = new BookManagementView();
            new BookController(bookView, bookModel);
            mainWindow.getMenuLibrarian().addContentPanel(bookView, MenuLibrarian.OPT_BOOKS);

            UserManagementView librarianUserView = new UserManagementView();
            new UserController(librarianUserView, userModel);
            mainWindow.getMenuLibrarian().addContentPanel(librarianUserView, MenuLibrarian.OPT_USERS);

            LoanManagementView libLoanView = new LoanManagementView();
            ReturnManagementView libReturnView = new ReturnManagementView();
            LoanController libLoanController = new LoanController(libLoanView, libReturnView, loanModel);
            mainWindow.getMenuLibrarian().addContentPanel(libLoanView, MenuLibrarian.OPT_LOANS);
            mainWindow.getMenuLibrarian().addContentPanel(libReturnView, MenuLibrarian.OPT_RETURNS);

            ReportManagementView libReportView = new ReportManagementView();
            ReportController libReportController = new ReportController(libReportView, reportModel);
            mainWindow.getMenuLibrarian().addContentPanel(libReportView, MenuLibrarian.OPT_REPORTS);

            libLoanController.setReportController(libReportController);

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
