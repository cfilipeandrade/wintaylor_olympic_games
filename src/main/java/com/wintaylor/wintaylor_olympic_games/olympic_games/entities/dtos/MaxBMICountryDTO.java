package com.wintaylor.wintaylor_olympic_games.olympic_games.entities.dtos;

public class MaxBMICountryDTO {
    private String country;
    private double averageBMI;

    public MaxBMICountryDTO(String country, double averageBMI) {
        this.country = country;
        this.averageBMI = averageBMI;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getAverageBMI() {
        return averageBMI;
    }

    public void setAverageBMI(double averageBMI) {
        this.averageBMI = averageBMI;
    }
}
