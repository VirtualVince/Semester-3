/*
 * lab1a.cs
 * Classes, Objects, Constructors, Initialization, Encapsulation, Required keyword, static keyword
 */

Console.WriteLine("Hello, World!");

// Instantiate the Plant class to create a new object called bamboo
Plant bamboo = new Plant(4.5, "oval"){ Id = 1001 };
Console.WriteLine(bamboo);
Console.WriteLine(bamboo.StemLength);
// Console.WriteLine(bamboo._leafShape); // Cannot do this, because it is only accessible from within the class definition
Console.WriteLine(bamboo.GetStemLength());
Console.WriteLine(bamboo.GetLeafShape());
// bamboo.CheckIsAlive(); // We cannot call a static method on an instance
Console.WriteLine(Plant.CheckIsAlive());


// Instatiate another Plant object called aloevera with 1.3 stem length and round leaf shape
Plant aloevera = new Plant(1.3, "round"){ Id = 3122 };
Console.WriteLine(aloevera.GetLeafShape());
Console.WriteLine(aloevera.Id);

// Define a class called Plant this will be instantiatable from the main execution
public class Plant {
 // Declare a required property, accessible from anywhere, called Id
 public required int Id { get; set; }

 // Declare a property named StemLength accessible from anywhere
 public double StemLength;
 
 // Declare a property named leafShape accessible from the class itself
 private string _leafShape;
 
 // Define the constructor for this class
 // overwriting the StemLength based on the param passed in
 public Plant(double stemLength, string leafShape){
  StemLength = stemLength;
  _leafShape = leafShape;
 }
 
 // Define a method that will print the stem length to the console accessible from the main execution
 // Not as necessary to create a getter for a public property
 // But can do it for uniformity
 public double GetStemLength() {
  return StemLength;
 }

 // Define a setter method, it will return void
 public void SetStemLength(double stemLength) {
  StemLength = stemLength;
 }
 
 // Define a method that will print the leaf shape to the console accessible from the main execution
 // General rule: Give private and protected properties a getter method, so they can be accessed from the main execution, but under your control 
 // potentially with some validation added
 public string GetLeafShape() {
  // We have much more control over how the property is accessed within the getter
  return _leafShape;
 }
 
 // Define a static method named checkIsAlive
 // A static method is a method that is defined for the __entire class____
 public static bool CheckIsAlive() {
  Console.WriteLine("The plant is alive");
  return true;
 }
}

