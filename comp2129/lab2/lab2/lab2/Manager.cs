/* Manager is also an Employee
 Therefore, the Employee will be a part of the composition of the Manager
 If the employee part of the person dies, the manager part of the person also dies
 
 RAM space
 -----------------------------
 Manager
 ------------------------
 
  Employee
 ----------
!           !
 ----------
 
 
 ------------------------
 
 */

public class Manager
{
    protected string DepType;
    
    public Manager(string depType)
    {
        Console.WriteLine("Creating the Manager object");
        DepType = depType;
    }

    // Finalizer 
    ~Manager()
    {
        Console.WriteLine("Destroying the Manager object");
    }
    public Employee EnrollAsEmployee()
    {
        // Instantiate the Employee object so that it is part of the Manager class
        Employee ming = new Employee(5000);
        return ming;
    }
}
