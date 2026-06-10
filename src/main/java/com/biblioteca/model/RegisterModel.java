package com.biblioteca.model;

import com.biblioteca.entity.User;
import com.biblioteca.repository.UserRepository;

/**
 * Class: RegisterModel
 *
 * Responsabilidad: Este modelo se encarga de gestionar el registro de nuevos
 * usuarios dentro del sistema
 *
 * Lo que hace es validar la información se que obtiene de los campos de texto y
 * verificar en la base de datos si los datos que se ingresaron ya existen o son
 * incorrectos, crear nuevos usuarios en la base de datos mediante el
 * repositorio correspondiente
 */
public class RegisterModel {

    private String message;
    private boolean successfulRegistration;
    private final UserRepository userRepo;

    public RegisterModel(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void registerUser(String name, String email, String password) {

        if (name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {

            this.message = "Error: All fields are required";
            this.successfulRegistration = false;

            return;
        }

        if (userRepo.existsByEmail(email)) {
            this.message = "Error: Email already registered";
            this.successfulRegistration = false;
            return;
        }

        User newUser = new User();

        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        userRepo.save(newUser);

        this.message = "Registration successful! You can now login";
        this.successfulRegistration = true;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean hasSuccessfulRegistration() {
        return this.successfulRegistration;
    }
}
