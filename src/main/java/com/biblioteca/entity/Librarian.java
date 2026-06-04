package com.biblioteca.entity;

public class Librarian {

    private int idLibrarian;
    private String identification;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String password;

    public int getIdLibrarian() {
        return idLibrarian;
    }

    public void setIdLibrarian(int idLibrarian) {
        this.idLibrarian = idLibrarian;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
