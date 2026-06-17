package com.biblioteca.entity;

public class Loan {

    private int idLoan;
    private int idUser;
    private int idBook;
    private String userEmail;
    private String bookTitle;
    private String loanDate;
    private String returnDate;
    private String state;
    private int daysActive;

    public int getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(int v) {
        this.idLoan = v;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int v) {
        this.idUser = v;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int v) {
        this.idBook = v;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String v) {
        this.userEmail = v;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String v) {
        this.bookTitle = v;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String v) {
        this.loanDate = v;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String v) {
        this.returnDate = v;
    }

    public String getState() {
        return state;
    }

    public void setState(String v) {
        this.state = v;
    }

    public int getDaysActive() {
        return daysActive;
    }

    public void setDaysActive(int v) {
        this.daysActive = v;
    }
}
