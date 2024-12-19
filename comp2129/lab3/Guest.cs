namespace lab3; 
// Namespace is used to prevent naming conflicts 
// in case there are similarily named classes, varaibles, functions
// across multiple files
// if we're using namespaces, then we must use a main method so that the top-level statements will not conflict
// if using "using", then we can get away without the main method

public class Guest : Person
{
    public int TotalRoomsBooked;

    public string CreateBooking()
    {
        Random rnd = new Random();
        int randomNumber = rnd.Next(1, 1000);
        Console.WriteLine("Booking a guest " + randomNumber);
        return "Booking a guest " + randomNumber;
    }

    public Guest(int totalRoomsBooked, string fullname, int sinNumber, string[] friendsP) : base(fullname, sinNumber, friendsP)
    {
        TotalRoomsBooked = totalRoomsBooked;
    }
}