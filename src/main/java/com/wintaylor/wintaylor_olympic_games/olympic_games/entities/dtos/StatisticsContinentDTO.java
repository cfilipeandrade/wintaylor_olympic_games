package com.wintaylor.wintaylor_olympic_games.olympic_games.entities.dtos;

import java.util.Objects;

public class StatisticsContinentDTO {

    private String continent;
    private double ageMedia;

    public StatisticsContinentDTO() {
    }

    public StatisticsContinentDTO(String continent, double ageMedia) {
        this.continent = continent;
        this.ageMedia = ageMedia;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public double getAgeMedia() {
        return ageMedia;
    }

    public void setAgeMedia(double ageMedia) {
        this.ageMedia = ageMedia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatisticsContinentDTO)) return false;
        StatisticsContinentDTO that = (StatisticsContinentDTO) o;
        return Double.compare(getAgeMedia(), that.getAgeMedia()) == 0 && Objects.equals(getContinent(), that.getContinent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContinent(), getAgeMedia());
    }

    @Override
    public String toString() {
        return "StatisticsContinentDTO{" +
                "continent='" + continent + '\'' +
                ", ageMedia=" + ageMedia +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
