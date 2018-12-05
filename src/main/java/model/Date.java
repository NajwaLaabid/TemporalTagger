package model;

import static util.Constants.*;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date() {
        this.day = DEFAULT_COMPONENT;
        this.month = DEFAULT_COMPONENT;
        this.year = DEFAULT_COMPONENT;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean empty() {
        return day == -1 && month == -1 && year == -1;
    }

    public Date copy(){
        Date date = new Date();

        date.setYear(year);
        date.setMonth(month);
        date.setDay(day);

        return date;
    }

}
