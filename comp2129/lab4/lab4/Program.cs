namespace lab4;

public partial class Employee
{
    public void GoToLunch()
    {
        Console.WriteLine("Eating...");
    }
}

public class Program
{
    static void Main(string[] args)
    {
        // Run the main execution
        Employee emp1 = new Employee(35);
        emp1.GoToLunch();
        emp1.DoWork();
        NonPartialOuterNest nonPartial = new NonPartialOuterNest();
        NonPartialOuterNest.PartialNested partialNested =
            new NonPartialOuterNest.PartialNested("this is a prop for a nested class that is also partial");
        // Now we want to call the method Method1
        partialNested.Method1();
        partialNested.Method2();

        // Overriding methods
        bool isSealed = false;
        DerivedClass child = new DerivedClass(isSealed);
        child.Function1();

        BaseClass parent = new BaseClass(isSealed);
        parent.Function1();

        BaseClass actuallyDerived = new DerivedClass(isSealed);
        actuallyDerived.Function1();

        // Cannot instantiate a BaseClass because it is the parent of the Derived class
        // DerivedClass question = new BaseClass(isSealed);

        GrandchildClass grandchildClass = new GrandchildClass(isSealed);
        grandchildClass.Function1();

    }
}