namespace lab4;

// Even though an outer class is not partial, its inner classes can be
public class NonPartialOuterNest
{
    // This is also an example of Composition, since the memory is connected
    public partial class PartialNested
    {
        public string NestedProp;
        public void Method1()
        {
            Console.WriteLine("Executing Method 1");
        }

        public PartialNested(string nestedProp)
        {
            NestedProp = nestedProp;
        }
    }

    public partial class PartialNested
    {
        public void Method2()
        {
            Console.WriteLine("Executing Method 2");
        }
    }
}

