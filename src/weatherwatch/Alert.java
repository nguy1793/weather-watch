package com.weatherwatch;

public class Alert {

    String date;
    String day;
    String alertText;

    public Alert(String date, String day, String alertText) {
        this.date = date;
        this.day = day;
        this.alertText = alertText;
    }

    public void printAlert() {
        System.out.println(alertText + " alert for " + day + ", " + date);
    }
}
