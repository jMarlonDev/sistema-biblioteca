package com.biblioteca.entity;

public class Report {

    private int idReport;
    private String reportDate;
    private int totalLoans;
    private int returnedBooks;
    private int overdueBooks;

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int v) {
        this.idReport = v;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String v) {
        this.reportDate = v;
    }

    public int getTotalLoans() {
        return totalLoans;
    }

    public void setTotalLoans(int v) {
        this.totalLoans = v;
    }

    public int getReturnedBooks() {
        return returnedBooks;
    }

    public void setReturnedBooks(int v) {
        this.returnedBooks = v;
    }

    public int getOverdueBooks() {
        return overdueBooks;
    }

    public void setOverdueBooks(int v) {
        this.overdueBooks = v;
    }
}
