package com.wintaylor.wintaylor_olympic_games.olympic_games.entities.dtos;

import java.util.Objects;

public class AthleteBrazilFootballDTO {

    private String continent;
    private String country;
    private double height;
    private double weight;
    private double BMI;
    private double age;
    private String gender;
    private String sport;

    public AthleteBrazilFootballDTO() {
    }

    public AthleteBrazilFootballDTO(String continent, String country, double height, double weight, double BMI, double age, String gender, String sport) {
        this.continent = continent;
        this.country = country;
        this.height = height;
        this.weight = weight;
        this.BMI = BMI;
        this.age = age;
        this.gender = gender;
        this.sport = sport;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AthleteBrazilFootballDTO)) return false;
        AthleteBrazilFootballDTO that = (AthleteBrazilFootballDTO) o;
        return Double.compare(getHeight(), that.getHeight()) == 0 && Double.compare(getWeight(), that.getWeight()) == 0 && Double.compare(getBMI(), that.getBMI()) == 0 && Double.compare(getAge(), that.getAge()) == 0 && Objects.equals(getContinent(), that.getContinent()) && Objects.equals(getCountry(), that.getCountry()) && Objects.equals(getGender(), that.getGender()) && Objects.equals(getSport(), that.getSport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContinent(), getCountry(), getHeight(), getWeight(), getBMI(), getAge(), getGender(), getSport());
    }

    @Override
    public String toString() {
        return "AthleteBrazilFootballDTO{" +
                "continent='" + continent + '\'' +
                ", country='" + country + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", BMI=" + BMI +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", sport='" + sport + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
