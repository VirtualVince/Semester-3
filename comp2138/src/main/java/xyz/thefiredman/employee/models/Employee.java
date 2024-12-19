package xyz.thefiredman.employee.models;

import java.io.Serializable;

public class Employee implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String department;
    private Payroll payroll;

    public Employee(int id, String name, String phone, String email, String department,
            double workHours, double hourlyRate, double bonus) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.payroll = new Payroll(workHours, hourlyRate, bonus);
    }

    public int getId() {
        return id;
    }

    // reassign id required for mutation of employee data
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public Payroll getPayroll() {
        return payroll;
    }

    public void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }

    @Override
    public String toString() {
        return String.format(
                "Employee{id=%d, name='%s', phone='%s', email='%s', department=%s, hoursWorked=%d, hourlyRate=%d, bonus=%d, payroll=%s}",
                id,
                name,
                phone,
                email,
                department,
                payroll.toString());
    }
}
