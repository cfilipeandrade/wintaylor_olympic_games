package com.wintaylor.wintaylor_olympic_games.olympic_games.entities.dtos;

import java.util.Objects;

public class StatisticsDTO {

    int maleCount = 0;
    int femaleCount = 0;
    double totalAge = 0;

    public StatisticsDTO() {
    }

    public StatisticsDTO(int maleCount, int femaleCount, double totalAge) {
        this.maleCount = maleCount;
        this.femaleCount = femaleCount;
        this.totalAge = totalAge;
    }

    public int getMaleCount() {
        return maleCount;
    }

    public void setMaleCount(int maleCount) {
        this.maleCount = maleCount;
    }

    public int getFemaleCount() {
        return femaleCount;
    }

    public void setFemaleCount(int femaleCount) {
        this.femaleCount = femaleCount;
    }

    public double getTotalAge() {
        return totalAge;
    }

    public void setTotalAge(double totalAge) {
        this.totalAge = totalAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatisticsDTO)) return false;
        StatisticsDTO that = (StatisticsDTO) o;
        return getMaleCount() == that.getMaleCount() && getFemaleCount() == that.getFemaleCount() && Double.compare(getTotalAge(), that.getTotalAge()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaleCount(), getFemaleCount(), getTotalAge());
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "maleCount=" + maleCount +
                ", femaleCount=" + femaleCount +
                ", totalAge=" + totalAge +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
