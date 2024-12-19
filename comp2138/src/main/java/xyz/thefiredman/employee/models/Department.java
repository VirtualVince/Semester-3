package xyz.thefiredman.employee.models;

import java.util.ArrayList;

public class Department {
    private ArrayList<Integer> employees;
    private String name;

    public Department(String name) {
        this.employees = new ArrayList<>();
        this.name = name;
    }

    public void add(Employee emloyee) {
        this.employees.add(emloyee.getId());
    }

    public void remove(Employee emloyee) {
        this.employees.remove(emloyee.getId());
    }

    public boolean isEmpty() {
        return this.employees.isEmpty();
    }

    public String getName() {
        return name;
    }

    public double computeAveragePayroll(ArrayList<Employee> employeeData) {
        double totalPay = 0;

        if (!this.employees.isEmpty()) {
            for (Employee employee : employeeData) {
                if (!employees.contains(employee.getId())) {
                    continue;
                }

                totalPay += employee.getPayroll().calculatePayroll();
            }

            return totalPay / employeeData.size();
        } else {
            return 0;
        }
    }
}
