package com.wintaylor.wintaylor_olympic_games.olympic_games.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Athlete {

    private String continent;
    private String country;
    private double height;
    private double weight;
    private double BMI;
    private double age;
    private String gender;
    private String sport;

    @JsonCreator
    public Athlete(@JsonProperty("continent") String continent,
                   @JsonProperty("country") String country,
                   @JsonProperty("height") double height,
                   @JsonProperty("weight") double weight,
                   @JsonProperty("BMI") double BMI,
                   @JsonProperty("age") double age,
                   @JsonProperty("gender") String gender,
                   @JsonProperty("sport") String sport) {
        this.continent = continent;
        this.country = country;
        this.height = height;
        this.weight = weight;
        this.BMI = BMI;
        this.age = age;
        this.gender = gender;
        this.sport = sport;
    }


    public Athlete() {
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
        if (!(o instanceof Athlete)) return false;
        Athlete athlete = (Athlete) o;
        return Double.compare(getHeight(), athlete.getHeight()) == 0 && Double.compare(getWeight(), athlete.getWeight()) == 0 && Double.compare(getBMI(), athlete.getBMI()) == 0 && Double.compare(getAge(), athlete.getAge()) == 0 && Objects.equals(getContinent(), athlete.getContinent()) && Objects.equals(getCountry(), athlete.getCountry()) && Objects.equals(getGender(), athlete.getGender()) && Objects.equals(getSport(), athlete.getSport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContinent(), getCountry(), getHeight(), getWeight(), getBMI(), getAge(), getGender(), getSport());
    }

    @Override
    public String toString() {
        return "Athlete{" +
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
