/*
 * Lab 2: getters and setters and initialize
 */

// Define the class Employee
public class Employee
{
    // Implement the constructor
    public Employee(int empId)
    {
        Console.WriteLine("Creating the Employee object");
        // Initialize the id with the parameter value
        _empId = empId;
    }
    
    // Finalizer 
    ~Employee()
    {
        Console.WriteLine("Destroying the Employee object");
    }
    
    // Create a private property: empID
    private int _empId;

    // Make a public getter and setter for it
    public int EmpId
    {
        // For a private property, we should make sure to modify/hide the value before returning it
        get => _empId - 1000;
        // For a private property, we should make sure to validiate the value before setting it
        set => _empId = (value != 0)? value : -1;
    }
}


