package com.example.laibrary.ui.book;

public class DateDatabase {

    public String day, month, year, status="0";

    public DateDatabase() {
    }

    public DateDatabase(String day, String month, String year, String status) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.status = status;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
