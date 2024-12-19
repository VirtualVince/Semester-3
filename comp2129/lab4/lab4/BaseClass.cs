// Practice: Overriding Methods, Sealed methods
namespace lab4;

public class BaseClass
{
    protected bool IsSealed;
    // Make it possible to override this function
    public virtual void Function1()
    {
        Console.WriteLine("Running Function 1");
    }

    public virtual string Function2()
    {
        Console.WriteLine("Running Function 2");
        return "Function2";
    }

    public BaseClass(bool isSealed)
    {
        IsSealed = isSealed;
    }
}

public class DerivedClass : BaseClass
{
    public override void Function1()
    {
        Console.WriteLine("Running Function 1 - From the derived class");
    }

    public sealed override string Function2()
    {
        Console.WriteLine("Running Function 2 - From the derived class");
        return "Function2";
    }

    public DerivedClass(bool isSealed) : base(isSealed)
    {
    }
}

public class GrandchildClass : DerivedClass
{
    public GrandchildClass(bool isSealed): base(isSealed)
    {
    }
    // Function can be overridden
    public override void Function1()
    {
        Console.WriteLine("Running Function 1 - From the grandchild class");
    }

    // Cannot override a sealed method
    /*
    public override void Function2()
    {
    }
    */
}



 