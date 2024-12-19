// lab3.cs
// Practice: Inheritance

using System.Text.Json.Serialization;

namespace lab3;

public class Person
{
    public string Fullname;
    private int _sinNumber;
    protected string[] friends;

    public Person(string fullname, int sinNumber, string[] friendsP)
    {
        Fullname = fullname;
        _sinNumber = sinNumber; // Validation or cleanup for the private and protected
                                // values before setting them directly into the object's
                                // properties
        friends = friendsP;     
    }

    protected void Walk()
    {
        Console.WriteLine("The person is walking");
    }
}