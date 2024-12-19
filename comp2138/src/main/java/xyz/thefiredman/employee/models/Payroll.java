package xyz.thefiredman.employee.models;

import java.io.Serializable;

public class Payroll implements Serializable {
    private double workHours;
    private double hourlyRate;
    private double bonus;

    public Payroll(double workHours, double hourlyRate, double bonus) {
        this.workHours = workHours;
        this.hourlyRate = hourlyRate;
        this.bonus = bonus;
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double calculatePayroll() {
        return (workHours * hourlyRate) + bonus;
    }

    @Override
    public String toString() {
        return String.format(
                "Payroll{workHours=%.2f, hourlyRate=%.2f, bonus=%.2f, totalPayroll=%.2f}",
                workHours, hourlyRate, bonus, calculatePayroll());
    }
}
