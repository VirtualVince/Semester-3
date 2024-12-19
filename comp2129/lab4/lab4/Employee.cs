// Practicing: Partial classes - a class written across multiple code files
// Often used for User Interface portions
namespace lab4;

public partial class Employee
{
 public int Id { get; set; }

 // Define the constructor
 public Employee(int id)
 {
  Id = id;
 }

 public void DoWork()
 {
  Console.WriteLine("Working...");
 }
}