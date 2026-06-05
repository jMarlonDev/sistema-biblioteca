package com.biblioteca.entity;

public class Report {

    private int idReport;
    private String reportDate;      // Formato: "2026-06-05"
    private int totalLoans;         // Total de préstamos registrados
    private int returnedBooks;      // Libros devueltos
    private int overdueBooks;       // Libros en mora

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public int getTotalLoans() {
        return totalLoans;
    }

    public void setTotalLoans(int totalLoans) {
        this.totalLoans = totalLoans;
    }

    public int getReturnedBooks() {
        return returnedBooks;
    }

    public void setReturnedBooks(int returnedBooks) {
        this.returnedBooks = returnedBooks;
    }

    public int getOverdueBooks() {
        return overdueBooks;
    }

    public void setOverdueBooks(int overdueBooks) {
        this.overdueBooks = overdueBooks;
    }
}
