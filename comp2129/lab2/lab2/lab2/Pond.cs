// Aggregation: The ducks can exist without the pond

// Define the class that will contain the aggregated objects
public class Pond
{
    // Set one of the properties of the Pond class to be of type Duck
    public Duck D1;
    
    public Pond(Duck d1)
    {
        D1 = d1;
        Console.WriteLine(D1.BeakLength);
    }
}
