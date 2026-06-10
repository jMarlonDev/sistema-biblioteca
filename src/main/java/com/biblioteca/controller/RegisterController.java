package com.biblioteca.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.biblioteca.model.RegisterModel;
import com.biblioteca.view.FormRegister;
import com.biblioteca.view.MainWindow;

/**
 * Class: RegisterController
 *
 * Responsabilidad: Se encarga de gestionar el proceso de registro de nuevos
 * usuarios dentro del sistema y que los datos sean correctos
 *
 * Maneja la interacción entre la vista de registro y la navegación entre las
 * distintas pantallas de la aplicación Si ya un usuario se registro
 * correctamente puede cambiar a la vista de Login para iniciar sesión
 */
public class RegisterController {

    private final FormRegister view;
    private final RegisterModel model;
    private final MainWindow mainWindow;

    public RegisterController(FormRegister view, RegisterModel model, MainWindow mainWindow) {
        this.view = view;
        this.model = model;
        this.mainWindow = mainWindow;

        initListeners();
    }

    private void initListeners() {

        view.getBtnSubmit().addActionListener((e) -> {
            handleRegister();
        });

        view.getGoToLogin().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.clearFields();
                mainWindow.showView(MainWindow.VISTA_LOGIN);
            }
        });
    }

    private void handleRegister() {
        String name = view.getName();
        String email = view.getEmail();
        String password = view.getPassword();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            view.showStatusMessage("Please fill in all fields.", true);
            return;
        }

        if (password.length() < 6) {
            view.showStatusMessage("Password must be at least 6 characters.", true);
            return;
        }

        model.registerUser(name, email, password);

        boolean success = model.hasSuccessfulRegistration();

        view.showStatusMessage(model.getMessage(), !success);

        if (success) {
            view.clearFields();
            mainWindow.showView(MainWindow.VISTA_LOGIN);
        }
    }
}
