package cn.star.domain;

public class AverageCapital {
    private int numMonth;//期数
    private double monthRate;//每月本息
    private double benjin;//本金
    private double interest;//利息

    public double getBenjin() {
        return benjin;
    }

    public void setBenjin(double benjin) {
        this.benjin = benjin;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getNumMonth() {
        return numMonth;
    }

    public void setNumMonth(int numMonth) {
        this.numMonth = numMonth;
    }

    public double getMonthRate() {
        return monthRate;
    }

    public void setMonthRate(double monthRate) {
        this.monthRate = monthRate;
    }

}
