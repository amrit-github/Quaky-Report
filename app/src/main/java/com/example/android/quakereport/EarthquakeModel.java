package com.example.android.quakereport;

public class EarthquakeModel {
    private String place,date;
    private double magnitude;

    public EarthquakeModel(String place, double magnitude, String date) {
        this.place = place;
        this.magnitude = magnitude;
        this.date = date;

    }

    public EarthquakeModel() {
    }

    public String getPlace() {
        return place;
    }

    public double getMagnitude() {
        return magnitude;
    }


    public String getDate() {
        return date;
    }
}
