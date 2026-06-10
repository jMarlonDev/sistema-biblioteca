package com.biblioteca.model;

import com.biblioteca.entity.Administrator;
import com.biblioteca.entity.Librarian;
import com.biblioteca.entity.User;
import com.biblioteca.repository.AdministratorRepository;
import com.biblioteca.repository.LibrarianRepository;
import com.biblioteca.repository.UserRepository;

/**
 * Class: LoginModel
 *
 * Responsabilidad: Este modelo se encarga de gestionar el proceso de
 * autenticación de usuarios dentro del sistema de biblioteca verificando si los
 * usuarios que están intentando iniciar sesión existen en la base de datos
 *
 * Se encarga de validar credenciales consultando los distintos repositorios:
 * AdministratorRepo, LibrarianRepo, UserRepo, identificar el rol asociado a la
 * cuenta que inicio sesión
 */
public class LoginModel {

    private Object loggedUser;
    private String role;
    private String errorMessage;
    private boolean authenticated;

    private final AdministratorRepository adminRepo;
    private final LibrarianRepository librarianRepo;
    private final UserRepository userRepo;

    public LoginModel(AdministratorRepository adminRepo, LibrarianRepository librarianRepo, UserRepository userRepo) {
        this.adminRepo = adminRepo;
        this.librarianRepo = librarianRepo;
        this.userRepo = userRepo;

        this.authenticated = false;
        this.errorMessage = "";
    }

    public void validateUser(String email, String password) {

        Administrator admin = adminRepo.findByEmail(email);

        if (admin != null && admin.getPassword().equals(password)) {

            this.loggedUser = admin;
            this.role = "ADMIN";
            this.authenticated = true;
            this.errorMessage = "";

            return;
        }

        Librarian librarian = librarianRepo.findByEmail(email);

        if (librarian != null && librarian.getPassword().equals(password)) {

            this.loggedUser = librarian;
            this.role = "LIBRARIAN";
            this.authenticated = true;
            this.errorMessage = "";

            return;
        }

        User user = userRepo.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {

            this.loggedUser = user;
            this.role = "USER";
            this.authenticated = true;
            this.errorMessage = "";

            return;
        }

        this.authenticated = false;
        this.errorMessage = "Email or password incorrect";
        this.role = "";
        this.loggedUser = null;
    }

    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public String getRoleDetected() {
        return this.role;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public Object getLoggedUser() {
        return this.loggedUser;
    }
}
