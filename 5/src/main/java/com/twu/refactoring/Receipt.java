package com.twu.refactoring;

public class Receipt {

    private final Taxi taxi;

    public Receipt(Taxi taxi) {
        this.taxi = taxi;
    }

    public double getTotalCost() {
        double totalCost = 0;
        totalCost += Taxi.FIXED_CHARGE;
        int totalKms = taxi.getTotalKms();
        double peakTimeMultiple = taxi.isPeakTime() ? Taxi.PEAK_TIME_MULTIPLIER : Taxi.OFF_PEAK_MULTIPLIER;
        totalCost = getTotalCost(totalCost, totalKms, peakTimeMultiple);
        return totalCost * (1 + Taxi.SALES_TAX_RATE);
    }

    private double getTotalCost(double totalCost, int totalKms, double peakTimeMultiple) {
        if(taxi.isAirConditioned()) {
            totalCost = getTotalCost(totalCost, totalKms, peakTimeMultiple, Taxi.PRE_RATE_CHANGE_AC_RATE, Taxi.POST_RATE_CHANGE_AC_RATE);
        } else {
            totalCost = getTotalCost(totalCost, totalKms, peakTimeMultiple, Taxi.PRE_RATE_CHANGE_NON_AC_RATE, Taxi.POST_RATE_CHANGE_NON_AC_RATE);
        }
        return totalCost;
    }

    private double getTotalCost(double totalCost, int totalKms, double peakTimeMultiple,int num1,int num2) {
        totalCost += Math.min(Taxi.RATE_CHANGE_DISTANCE, totalKms) * num1 * peakTimeMultiple;
        totalCost += Math.max(0, totalKms - Taxi.RATE_CHANGE_DISTANCE) * num2 * peakTimeMultiple;
        return totalCost;
    }
}
