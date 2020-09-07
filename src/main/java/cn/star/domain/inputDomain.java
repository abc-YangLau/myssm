package cn.star.domain;

public class inputDomain {
    private double invest; // 本金
    private int month;
    private double yearRate; // 年利率

    public double getInvest() {
        return invest;
    }

    public void setInvest(double invest) {
        this.invest = invest * 10000;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month * 12;
    }

    public double getYearRate() {
        return yearRate;
    }

    public void setYearRate(double yearRate) {
        this.yearRate = yearRate;
    }

}
