namespace lab5;

public class CoffeeShop
{
    protected int NumEmployees;
    private bool registered;
    public string NameOfShop;

    // Define a constructor
    public CoffeeShop(int numEmployees, bool registered, string nameOfShop)
    {
        NumEmployees = numEmployees;
        this.registered = registered;
        NameOfShop = nameOfShop;
        Console.WriteLine("Num employees = " + NumEmployees + " registered = " + registered + " Name Of Shop: " + NameOfShop);
    }

    // Overload the constructor
    public CoffeeShop(bool registered, string nameOfShop)
    {
        NumEmployees = 1; // e.g. First day of opening the coffee shop
        this.registered = registered;
        NameOfShop = nameOfShop;
        Console.WriteLine("Num employees = " + NumEmployees + " registered = " + registered + " Name Of Shop: " + NameOfShop);
    }
}
