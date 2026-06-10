package com.biblioteca.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.biblioteca.model.LoginModel;
import com.biblioteca.view.FormLogin;
import com.biblioteca.view.MainWindow;

/**
 * Class: LoginController
 *
 * Responsabilidad: Gestionar los eventos de la interfaz que provienen del
 * formulario de Login
 *
 * ¿Que Hace?
 *
 * Recibe los datos ingresados en los campos de texto en la vista de FormLogin,
 * hace una validación de estos datos en la autenticación de usuarios usando el
 * LoginModel, gestiona la navegación entre las diferentes vistas mediante
 * MainWindow donde se muestra la vista dependiendo del rol que tenga el
 * usuario: Administrator, Librarian, User
 */
public class LoginController {

    private final FormLogin view;
    private final LoginModel model;
    private final MainWindow mainWindow;

    public LoginController(FormLogin view, LoginModel model, MainWindow mainWindow) {
        this.view = view;
        this.model = model;
        this.mainWindow = mainWindow;

        initListeners();
    }

    private void initListeners() {

        view.getBtnSubmit().addActionListener((e) -> {
            handleLogin();
        });

        view.getGoToRegister().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                view.clearFields();
                mainWindow.showView(MainWindow.VISTA_REGISTER);
            }

        });
    }

    private void handleLogin() {
        String email = view.getEmail();
        String password = view.getPassword();

        if (email.isEmpty() || password.isEmpty()) {
            view.showError("Please fill in all fields.");
            return;
        }

        model.validateUser(email, password);

        if (!model.isAuthenticated()) {
            view.showError(model.getErrorMessage());
            return;
        }

        view.clearFields(); // Limpiar todos los campos

        redirectByRole(model.getRoleDetected()); // Redirigir a la vista específica para el rol
    }

    private void redirectByRole(String role) {

        switch (role) {
            case "ADMIN":
                mainWindow.showView(MainWindow.VISTA_ADMIN);
                break;
            case "LIBRARIAN":
                mainWindow.showView(MainWindow.VISTA_LIBRARIAN);
                break;
            case "USER":
                mainWindow.showView(MainWindow.VISTA_USER);
                break;
            default:
                view.showError("Unknown role");
        }
    }
}
