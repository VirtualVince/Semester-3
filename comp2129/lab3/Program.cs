namespace lab3;

public class Program
{
    
    // If creating an ASP.net application, the main() method may be optional
    // Required for C# terminal application that uses namespaces
    public static void Main(string[] args) {
        // Instantiate a new object from the Guest class
        Guest guestSaraBackman = new Guest(3, "Sara Backman", 123123123, ["yalda", "tommy", "han"]);
        guestSaraBackman.CreateBooking();
        // Call method
    }
}