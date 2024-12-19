package xyz.thefiredman.employee;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import xyz.thefiredman.employee.models.Department;
import xyz.thefiredman.employee.models.Employee;

public class EmployeeData {
    private ArrayList<Employee> employees = new ArrayList<>();
    // map of department name to department data
    private HashMap<String, Department> departments = new HashMap<>();
    private final File employeeData;

    public EmployeeData() {
        // load employee.data file or create it
        this.employeeData = Resources.initFS("employee.data");

        // load employee serilized data
        try (FileInputStream fileStream = new FileInputStream(employeeData)) {
            Object data = new ObjectInputStream(fileStream).readObject();
            if (data instanceof ArrayList) {
                this.employees = (ArrayList<Employee>) data;
            }

            for (Employee e : this.employees) {
                addDepartment(e);
            }
        } catch (EOFException e) {
            // perfectly ok meaning there is no data
        } catch (IOException e) {
            Resources.crashFS(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Employee data corrupt. Assuming empty.");
        }
    }

    // adds an employee to a department
    // and creates a new one if needed
    public void addDepartment(Employee employee) {
        String departmentId = employee.getDepartment();

        if (this.departments.containsKey(departmentId)) {
            this.departments.get(departmentId).add(employee);
        } else {
            Department department = new Department(departmentId);
            department.add(employee);
            this.departments.put(departmentId, department);
        }
    }

    // removes an employee to a department
    // and removes a old one if needed
    public void removeDepartment(Employee employee) {
        String departmentId = employee.getDepartment();

        if (this.departments.containsKey(departmentId)) {
            this.departments.get(departmentId).remove(employee);

            // remove the department if there is no one in it
            if (this.departments.get(departmentId).isEmpty()) {
                this.departments.remove(departmentId);
            }
        }
    }

    public void removeEmployee(Employee employee) {
        this.getEmployees().remove(employee);
        this.removeDepartment(employee);

        // reassign id's to ensure they accend
        for (int i = 0; i < this.getEmployees().size(); i++) {
            this.getEmployees().get(i).setId(i);
        }

        this.saveEmployees();
    }

    // save employee serilized data
    public void saveEmployees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.employeeData))) {
            oos.writeObject(this.employees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public HashMap<String, Department> getDepartments() {
        return departments;
    }
}
