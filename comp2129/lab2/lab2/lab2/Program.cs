// All the main execution lines of code

// Instantiate 2 employees sharon, fred
Employee sharon = new Employee(9000);
Employee fred = new Employee(2000);

// Output to the system console the id of sharon
Console.WriteLine(sharon.EmpId); // Even though this is a getter function, we use it with the dot operator and no brackets
Console.WriteLine(fred.EmpId); // Even though this is a getter function, we use it with the dot operator and no brackets

// To test the individual/interconnected deletion of memory space in Composition vs Aggregation
// **Optionally** review this doc: https://learn.microsoft.com/en-us/dotnet/csharp/programming-guide/classes-and-structs/finalizers

// Composition
Manager mrMing = new Manager("accounting");
Console.WriteLine("Completed the instantiation of the Manager object");
Console.WriteLine(mrMing.EnrollAsEmployee()); // Must call the method to instantiate the composed class's object
Console.WriteLine(mrMing.EnrollAsEmployee().EmpId);

// Aggregation
// Instantiate the Pond class 
Pond mississaugaPond = new Pond(new Duck(7)); // The aggregated object is instantiated with the object